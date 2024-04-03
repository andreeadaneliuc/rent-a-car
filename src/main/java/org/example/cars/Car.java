package org.example.cars;

import jakarta.persistence.*;
import org.example.rents.Rent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car {
    @Id
    private String vin;
    private String marca;
    private String model;
    private String motorizare;
    private Integer anFabricatie;
    private String culoare;
    private Double pretZi;

    @OneToMany(mappedBy = "car")
    private List<Rent> rent = new ArrayList<>();

    public Car(String vin) {
        this.vin = vin;
    }

    public Car(String vin, String marca, String model, String motorizare, Integer anFabricatie, String culoare, Double pretZi) {
        this.vin = vin;
        this.marca = marca;
        this.model = model;
        this.motorizare = motorizare;
        this.anFabricatie = anFabricatie;
        this.culoare = culoare;
        this.pretZi = pretZi;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMotorizare() {
        return motorizare;
    }

    public void setMotorizare(String motorizare) {
        this.motorizare = motorizare;
    }

    public Integer getAnFabricatie() {
        return anFabricatie;
    }

    public void setAnFabricatie(Integer anFabricatie) {
        this.anFabricatie = anFabricatie;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public Double getPretZi() {
        return pretZi;
    }

    public void setPretZi(Double pretZi) {
        this.pretZi = pretZi;
    }
}
