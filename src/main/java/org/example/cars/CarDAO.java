package org.example.cars;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.clients.Client;
import org.example.utils.HibernateUtils;

import java.sql.SQLException;
import java.util.List;

public class CarDAO {

    private EntityManagerFactory entityManagerFactory = HibernateUtils.getSessionFactory();

    public CarDAO(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("name");
    }

    public void createCar(Car car){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Car> cars = entityManager.createQuery("FROM Car", Car.class).getResultList();
        if(cars.contains(car))
        {
            System.out.println("This car already exists");
        }
        else{
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(car);
                entityManager.getTransaction().commit();
            } finally {
                if (entityManager.isOpen()) {
                    entityManager.close();
                }
            }
        }
    }

    public Car getCarByVin(String vin) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Car.class, vin);
        } finally {
            entityManager.close();
        }
    }
    public boolean findCar(Car car) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return (entityManager.find(Car.class, car.getVin()))!=null;
        } finally {
            entityManager.close();
        }
    }

    public void updateCar(Car car){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(car);
            entityManager.getTransaction().commit();

        } finally {
            entityManager.close();
        }
    }

    public void deleteClient(Car car){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Car toRemove = entityManager.merge(car);
            entityManager.remove(toRemove);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
