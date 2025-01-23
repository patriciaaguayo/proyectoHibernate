package org.example;

import org.example.DAO.IFamiliaDAOImpl;
import org.example.entities.Familia;

import java.util.List;
import java.util.Scanner;

public class GestionFamilia {

    private IFamiliaDAOImpl familiaDAO;

    public GestionFamilia() {
        familiaDAO = new IFamiliaDAOImpl();
    }

    /**
     *  Método para registrar una familia
     */

    public void registrarFamilia() {

        Scanner scanner = new Scanner(System.in);
        String dni = solicitarDNI(scanner);

        if (familiaDAO.buscarPorDNI(dni) != null) {
            System.out.println("\n El DNI ingresado ya está registrado. No se puede registrar la familia nuevamente.");
            return;
        }

        String nombre = solicitarNombre(scanner);
        int edad = solicitarEdad(scanner);
        String ciudad = solicitarCiudad(scanner);

        Familia familia = new Familia(dni, edad, nombre, ciudad);
        familiaDAO.registrarFamilia(familia);

        System.out.println("\n Familia registrada exitosamente.");
    }

    /**
     * Método para mostrar todas las familias
     */

    public void mostrarFamilias() {
        List<Familia> familias = familiaDAO.obtenerFamilias();
        if (familias != null && !familias.isEmpty()) {

            System.out.println("\n LISTADO DE FAMILIAS DE ACOGIDA: ");
            for (Familia familia : familias) {
                System.out.println(familia);
            }
        } else {
            System.out.println("\n No se encontraron familias.");
        }
    }

    /**
     * Método para eliminar una familia por DNI
     */

    public void eliminarFamilia() {

        Scanner scanner = new Scanner(System.in);
        String dni = solicitarDNI(scanner);
        Familia familia = familiaDAO.buscarPorDNI(dni);
        familiaDAO.eliminarFamilia(dni);
        System.out.println("\n Familia con DNI: " + dni + " eliminada exitosamente.");
    }

    /**
     *  Método para buscar una familia por DNI
     */

    public void buscarFamiliaPorDNI() {

        Scanner scanner = new Scanner(System.in);

        String dni = solicitarDNI(scanner);
        Familia familia = familiaDAO.buscarPorDNI(dni);

        if (familia != null) {
            System.out.println("\n Familia encontrada: " + familia);
        } else {
            System.out.println("\n Familia con el DNI " + dni + " no encontrada.");
        }
    }

    // MÉTODOS DE VALIDACIÓN

    /**
     *
     * @param scanner pasa el scanner
     * @return devuelve el DNI
     */

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

}