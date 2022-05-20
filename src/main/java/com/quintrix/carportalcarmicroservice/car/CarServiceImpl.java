package com.quintrix.carportalcarmicroservice.car;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

  @Autowired
  CarEntityRepository carEntityRepository;

  @Autowired
  CarMinimalRepository carMinimalRepository;

  @Override
  public CarEntity fetchcarDetailedById(String uuid) {
    return carEntityRepository.fetchCarEntityByUUID(uuid);
  }

  @Override
  public CarsList fetchAllCarMinimal() {
    CarsList carsList = new CarsList();
    List<CarMinimal> carList = carMinimalRepository.findAll();
    carsList.setCars(carList);
    return carsList;
  }

  @Override
  public CarsList fetchCarMinimalByMake(String make) {
    CarsList carsList = new CarsList();
    List<CarMinimal> carList = carMinimalRepository.fetchCarMinimalByMake(make);
    carsList.setCars(carList);
    return carsList;
  }

  @Override
  public CarsList fetchCarMinimalByModel(String model) {
    CarsList carsList = new CarsList();
    List<CarMinimal> carList = carMinimalRepository.fetchCarMinimalByModel(model);
    carsList.setCars(carList);
    return carsList;
  }

  @Override
  public CarsList fetchCarMinimalByYear(Integer year) {
    CarsList carsList = new CarsList();
    List<CarMinimal> carList = carMinimalRepository.fetchCarMinimalByYear(year);
    carsList.setCars(carList);
    return carsList;
  }

  @Override
  public CarsList fetchCarMinimalByImageURL(String url) {
    CarsList carsList = new CarsList();
    List<CarMinimal> carList = carMinimalRepository.fetchCarMinimalByImageUrl(url);
    carsList.setCars(carList);
    return carsList;
  }

  @Override
  public CarEntity addCarEntity(CarEntity carEntity) {
    return carEntityRepository.save(carEntity);
  }

  @Override
  public CarEntity updateCarEntity(CarEntity carEntity, String id) {
    carEntityRepository.updateCarEntity(carEntity.getMake(), carEntity.getModel(),
        carEntity.getYear(), carEntity.getMileage(), carEntity.getMilesPerGallon(),
        carEntity.getKellyBlueBookEstemate(), carEntity.getDescription(), carEntity.getListPrice(),
        carEntity.getCondition(), carEntity.getOwner(), carEntity.getImages(), carEntity.getUuid());
    return carEntityRepository.fetchCarEntityByUUID(id);
  }

  @Override
  public void deleteCarEntity(String id) {
    // TODO Auto-generated method stub
    carEntityRepository.deleteCarEntityByUUID(id);
    carMinimalRepository.deleteCarMinimalByUUID(id);

  }

}
