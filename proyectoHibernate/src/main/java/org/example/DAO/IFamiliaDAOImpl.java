package org.example.DAO;

import org.example.Util.HibernateUtil;
import org.example.entities.Familia;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class IFamiliaDAOImpl implements IFamiliaDAO {

    /**
     * @param familia se le pasa la familia a registrar
     */

    @Override
    public void registrarFamilia(Familia familia) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(familia);
            transaction.commit();
            System.out.println("\n Familia registrada exitosamente.");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\n Error al registrar la familia: " + e.getMessage());
        }
    }

    /**
     * @param dni se le pasa el DNI de la familia a buscar
     * @return devuelve la familia buscada
     */

    @Override
    public Familia buscarPorDNI(String dni) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query<Familia> query = session.createQuery("FROM Familia f WHERE f.dni = :dni", Familia.class);
            query.setParameter("dni", dni);
            return query.uniqueResult();

        } catch (Exception e) {
            System.out.println("\n Error al buscar la familia por DNI: " + e.getMessage());
            return null;
        }
    }

    /**
     * @return devuelve una lista contodas las familias
     */

    @Override
    public List<Familia> obtenerFamilias() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("FROM Familia", Familia.class).list();

        } catch (Exception e) {
            System.out.println("\n Error al obtener las familias: " + e.getMessage());
            return null;
        }
    }

    /**
     * @param dni se le pasa el DNI de la familia a eliminar
     */

    @Override
    public void eliminarFamilia(String dni) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Familia familia = buscarPorDNI(dni);

            if (familia != null) {
                session.delete(familia);
                transaction.commit();
                System.out.println("\n Familia eliminada exitosamente.");

            } else {
                System.out.println("\n Familia con DNI " + dni + " no encontrada.");
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\n Error al eliminar la familia: " + e.getMessage());
        }
    }
}
