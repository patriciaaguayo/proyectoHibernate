package org.example.DAO;

import org.example.entities.Animal;
import org.example.entities.Familia;

import java.util.List;

public interface IRefugioDAO {

    /**
     *
     * @param animal se le pasa el animal que va a ser adoptado
     * @param familia se le pasa la familia que lo adopta
     * @param nombreRefugio se le pasa el nombre del refugio
     */

    void registrarAdopcion(Animal animal, Familia familia, String nombreRefugio);

    /**
     *
     * @param nombreRefugio se le pasa el nombre del refugio
     * @return la lista de animales que han sido adoptados en el refugio
     */

    List<Animal> obtenerAnimalesPorRefugio(String nombreRefugio);

    /**
     *
     * @param familia se le pasa la familia
     * @return la lista de animales que han sido adoptados por la familia en cuestión
     */

    List <Animal> obtenerAnimalesPorFamilia(Familia familia);


}
