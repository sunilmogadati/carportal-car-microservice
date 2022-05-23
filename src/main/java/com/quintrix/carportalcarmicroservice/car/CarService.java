package com.quintrix.carportalcarmicroservice.car;

import java.util.Optional;

public interface CarService {

  // Fetch the detailed car by its id
  CarEntity fetchcarDetailedById(String uuid);

  // Fetch all the cars in the table through the wrapper class CarsList
  CarsList fetchAllCarMinimal();

  // Fetch all the cars in the table that are of a specific make
  CarsList fetchCarMinimalByParam(Optional<String> make, Optional<String> model,
      Optional<Integer> year);

  // Add car to table
  CarEntity addCarEntity(CarEntity carEntity);

  // Update car in table
  CarEntity updateCarEntity(CarEntity carEntity, String id);

  // Delete car in CarEntity table and CarMinimal table
  void deleteCarEntity(String id);
}
