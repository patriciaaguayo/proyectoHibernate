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

    /**
     * Método para realizar una adopcion
     */

    public void realizarAdopcion() {

        List<Familia> familias = refugioDAO.obtenerFamilias();
        if (familias.isEmpty()) {
            System.out.println("\n No hay familias disponibles para la adopción.");
            return;
        }

        List<Animal> animales = refugioDAO.obtenerAnimalesDisponibles();
        if (animales.isEmpty()) {
            System.out.println("\n No hay animales disponibles para la adopción.");
            return;
        }

        System.out.println("\n FAMILIAS DISPONIBLES:");
        for (Familia familia : familias) {
            System.out.println(familia.getNombre() + " - " + familia.getDni());
        }

        System.out.println("\n ANIMALES DISPONIBLES:");
        for (Animal animal : animales) {
            System.out.println(animal.getNombre() + " - " + animal.getId());
        }

        Scanner scanner = new Scanner(System.in);

        String dniFamilia = solicitarDNI(scanner);
        Familia familia = familiaDAO.buscarPorDNI(dniFamilia);
        if (familia == null) {
            System.out.println("\n No se encontró ninguna familia con ese DNI.");
            return;
        }

        int idAnimal = solicitarIDAnimal(scanner);
        Animal animal = animalDAO.buscarAnimalPorId(idAnimal);
        if (animal == null) {
            System.out.println("\n No se encontró ningún animal con ese ID.");
            return;
        }

        Refugio refugio = new Refugio();
        refugio.setFamilia(familia);
        refugio.setAnimal(animal);
        refugio.setNombreRefugio("Prado Verde");
        refugioDAO.guardarRefugio(refugio);

        animal.setEstado("Próximamente en acogida");
        animal.setAdoptado(true);
        refugioDAO.actualizarAnimal(animal);

        System.out.println("\n ¡Adopción realizada con éxito!");
    }

    //

    /**
     * Método para mostrar las adopciones realizadas
     */

    public void mostrarAdopciones() {

        List<Refugio> adopciones = refugioDAO.obtenerAdopcionesRealizadas();

        if (adopciones.isEmpty()) {
            System.out.println("\n No se han realizado adopciones todavía.");
            return;
        }

        Map<Familia, List<Animal>> adopcionesPorFamilia = new HashMap<>();

        for (Refugio refugio : adopciones) {
            Familia familia = refugio.getFamilia();
            Animal animal = refugio.getAnimal();

            adopcionesPorFamilia
                    .computeIfAbsent(familia, k -> new ArrayList<>()) // Si no existe la familia, inicializamos la lista
                    .add(animal); // Agregamos el animal adoptado a la lista de esa familia
        }

        System.out.println("\n ADOPCIONES REALIZADAS:");

        for (Map.Entry<Familia, List<Animal>> entry : adopcionesPorFamilia.entrySet()) {
            Familia familia = entry.getKey();
            List<Animal> animalesAdoptados = entry.getValue();

            System.out.println("\n Familia: " + familia.getNombre() + " (DNI: " + familia.getDni() + ")");
            for (Animal animal : animalesAdoptados) {
                System.out.println("\n\t - Animal Adoptado: [ID: " + animal.getId() + ", Nombre: " + animal.getNombre() + ", Edad: " + animal.getEdad() + "]");
            }
        }
    }

    /**
     * Método para mostrar las adopciones realizadas por una familia
     */

    public void mostrarAdopcionesPorFamilia() {

        Scanner scanner = new Scanner(System.in);
        String dniFamilia = solicitarDNI(scanner);
        Familia familia = familiaDAO.buscarPorDNI(dniFamilia);

        if (familia == null) {
            System.out.println("\n No se encontró ninguna familia con el DNI: " + dniFamilia);
            return;
        }

        List<Refugio> adopciones = refugioDAO.obtenerAdopcionesRealizadas();

        List<Refugio> adopcionesFamilia = adopciones.stream()
                .filter(refugio -> refugio.getFamilia().getDni().equalsIgnoreCase(dniFamilia))
                .toList();

        if (adopcionesFamilia.isEmpty()) {
            System.out.println("\n La familia con DNI: " + dniFamilia + " no ha realizado ninguna adopción.");
            return;
        }

        System.out.println("\n ADOPCIONES REALIZADAS POR LA FAMILIA CON DNI: " + dniFamilia);
        System.out.println("\n Familia: " + familia.getNombre() + " (DNI: " + familia.getDni() + ")");

        for (Refugio refugio : adopcionesFamilia) {
            Animal animal = refugio.getAnimal();
            System.out.println("\n\t - Animal Adoptado: [ID: " + animal.getId() + ", Nombre: " + animal.getNombre() + ", Edad: " + animal.getEdad() + "]");
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
}