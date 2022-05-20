package com.quintrix.carportalcarmicroservice.car;

import java.util.List;

public class CarsList {

  // wrapper class to be returned when querying for cars
  private List<CarMinimal> cars;

  public List<CarMinimal> getCars() {
    return cars;
  }

  public void setCars(List<CarMinimal> cars) {
    this.cars = cars;
  }


  public void addCar(CarMinimal car) {
    this.cars.add(car);
  }

}
