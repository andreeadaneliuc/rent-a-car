package org.example.clients;

import jakarta.persistence.*;
import org.example.rents.Rent;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "cnp", unique = true)
    private String cnp;
    @OneToMany(mappedBy = "client")
    private Set<Rent> rentsSet = new HashSet<>();

    public Client() {
    }

    public Client(String name, String cnp) {
        this.name = name;
        this.cnp = cnp;
    }

    public Client(String name, String cnp, Set<Rent> rentsSet) {
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

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Set<Rent> getRentsSet() {
        return rentsSet;
    }

    public void setRentsSet(Set<Rent> rentsSet) {
        this.rentsSet = rentsSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(cnp, client.cnp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnp);
    }
}
