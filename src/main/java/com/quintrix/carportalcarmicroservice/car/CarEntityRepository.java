package com.quintrix.carportalcarmicroservice.car;

import java.util.HashMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.quintrix.carportalcarmicroservice.customer.CustomerModel;

public interface CarEntityRepository extends JpaRepository<CarEntity, String> {

  @Query("select c from CarEntity c where c.uuid = ?1")
  CarEntity fetchCarEntityByUUID(String uuid);

  @Modifying
  @Query("update c from CarEntity c set c.make = ?1, c.model = ?2, c.year = ?3, c.mileage = ?4, c.milesPerGallon = ?5, c.KellyBlueBookEstimate = ?6, c.description = ?7, c.listPrice = ?8, c.condition = ?9, c.owner = ?10, c.images = ?11 where c.uuid = ?12")
  void updateCarEntity(String make, String model, Integer year, Integer mileage,
      Integer milesPerGallon, Integer KellyBlueBookEstimate, String description, Integer listPrice,
      String condition, CustomerModel owner, HashMap<Integer, String> images, String uuid);

  @Modifying
  @Query("delete c from CarEntity c where c.uuid = ?1")
  void deleteCarEntityByUUID(String uuid);

}
