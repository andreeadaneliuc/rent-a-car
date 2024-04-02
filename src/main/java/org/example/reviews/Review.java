package org.example.reviews;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.example.cars.Car;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @OneToOne
    private Long nrComanda;

    private String text;
    private Car car;
    private Integer nota;

    public Review(Long nrComanda, String text, Car car, Integer nota) {
        this.nrComanda = nrComanda;
        this.text = text;
        this.car = car;
        this.nota = nota;
    }

    public Long getNrComanda() {
        return nrComanda;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Car getCar() {
        return car;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
