package com.quintrix.carportalcarmicroservice.car;

import java.util.HashMap;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.quintrix.carportalcarmicroservice.customer.CustomerModel;

public interface CarRepository extends JpaRepository<CarEntity, String> {

  @Query("select c from car_info c where c.id = ?1")
  CarEntity fetchCarEntityByID(String uuid);

  @Modifying
  @Query("update c from car_info c set c.make = ?1, c.model = ?2, c.year = ?3, c.mileage = ?4, c.miles_per_gallon = ?5, c.kbb_est = ?6, c.desc = ?7, c.list_price = ?8, where c.id = ?9; update b from car_photos b set b.car_info_id = ?9")
  void updateCarEntity(String make, String model, Integer year, Integer mileage,
      Integer milesPerGallon, Integer KellyBlueBookEstimate, String description, Integer listPrice,
      String condition, CustomerModel owner, HashMap<Integer, String> images, String id);

  @Modifying
  @Query("delete c from car_info c where c.uuid = ?1; delete b from car_photos b where b.car_info_id = ?1")
  void deleteCarEntityByUUID(String uuid);

  @Query("select c.id, c.make, c.model, c.year from car_info c")
  List<CarMinimal> fetchAllCarMinimal();

}
