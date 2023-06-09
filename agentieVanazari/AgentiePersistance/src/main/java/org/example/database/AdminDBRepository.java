package org.example.database;

import antlr.Utils;
import org.example.Admin;
import org.example.Agent;
import org.example.JdbcUtils;
import org.example.UtilsHibernate;
import org.example.interfaces.AdminRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Properties;

public class AdminDBRepository implements AdminRepository {

    public AdminDBRepository() {
    }

    @Override
    public Admin findOne(Long aLong) {
        try (Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Admin admin = session.createQuery("from Admin where id = :id", Admin.class)
                        .setParameter("id", aLong)
                        .setMaxResults(1)
                        .uniqueResult();
                tx.commit();
                return admin;
            }
            catch (RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        UtilsHibernate.getInstance().close();
        return null;
    }

    @Override
    public Iterable<Admin> findAll() {
        try (Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Admin> admins = session.createQuery("from Admin", Admin.class)
                        .list();
                tx.commit();
                return admins;
            }
            catch (RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        UtilsHibernate.getInstance().close();
        return null;
    }

    @Override
    public Admin save(Admin entity) {
        try (Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(entity);
                tx.commit();
                return entity;
            }
            catch (RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        UtilsHibernate.getInstance().close();
        return null;
    }

    @Override
    public Admin delete(Long aLong) {
        try (Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Admin admin = session.createQuery("from Admin where id = :id", Admin.class)
                        .setParameter("id", aLong)
                        .setMaxResults(1)
                        .uniqueResult();
                session.delete(admin);
                tx.commit();
                return admin;
            }
            catch (RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        UtilsHibernate.getInstance().close();
        return null;
    }

    @Override
    public Admin update(Admin entity) {
        try (Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(entity);
                tx.commit();
                return entity;
            }
            catch (RuntimeException ex) {
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
                Admin admin = session.createQuery("from Admin where id = :id", Admin.class)
                        .setParameter("id", aLong)
                        .setMaxResults(1)
                        .uniqueResult();
                tx.commit();
                return admin != null;
            }
            catch ( RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        UtilsHibernate.getInstance().close();
        return false;
    }

    @Override
    public Admin findByUsername(String username) {
        try (Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Admin admin = session.createQuery("from Admin where username = :username", Admin.class)
                        .setParameter("username", username)
                        .setMaxResults(1)
                        .uniqueResult();
                tx.commit();
                return admin;
            }
            catch ( RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        UtilsHibernate.getInstance().close();
        return null;
    }
}
