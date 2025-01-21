package org.example.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "animales")
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String especie;

    private  String descripcion;

    private String estado;

    private enum ESPECIE {
        PERRO, GATO, PAJARO, CERDO_VIETNAMITA, SERPIENTE, CAMALEON, ARANA
    }

    private enum ESTADO{
        RECIEN_ABANDONADO, TIEMPO_EN_REFUGIO, PROXIMAMENTE_ACOGIDA
    }

    public Animal() {
    }

    public Animal(String nombre, String especie, String descripcion, String estado) {
        this.nombre = nombre;
        this.especie = especie;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // GETTERS

    public Integer getId() {return id;}

    public String getNombre() {return nombre;}

    public String getEspecie() {return especie;}

    public String getDescripcion() {return descripcion;}

    public String getEstado() {return estado;}

    // SETTERS

    public void setId(Integer id) {this.id = id;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setEspecie(String especie) {this.especie = especie;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public void setEstado(String estado) {this.estado = estado;}

    // TO STRING

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
