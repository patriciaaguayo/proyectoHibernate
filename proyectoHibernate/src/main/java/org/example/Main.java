package org.example;

import org.example.DAO.IAnimalDAO;
import org.example.DAO.IAnimalDAOImpl;
import org.example.entities.Animal;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Instancia del DAO
        IAnimalDAO animalDAO = new IAnimalDAOImpl();

        // 1. Registrar un nuevo animal
        Animal animal1 = new Animal();
        animal1.setNombre("Firulais");
        animal1.setEspecie("Perro");
        animal1.setEdad(3);
        animal1.setDescripcion("Perdido en el parque central");
        animal1.setEstado("Recién abandonado");

        animalDAO.registrarAnimal(animal1);
        System.out.println("\n Animal registrado: " + animal1);

        // 2. Obtener todos los animales




        // 3. Buscar animales por especie


        // 4. Actualizar el estado de un animal



        // 5. Verificar el estado actualizado



        // 6. Eliminar un animal



        // 7. Verificar la lista de animales después de la eliminación


    }
}