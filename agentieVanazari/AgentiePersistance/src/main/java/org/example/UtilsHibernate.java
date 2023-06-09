package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class UtilsHibernate {

    private static SessionFactory sessionFactory;

    public static SessionFactory getInstance() {
        if (sessionFactory == null) {
            synchronized (UtilsHibernate.class) {
                if (sessionFactory == null) {
                    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                            .configure()
                            .build();
                    sessionFactory = new MetadataSources( registry).buildMetadata().buildSessionFactory();
                }
            }
        }
        return sessionFactory;
    }


    static void initialize(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try{
            sessionFactory = new MetadataSources( registry).buildMetadata().buildSessionFactory();
        }
        catch(Exception e){
            System.err.println("Exception " + e);
            StandardServiceRegistryBuilder.destroy( registry);
        }
    }

    static void close(){
        if(sessionFactory != null){
            sessionFactory.close();
        }
    }
}
