package org.example.utils;

import org.example.cars.Car;
import org.example.clients.Client;
import org.example.rents.Rent;
import org.example.reviews.Review;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration()
                    .setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver")
                    .setProperty(Environment.URL, "jdbc:mysql://localhost:3306/rent_a_car")
                    .setProperty(Environment.USER, "root")
                    .setProperty(Environment.PASS, "123456789")
                    .setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect")
                    .setProperty(Environment.HBM2DDL_AUTO, "update")
                    .setProperty(Environment.SHOW_SQL, "true")
                    .setProperty(Environment.FORMAT_SQL, "true")
                    .setProperty(Environment.HIGHLIGHT_SQL, "true")
                    .addAnnotatedClass(Car.class)
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(Rent.class)
                    .addAnnotatedClass(Review.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}