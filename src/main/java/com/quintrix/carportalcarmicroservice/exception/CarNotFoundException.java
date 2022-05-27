package com.quintrix.carportalcarmicroservice.exception;

public class CarNotFoundException extends RuntimeException {

  String displayMessage;

  String detailedMessage;

  public CarNotFoundException(String displayMessage, String detailedMessage) {
    super();
    this.displayMessage = displayMessage;
    this.detailedMessage = detailedMessage;
  }

}
