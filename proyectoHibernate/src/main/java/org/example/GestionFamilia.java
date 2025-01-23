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

    // Método para registrar una familia
    public void registrarFamilia() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar datos al usuario
        System.out.println("\nIngrese el DNI de la familia:");
        String dni = scanner.nextLine();

        System.out.println("\nIngrese el nombre de la persona:");
        String nombre = scanner.nextLine();

        System.out.println("\nIngrese la edad de la persona:");
        int edad = Integer.parseInt(scanner.nextLine());

        System.out.println("\nIngrese la ciudad de la familia:");
        String ciudad = scanner.nextLine();

        // Crear una nueva instancia de la clase Familia
        Familia familia = new Familia(dni, edad, nombre, ciudad);

        // Registrar la familia en la base de datos usando el DAO
        familiaDAO.registrarFamilia(familia);
    }

    // Método para mostrar todas las familias
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

    // Método para eliminar una familia por DNI
    public void eliminarFamilia() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIngrese el DNI de la familia a eliminar: ");
        String dni = scanner.nextLine();
        familiaDAO.eliminarFamilia(dni);
    }

    // Método para buscar una familia por DNI
    public void buscarFamiliaPorDNI() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIngrese el DNI de la familia a buscar: ");
        String dni = scanner.nextLine();
        Familia familia = familiaDAO.buscarPorDNI(dni);
        if (familia != null) {
            System.out.println("\nFamilia encontrada: " + familia);
        } else {
            System.out.println("\nFamilia con el DNI " + dni + " no encontrada.");
        }
    }
}