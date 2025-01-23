package org.example.DAO;

import org.example.Util.HibernateUtil;
import org.example.entities.Animal;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
            return session.createQuery("FROM Animal", Animal.class).list();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de animales", e);
        }
    }

    /**
     * @param especie se le pasa el nombre de la especie
     * @return busca animales por especie
     */
    @Override
    public List<Animal> buscarPorEspecie(String especie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Animal a WHERE a.especie = :especie", Animal.class)
                    .setParameter("especie", especie)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar animales por especie", e);
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
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error al registrar un nuevo animal", e);
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
                transaction.commit();
            } else {
                throw new RuntimeException("Animal con ID " + idAnimal + " no encontrado");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error al actualizar el estado del animal", e);
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
                transaction.commit();

            } else {
                throw new RuntimeException("\n Animal con ID " + idAnimal + " no encontrado.");
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("\n Error al eliminar el animal: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Animal> buscarPorEstadoAdopcion(boolean adoptado) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Animal a WHERE a.adoptado = :adoptado", Animal.class)
                    .setParameter("adoptado", adoptado)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar animales por estado de adopción", e);
        }
    }

    /**
     * Busca un animal por su ID.
     *
     * @param idAnimal ID del animal a buscar.
     * @return El animal con el ID especificado, o null si no se encuentra.
     */
    @Override
    public Animal buscarAnimalPorId(Integer idAnimal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Animal animal = session.get(Animal.class, idAnimal);
        session.close();
        return animal;
    }


}
