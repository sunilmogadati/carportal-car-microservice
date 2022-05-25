package com.quintrix.carportalcarmicroservice.exception;

public class Error {

  private String message;

  private String customMessage;

  private int HttpStatusCode;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCustomMessage() {
    return customMessage;
  }

  public void setCustomMessage(String customMessage) {
    this.customMessage = customMessage;
  }

  public int getHttpStatusCode() {
    return HttpStatusCode;
  }

  public void setHttpStatusCode(int httpStatusCode) {
    HttpStatusCode = httpStatusCode;
  }

}
