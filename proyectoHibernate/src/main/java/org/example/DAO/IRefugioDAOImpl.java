package org.example.DAO;

import org.example.Util.HibernateUtil;
import org.example.entities.Refugio;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class IRefugioDAOImpl implements IRefugioDAO {
    /**
     * @param refugio se le pasa el refugio a guardar
     */
    @Override
    public void registrarAdopcion(Refugio refugio) {
        // Abre una nueva sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Inicia una transacción
            transaction = session.beginTransaction();

            // Guarda el objeto refugio en la base de datos
            session.save(refugio);

            // Si todo sale bien, hace commit de la transacción
            transaction.commit();
        } catch (Exception e) {
            // Si algo sale mal, hace rollback de la transacción
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cierra la sesión
            session.close();
        }
    }

    /**
     * @param nombreRefugio
     * @return obtiene todas las adopciones del refugio
     */
    @Override
    public List<Refugio> obtenerAdopciones(String nombreRefugio) {
        // Abre una nueva sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Refugio> adopciones = null;

        try {
            // Crea una consulta para obtener todos los refugios con el nombre dado
            String hql = "FROM Refugio WHERE nombreRefugio = :nombreRefugio";
            Query<Refugio> query = session.createQuery(hql, Refugio.class);
            query.setParameter("nombreRefugio", nombreRefugio);

            // Ejecuta la consulta y guarda el resultado en la lista
            adopciones = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cierra la sesión
            session.close();
        }

        return adopciones;
    }

    /**
     * @param dni se le pasa el dni de la familia o representante
     * @return devuelve los animales acogidos por esa familia
     */
    @Override
    public Refugio buscarAdopcionPorDni(String dni) {
        // Abre una nueva sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Refugio refugio = null;

        try {
            // Crea una consulta para buscar un refugio con el DNI dado
            String hql = "FROM Refugio WHERE familia.dni = :dni";
            Query<Refugio> query = session.createQuery(hql, Refugio.class);
            query.setParameter("dni", dni);

            // Ejecuta la consulta y obtiene el único resultado
            refugio = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cierra la sesión
            session.close();
        }

        return refugio;
    }
}
