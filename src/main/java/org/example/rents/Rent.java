package org.example.rents;

import jakarta.persistence.*;
import org.example.cars.Car;
import org.example.clients.Client;
import org.example.reviews.Review;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nrComanda;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    private Date startDate;
    private Date endDate;
    private Double price;
    private String stare;
    private Double depozit;
    @OneToOne(mappedBy = "rent")
    private Review review;

    public Rent() {
    }

    public Rent(Car car, Client client,  Date startDate, Date endDate, Double price, String stare, Double depozit) {
        this.car = car;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.stare = stare;
        this.depozit = depozit;
    }

    public Long getNrComanda() {
        return nrComanda;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }



    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStare() {
        return stare;
    }

    public void setStare(String stare) {
        this.stare = stare;
    }

    public Double getDepozit() {
        return depozit;
    }

    public void setDepozit(Double depozit) {
        this.depozit = depozit;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rent rent)) return false;
        return Objects.equals(nrComanda, rent.nrComanda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrComanda);
    }
}
