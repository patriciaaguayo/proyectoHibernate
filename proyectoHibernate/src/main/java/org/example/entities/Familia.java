package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Scanner;

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

    public Familia() {}

    public Familia(String dni, Integer edad, String nombre, String ciudad) {

        this.setDni(dni);
        this.setEdad(edad);
        this.setNombre(nombre);
        this.setCiudad(ciudad);
    }

    // GETTERS

    public String getDni() { return dni; }

    public Integer getEdad() { return edadPersona; }

    public String getNombre(){ return nombrePersona; }

    public String getCiudad() { return ciudad; }

    // SETTERS

    public void setDni(String dni) { this.dni = dni; }

    public void setEdad(Integer edad) { this.edadPersona = edad; }

    public void setNombre(String nombre) { this.nombrePersona = capitalizarPrimeraLetra(nombre); }

    public void setCiudad(String ciudad) { this.ciudad = capitalizarPrimeraLetra(ciudad); }

    // TO STRING

    @Override
    public String toString() {
        return "\n Dni: " + dni +
                ", Nombre: " + nombrePersona +
                ", Edad: " + edadPersona +
                ", Ciudad: " + ciudad;
    }

    // MÉTODOS

    private static String capitalizarPrimeraLetra(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return palabra;
        }
        return palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();
    }

    public String ingresarDNI() {
        
        Scanner leer = new Scanner(System.in);

        String dni;
        String[] letras = null;
        do {
            System.out.print("\n Introduce el DNI (8 dígitos y una letra): ");
            dni = leer.next().toUpperCase();
            if (!dni.matches("\\d{8}[A-Za-z]")) {
                System.out.println("\n El formato del DNI no es correcto.");
            } else {
                letras = new String[]{"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
                int numero = Integer.parseInt(dni.substring(0, 8));
                int resto = numero % 23;
                String letraCorrecta = letras[resto];
                if (!dni.substring(8).equals(letraCorrecta)) {
                    System.out.println("\n La letra del DNI no corresponde con el número.");
                }
            }
        } while (!dni.matches("\\d{8}[A-Za-z]") || !dni.substring(8).equals(letras[Integer.parseInt(dni.substring(0, 8)) % 23]));
        return dni;
    }

}
