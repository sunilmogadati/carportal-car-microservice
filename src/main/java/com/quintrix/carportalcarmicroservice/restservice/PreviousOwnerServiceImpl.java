package com.quintrix.carportalcarmicroservice.restservice;

import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.quintrix.carportalcarmicroservice.customer.CustomerModel;

@RestController
public class PreviousOwnerServiceImpl implements PreviousOwnerService {

  @Autowired
  RestTemplate restTemplate;

  @Value("${previousOwnerSerivce.getUrl}")
  String uriExact;

  @Value("${previousOwnerSerivce.name}")
  String username;

  @Value("${previousOwnerSerivce.password}")
  String password;

  // Basic authentication requires username and password be encoded when passed
  private HttpHeaders createHeaders() {
    // Encode user name and password
    String auth = username + ":" + password;
    String authHeader = "Basic " + new String(Base64.getEncoder().encodeToString(auth.getBytes()));

    // Set Authorization with encoded string
    HttpHeaders header = new HttpHeaders();
    header.set("Authorization", authHeader);

    return header;
  }

  @GetMapping(value = "/previousOwners")
  public List<CustomerModel> getPreviousOwners() {
    List<CustomerModel> previousOwnersList = null;

    // Header Authorization
    HttpEntity<CustomerModel> requestBody = new HttpEntity<>(createHeaders());

    // Grab entity
    ResponseEntity<List<CustomerModel>> agentsListResponseEntity = restTemplate.exchange(uriExact,
        HttpMethod.GET, requestBody, new ParameterizedTypeReference<List<CustomerModel>>() {});

    if (agentsListResponseEntity.getStatusCode() == HttpStatus.OK) {
      previousOwnersList = agentsListResponseEntity.getBody();
    }

    return previousOwnersList;
  }
}
