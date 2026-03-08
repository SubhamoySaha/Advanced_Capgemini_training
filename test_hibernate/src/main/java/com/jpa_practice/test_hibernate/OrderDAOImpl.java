package com.jpa_practice.test_hibernate;

import jakarta.persistence.*;

public class OrderDAOImpl {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hibernatePU");

    public void updateOrder(Order order) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(order);
            tx.commit();
        } finally {
            em.close();
        }
    }

    public Order getOrderById(int id) {

        EntityManager em = emf.createEntityManager();
        Order order = em.find(Order.class, id);
        em.close();
        return order;
    }
}
