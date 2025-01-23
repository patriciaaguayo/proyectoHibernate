package org.example.DAO;

import org.example.Util.HibernateUtil;
import org.example.entities.Animal;
import org.example.entities.Familia;
import org.example.entities.Refugio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class IRefugioDAOImpl implements IRefugioDAO {

    private SessionFactory sessionFactory;

    /**
     * @param refugio
     */
    @Override
    public void guardarRefugio(Refugio refugio) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(refugio);
        transaction.commit();
        session.close();
    }

    /**
     * @param animal
     */
    @Override
    public void actualizarAnimal(Animal animal) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(animal);
        transaction.commit();
        session.close();
    }

    /**
     * @return
     */
    @Override
    public List<Familia> obtenerFamilias() {
        Session session = sessionFactory.openSession();
        Query<Familia> query = session.createQuery("FROM Familia", Familia.class);
        List<Familia> familias = query.list();
        session.close();
        return familias;
    }

    /**
     * @return
     */
    @Override
    public List<Animal> obtenerAnimalesDisponibles() {
        Session session = sessionFactory.openSession();
        Query<Animal> query = session.createQuery("FROM Animal WHERE adoptado = false", Animal.class);
        List<Animal> animales = query.list();
        session.close();
        return animales;
    }
}
