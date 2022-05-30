package com.quintrix.carportalcarmicroservice.soap.calculator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Hidden;

@RestController
@Hidden
public class CalculatorController {

  // http://localhost:8080/add?first=x&second=x
  @RequestMapping(method = RequestMethod.GET, value = "/add")
  Integer getAdd(@RequestParam(name = "first", required = true) Integer first,
      @RequestParam(name = "second", required = true) Integer second) {

    Calculator calcService = new Calculator();
    CalculatorSoap calculatorSoapProxy = calcService.getCalculatorSoap();
    int sum = calculatorSoapProxy.add(first, second);
    System.out.println(sum);

    return sum;
  }

}
