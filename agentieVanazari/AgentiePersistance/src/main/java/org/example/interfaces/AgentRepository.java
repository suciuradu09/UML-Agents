package org.example.interfaces;

import org.example.Agent;
import org.example.Repository;

import java.util.List;

public interface AgentRepository extends Repository<Long, Agent> {
    Agent findByUsername(String username);
    Agent deleteByUsername(String username);
}
