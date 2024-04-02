package org.example.cars;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.clients.Client;

public class CarDAO {

    private EntityManagerFactory entityManagerFactory;

    public CarDAO(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("yourPersistenceUnitName");
    }

    public void createCar(Car car){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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

    public Car getCarByVin(String vin) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Car.class, vin);
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
