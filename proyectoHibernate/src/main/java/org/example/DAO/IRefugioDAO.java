package org.example.DAO;

import org.example.entities.Animal;
import org.example.entities.Familia;
import org.example.entities.Refugio;

import java.util.List;

public interface IRefugioDAO {

    /**
     *
     * @param refugio
     */

    void guardarRefugio(Refugio refugio);

    /**
     *
     * @param animal
     */

    void actualizarAnimal(Animal animal);

    /**
     *
     * @return
     */

    List<Familia> obtenerFamilias();

    /**
     *
     * @return
     */

    List<Animal> obtenerAnimalesDisponibles();


}
