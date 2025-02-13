package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GestionAnimalTest {

    @Test
    void testSolicitarNombre() {
        Validacion validacion = new Validacion();

        // Caso 1: Nombre válido
        Scanner scannerNombreValido = new Scanner("Juan\n");
        String nombreValido = validacion.solicitarNombre(scannerNombreValido);
        assertEquals("Juan", nombreValido, "El nombre válido debería ser aceptado tal cual.");

        // Caso 2: Nombre vacío al principio
        Scanner scannerNombreVacio = new Scanner("\nMaría\n");
        String nombreConVacio = validacion.solicitarNombre(scannerNombreVacio);
        assertEquals("María", nombreConVacio, "El nombre no debería ser aceptado si está vacío, y debería aceptar el válido.");

        // Caso 3: Nombre con espacios al principio y al final
        Scanner scannerNombreConEspacios = new Scanner("   Carlos   \n");
        String nombreConEspacios = validacion.solicitarNombre(scannerNombreConEspacios);
        assertEquals("Carlos", nombreConEspacios, "El nombre con espacios debería ser aceptado sin los espacios extra.");
    }

    @Test
    void testObtenerEdad() {
        Validacion validacion = new Validacion();

        // Caso 1: Edad válida
        String inputValida = "5\n";
        System.setIn(new ByteArrayInputStream(inputValida.getBytes()));
        int edadValida = validacion.obtenerEdad();
        assertEquals(5, edadValida, "La edad válida debería ser aceptada tal cual.");

        // Caso 2: Edad negativa
        String inputNegativa = "-3\n4\n";
        System.setIn(new ByteArrayInputStream(inputNegativa.getBytes()));
        int edadNegativa = validacion.obtenerEdad();
        assertEquals(4, edadNegativa, "Una edad negativa debería ser rechazada, y la entrada válida aceptada.");

        // Caso 3: Edad no numérica
        String inputNoNumerica = "abc\n7\n";
        System.setIn(new ByteArrayInputStream(inputNoNumerica.getBytes()));
        int edadNoNumerica = validacion.obtenerEdad();
        assertEquals(7, edadNoNumerica, "Una entrada no numérica debería ser rechazada, y la edad válida aceptada.");

        // Caso 4: Edad igual a 0
        String inputCero = "0\n8\n";
        System.setIn(new ByteArrayInputStream(inputCero.getBytes()));
        int edadCero = validacion.obtenerEdad();
        assertEquals(8, edadCero, "Una edad igual a 0 debería ser rechazada, y la entrada válida aceptada.");
    }

    @Test
    void testObtenerEspecie() {
        Validacion validacion = new Validacion();

        // Caso 1: Especie válida
        String inputValida = "perro\n";
        System.setIn(new ByteArrayInputStream(inputValida.getBytes()));
        String especieValida = validacion.obtenerEspecie();
        assertEquals("Perro", especieValida, "La especie válida debería ser aceptada tal cual.");

        // Caso 2: Especie no válida (vacío)
        String inputVacio = "\nperro\n";
        System.setIn(new ByteArrayInputStream(inputVacio.getBytes()));
        String especieVacia = validacion.obtenerEspecie();
        assertEquals("Perro", especieVacia, "Una especie vacía debería ser rechazada, y la válida aceptada.");

        // Caso 3: Especie no válida (caracteres no permitidos)
        String inputNoValida = "elefante\nperro\n";
        System.setIn(new ByteArrayInputStream(inputNoValida.getBytes()));
        String especieNoValida = validacion.obtenerEspecie();
        assertEquals("Perro", especieNoValida, "Una especie no válida debería ser rechazada, y la válida aceptada.");

        // Caso 4: Especie con letras en mayúsculas y minúsculas (asegurándonos de que se capitaliza correctamente)
        String inputMayusculas = "CAMALEON\n";
        System.setIn(new ByteArrayInputStream(inputMayusculas.getBytes()));
        String especieMayusculas = validacion.obtenerEspecie();
        assertEquals("Camaleon", especieMayusculas, "La especie con mayúsculas debería ser aceptada y capitalizada correctamente.");

        // Caso 5: Especie con espacios al inicio y final (se deben quitar los espacios)
        String inputConEspacios = "   gato   \n";
        System.setIn(new ByteArrayInputStream(inputConEspacios.getBytes()));
        String especieConEspacios = validacion.obtenerEspecie();
        assertEquals("Gato", especieConEspacios, "La especie con espacios debe ser aceptada sin los espacios extra.");
    }

    @Test
    void testObtenerDescripcion() {
        Validacion validacion = new Validacion();

        // Caso 1: Descripción válida
        String inputValida = "Un perro muy juguetón.\n";
        System.setIn(new ByteArrayInputStream(inputValida.getBytes()));
        String descripcionValida = validacion.obtenerDescripcion();
        assertEquals("Un perro muy juguetón.", descripcionValida, "La descripción válida debería ser aceptada tal cual.");

        // Caso 2: Descripción vacía (se espera que se pida de nuevo)
        String inputVacio = "\nUn perro muy juguetón.\n";
        System.setIn(new ByteArrayInputStream(inputVacio.getBytes()));
        String descripcionVacia = validacion.obtenerDescripcion();
        assertEquals("Un perro muy juguetón.", descripcionVacia, "La descripción vacía debería ser rechazada, y la válida aceptada.");

        // Caso 3: Descripción con espacios al principio y final (se deben quitar los espacios)
        String inputConEspacios = "   Un gato tranquilo   \n";
        System.setIn(new ByteArrayInputStream(inputConEspacios.getBytes()));
        String descripcionConEspacios = validacion.obtenerDescripcion();
        assertEquals("Un gato tranquilo", descripcionConEspacios, "La descripción con espacios extra debe ser aceptada sin los espacios.");

        // Caso 4: Descripción con letras en mayúsculas y minúsculas (se espera que se capitalice correctamente)
        String inputMayusculas = "ESTE GATO ES MUY CURIOSO.\n";
        System.setIn(new ByteArrayInputStream(inputMayusculas.getBytes()));
        String descripcionMayusculas = validacion.obtenerDescripcion();
        assertEquals("Este gato es muy curioso.", descripcionMayusculas, "La descripción con mayúsculas debe ser capitalizada correctamente.");
    }

    @Test
    void testSolicitarIDAnimalConTodosLosCasos() {

        Scanner scanner = new Scanner("-5\nabc\n0\n25");
        Validacion validacion = new Validacion();
        int idAnimal = validacion.solicitarIDAnimal(scanner);
        assertEquals(25, idAnimal, "El método debería aceptar solo un ID válido después de rechazar las entradas inválidas.");

    }

    @Test
    void testValidarEstadoAnimal() {
        Validacion validacion = new Validacion();

        // Caso 1: Estado válido
        String inputValido = "recién abandonado\n";
        System.setIn(new ByteArrayInputStream(inputValido.getBytes()));
        String estadoValido = validacion.validarEstadoAnimal();
        assertEquals("Recién abandonado", estadoValido, "El estado válido debería ser aceptado tal cual.");

        // Caso 2: Estado vacío (se espera que se pida de nuevo)
        String inputVacio = "\nrecién abandonado\n";
        System.setIn(new ByteArrayInputStream(inputVacio.getBytes()));
        estadoValido = validacion.validarEstadoAnimal();
        assertEquals("Recién abandonado", estadoValido, "Un estado vacío debería ser rechazado, y el estado válido aceptado.");

        // Caso 3: Estado con mayúsculas (se debe convertir a minúsculas y capitalizar la primera letra)
        String inputMayusculas = "TIEMPO EN EL REFUGIO\n";
        System.setIn(new ByteArrayInputStream(inputMayusculas.getBytes()));
        estadoValido = validacion.validarEstadoAnimal();
        assertEquals("Tiempo en el refugio", estadoValido, "El estado con mayúsculas debe ser convertido correctamente a minúsculas y capitalizado.");
    }


}