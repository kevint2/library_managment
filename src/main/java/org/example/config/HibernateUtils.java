package org.example.config;

import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
    private  static SessionFactory sessionFactory;
    public static  SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            Configuration configuration = new Configuration();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            configuration.addAnnotatedClass(Author.class);
            configuration.addAnnotatedClass(Request.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Publisher.class);
            configuration.addAnnotatedClass(Book.class);
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
