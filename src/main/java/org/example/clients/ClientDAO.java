package org.example.clients;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.cars.Car;
import org.example.reviews.Review;
import org.example.utils.HibernateUtils;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class ClientDAO {

    private EntityManagerFactory entityManagerFactory = HibernateUtils.getSessionFactory();

    public ClientDAO(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("name");
    }

    public void createClient(Client client){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Client> clients = entityManager.createQuery("FROM Client", Client.class).getResultList();
        if(clients.contains(client))
        {
            System.out.println("This client already exists");
        }
        else {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(client);
                entityManager.getTransaction().commit();
            } finally {
                if (entityManager.isOpen()) entityManager.close();
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
    public boolean findClient(Client client) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return (entityManager.find(Client.class, client.getId())) != null;
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
    public List<Client> findAllClients() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT c").append(" FROM Client c");
        String hql = stringBuilder.toString();
        List<Client> client = entityManager.createQuery(hql, Client.class)
                .getResultList();
        entityManager.close();
        return client;
    }
    public Client findClientByCnp(String cnp) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT c").append(" FROM Client c WHERE c.cnp = :cnp");
        String hql = stringBuilder.toString();
        List<Client> clients = entityManager.createQuery(hql, Client.class)
                .setParameter("cnp", cnp)
                .getResultList();
        entityManager.close();

        try {
            return clients.get(0);
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }

}
