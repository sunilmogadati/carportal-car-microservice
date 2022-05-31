package com.quintrix.carportalcarmicroservice.agents;

import java.util.List;
import org.aspectj.weaver.loadtime.Agent;

public interface AgentsService {

  List<AgentsEntity> getAgentList();

  Agent getAgent(int id);


}
