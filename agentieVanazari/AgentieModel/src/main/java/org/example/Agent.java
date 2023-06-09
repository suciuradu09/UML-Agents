package org.example;

import javax.persistence.*;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "agents")
public class Agent extends Entity<Long>{

    private Long id;

    private String username;

    private String password;

    public Agent(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Agent(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Agent(){

    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return Objects.equals(id, agent.id) && Objects.equals(username, agent.username) && Objects.equals(password, agent.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
