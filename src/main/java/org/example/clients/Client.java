package org.example.clients;

import jakarta.persistence.*;
import org.example.rents.Rent;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "cnp", unique = true)
    private String cnp;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
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

    public static boolean isCnpValid(String cnp) {
        String emailRegex = "^[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(cnp);
        return matcher.matches();
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
        if (!(o instanceof Client client)) return false;
        return Objects.equals(cnp, client.cnp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnp);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", cnp='").append(cnp).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
