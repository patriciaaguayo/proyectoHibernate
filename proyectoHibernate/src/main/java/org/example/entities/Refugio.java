package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "refugio")
public class Refugio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Nombre_Refugio", nullable = false)
    private String nombreRefugio;

    @ManyToOne
    @JoinColumn(name = "Dni", nullable = false)
    private Familia familia;

    // CONSTRUCTORES

    public Refugio() {}

    public Refugio(Familia familia) {
        this.familia = familia;
        this.nombreRefugio = "Prado Verde";
    }

    public Refugio(Familia familia, String nombreRefugio) {
        this.familia = familia;
        this.nombreRefugio = nombreRefugio;
    }

    // GETTERS

    public Familia getFamilia() { return familia; }

    public Integer getId() { return id; }

    public String getNombreRefugio() { return nombreRefugio; }

    // SETTERS

    public void setFamilia(Familia familia) { this.familia = familia; }

    public void setNombreRefugio(String nombreRefugio) { this.nombreRefugio = nombreRefugio; }

    // TO STRING

    @Override
    public String toString() {
        return "Refugio{" +
                "id=" + id +
                ", familia=" + familia +
                ", nombreRefugio='" + nombreRefugio + '\'' +
                '}';
    }
}
