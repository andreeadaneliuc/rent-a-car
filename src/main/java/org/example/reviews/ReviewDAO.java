package org.example.reviews;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.rents.Rent;

import java.util.List;
import java.util.Set;


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
        Review toBeSent;
        try {
            toBeSent = entityManager.find(Review.class, nrComanda);
        } finally {
            entityManager.close();
        }
        return toBeSent;
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

    public List<Review> findAllReviewsByClient(Rent rent) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT r").append(" FROM Review r WHERE r.rent.nrComanda = :numar");
        String hql = stringBuilder.toString();
        List<Review> review = entityManager.createQuery(hql, Review.class)
                .setParameter("numar", rent.getNrComanda())
                .getResultList();
        entityManager.close();
        return review;
    }
    public Review findReviewByNrComanda(Rent nrComanda) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT r").append(" FROM Review r WHERE r.rent = :numar");
        String hql = stringBuilder.toString();
        List<Review> review = entityManager.createQuery(hql, Review.class)
                .setParameter("numar", nrComanda)
                .getResultList();
        entityManager.close();
        try {
            return review.get(0);
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }
}
