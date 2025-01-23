package org.example;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Validacion {
    public String solicitarDNI(Scanner scanner) {
        String dni;
        String[] letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        boolean dniValido;

        do {
            System.out.print("\n Introduce el DNI (8 dígitos y una letra): ");
            dni = scanner.nextLine().toUpperCase();
            dniValido = dni.matches("\\d{8}[A-Z]");

            if (dniValido) {
                int numero = Integer.parseInt(dni.substring(0, 8));
                int resto = numero % 23;
                String letraCorrecta = letras[resto];
                if (!dni.substring(8).equals(letraCorrecta)) {
                    System.out.println("\n La letra del DNI no corresponde al número. Inténtalo de nuevo.");
                    dniValido = false;
                }
            } else {
                System.out.println("\n Formato de DNI incorrecto. Debe contener 8 dígitos seguidos de una letra.");
            }
        } while (!dniValido);

        return dni;
    }

    /**
     *
     * @param scanner se le pasa el scanner
     * @return devuelve el ID del animal
     */

    public int solicitarIDAnimal(Scanner scanner) {

        int idAnimal = -1;
        while (true) {
            System.out.print("\n Introduce el ID del animal: ");
            String input = scanner.nextLine();

            try {
                idAnimal = Integer.parseInt(input);
                if (idAnimal <= 0) {
                    System.out.println("\n El ID debe ser un número entero positivo. Inténtalo de nuevo.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\n Debe ingresar un número válido para el ID del animal. Inténtalo de nuevo.");
            }
        }
        return idAnimal;
    }

    /**
     *
     * @param scanner se le pasa el scanner
     * @return devuelve el nombre
     */

    public String solicitarNombre(Scanner scanner) {

        String nombre;
        do {
            System.out.print("\n Introduce el nombre de la persona: ");
            nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("\n El nombre no puede estar vacío. Inténtalo de nuevo.");
            }
        } while (nombre.isEmpty());
        return capitalizarPrimeraLetra(nombre);
    }

    /**
     *
     * @param scanner se le pasa el scanner
     * @return devuelve la edad
     */

    public int solicitarEdad(Scanner scanner) {
        int edad = -1;
        do {
            try {
                System.out.print("\n Introduce la edad de la persona: ");
                edad = Integer.parseInt(scanner.nextLine());

                if (edad < 18) {
                    System.out.println("\n La persona debe ser mayor de edad (18 años o más). Inténtalo de nuevo.");

                } else if (edad <= 0) {
                    System.out.println("\n La edad debe ser un número positivo. Inténtalo de nuevo.");
                }

            } catch (NumberFormatException e) {
                System.out.println("\n Debe ingresar un número válido para la edad. Inténtalo de nuevo.");
            }

        } while (edad < 18);
        return edad;
    }

    /**
     *
     * @param scanner se le pasa el scanner
     * @return devuelve la ciudad
     */

    public String solicitarCiudad(Scanner scanner) {

        String ciudad;

        do {
            System.out.print("\n Introduce la ciudad de la familia:");
            ciudad = scanner.nextLine().trim();
            if (ciudad.isEmpty()) {
                System.out.println("\n La ciudad no puede estar vacía. Inténtalo de nuevo.");
            }
        } while (ciudad.isEmpty());
        return capitalizarPrimeraLetra(ciudad);
    }

    /**
     *
     * @param palabra se le pasa la palabra
     * @return devuelve la primera letra en mayúscula
     */

    private String capitalizarPrimeraLetra(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return palabra;
        }
        return palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();
    }

    /**
     *
     * @return devuelve el nombre del animal
     */

    public String obtenerNombre() {
        Scanner scanner = new Scanner(System.in);
        String nombre;
        while (true) {
            System.out.print("\n Introduce el nombre del animal: ");
            nombre = scanner.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("\n El nombre no puede estar vacío. Ingrese un nombre válido.");
            } else if (!nombre.matches("[a-zA-Z ]+")) {
                System.out.println("\n El nombre solo puede contener letras y espacios.");
            } else {
                break;
            }
        }
        return capitalizarPrimeraLetra(nombre);
    }

    /**
     *
     * @return devuelve la edad del animal
     */

    public int obtenerEdad() {
        Scanner scanner = new Scanner(System.in);
        int edad;
        while (true) {
            System.out.print("\n Introduce la edad del animal: ");
            String input = scanner.nextLine();

            try {
                edad = Integer.parseInt(input);
                if (edad <= 0) {
                    System.out.println("\n La edad debe ser un número positivo.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\n Ingresa un número válido para la edad.");
            }
        }
        return edad;
    }

    /**
     *
     * @return devuelve la especie del animal
     */

    public String obtenerEspecie() {
        Scanner scanner = new Scanner(System.in);
        String especie;

        List<String> especiesValidas = Arrays.asList(
                "perro", "gato", "pajaro", "pájaro", "cerdo vietnamita",
                "serpiente", "camaleon", "camaleón", "arana", "araña"
        );

        do {
            System.out.print("\n Introduce la especie del animal:");
            especie = scanner.nextLine().trim().toLowerCase();

            if (especie.isEmpty() || !especiesValidas.contains(especie)) {
                System.out.println("\n Especie no válida. Intenta de nuevo. Las especies válidas son: " + especiesValidas);
            }
        } while (especie.isEmpty() || !especiesValidas.contains(especie));

        return capitalizarPrimeraLetra(especie);
    }

    /**
     *
     * @return devuelve la descripción del animal
     */

    public String obtenerDescripcion() {
        Scanner scanner = new Scanner(System.in);
        String descripcion;
        while (true) {
            System.out.print("\n Introduce la descripción del animal: ");
            descripcion = scanner.nextLine().trim();

            if (descripcion.isEmpty()) {
                System.out.println("\n La descripción no puede estar vacía. Por favor ingrese una descripción válida.");
            } else {
                break;
            }
        }
        return capitalizarPrimeraLetra(descripcion);
    }

    /**
     *
     * @return devuelve el nuevo estado del animal
     */

    public String validarEstadoAnimal() {

        Scanner scanner = new Scanner(System.in);
        String estado;

        List<String> estadosValidos = List.of(
                "recien abandonado",
                "recién abandonado",
                "tiempo en el refugio",
                "proximamente en acogida",
                "próximamente en acogida"
        );

        while (true) {
            System.out.print("\n Introduce el nuevo estado del animal:");

            estado = scanner.nextLine().trim().toLowerCase();

            if (estadosValidos.contains(estado)) {
                break;
            } else {
                System.out.println("\n Estado inválido. Introduce uno de los siguientes estados: " + estadosValidos);
            }
        }

        return capitalizarPrimeraLetra(estado);
    }


}
