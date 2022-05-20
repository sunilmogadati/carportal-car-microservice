package com.quintrix.carportalcarmicroservice.car;

import java.util.HashMap;
import org.hibernate.annotations.Entity;
import com.quintrix.carportalcarmicroservice.customer.CustomerModel;

@SuppressWarnings("deprecation")
@Entity
public class CarEntity {

  // uuid is similar to id, except it is a random String
  private String uuid;

  private String make;
  private String model;
  private Integer year;
  private Integer mileage;
  private Integer milesPerGallon;
  private Integer KellyBlueBookEstemate;
  private String description;
  private Integer listPrice;

  // needed for kbb estimate
  private String condition;

  // curent owner from customers
  private CustomerModel owner;

  // url locations for s3 bucket / key is image order / value is url for image
  private HashMap<Integer, String> images;

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
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

  public Integer getMileage() {
    return mileage;
  }

  public void setMileage(Integer mileage) {
    this.mileage = mileage;
  }

  public Integer getMilesPerGallon() {
    return milesPerGallon;
  }

  public void setMilesPerGallon(Integer milesPerGallon) {
    this.milesPerGallon = milesPerGallon;
  }

  public Integer getKellyBlueBookEstemate() {
    return KellyBlueBookEstemate;
  }

  public void setKellyBlueBookEstemate(Integer kellyBlueBookEstemate) {
    KellyBlueBookEstemate = kellyBlueBookEstemate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getListPrice() {
    return listPrice;
  }

  public void setListPrice(Integer listPrice) {
    this.listPrice = listPrice;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public CustomerModel getOwner() {
    return owner;
  }

  public void setOwner(CustomerModel owner) {
    this.owner = owner;
  }

  public HashMap<Integer, String> getImages() {
    return images;
  }

  public void setImages(HashMap<Integer, String> images) {
    this.images = images;
  }



}
