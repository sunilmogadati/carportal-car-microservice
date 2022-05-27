package com.quintrix.carportalcarmicroservice.restservice;

import java.util.List;
import com.quintrix.carportalcarmicroservice.customer.CustomerModel;

public interface PreviousOwnerService {
  List<CustomerModel> getPreviousOwners();
}
