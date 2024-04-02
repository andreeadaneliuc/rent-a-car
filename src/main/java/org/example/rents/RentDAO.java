package org.example.rents;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.clients.Client;

public class RentDAO {

    private EntityManagerFactory entityManagerFactory;

    public RentDAO(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("yourPersistenceUnitName");
    }

    public void createRent(Rent rent){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(rent);
            entityManager.getTransaction().commit();

        } finally {
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
