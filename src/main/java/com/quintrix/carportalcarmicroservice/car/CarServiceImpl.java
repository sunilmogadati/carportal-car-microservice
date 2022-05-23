package com.quintrix.carportalcarmicroservice.car;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
  public CarsList fetchCarMinimalByParam(Optional<String> make, Optional<String> model,
      Optional<Integer> year) {
    CarsList carsList = new CarsList();
    CarMinimal car = new CarMinimal();

    if (make.isPresent()) {
      car.setMake(make.get());
    }
    if (model.isPresent()) {
      car.setModel(model.get());
    }
    if (year.isPresent()) {
      car.setYear(year.get());
    }
    List<CarMinimal> carList = carMinimalRepository.findAll(Example.of(car));
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
