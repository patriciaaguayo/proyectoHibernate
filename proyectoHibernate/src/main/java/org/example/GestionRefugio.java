package org.example;

import org.example.DAO.IAnimalDAOImpl;
import org.example.DAO.IFamiliaDAO;
import org.example.DAO.IFamiliaDAOImpl;
import org.example.DAO.IRefugioDAOImpl;
import org.example.entities.Animal;
import org.example.entities.Familia;
import org.example.entities.Refugio;

import java.util.List;
import java.util.Scanner;

public class GestionRefugio {

    IRefugioDAOImpl refugioDAO;
    IFamiliaDAOImpl familiaDAO;
    IAnimalDAOImpl animalDAO;;

    public GestionRefugio() {
        refugioDAO = new IRefugioDAOImpl();
        familiaDAO = new IFamiliaDAOImpl();
        animalDAO = new IAnimalDAOImpl();
    }

    public void realizarAdopcion() {
        // Revisar si hay familias en la tabla Familia
        List<Familia> familias = refugioDAO.obtenerFamilias();
        if (familias.isEmpty()) {
            System.out.println("No hay familias disponibles para la adopción.");
            return;
        }

        // Revisar si hay animales disponibles para la adopción
        List<Animal> animales = refugioDAO.obtenerAnimalesDisponibles();
        if (animales.isEmpty()) {
            System.out.println("No hay animales disponibles para la adopción.");
            return;
        }

        // Mostrar las familias y animales disponibles
        System.out.println("Familias disponibles:");
        for (Familia familia : familias) {
            System.out.println(familia.getNombre() + " - " + familia.getDni());
        }

        System.out.println("Animales disponibles:");
        for (Animal animal : animales) {
            System.out.println(animal.getNombre() + " - " + animal.getId());
        }

        // Pedir el DNI de la familia y el ID del animal
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el DNI de la familia: ");
        String dniFamilia = scanner.nextLine();
        System.out.print("Ingrese el ID del animal: ");
        int idAnimal = scanner.nextInt();

        // Buscar la familia y el animal
        Familia familia = familiaDAO.buscarPorDNI(dniFamilia);
        Animal animal = animalDAO.buscarAnimalPorId(idAnimal);

        // Crear un nuevo refugio
        Refugio refugio = new Refugio();
        refugio.setFamilia(familia);
        refugio.setAnimal(animal);
        refugio.setNombreRefugio("Prado Verde");
        refugioDAO.guardarRefugio(refugio);

        // Actualizar el estado del animal
        animal.setEstado("Próximamente en acogida");
        animal.setAdoptado(true);
        refugioDAO.actualizarAnimal(animal);
    }

    // Nuevo método: Listar adopciones realizadas

    public void mostrarAdopciones() {
        System.out.println("\nAdopciones realizadas:");

        // Obtener adopciones realizadas
        List<Refugio> adopciones = refugioDAO.obtenerAdopcionesRealizadas();

        // Verificar si hay adopciones
        if (adopciones.isEmpty()) {
            System.out.println("No se han realizado adopciones todavía.");
            return;
        }

        // Mostrar la información de las adopciones
        for (Refugio refugio : adopciones) {
            Familia familia = refugio.getFamilia();
            Animal animal = refugio.getAnimal();

            System.out.println("Familia: " + familia.getNombre() + " (DNI: " + familia.getDni() + ")");
            System.out.println(" - Animal Adoptado: [ID: " + animal.getId() + ", Nombre: " + animal.getNombre() + ", Edad: " + animal.getEdad() + "]");
        }
    }
}