package org.example;

import org.example.interfaces.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceApp implements AgentieServiceInterface {

    private final AgentRepository agenti;
    private final AdminRepository admini;
    private final ProdusRepository produse;
    private Map<Long, ObserverInterface> loggedAgents;
    private List<Order> orders;

    public ServiceApp(AgentRepository agenti, AdminRepository admini, ProdusRepository produse) {
        this.agenti = agenti;
        this.admini = admini;
        this.produse = produse;
        loggedAgents = new ConcurrentHashMap<>();
        orders = new ArrayList<>();
    }


    @Override
    public Agent saveAgent(Agent entity) {
        return agenti.save(entity);
    }

    @Override
    public Agent deleteAgent(Long aLong) {
        return agenti.delete(aLong);
    }

    @Override
    public Agent deleteAgent(String username) {
        return agenti.deleteByUsername(username);
    }

    @Override
    public Agent updateAgent(Agent entity) {
        return agenti.update(entity);
    }

    @Override
    public Agent updateAgent(String username, String password) {
        Agent agent = agenti.findByUsername(username);
        agent.setPassword(password);
        return agenti.update(agent);
    }

    @Override
    public Agent findOneAgent(Long aLong) {
        return agenti.findOne(aLong);
    }

    @Override
    public Iterable<Agent> findAllAgents() {
        return agenti.findAll();
    }

    @Override
    public List<Agent> showWorkingAgents() {
        Iterable<Agent> agents = agenti.findAll();
        // return the list of agents with the id in the loggedAgents map
        return StreamSupport.stream(agents.spliterator(), false)
                .filter(agent -> loggedAgents.containsKey(agent.getId()))
                .collect(Collectors.toList());
    }
    @Override
    public boolean loginAdmin(String username, String password) throws Exception {
        Admin admin = admini.findByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            System.out.println("Admin logged in!");
            return true;
        }
        else {
            throw new Exception("Authentication failed!");
        }
    }

    @Override
    public synchronized boolean loginAgent(String username, String password, ObserverInterface agentObserver) throws Exception {
        Agent agent = agenti.findByUsername(username);
        if (agent != null && agent.getPassword().equals(password)) {
            if (loggedAgents.containsKey(agent.getId())) {
                throw new Exception("Agent already logged in!");
            }
            loggedAgents.put(agent.getId(), agentObserver);
            System.out.println("Users logged in: " + loggedAgents.size());
            return true;
        }
        else {
            throw new Exception("Authentication failed!");
        }
    }

    @Override
    public boolean logoutAgent(String username) throws Exception {
        if (loggedAgents.containsKey(agenti.findByUsername(username).getId())) {
            loggedAgents.remove(agenti.findByUsername(username).getId());
            System.out.println("Users logged in: " + loggedAgents.size());
            return true;
        }
        else {
            throw new Exception("Agent not logged in!");
        }
    }



    @Override
    public Iterable<Produs> getAllProducts() {
        return produse.findAll();
    }

    @Override
    public Produs findByDenumire(String denumire) {
        return produse.findByDenumire(denumire);
    }

    @Override
    public synchronized Produs updateProduct(Produs entity) {
        produse.update(entity);
        notifyProductModified(entity);
        return entity;
    }

    public String getTotalCost(List<Produs> items) {
        // get items in a list
        // compute total cost * quantity
        Double totalCost = 0.0;
        for (Produs p : items) {
            totalCost += p.getPret() * p.getCantitate();
        }

        return "Total cost: " + totalCost.toString();
    }

    public synchronized void notifyProductModified(Produs produs) {
        for (ObserverInterface agentObserver : loggedAgents.values()) {
            try {
                agentObserver.produsRecieved(produs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
