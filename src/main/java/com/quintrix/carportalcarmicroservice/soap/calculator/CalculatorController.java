package com.quintrix.carportalcarmicroservice.soap.calculator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

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
