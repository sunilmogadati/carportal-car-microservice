package com.quintrix.carportalcarmicroservice.car;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

// TODO autowire cars service interface and call interface with return
@OpenAPIDefinition(info = @Info(title = "The Car API for Place4Cars"))
@Tag(name = "Car API", description = "Create, retrieve, update, and delete cars.")

@RestController
@RequestMapping("/car")
public class CarController {

  @Autowired
  CarService carService;

  @Operation(summary = "Add a car.")
  @ApiResponse(responseCode = "200",
      content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
          schema = @Schema(implementation = CarEntity.class)

      ))

  @RequestMapping(value = "", method = RequestMethod.POST)
  public CarEntity addCar(@RequestBody CarEntity car) {

    // return created car
    return carService.addCarEntity(car);
  }


  @Operation(
      summary = "Retrieve basic information on a list of cars. Can filter by Make/Model/Year")
  @ApiResponse(responseCode = "200",
      content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
          array = @ArraySchema(schema = @Schema(implementation = CarMinimal.class))))

  @RequestMapping(value = "", method = RequestMethod.GET)
  public CarsList searchCars(@RequestParam(required = false, name = "year") Optional<Integer> year,
      @RequestParam(required = false, name = "make") Optional<String> make,
      @RequestParam(required = false, name = "model") Optional<String> model) {

    // return car search result
    return carService.fetchCarMinimalByParam(make, model, year);
  }


  @Operation(summary = "Grab detailed information for a specific car.")
  @ApiResponses({
      @ApiResponse(responseCode = "200",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = CarEntity.class))),
      @ApiResponse(responseCode = "404", content = @Content())})

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public CarEntity getDetailedCar(@PathVariable("id") String id) {

    // return detailed car by uuid
    return carService.fetchcarDetailedById(id);
  }


  @Operation(summary = "Update information for a specific car.")
  @ApiResponses({
      @ApiResponse(responseCode = "200",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = CarEntity.class))),
      @ApiResponse(responseCode = "404", content = @Content())})

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public CarEntity updateCar(@RequestBody CarEntity car, @PathVariable("id") String id) {

    // return updated object
    return carService.updateCarEntity(car, id);
  }


  @Operation(summary = "Delete a specific car.")
  @ApiResponses({
      @ApiResponse(responseCode = "200",
          content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
              schema = @Schema(implementation = CarEntity.class))),
      @ApiResponse(responseCode = "404", content = @Content())})

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void DeleteCar(@PathVariable("id") String id) {

    carService.deleteCarEntity(id);
    // return status / null
  }

}
