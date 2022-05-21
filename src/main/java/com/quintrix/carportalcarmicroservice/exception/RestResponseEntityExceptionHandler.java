package com.quintrix.carportalcarmicroservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {IllegalStateException.class, IllegalArgumentException.class})
  protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    Error error = new Error();
    error.setCustomMessage("Encountered an Illegal State or Argument");
    error.setMessage(ex.getMessage());
    error.setHttpStatusCode(HttpStatus.CONFLICT.value());

    return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler({CarNotFoundException.class})
  public ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {

    if (ex instanceof CarNotFoundException) {
      Error error = new Error();
      error.setCustomMessage("Could not find a matching car.");
      error.setMessage(ex.getMessage());
      error.setHttpStatusCode(HttpStatus.NOT_FOUND.value());

      return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }
    // encountered error was not a CarNotFoundException - exit.
    return null;
  }
}
