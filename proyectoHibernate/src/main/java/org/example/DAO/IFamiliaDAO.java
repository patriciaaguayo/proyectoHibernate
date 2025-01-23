package org.example.DAO;

import org.example.entities.Familia;

import java.util.List;

public interface IFamiliaDAO {

    /**
     *
     * @param familia se le pasa la familia a registrar
     */

    void registrarFamilia(Familia familia);

    /**
     *
     * @param dni se le pasa el DNI de la familia a buscar
     * @return devuelve la familia buscada
     */

    Familia buscarPorDNI(String dni);

    /**
     *
     * @return devuelve una lista contodas las familias
     */

    List<Familia> obtenerFamilias();

    /**
     *
     * @param dni se le pasa el DNI de la familia a eliminar
     */

    void eliminarFamilia(String dni);

}
