package org.example.DAO;

import org.example.Util.HibernateUtil;
import org.example.entities.Animal;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class IAnimalDAOImpl implements IAnimalDAO {

    /**
     * @return obtiene todos los animales
     */
    @Override
    public List<Animal> obtenerAnimales() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("FROM Animal", Animal.class).list(); // Hibernate HQL query

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * @param especie se le pasa el nombre de la especie
     * @return busca animales por especie
     */
    @Override
    public List<Animal> buscarPorEspecie(String especie) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "FROM Animal WHERE Especie = :Especie";
            Query<Animal> query = session.createQuery(hql, Animal.class);
            query.setParameter("Especie", especie);
            return query.list();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * @param animal se le pasa el animal a guardar
     */
    @Override
    public void registrarAnimal(Animal animal) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(animal);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * @param idAnimal    se le pasa el id del animal a actualizar
     * @param nuevoEstado se le pasa el nuevo estado del animal
     */
    @Override
    public void actualizarEstado(Integer idAnimal, String nuevoEstado) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Animal animal = session.get(Animal.class, idAnimal);
            if (animal != null) {
                animal.setEstado(nuevoEstado);
                session.update(animal);
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * @param idAnimal se le pasa el id del animal a eliminar
     */
    @Override
    public void eliminarAnimal(Integer idAnimal) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Animal animal = session.get(Animal.class, idAnimal);
            if (animal != null) {
                session.delete(animal);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }
}
