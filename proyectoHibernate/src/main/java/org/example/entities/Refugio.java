package org.example.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "refugio")
public class Refugio implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "Animal_Id", nullable = false)
    private Animal animal;

    @Column(name = "Nombre_Refugio", nullable = false)
    private String nombreRefugio = "Prado Verde";

    @ManyToOne
    @JoinColumn(name = "Dni", nullable = false)
    private Familia familia;

    // CONSTRUCTORES

    public Refugio() {}

    public Refugio(Familia familia, Animal animal) {
        this.familia = familia;
        this.animal = animal;
        this.nombreRefugio = "Prado Verde";
    }

    public Refugio(Familia familia, Animal animal, String nombreRefugio) {
        this.familia = familia;
        this.animal = animal;
        this.nombreRefugio = nombreRefugio;
    }

    // GETTERS

    public Familia getFamilia() { return familia; }

    public Animal getAnimal() { return animal; }

    public String getNombreRefugio() { return nombreRefugio; }

    // SETTERS

    public void setFamilia(Familia familia) { this.familia = familia; }

    public void setAnimal(Animal animal) { this.animal = animal; }

    public void setNombreRefugio(String nombreRefugio) { this.nombreRefugio = nombreRefugio; }

    // TO STRING

    @Override
    public String toString() {
        return "Refugio{" +
                "familia=" + familia +
                ", animal=" + animal +
                ", nombreRefugio='" + nombreRefugio + '\'' +
                '}';
    }
}
