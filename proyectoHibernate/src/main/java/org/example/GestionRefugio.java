package org.example;

import org.example.DAO.IRefugioDAOImpl;
import org.example.entities.Animal;
import org.example.entities.Familia;
import org.example.entities.Refugio;

import java.util.List;
import java.util.Scanner;

public class GestionRefugio {

    IRefugioDAOImpl refugioDAO;

    public GestionRefugio() {
        refugioDAO = new IRefugioDAOImpl();
    }

    // Método para realizar una adopción
    public void realizarAdopcion() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n ----- Realizar Adopción -----");

        // Solicitar información para crear un objeto Refugio
        System.out.print("Introduce el DNI de la familia: ");
        String dniFamilia = scanner.nextLine();

        String nombreRefugio = "Prado Verde";

        // Aquí deberías obtener el animal que se va a adoptar,
        // por ahora tomaremos un ejemplo de Animal.
        Animal animal = new Animal("Rex", 5, "Perro", "Perro de raza grande", "Recien abandonado");

        Familia familia = new Familia(dniFamilia, 35, "Juan Pérez", "Madrid");  // Ejemplo de familia

        // Crear el objeto Refugio con los datos obtenidos
        Refugio refugio = new Refugio(familia, animal, nombreRefugio);

        // Llamar al método de la interfaz para registrar la adopción
        refugioDAO.registrarAdopcion(refugio);

        System.out.println("\nAdopción realizada con éxito.");
    }

    // Método para mostrar todas las adopciones
    public void mostrarAdopciones() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n ----- Mostrar Adopciones -----");
        System.out.print("Introduce el nombre del refugio para ver las adopciones: ");
        String nombreRefugio = scanner.nextLine();

        // Llamar al método de la interfaz para obtener las adopciones
        List<Refugio> adopciones = refugioDAO.obtenerAdopciones(nombreRefugio);

        if (adopciones.isEmpty()) {
            System.out.println("\nNo se han encontrado adopciones en este refugio.");
        } else {
            System.out.println("\nAdopciones en el refugio '" + nombreRefugio + "':");
            for (Refugio r : adopciones) {
                System.out.println(r);  // Utiliza el método toString() de Refugio para mostrar la información
            }
        }
    }

    // Método para buscar adopciones por DNI de la familia
    public void buscarAdopcionPorDni() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n ----- Buscar Adopciones por Familia (DNI) -----");
        System.out.print("Introduce el DNI de la familia: ");
        String dniFamilia = scanner.nextLine();

        // Llamar al método de la interfaz para obtener el refugio relacionado con el DNI
        Refugio refugio = refugioDAO.buscarAdopcionPorDni(dniFamilia);

        if (refugio == null) {
            System.out.println("\nNo se han encontrado adopciones para este DNI.");
        } else {
            System.out.println("\nAdopciones de la familia con DNI '" + dniFamilia + "':");
            System.out.println(refugio);  // Utiliza el método toString() de Refugio para mostrar la información
        }
    }
}