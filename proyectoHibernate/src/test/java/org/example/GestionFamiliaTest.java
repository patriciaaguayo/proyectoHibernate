package org.example;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GestionFamiliaTest {

    @Test
    void testSolicitarDNIConTodosLosCasos() {

        Scanner scanner = new Scanner("12345678B\n1234A\nABCDEFGA\n12345678Z");
        Validacion validacion = new Validacion();
        String dni = validacion.solicitarDNI(scanner);
        assertEquals("12345678Z", dni, "El método debe aceptar el primer DNI válido tras rechazar los incorrectos.");
    }

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
    void testSolicitarEdad() {
        Validacion validacion = new Validacion();

        // Caso 1: Edad válida
        Scanner scannerEdadValida = new Scanner("25\n");
        int edadValida = validacion.solicitarEdad(scannerEdadValida);
        assertEquals(25, edadValida, "La edad válida debería ser aceptada tal cual.");

        // Caso 2: Edad menor de 18
        Scanner scannerEdadMenor = new Scanner("15\n20\n");
        int edadMenor = validacion.solicitarEdad(scannerEdadMenor);
        assertEquals(20, edadMenor, "La edad menor de 18 no debería ser aceptada, y debería pedir una válida.");

        // Caso 3: Edad no numérica
        Scanner scannerEdadNoNumerica = new Scanner("abc\n30\n");
        int edadNoNumerica = validacion.solicitarEdad(scannerEdadNoNumerica);
        assertEquals(30, edadNoNumerica, "Una entrada no numérica debería ser rechazada, y la válida aceptada.");

        // Caso 4: Edad negativa
        Scanner scannerEdadNegativa = new Scanner("-5\n19\n");
        int edadNegativa = validacion.solicitarEdad(scannerEdadNegativa);
        assertEquals(19, edadNegativa, "La edad negativa no debería ser aceptada, y debería pedir una válida.");
    }

    @Test
    void testSolicitarCiudad() {
        Validacion validacion = new Validacion();

        // Caso 1: Ciudad válida
        Scanner scannerCiudadValida = new Scanner("Madrid\n");
        String ciudadValida = validacion.solicitarCiudad(scannerCiudadValida);
        assertEquals("Madrid", ciudadValida, "La ciudad válida debería ser aceptada tal cual.");

        // Caso 2: Ciudad vacía
        Scanner scannerCiudadVacia = new Scanner("\nBarcelona\n");
        String ciudadVacia = validacion.solicitarCiudad(scannerCiudadVacia);
        assertEquals("Barcelona", ciudadVacia, "Una entrada vacía debería ser rechazada, y la ciudad válida aceptada.");

        // Caso 3: Ciudad con espacios al principio y al final
        Scanner scannerCiudadConEspacios = new Scanner("   Sevilla   \n");
        String ciudadConEspacios = validacion.solicitarCiudad(scannerCiudadConEspacios);
        assertEquals("Sevilla", ciudadConEspacios, "La ciudad con espacios debería ser aceptada sin los espacios extra.");
    }
}