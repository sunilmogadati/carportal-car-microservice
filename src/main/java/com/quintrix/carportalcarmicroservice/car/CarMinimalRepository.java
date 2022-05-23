package com.quintrix.carportalcarmicroservice.car;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CarMinimalRepository extends JpaRepository<CarMinimal, String> {

  @Query("select c from CarMinimal c where c.uuid = ?1")
  List<CarMinimal> fetchCarMinimalByUUID(String uuid);

  @Modifying
  @Query("delete c from CarMinimal c where c.uuid = ?1")
  void deleteCarMinimalByUUID(String uuid);
}
