package org.example.reviews;

import jakarta.persistence.*;
import org.example.rents.Rent;

import java.util.Objects;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "nr_comanda")
    private Rent rent;
    private String text;

    private Integer nota;

    public Review() {
    }

    public Review(Rent rent) {
        this.rent = rent;
    }

    public Review(Rent rent, String text, Integer nota) {
        this.rent = rent;
        this.text = text;
        this.nota = nota;
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

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review)) return false;
        return Objects.equals(rent, review.rent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rent);
    }
}
