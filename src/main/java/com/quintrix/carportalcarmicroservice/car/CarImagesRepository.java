package com.quintrix.carportalcarmicroservice.car;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarImagesRepository extends JpaRepository<CarImagesEntity, String> {

  // native query for performance, called very frequently
  @Query(value = "select aws_s3_url from car_photos where car_info_id = ?1 and display_order = 1",
      nativeQuery = true)
  public String findFirstImageById(String uuid);

  @Query("select i from CarImagesEntity i where i.carInfoId = ?1")
  public List<CarImagesEntity> getAllPicturesForCar(String uuid);

}
