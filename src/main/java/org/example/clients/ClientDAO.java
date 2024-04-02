package org.example.clients;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ClientDAO {

    private EntityManagerFactory entityManagerFactory;

    public ClientDAO(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("yourPersistenceUnitName");
    }

    public void createClient(Client client){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();

        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public Client getClientById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Client.class, id);
        } finally {
            entityManager.close();
        }
    }

    public void updateClient(Client client){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(client);
            entityManager.getTransaction().commit();

        } finally {
            entityManager.close();
        }
    }

    public void deleteClient(Client client){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Client toRemove = entityManager.merge(client);
            entityManager.remove(toRemove);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
