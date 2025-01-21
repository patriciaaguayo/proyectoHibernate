package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "familias")
public class Familia implements Serializable {

    @Id
    @Column(name = "Dni")
    private String dni;

    @Column(name = "Edad", nullable = false)
    private Integer edadPersona;

    @Column(name = "Nombre", nullable = false)
    private String nombrePersona;

    @Column(name = "Ciudad")
    private String ciudad;

    // CONSTRUCTORES

    public Familia() {
    }

    public Familia(String dni, Integer edad, String nombre, String ciudad) {
        this.dni = dni;
        this.edadPersona = edad;
        this.nombrePersona = nombre;
        this.ciudad = ciudad;
    }

    // GETTERS

    public String getDni() { return dni; }

    public Integer getEdad() { return edadPersona; }

    public String getNombre(){ return nombrePersona; }

    public String getCiudad() { return ciudad; }

    // SETTERS

    public void setDni(String dni) { this.dni = dni; }

    public void setEdad(Integer edad) { this.edadPersona = edad; }

    public void setNombre(String nombre) { this.nombrePersona = nombre; }

    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    // TO STRING

    @Override
    public String toString() {
        return "Familia{" +
                "dni='" + dni + '\'' +
                ", edad=" + edadPersona +
                ", nombre='" + nombrePersona + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }

}
