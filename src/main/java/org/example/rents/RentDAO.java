package org.example.rents;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.cars.Car;
import org.example.utils.HibernateUtils;

import java.util.List;
//import org.example.clients.Client;

public class RentDAO {

    private EntityManagerFactory entityManagerFactory = HibernateUtils.getSessionFactory();

    public RentDAO(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("name");
    }

    public void createRent(Rent rent){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Rent> rents = entityManager.createQuery("FROM Rent", Rent.class).getResultList();
        if(rents.contains(rent))
        {
            System.out.println("This rent already exists");
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(rent);
            entityManager.getTransaction().commit();

        }
        catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
            finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }


    public Rent getRentByNrComanda(Long nrComanda) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Rent.class, nrComanda);
        } finally {
            entityManager.close();
        }
    }

    public void updateRent(Rent rent){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(rent);
            entityManager.getTransaction().commit();

        } finally {
            entityManager.close();
        }
    }

    public void deleteRent(Rent rent){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Rent toRemove = entityManager.merge(rent);
            entityManager.remove(toRemove);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
