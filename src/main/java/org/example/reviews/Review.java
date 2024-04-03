package org.example.reviews;

import jakarta.persistence.*;
import org.example.rents.Rent;

import java.util.Objects;

@Entity
@Table(name = "reviews")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review)) return false;
        return Objects.equals(nrComanda, review.nrComanda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrComanda);
    }
}
