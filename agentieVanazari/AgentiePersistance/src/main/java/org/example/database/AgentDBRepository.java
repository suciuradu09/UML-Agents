package org.example.database;

import antlr.Utils;
import org.example.Agent;
import org.example.JdbcUtils;
import org.example.UtilsHibernate;
import org.example.interfaces.AgentRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AgentDBRepository implements AgentRepository {


    public AgentDBRepository() {
    }

    @Override
    public Agent findOne(Long aLong) {
        try( Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                Agent agent = session.createQuery("from Agent where id = :id", Agent.class)
                        .setParameter("id", aLong)
                        .setMaxResults(1)
                        .uniqueResult();
                tx.commit();
                return agent;
            } catch ( RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
            }
        }
        UtilsHibernate.getInstance().close();
        return null;
    }

    @Override
    public Iterable<Agent> findAll() {
        try (Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Agent> agents = session.createQuery("from Agent", Agent.class)
                        .list();
                tx.commit();
                return agents;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
            }
        }
        UtilsHibernate.getInstance().close();
        return new ArrayList<>();
    }

    @Override
    public Agent save(Agent entity) {
        try ( Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(entity);
                tx.commit();
                return entity;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        UtilsHibernate.getInstance().close();
        return null;
    }

    @Override
    public Agent delete(Long aLong) {
        try (Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Agent agent = session.createQuery("from Agent where id = :id", Agent.class)
                        .setParameter("id", aLong)
                        .setMaxResults(1)
                        .uniqueResult();
                session.delete(agent);
                tx.commit();
                return agent;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        UtilsHibernate.getInstance().close();
        return null;
    }

    @Override
    public Agent update(Agent entity) {
        try ( Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(entity);
                tx.commit();
                return entity;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        UtilsHibernate.getInstance().close();
        return null;
    }

    @Override
    public boolean exists(Long aLong) {
        try (Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Agent agent = session.createQuery("from Agent where id = :id", Agent.class)
                        .setParameter("id", aLong)
                        .setMaxResults(1)
                        .uniqueResult();
                tx.commit();
                return agent != null;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
            }
        }
        UtilsHibernate.getInstance().close();
        return false;
    }

    @Override
    public Agent findByUsername(String username) {
        try (Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Agent agent = session.createQuery("from Agent where username = :username", Agent.class)
                        .setParameter("username", username)
                        .setMaxResults(1)
                        .uniqueResult();
                tx.commit();
                return agent;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
            }
        }
        UtilsHibernate.getInstance().close();
        return null;
    }

    @Override
    public Agent deleteByUsername(String username) {
        try (Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Agent agent = session.createQuery("from Agent where username = :username", Agent.class)
                        .setParameter("username", username)
                        .setMaxResults(1)
                        .uniqueResult();
                session.delete(agent);
                tx.commit();
                return agent;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        UtilsHibernate.getInstance().close();
        return null;
    }
}
