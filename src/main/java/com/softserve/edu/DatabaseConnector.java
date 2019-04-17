package com.softserve.edu;

import com.softserve.edu.entity.Product;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DatabaseConnector {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public void dbConnect() {

        Configuration config = new Configuration();
        config.configure();
        config.addAnnotatedClass(Product.class);
        config.addResource("Product.hbm.xml");
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        factory = config.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getFactory() {
        return factory;
    }

}
