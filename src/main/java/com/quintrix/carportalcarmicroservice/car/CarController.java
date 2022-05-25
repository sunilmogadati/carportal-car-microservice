package com.quintrix.carportalcarmicroservice.car;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



// TODO autowire cars service interface and call interface with return
@RestController
@RequestMapping("/car")
public class CarController {

  @Autowired
  CarService carService;

  @RequestMapping(value = "", method = RequestMethod.POST)
  public CarEntity addCar(@RequestBody CarEntity car) {

    // return created car
    return carService.addCarEntity(car);
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public CarsList searchCars(@RequestParam(required = false, name = "year") Optional<Integer> year,
      @RequestParam(required = false, name = "make") Optional<String> make,
      @RequestParam(required = false, name = "model") Optional<String> model) {

    // return car search result
    return carService.fetchCarMinimalByParam(make, model, year);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public CarEntity getDetailedCar(@PathVariable("id") String id) {

    // return detailed car by uuid
    return carService.fetchcarDetailedById(id);
  }


  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public CarEntity updateCar(@RequestBody CarEntity car, @PathVariable("id") String id) {

    // return updated object
    return carService.updateCarEntity(car, id);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void DeleteCar(@PathVariable("id") String id) {

    carService.deleteCarEntity(id);
    // return status / null
  }

}
