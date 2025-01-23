package org.example;

import org.example.DAO.IAnimalDAOImpl;
import org.example.DAO.IFamiliaDAO;
import org.example.DAO.IFamiliaDAOImpl;
import org.example.DAO.IRefugioDAOImpl;
import org.example.entities.Animal;
import org.example.entities.Familia;
import org.example.entities.Refugio;

import java.util.*;

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

        // Crear un mapa para agrupar animales por familia
        Map<Familia, List<Animal>> adopcionesPorFamilia = new HashMap<>();

        // Agrupar los animales adoptados por familia
        for (Refugio refugio : adopciones) {
            Familia familia = refugio.getFamilia();
            Animal animal = refugio.getAnimal();

            adopcionesPorFamilia
                    .computeIfAbsent(familia, k -> new ArrayList<>()) // Si no existe la familia, inicializamos la lista
                    .add(animal); // Agregamos el animal adoptado a la lista de esa familia
        }

        System.out.println("\n ADOPCIONES REALIZADAS:");

        // Mostrar la información agrupada
        for (Map.Entry<Familia, List<Animal>> entry : adopcionesPorFamilia.entrySet()) {
            Familia familia = entry.getKey();
            List<Animal> animalesAdoptados = entry.getValue();

            System.out.println("\n Familia: " + familia.getNombre() + " (DNI: " + familia.getDni() + ")");
            for (Animal animal : animalesAdoptados) {
                System.out.println("\n\t - Animal Adoptado: [ID: " + animal.getId() + ", Nombre: " + animal.getNombre() + ", Edad: " + animal.getEdad() + "]");
            }
        }
    }

    public void mostrarAdopcionesPorFamilia() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el DNI de la familia: ");
        String dniFamilia = scanner.nextLine();

        System.out.println("\nAdopciones realizadas por la familia con DNI: " + dniFamilia);

        // Obtener adopciones realizadas
        List<Refugio> adopciones = refugioDAO.obtenerAdopcionesRealizadas();

        // Filtrar las adopciones para encontrar las que corresponden al DNI proporcionado
        List<Refugio> adopcionesFamilia = adopciones.stream()
                .filter(refugio -> refugio.getFamilia().getDni().equalsIgnoreCase(dniFamilia))
                .toList();

        // Verificar si la familia tiene adopciones
        if (adopcionesFamilia.isEmpty()) {
            System.out.println("\n No se encontraron adopciones para la familia con DNI: " + dniFamilia);
            return;
        }

        System.out.println("\n ADOPCIONES REALIZADAS POR LA FAMILIA CON DNI: " + dniFamilia);

        // Mostrar la información de la familia y los animales adoptados
        Familia familia = adopcionesFamilia.get(0).getFamilia(); // Todas las adopciones tienen la misma familia
        System.out.println("\n Familia: " + familia.getNombre() + " (DNI: " + familia.getDni() + ")");

        for (Refugio refugio : adopcionesFamilia) {
            Animal animal = refugio.getAnimal();
            System.out.println("\n\t - Animal Adoptado: [ID: " + animal.getId() + ", Nombre: " + animal.getNombre() + ", Edad: " + animal.getEdad() + "]");
        }
    }
}