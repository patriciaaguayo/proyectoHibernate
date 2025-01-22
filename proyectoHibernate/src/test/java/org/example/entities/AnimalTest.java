package org.example.entities;

import org.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    @Test

    public void createTables(){
       /* SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Animal animal1 = new Animal("Paquito", 2, "Pajaro", "Se cayó del nido y estaba lesionado.", "Recién abandonado");
        Animal animal2 = new Animal("Salem", 1, "Gato", "Fue abandonado en la basura.", "Proximamente en acogida");

        // Transacción

        session.beginTransaction();

        // Guarda en mi bbdd

        session.persist(animal1);
        session.persist(animal2);

        // Commiteamos la transacción

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();*/

    }

}