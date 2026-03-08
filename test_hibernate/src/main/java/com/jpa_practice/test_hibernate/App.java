package com.jpa_practice.test_hibernate;

import java.time.LocalDate;

public class App {

    public static void main(String[] args) {

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();

        Customer customer = new Customer(
                "Rahul Sharma",
                "rahul@gmail.com",
                "Male",
                9876543210L,
                LocalDate.now()
        );

        Order order = new Order(
                "ORD1001",
                "Laptop",
                1,
                75000,
                LocalDate.now()
        );

        customer.setOrder(order);
        order.setCustomer(customer);

        customerDAO.insertCustomer(customer);

        System.out.println("Customer inserted successfully");
    }
}
