package com.quintrix.carportalcarmicroservice.car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.quintrix.carportalcarmicroservice.exception.CarNotFoundException;
import com.quintrix.carportalcarmicroservice.filestorage.FileService;
import com.quintrix.carportalcarmicroservice.soap.calculator.Calculator;
import com.quintrix.carportalcarmicroservice.soap.calculator.CalculatorSoap;


@Service
public class CarServiceImpl implements CarService {

  @Autowired
  CarEntityRepository carEntityRepository;

  @Autowired
  CarImagesRepository carImagesRepository;


  @Autowired
  FileService fileservice;

  @Value("${application.finance-length}")
  private int financeLength;


  private static final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);

  @Override
  public CarEntity fetchcarDetailedById(String uuid) {
    log.info("get car details called with id of " + uuid);
    Optional<CarEntity> carDetailedOptional = carEntityRepository.fetchCarEntityByUUID(uuid);
    if (!carDetailedOptional.isPresent()) {
      // throw error if nothing is found
      throw new CarNotFoundException("car not found", uuid + " not found");
    }
    CarEntity carDetailed = carDetailedOptional.get();
    carDetailed.setImages(carImagesRepository.getAllPicturesForCar(uuid));

    // TODO get precious owners with RestTemplate call

    // TODO SOAP call to get monthly payment
    Calculator calcService = new Calculator();
    CalculatorSoap calculatorSoapProxy = calcService.getCalculatorSoap();
    carDetailed
        .setMonthlyPayment((calculatorSoapProxy.divide(carDetailed.getListPrice(), financeLength)));

    // TODO add agents list via rest template

    log.info("get car details completed with id of " + uuid);
    return carDetailed;
  }

  @Override
  // fetches by parameter, will return empty list if nothing is found
  public CarsList fetchCarMinimalByParam(Optional<String> make, Optional<String> model,
      Optional<Integer> year) {

    log.info("paramaters searched called");

    CarsList carsList = new CarsList();
    CarEntity car = new CarEntity();

    if (make.isPresent()) {
      car.setMake(make.get());
      log.info("paramater make found: " + make.get());
    }

    if (model.isPresent()) {
      car.setModel(model.get());
      log.info("paramater model found: " + model.get());
    }

    if (year.isPresent()) {
      car.setYear(year.get());
      log.info("paramater year found: " + year.get());
    }

    // find by example
    List<CarEntity> carEntityList = carEntityRepository.findAll(Example.of(car));

    // turn into carMinimal, and attach first image
    // use parallel stream for multiple database calls
    carsList.setCars(carEntityList.parallelStream().map(c -> c.getCarMinimal())
        .map(c -> c.setImageUrlAndReturnSelf(carImagesRepository.findFirstImageById(c.getUuid())))
        .collect(Collectors.toList()));

    log.info("paramater search suceeded");
    return carsList;
  }

  @Override
  // adds car to database
  public CarEntity addCarEntity(CarEntity carEntity) {

    carEntity.setUuid(UUID.randomUUID().toString());
    log.info("saving car with id " + carEntity.getUuid());
    return carEntityRepository.save(carEntity);
  }

  @Override
  // overwrites car or creates
  // can be used to assign custom id. *feature*
  public CarEntity updateCarEntity(CarEntity carEntity, String id) {

    carEntity.setUuid(id);
    log.info("saving / updating car with id " + carEntity.getUuid());
    return carEntityRepository.save(carEntity);
  }

  @Override
  // deleted car
  public void deleteCarEntity(String id) {

    try {
      carEntityRepository.deleteById(id);
      fileservice.deletePhotos(id);

    } catch (EmptyResultDataAccessException ex) {
      log.info(id + " not found");
      throw new CarNotFoundException("car not found", id + " not found");
    }
    // TODO call function to delete from S3

  }

}
