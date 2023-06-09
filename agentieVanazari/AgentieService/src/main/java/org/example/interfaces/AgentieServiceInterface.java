package org.example.interfaces;


import org.example.Agent;
import org.example.Order;
import org.example.Produs;

import java.util.List;

public interface AgentieServiceInterface {
    // AGENT
    Agent saveAgent(Agent entity);

    Agent deleteAgent(Long aLong);

    Agent deleteAgent(String username);

    Agent updateAgent(Agent entity);

    Agent updateAgent(String username, String password);

    Agent findOneAgent(Long aLong);

    Iterable<Agent> findAllAgents();

    List<Agent> showWorkingAgents();

    boolean loginAdmin(String username, String password) throws Exception;

    boolean loginAgent(String username, String password, ObserverInterface agentObserver) throws Exception;

    boolean logoutAgent(String username) throws Exception;

    // PRODUCT

    Produs findByDenumire(String denumire);

    Produs updateProduct(Produs entity);

    Iterable<Produs> getAllProducts();

    // ORDER

    void addOrder(Order order);
}
