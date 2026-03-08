package com.jpa_practice.test_hibernate;

import jakarta.persistence.*;
import java.util.List;

public class CustomerDAOImpl {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hibernatePU");

    public void insertCustomer(Customer customer) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(customer);
            tx.commit();
        } finally {
            em.close();
        }
    }

    public void updateCustomer(Customer customer) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(customer);
            tx.commit();
        } finally {
            em.close();
        }
    }

    public void deleteCustomer(int id) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Customer c = em.find(Customer.class, id);
            if (c != null) {
                em.remove(c);
            }
            tx.commit();
        } finally {
            em.close();
        }
    }

    public Customer getCustomerById(int id) {

        EntityManager em = emf.createEntityManager();
        Customer customer = em.find(Customer.class, id);
        em.close();
        return customer;
    }

    public List<Customer> getAllCustomers() {

        EntityManager em = emf.createEntityManager();

        TypedQuery<Customer> query =
                em.createQuery("SELECT c FROM Customer c", Customer.class);

        List<Customer> list = query.getResultList();
        em.close();

        return list;
    }

   
    public Customer getCustomerByEmail(String email) {

        EntityManager em = emf.createEntityManager();

        TypedQuery<Customer> query = em.createQuery(
                "SELECT c FROM Customer c WHERE c.email = :email",
                Customer.class);

        query.setParameter("email", email);

        Customer customer = query.getSingleResult();

        em.close();

        return customer;
    }
}
