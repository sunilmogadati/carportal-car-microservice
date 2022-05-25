package com.quintrix.carportalcarmicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.quintrix.carportalcarmicroservice.soap.calculator.Calculator;
import com.quintrix.carportalcarmicroservice.soap.calculator.CalculatorSoap;

@SpringBootTest
public class SoapCalculatorTest {

  @Test
  void contextLoads() {}

  static CalculatorSoap calculatorSoapProxy = null;

  @BeforeAll
  public static void setup() {
    Calculator calcService = new Calculator();

    calculatorSoapProxy = calcService.getCalculatorSoap();
  }

  @Test
  public void givenCalcService_whenTwoNumbersAdded_thenReslutIsSum() {
    assertEquals(9, calculatorSoapProxy.add(4, 5));
  }



}
