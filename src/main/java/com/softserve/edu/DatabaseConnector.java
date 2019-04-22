/*
 * DatabaseConnector
 *
 * v. 1.0
 *
 * Copyright (c) 2019 Maksym Burko.
 */
package com.softserve.edu;

import com.softserve.edu.entity.Product;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Class for connection to database.
 */
public class DatabaseConnector {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    /**
     * Method which configure Hibernate connection
     * and initialize SessionFactory.
     */
    public void dbConnect() {

        Configuration config = new Configuration();
        config.configure();
        config.addAnnotatedClass(Product.class);
        config.addResource("Product.hbm.xml");
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        factory = config.buildSessionFactory(serviceRegistry);
    }

    /**
     * Simple getter method for SessionFactory.
     *
     * @return SessionFactory.
     */
    public static SessionFactory getFactory() {
        return factory;
    }

    /**
     * Method for SessionFactory closing.
     */
    public void dbClose() {
        factory.close();
    }

}
