/*
 * Product
 *
 * v. 1.0
 *
 * Copyright (c) 2019 Maksym Burko.
 */
package com.softserve.edu.entity;

import javax.persistence.*;

/**
 * Hibernate entity for oc_product table.
 */
@Entity
@Table(name = "oc_product")
public class Product {
    @Id @GeneratedValue
    @Column(name = "product_id")
    private int product_id;
    @Column(name = "model")
    private String model;
    @Column(name = "quantity")
    private int quantity;

    /**
     * Constructor of entity 'Product' with parameters.
     * @param empId id of product.
     * @param model product model.
     * @param quantity quantity of product.
     */
    public Product(int empId, String model, int quantity) {
        this.product_id = empId;
        this.model = model;
        this.quantity = quantity;
    }

    /**
     * Default constructor of entity 'Product'.
     */
    public Product() {
    }

    /**
     * Simple getter.
     *
     * @return product id.
     */
    public int getProduct_id() {
        return product_id;
    }

    /**
     * Simple setter.
     * @param product_id product id.
     */
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    /**
     * Simple getter.
     *
     * @return product model.
     */
    public String getModel() {
        return model;
    }

    /**
     * Simple setter.
     * @param model product model.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Simple getter.
     *
     * @return product quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Simple setter.
     * @param quantity product quantity.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
