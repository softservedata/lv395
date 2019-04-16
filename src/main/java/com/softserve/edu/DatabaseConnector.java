package com.softserve.edu;

import com.softserve.edu.entities.Product;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
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

    public int getProductQuantity(int product_id) {
        Session session = factory.openSession();
        Criteria userCriteria = session.createCriteria(Product.class);
        userCriteria.add(Restrictions.eq("product_id", product_id));
        Product product = (Product) userCriteria.uniqueResult();
        session.close();
        return product.getQuantity();
    }

}
