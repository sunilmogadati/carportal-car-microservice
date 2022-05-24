package com.quintrix.carportalcarmicroservice.car;

import com.fasterxml.jackson.annotation.JsonIgnore;

// this is not an entity
public class CarMinimal {

  private String uuid;
  private String make;
  private String model;
  private Integer year;
  private Integer listPrice;
  // this is the image shown to the customer in non detail view
  // should be index 1 (first image) in CarEntity.images HashMap
  private String imageUrl;

  // constructor called by carEntity
  public CarMinimal(String uuid, String make, String model, Integer year, Integer listPrice) {
    this.uuid = uuid;
    this.year = year;
    this.make = make;
    this.model = model;
    this.listPrice = listPrice;
  }

  // default constructor
  public CarMinimal() {}

  // hack to set image via map without creating new object
  @JsonIgnore
  public CarMinimal setImageUrlAndReturnSelf(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public Integer getListPrice() {
    return listPrice;
  }

  public void setListPrice(Integer listPrice) {
    this.listPrice = listPrice;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getImageUrl() {
    return imageUrl;
  }


  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }


}
