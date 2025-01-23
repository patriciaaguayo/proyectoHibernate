package org.example;

import org.example.DAO.IAnimalDAOImpl;
import org.example.entities.Animal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GestionAnimal {

    private IAnimalDAOImpl animalDAO;

    public GestionAnimal() {
        animalDAO = new IAnimalDAOImpl();
    }

    // Método para registrar un nuevo animal

    public void registrarAnimal() {

        String nombre = obtenerNombre();
        int edad = obtenerEdad();
        String especie = obtenerEspecie();
        String descripcion = obtenerDescripcion();

        Animal nuevoAnimal = new Animal(nombre, edad, especie, descripcion, "Recien abandonado");

        animalDAO.registrarAnimal(nuevoAnimal);
        System.out.println("\n Animal registrado exitosamente.");
    }

    // Método para mostrar todos los animales

    public void mostrarAnimales() {
        List<Animal> animales = animalDAO.obtenerAnimales();
        if (animales.isEmpty()) {
            System.out.println("\n No hay animales registrados.");
        } else {
            System.out.println("\n LISTA DE ANIMALES REGISTRADOS: ");
            for (Animal animal : animales) {
                System.out.println(animal);
            }
        }
    }

    // Método para buscar animales por especie

    public void buscarAnimalesPorEspecie() {

        String especie = obtenerEspecie();

        List<Animal> animales = animalDAO.buscarPorEspecie(especie);
        if (animales.isEmpty()) {
            System.out.println("\n No se encontraron animales de esa especie.");
        } else {
            for (Animal animal : animales) {
                System.out.println(animal);
            }
        }
    }

    // Método para actualizar el estado de un animal

    public void actualizarEstadoAnimal() {

        int id = validarIdAnimal();
        String nuevoEstado = validarEstadoAnimal();

        animalDAO.actualizarEstado(id, nuevoEstado);
        System.out.println("\n Estado actualizado exitosamente.");
    }

    // Método para eliminar un animal

    public void eliminarAnimal() {
        int id = validarIdAnimal();

        try {
            animalDAO.eliminarAnimal(id);
            System.out.println("\n Animal eliminado exitosamente.");

        } catch (RuntimeException e) {
            System.out.println("\n Error: " + e.getMessage());
        }
    }

    // Método para mostrar los animales según su estado de adopción

    public void mostrarAnimalesPorAdopcion() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Mostrar animales adoptados? (true/false):");
        boolean adoptado = Boolean.parseBoolean(scanner.nextLine());

        List<Animal> animales = animalDAO.buscarPorEstadoAdopcion(adoptado);
        if (animales.isEmpty()) {
            System.out.println("\n No se encontraron animales con ese estado de adopción.");
        } else {
            for (Animal animal : animales) {
                System.out.println(animal);
            }
        }
    }

    // Métodos de comprobación de entrada de datos

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

    // Método para validar el ID del animal

    private int validarIdAnimal() {
        Scanner scanner = new Scanner(System.in);
        int id = -1;

        while (true) {
            System.out.println("\n Introduce el ID del animal a buscar:");

            try {
                id = Integer.parseInt(scanner.nextLine());
                if (id > 0) {
                    break;
                } else {
                    System.out.println("\n El ID debe ser un número positivo mayor a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n ID inválido. Introduce un número entero válido.");
            }
        }

        return id;
    }

    // Método para validar el nuevo estado del animal

    private String validarEstadoAnimal() {

        Scanner scanner = new Scanner(System.in);
        String estado;

        // Lista de estados válidos dentro del método
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

    private static String capitalizarPrimeraLetra(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return palabra;
        }
        return palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();
    }
}