package org.example.DAO;

import org.example.Util.HibernateUtil;
import org.example.entities.Animal;
import org.example.entities.Familia;
import org.example.entities.Refugio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class IRefugioDAOImpl implements IRefugioDAO {

    /**
     * @param refugio se le pasa el refugio
     */

    @Override
    public void guardarRefugio(Refugio refugio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(refugio);
        transaction.commit();
        session.close();
    }

    /**
     * @param animal se le pasa el animal a actualizar
     */

    @Override
    public void actualizarAnimal(Animal animal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(animal);
        transaction.commit();
        session.close();
    }

    /**
     * @return devuelve familias
     */

    @Override
    public List<Familia> obtenerFamilias() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Familia> query = session.createQuery("FROM Familia", Familia.class);
        List<Familia> familias = query.list();
        session.close();
        return familias;
    }

    /**
     * @return devuelve animales
     */

    @Override
    public List<Animal> obtenerAnimalesDisponibles() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Animal> query = session.createQuery("FROM Animal WHERE adoptado = false", Animal.class);
        List<Animal> animales = query.list();
        session.close();
        return animales;
    }

    /**
     * Obtiene una lista de adopciones realizadas, mostrando el nombre de la familia/persona
     * con su DNI, y los animales que han adoptado.
     *
     * @return Una lista de adopciones realizadas.
     */

    @Override
    public List<Refugio> obtenerAdopcionesRealizadas() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Refugio> adopciones = new ArrayList<>();

        try {
            session.beginTransaction();

            String hql = """
            FROM Refugio r
            JOIN FETCH r.familia f
            JOIN FETCH r.animal a
            WHERE a.adoptado = true
        """;

            Query<Refugio> query = session.createQuery(hql, Refugio.class);
            adopciones = query.getResultList();

            session.getTransaction().commit();

        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();

        } finally {
            session.close();
        }

        return adopciones;
    }
}
