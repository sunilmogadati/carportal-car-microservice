package com.quintrix.carportalcarmicroservice.car;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarEntityRepository extends JpaRepository<CarEntity, String> {

  @Query("select c from CarEntity c where c.uuid = ?1")
  Optional<CarEntity> fetchCarEntityByUUID(String uuid);

}
