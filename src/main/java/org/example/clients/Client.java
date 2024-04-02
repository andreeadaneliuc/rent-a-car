package org.example.clients;

import jakarta.persistence.*;

import java.util.HashSet;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer cnp;
    @OneToMany(mappedBy = "client")
    private Set<Rents> rentsSet = new HashSet<>();

    public Client() {
    }

    public Client(String name, Integer cnp, Set<Rents> rentsSet) {
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

    public Set<Rents> getRentsSet() {
        return rentsSet;
    }

    public void setRentsSet(Set<Rents> rentsSet) {
        this.rentsSet = rentsSet;
    }
}
