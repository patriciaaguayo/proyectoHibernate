package org.example;

import org.example.DAO.IAnimalDAO;
import org.example.DAO.IAnimalDAOImpl;
import org.example.entities.Animal;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        menuPrincipal();
    }

    public static void menuPrincipal() {
        Scanner leer = new Scanner(System.in);

        while (true) {
            System.out.println("\n ----- Menu Principal -----");
            System.out.println("\n 1. Animales");
            System.out.println(" 2. Familias");
            System.out.println(" 3. Adopciones");
            System.out.println(" 4. Salir");

            System.out.print("\n Seleccione una opción: ");
            String opcion = leer.nextLine();

            if (validarEntrada(opcion)) {
                int opcionInt = Integer.parseInt(opcion);

                switch (opcionInt) {
                    case 1:
                        menuAnimales();
                        break;
                    case 2:
                        menuFamilias();
                        break;
                    case 3:
                        menuAdopciones();
                        break;
                    case 4:
                        System.out.println("\n Gracias por usar el programa. Adios!");
                        leer.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\n Opción no válida. Selecciona una opción válida");
                        break;
                }
            } else {
                System.out.println("\n Solo se admiten números enteros positivos");
            }
        }
    }

    public static void menuAnimales() {
        Scanner scanner = new Scanner(System.in);
        GestionAnimal gestor = new GestionAnimal();

        while (true) {
            System.out.println("\n ----- Menu Animales -----");
            System.out.println("\n 1. Registrar animal");
            System.out.println(" 2. Mostrar animales");
            System.out.println(" 3. Buscar animales por especie");
            System.out.println(" 4. Actualizar estado de un animal");
            System.out.println(" 5. Eliminar animal");
            System.out.println(" 6. Volver al menú principal");
            System.out.println(" 7. Cerrar programa");

            System.out.print("\n Seleccione una opción: ");
            String opcion = scanner.nextLine();

            if (validarEntrada(opcion)) {
                int opcionInt = Integer.parseInt(opcion);

                switch (opcionInt) {

                    case 1:
                        gestor.registrarAnimal(); // Funciona
                        break;

                    case 2:
                        gestor.mostrarAnimales(); // Funciona
                        break;

                    case 3:
                        gestor.buscarAnimalesPorEspecie(); // Funciona
                        break;

                    case 4:
                        gestor.actualizarEstadoAnimal(); // Funciona
                        break;

                    case 5:
                        gestor.eliminarAnimal(); // Funciona pero falta configurar mensaje de error
                        break;

                    case 6:
                        System.out.println("\n Volviendo al menú principal...");
                        return;

                    case 7:
                        System.out.println("\n Gracias por usar el programa. Adios!");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("\n Opción no válida. Selecciona una opción válida");
                        break;
                }
            } else {
                System.out.println("\n Solo se admiten números enteros positivos");
            }
        }
    }

    public static void menuFamilias() {
        Scanner scanner = new Scanner(System.in);

        GestionFamilia gestor = new GestionFamilia();

        while (true) {
            System.out.println("\n ----- Menu Familias -----");
            System.out.println("\n 1. Registrar familia");
            System.out.println(" 2. Mostrar familias");
            System.out.println(" 3. Eliminar familia");
            System.out.println(" 4. Buscar familia por DNI");
            System.out.println(" 5. Volver al menú principal");
            System.out.println(" 6. Cerrar programa");

            System.out.print("\n Seleccione una opción: ");
            String opcion = scanner.nextLine();

            if (validarEntrada(opcion)) {
                int opcionInt = Integer.parseInt(opcion);

                switch (opcionInt) {

                    case 1:
                        gestor.registrarFamilia();
                        break;

                    case 2:
                        gestor.mostrarFamilias();
                        break;

                    case 3:
                        gestor.eliminarFamilia();
                        return;

                    case 4:
                        gestor.buscarFamiliaPorDNI();
                        break;

                    case 5:
                        System.out.println("\n Volviendo al menú principal...");
                        return;

                    case 6:
                        System.out.println("\n Gracias por usar el programa. Adios!");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("\n Opción no válida. Selecciona una opción válida");
                        break;
                }
            } else {
                System.out.println("\n Solo se admiten números enteros positivos");
            }
        }
    }

    public static void menuAdopciones() {
        Scanner scanner = new Scanner(System.in);

        GestionRefugio gestor = new GestionRefugio();

        while (true) {
            System.out.println("\n ----- Menu Adopciones -----");
            System.out.println("\n 1. Realizar adopcion");
            System.out.println(" 2. Mostrar adopciones");
            System.out.println(" 3. Buscar adopciones por familia (dni)");
            System.out.println(" 4. Volver al menú principal");
            System.out.println(" 5. Cerrar programa");

            System.out.print("\nSeleccione una opción: ");
            String opcion = scanner.nextLine();

            if (validarEntrada(opcion)) {
                int opcionInt = Integer.parseInt(opcion);

                switch (opcionInt) {

                    case 1:
                        gestor.realizarAdopcion();
                        break;

                    case 2:
                        gestor.mostrarAdopciones();
                        break;

                    case 3:
                        gestor.mostrarAdopcionesPorFamilia();
                        break;

                    case 4:
                        System.out.println("\n Volviendo al menú principal...");
                        return;

                    case 5:
                        System.out.println("\n Gracias por usar el programa. Adios!");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("\n Opción no válida. Selecciona una opción válida");
                        break;
                }
            } else {
                System.out.println("\n Solo se admiten números enteros positivos");
            }
        }
    }

    public static boolean validarEntrada(String opcion) {
        try {
            int valor = Integer.parseInt(opcion);
            return valor > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}