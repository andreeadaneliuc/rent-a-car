package org.example.cars;

import jakarta.persistence.*;
import org.example.rents.Rent;

import java.util.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    private String vin;
    private String marca;
    private String model;
    private String motorizare;
    private Integer anFabricatie;
    private String culoare;
    private Double pretZi;
    private boolean disponibila;

    @OneToMany(mappedBy = "car")
    private List<Rent> rent = new ArrayList<>();

    public Car() {
    }

    public Car(String vin) {
        this.vin = vin;
    }

    public Car(String vin, String marca, String model, String motorizare, Integer anFabricatie, String culoare, Double pretZi, boolean disponibila) {
        this.vin = vin;
        this.marca = marca;
        this.model = model;
        this.motorizare = motorizare;
        this.anFabricatie = anFabricatie;
        this.culoare = culoare;
        this.pretZi = pretZi;
        this.disponibila = disponibila;
    }

    public boolean getDisponibila() {
        return disponibila;
    }

    public void setDisponibila(boolean disponibila) {
        this.disponibila = disponibila;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return Objects.equals(vin, car.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("vin='").append(vin).append('\'');
        sb.append(", marca='").append(marca).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", motorizare='").append(motorizare).append('\'');
        sb.append(", anFabricatie=").append(anFabricatie);
        sb.append(", culoare='").append(culoare).append('\'');
        sb.append(", pretZi=").append(pretZi);
        sb.append('}');
        return sb.toString();
    }
}
