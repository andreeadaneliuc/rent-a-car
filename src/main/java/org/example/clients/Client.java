package org.example.clients;

import jakarta.persistence.*;
import org.example.rents.Rent;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer cnp;
    @OneToMany(mappedBy = "client")
    private Set<Rent> rentsSet = new HashSet<>();

    public Client() {
    }

    public Client(String name, Integer cnp, Set<Rent> rentsSet) {
        this.name = name;
        this.cnp = cnp;
        this.rentsSet = rentsSet;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCnp() {
        return cnp;
    }

    public void setCnp(Integer cnp) {
        this.cnp = cnp;
    }

    public Set<Rent> getRentsSet() {
        return rentsSet;
    }

    public void setRentsSet(Set<Rent> rentsSet) {
        this.rentsSet = rentsSet;
    }
}
