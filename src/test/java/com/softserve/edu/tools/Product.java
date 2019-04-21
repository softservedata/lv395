package com.softserve.edu.tools;

import java.util.List;
import java.util.Objects;

/**
 * Product class.
 * This class was created to define product with
 * name and category, this object is often use in tests.
 *
 * @author Iryna Ratushniak
 */
public class Product {
    /**
     * name of product.
     */
    private String name;
    /**
     * categories, connected with product.
     */
    private List<String> categories;

    /**
     * constructor without parameters.
     */
    public Product() {
    }

    /**
     * This method is for getting name of product.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This method is for changing name of product.
     *
     * @param name - new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This name is for getting categories of product.
     *
     * @return set of category
     */
    public List<String> getCategory() {
        return categories;
    }

    /**
     * This method is for changing set of category
     *
     * @param categories -new categories
     */
    public void setCategory(List<String> categories) {
        this.categories = categories;
    }

    /**
     * This method is to define: are products equal or not.
     *
     * @param o - object that we will compare with
     * @return - result : are objects equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(name, product.name)
                && Objects.equals(categories, product.categories);
    }

    /**
     * Method to override hash code that will have object.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, categories);
    }

    /**
     * Method will do string from over object.
     *
     * @return string
     */
    @Override
    public String toString() {
        return "Product{"
                + "name='" + name + '\''
                + ", category=" + categories
                + '}';
    }
}
