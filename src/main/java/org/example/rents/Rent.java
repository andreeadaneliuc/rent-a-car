package org.example.rents;

import jakarta.persistence.*;
import org.example.cars.Car;
import org.example.clients.Client;
import org.example.reviews.Review;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private String stare;
    private Double depozit;
    @OneToOne(mappedBy = "rent")
    private Review review;

    public Rent() {
    }

    public Rent(Car car, Client client,  LocalDate startDate, LocalDate endDate, String stare, Double depozit) {
        this.car = car;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = car.getPretZi() * ChronoUnit.DAYS.between(startDate,endDate);
        if(ChronoUnit.DAYS.between(LocalDate.now(), endDate) >= 0)
        {
            this.stare = "Activ";
            this.car.setDisponibila(false);
        }
        else {
            this.stare = "Inactiv";
            this.car.setDisponibila(true);
        }

        this.depozit = depozit;
        //this.review = new Review(this);
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice() {
        this.price = car.getPretZi() * ChronoUnit.DAYS.between(startDate,endDate);;
    }

    public String getStare() {
        return stare;
    }

    public void setStare() {
        if(ChronoUnit.DAYS.between(LocalDate.now(), endDate) >= 0)
        {
            this.stare = "Activ";
            this.car.setDisponibila(false);
        }
        else {
            this.stare = "Inactiv";
            this.car.setDisponibila(true);
        };
    }

    public Double getDepozit() {
        return depozit;
    }

    public void setDepozit() {
        this.depozit = price*0.05;
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
