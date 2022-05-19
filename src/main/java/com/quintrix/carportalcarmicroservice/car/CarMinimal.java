package com.quintrix.carportalcarmicroservice.car;

public class CarMinimal {

  private String make;
  private String model;
  private Integer year;
  // this is the image shown to the customer in non detail view
  // should be index 1 (first image) in CarEntity.images HashMap
  private String imageUrl;

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


}
