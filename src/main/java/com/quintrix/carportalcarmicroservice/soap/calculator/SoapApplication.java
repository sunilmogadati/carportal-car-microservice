package com.quintrix.carportalcarmicroservice.soap.calculator;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoapApplication implements CommandLineRunner {



  public static void main(String[] args) {
    SpringApplication.run(SoapApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    Calculator calcService = new Calculator();
    CalculatorSoap calculatorSoapProxy = calcService.getCalculatorSoap();
    System.out.println(calculatorSoapProxy.add(7, 5));

  }

}
