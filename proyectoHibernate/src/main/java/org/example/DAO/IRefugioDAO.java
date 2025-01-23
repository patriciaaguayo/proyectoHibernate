package org.example.DAO;

import org.example.entities.Animal;
import org.example.entities.Familia;
import org.example.entities.Refugio;

import java.util.List;

public interface IRefugioDAO {

    /**
     *
     * @param refugio se le pasa el refugio a guardar
     */

    void registrarAdopcion(Refugio refugio);

    /**
     *
     * @return obtiene todas las adopciones del refugio
     */

    List<Refugio> obtenerAdopciones(String refugio);

    /**
     *
     * @param dni se le pasa el dni de la familia o representante
     * @return devuelve los animales acogidos por esa familia
     */

    Refugio buscarAdopcionPorDni(String dni);

    /**
     *
     * @param dni
     * @return
     */

    Familia buscarFamiliaPorDni(String dni);

    /**
     *
     * @param id
     * @return
     */

    Animal buscarAnimalPorId(int id);

    /**
     *
     * @param animal
     */

    void actualizarAnimal(Animal animal);


}
