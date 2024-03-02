package org.example;

import org.example.config.HibernateUtils;
import org.example.daos.AuthorDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();



    }
}