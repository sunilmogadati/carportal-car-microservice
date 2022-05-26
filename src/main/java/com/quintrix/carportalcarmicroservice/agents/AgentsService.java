package com.quintrix.carportalcarmicroservice.agents;

import java.util.List;

public interface AgentsService {

  List<AgentsEntity> getAgentList();

  AgentsEntity getAgent(int id);


}
