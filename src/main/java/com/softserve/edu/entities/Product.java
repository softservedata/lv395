package com.softserve.edu.entities;

import javax.persistence.*;

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

    public Product(int empId, String model, int quantity) {
        this.product_id = empId;
        this.model = model;
        this.quantity = quantity;
    }

    public Product() {
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
