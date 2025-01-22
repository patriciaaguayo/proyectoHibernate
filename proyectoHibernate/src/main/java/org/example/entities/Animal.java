package org.example.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Map;

@Entity
@Table(name = "animales")
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Nombre_Animal", nullable = false)
    private String nombreAnimal;

    @Column(name="Edad_Animal")
    private Integer edadAnimal;

    @Column(name = "Especie", nullable = false)
    private String especie;

    @Column(name="Descripcion")
    private  String descripcion;

    @Column(name="Estado")
    private String estado;

    // Enumerados

    private enum ESPECIE {
        PERRO, GATO, PAJARO, CERDO_VIETNAMITA, SERPIENTE, CAMALEON, ARANA
    }

    private enum ESTADO{
        RECIEN_ABANDONADO, TIEMPO_EN_REFUGIO, PROXIMAMENTE_ACOGIDA
    }

    public Animal() {}

    public Animal(String nombre, Integer edad, String especie, String descripcion, String estado) {
        this.nombreAnimal = nombre;
        this.edadAnimal = edad;
        this.especie = especie;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // GETTERS

    public Integer getId() {return id;}

    public String getNombre() {return nombreAnimal;}

    public Integer getEdad() {return edadAnimal;}

    public String getEspecie() {return especie;}

    public String getDescripcion() {return descripcion;}

    public String getEstado() {return estado;}

    // SETTERS

    public void setId(Integer id) {this.id = id;}

    public void setNombre(String nombre) { this.nombreAnimal = capitalizarPrimeraLetra(nombre);}

    public void setEdad(Integer edad) {this.edadAnimal = edad;}

    public void setEspecie(String especie) {

        if (especie == null || especie.trim().isEmpty()) {
            throw new IllegalArgumentException("La especie no puede ser nula o vacía");
        }

        String aux = especie.toLowerCase();

        Map<String, ESPECIE> mapaEspecies = Map.of(
                "perro", ESPECIE.PERRO,
                "gato", ESPECIE.GATO,
                "pajaro", ESPECIE.PAJARO,
                "cerdo vietnamita", ESPECIE.CERDO_VIETNAMITA,
                "serpiente", ESPECIE.SERPIENTE,
                "camaleon", ESPECIE.CAMALEON,
                "camaleón", ESPECIE.CAMALEON,
                "arana", ESPECIE.ARANA,
                "araña", ESPECIE.ARANA
        );

        ESPECIE auxEnum = mapaEspecies.get(aux);

        if (auxEnum == null) {
            this.especie = "Perro";
        }

        this.especie = capitalizarPrimeraLetra(aux);
    }

    public void setDescripcion(String descripcion) {this.descripcion = capitalizarPrimeraLetra(descripcion);}

    public void setEstado(String estado) {

        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado no puede ser nulo o vacío");
        }

        String aux = estado.toLowerCase();

        Map<String, ESTADO> mapaEstados = Map.of(
                "recien abandonado", ESTADO.RECIEN_ABANDONADO,
                "recién abandonado", ESTADO.RECIEN_ABANDONADO,
                "tiempo en refugio", ESTADO.TIEMPO_EN_REFUGIO,
                "proximamente acogida", ESTADO.PROXIMAMENTE_ACOGIDA,
                "próximamente acogida", ESTADO.PROXIMAMENTE_ACOGIDA
        );

        ESTADO auxEnum = mapaEstados.get(aux);

        if (auxEnum == null) {
            this.estado = "Recien abandonado";
        }

        this.estado = capitalizarPrimeraLetra(aux);

    }

    // TO STRING

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nombre='" + nombreAnimal + '\'' +
                ", edad=" + edadAnimal +
                ", especie='" + especie + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    // MÉTODOS

    private static String capitalizarPrimeraLetra(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return palabra;
        }
        return palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();
    }
}
