package org.example.reviews;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.cars.Car;

import java.util.List;
//import org.example.clients.Client;

public class ReviewDAO {

    private EntityManagerFactory entityManagerFactory;

    public ReviewDAO(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("name");
    }

    public void createReview(Review review){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(review);
            entityManager.getTransaction().commit();



        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public Review getReviewByNrComanda(Long nrComanda) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Review.class, nrComanda);
        } finally {
            entityManager.close();
        }
    }

    public void updateReview(Review review){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(review);
            entityManager.getTransaction().commit();

        } finally {
            entityManager.close();
        }
    }

    public void deleteReview(Review review){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Review toRemove = entityManager.merge(review);
            entityManager.remove(toRemove);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
    public List<Review> findAllReviews() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT r").append(" FROM Review r");
        String hql = stringBuilder.toString();
        List<Review> review = entityManager.createQuery(hql, Review.class)
                .getResultList();
        entityManager.close();
        return review;
    }
}
