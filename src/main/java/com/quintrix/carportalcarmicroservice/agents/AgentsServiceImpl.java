package com.quintrix.carportalcarmicroservice.agents;

import java.util.List;
import org.aspectj.weaver.loadtime.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AgentsServiceImpl implements AgentsService {



  @Autowired
  RestTemplate restTemplate;

  @Value("${agentService.getUrl}")
  String agentServiceGetUrl;

  @Override
  public List<AgentsEntity> getAgentList() {
    List<AgentsEntity> agentsList = null;

    ResponseEntity<List<AgentsEntity>> agentsListResponseEntity =
        restTemplate.exchange(agentServiceGetUrl, HttpMethod.GET, null,
            new ParameterizedTypeReference<List<AgentsEntity>>() {});

    if (agentsListResponseEntity.getStatusCode() == HttpStatus.OK) {
      agentsList = agentsListResponseEntity.getBody();
    }

    return agentsList;

  }

  @Override
  public Agent getAgent(int id) {

    // TEST CASE: id = 4
    ResponseEntity<Agent> getAgents =
        restTemplate.exchange(agentServiceGetUrl + "/" + id, HttpMethod.GET, null, Agent.class);

    return getAgents.getBody();


    // Work around: No longer needed

    // List<AgentsEntity> agentsList = getAgentList();
    // Optional<AgentsEntity> agent = agentsList.stream().filter(x ->
    // x.getId().equals(id)).findAny();

    // return agent.get();


  }
}


