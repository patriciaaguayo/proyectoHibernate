package org.example.DAO;

import org.example.entities.Animal;

import java.util.List;

public interface IAnimalDAO {

    /**
     * @return obtiene todos los animales
     */

    List<Animal> obtenerAnimales();

    /**
     * @return busca animales por especie
     * @param especie se le pasa el nombre de la especie
     */

    List<Animal> buscarPorEspecie(String especie);

    /**
     * @param animal se le pasa el animal a guardar
     */

    void registrarAnimal(Animal animal);

    /**
     *
     * @param idAnimal se le pasa el id del animal a actualizar
     * @param nuevoEstado se le pasa el nuevo estado del animal
     */

    void actualizarEstado(Integer idAnimal, String nuevoEstado);

    /**
     *
     * @param idAnimal se le pasa el id del animal a eliminar
     */

    void eliminarAnimal(Integer idAnimal);
}
