package com.quintrix.carportalcarmicroservice.car;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quintrix.carportalcarmicroservice.customer.CustomerModel;

@Table(name = "car_info")
@Entity
public class CarEntity {

  // uuid is similar to id, except it is a random String
  @Id
  @Column(name = "id")
  private String uuid;

  private String make;
  private String model;
  private Integer year;
  private Integer mileage;
  @Column(name = "kbb_est")
  private Integer KellyBlueBookEstemate;
  @Column(name = "user_desc")
  private String description;
  private Integer listPrice;
  @Transient
  private Integer monthlyPayment;


  // owners list from customers, not stored in database, marked transient
  @Transient
  private List<CustomerModel> ownerList;

  // agent list from external, not stored in database, marked transient
  @Transient
  private List<CustomerModel> AgentList;

  // url locations for s3 bucket, not stored in table, marked transient
  @Transient
  private List<CarImagesEntity> images;

  @JsonIgnore
  // getter ignored by jackson and used to convert carEntity onto CarMinimal
  public CarMinimal getCarMinimal() {
    return new CarMinimal(this.uuid, this.make, this.model, this.year, this.listPrice);
  }

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

  public List<CarImagesEntity> getImages() {
    return images;
  }

  public void setImages(List<CarImagesEntity> images) {
    this.images = images;
  }

  public List<CustomerModel> getAgentList() {
    return AgentList;
  }

  public void setAgentList(List<CustomerModel> agentList) {
    AgentList = agentList;
  }

  public void setOwnerList(List<CustomerModel> ownerList) {
    this.ownerList = ownerList;
  }

  public List<CustomerModel> getOwnerList() {
    return this.ownerList;
  }

  public Integer getMonthlyPayment() {
    return monthlyPayment;
  }

  public void setMonthlyPayment(Integer monthlyPayment) {
    this.monthlyPayment = monthlyPayment;
  }


}
