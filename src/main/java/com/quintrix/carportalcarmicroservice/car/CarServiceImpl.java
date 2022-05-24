package com.quintrix.carportalcarmicroservice.car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

  @Autowired
  CarRepository carRepository;

  @Override
  public CarEntity fetchcarDetailedById(String uuid) {
    return carRepository.fetchCarEntityByID(uuid);
  }

  @Override
  public CarsList fetchAllCarMinimal() {
    CarsList carsList = new CarsList();
    List<CarMinimal> carList = carRepository.fetchAllCarMinimal();
    carsList.setCars(carList);
    return carsList;
  }

  @Override
  public CarsList fetchCarMinimalByParam(Optional<String> make, Optional<String> model,
      Optional<Integer> year) {
    CarsList carsList = new CarsList();
    CarMinimal car = new CarMinimal();
    ArrayList<CarMinimal> cars = new ArrayList<CarMinimal>();

    if (make.isPresent()) {
      car.setMake(make.get());
      cars.addAll(carRepository.fetchAllCarMinimal().stream()
          .filter(c -> c.getMake().contains(make.get())).collect(Collectors.toList()));
    }
    if (model.isPresent()) {
      car.setModel(model.get());
      cars.addAll(carRepository.fetchAllCarMinimal().stream()
          .filter(c -> c.getModel().contains(model.get())).collect(Collectors.toList()));
    }
    if (year.isPresent()) {
      car.setYear(year.get());
      cars.addAll(carRepository.fetchAllCarMinimal().stream()
          .filter(c -> c.getYear().equals(year.get())).collect(Collectors.toList()));
    }

    cars.stream().distinct().forEach(c -> carsList.addCar(c));

    return carsList;
  }

  @Override
  public CarEntity addCarEntity(CarEntity carEntity) {
    return carRepository.save(carEntity);
  }

  @Override
  public CarEntity updateCarEntity(CarEntity carEntity, String id) {
    carRepository.updateCarEntity(carEntity.getMake(), carEntity.getModel(), carEntity.getYear(),
        carEntity.getMileage(), carEntity.getMilesPerGallon(), carEntity.getKellyBlueBookEstemate(),
        carEntity.getDescription(), carEntity.getListPrice(), carEntity.getCondition(),
        carEntity.getOwner(), carEntity.getImages(), carEntity.getUuid());
    return carRepository.fetchCarEntityByID(id);
  }

  @Override
  public void deleteCarEntity(String id) {
    carRepository.deleteCarEntityByUUID(id);
  }

}
