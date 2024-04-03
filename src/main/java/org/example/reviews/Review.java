package org.example.reviews;

import jakarta.persistence.*;
import org.example.cars.Car;
import org.example.rents.Rent;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nrComanda;

    @OneToOne
    @JoinColumn(name = "rent_id")
    @MapsId
    private Rent rent;
    private String text;

    private Integer nota;


    public Review(Long nrComanda, String text, Integer nota) {
        this.nrComanda = nrComanda;
        this.text = text;

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



    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
