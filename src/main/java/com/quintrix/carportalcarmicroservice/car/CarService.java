package com.quintrix.carportalcarmicroservice.car;

public interface CarService {

  // Fetch the detailed car by its id
  CarEntity fetchcarDetailedById(String uuid);

  // Fetch all the cars in the table through the wrapper class CarsList
  CarsList fetchAllCarMinimal();

  // Fetch all the cars in the table that are of a specific make
  CarsList fetchCarMinimalByMake(String make);

  // Fetch all the cars in the table that are of a specific model
  CarsList fetchCarMinimalByModel(String model);

  // Fetch all the cars in the table that are of a specific year
  CarsList fetchCarMinimalByYear(Integer year);

  // Fetch all the cars in the table by image
  CarsList fetchCarMinimalByImageURL(String url);

  // Add car to table
  CarEntity addCarEntity(CarEntity carEntity);

  // Update car in table
  CarEntity updateCarEntity(CarEntity carEntity, String id);

  // Delete car in CarEntity table and CarMinimal table
  void deleteCarEntity(String id);
}
