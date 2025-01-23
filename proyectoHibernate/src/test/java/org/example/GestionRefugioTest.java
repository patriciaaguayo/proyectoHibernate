package org.example;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GestionRefugioTest {

    @Test
    void testSolicitarDNIConTodosLosCasos() {

        Scanner scanner = new Scanner("12345678B\n1234A\nABCDEFGA\n12345678Z");
        Validacion validacion = new Validacion();
        String dni = validacion.solicitarDNI(scanner);
        assertEquals("12345678Z", dni, "El método debe aceptar el primer DNI válido tras rechazar los incorrectos.");
    }

    @Test
    void testSolicitarIDAnimalConTodosLosCasos() {

        Scanner scanner = new Scanner("-5\nabc\n0\n25");
        Validacion validacion = new Validacion();
        int idAnimal = validacion.solicitarIDAnimal(scanner);
        assertEquals(25, idAnimal, "El método debería aceptar solo un ID válido después de rechazar las entradas inválidas.");

    }
}