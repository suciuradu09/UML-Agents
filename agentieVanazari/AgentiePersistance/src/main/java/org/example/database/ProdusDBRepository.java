package org.example.database;

import org.example.Produs;
import org.example.UtilsHibernate;
import org.example.interfaces.ProdusRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProdusDBRepository implements ProdusRepository {

    public ProdusDBRepository() {
    }

    @Override
    public Produs findOne(Long aLong) {
        return null;
    }

    @Override
    public Produs findByDenumire(String denumire) {
        try(Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Produs produs = session.createQuery("from Produs where denumire = :denumire", Produs.class)
                        .setParameter("denumire", denumire)
                        .setMaxResults(1)
                        .uniqueResult();
                tx.commit();
                return produs;
            }
            catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        UtilsHibernate.getInstance().close();
        return null;
    }

    @Override
    public Iterable<Produs> findAll() {
        try (Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Iterable<Produs> produse = session.createQuery("from Produs", Produs.class)
                        .list();
                tx.commit();
                return produse;
            }
            catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        UtilsHibernate.getInstance().close();
        return null;
    }

    @Override
    public Produs save(Produs entity) {
        return null;
    }

    @Override
    public Produs delete(Long i) {
        return null;
    }

    @Override
    public Produs update(Produs entity) {
        try (Session session = UtilsHibernate.getInstance().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(entity);
                tx.commit();
                return entity;
            }
            catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        UtilsHibernate.getInstance().close();
        return null;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }
}

