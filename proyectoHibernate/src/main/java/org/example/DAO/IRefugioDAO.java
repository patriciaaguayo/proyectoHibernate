package org.example.DAO;

import org.example.entities.Animal;
import org.example.entities.Familia;
import org.example.entities.Refugio;

import java.util.List;

public interface IRefugioDAO {

    /**
     *
     * @param refugio se le pasa el refugio
     */

    void guardarRefugio(Refugio refugio);

    /**
     *
     * @param animal se le pasa el animal a actualizar
     */

    void actualizarAnimal(Animal animal);

    /**
     *
     * @return devuelve familias
     */

    List<Familia> obtenerFamilias();

    /**
     *
     * @return devuelve animales
     */

    List<Animal> obtenerAnimalesDisponibles();

    /**
     * Obtiene una lista de adopciones realizadas, mostrando el nombre de la familia/persona
     * con su DNI, y los animales que han adoptado.
     *
     * @return Una lista de adopciones realizadas.
     */

    List<Refugio> obtenerAdopcionesRealizadas();

}
