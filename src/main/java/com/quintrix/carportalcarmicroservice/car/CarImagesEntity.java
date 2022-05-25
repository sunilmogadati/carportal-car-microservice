package com.quintrix.carportalcarmicroservice.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car_photos")
public class CarImagesEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String carInfoId;
  @Column(name = "aws_s3_url")
  private String awsS3Url;
  private Integer displayOrder;


  public String getCarInfoId() {
    return carInfoId;
  }

  public void setCarInfoId(String carInfoId) {
    this.carInfoId = carInfoId;
  }

  public String getAwsS3Url() {
    return awsS3Url;
  }

  public void setAwsS3Url(String awsS3Url) {
    this.awsS3Url = awsS3Url;
  }

  public Integer getDisplayOrder() {
    return displayOrder;
  }

  public void setDisplayOrder(Integer displayOrder) {
    this.displayOrder = displayOrder;
  }


}
