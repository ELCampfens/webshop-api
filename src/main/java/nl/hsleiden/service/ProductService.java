/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.service;

import java.util.Collection;
import javax.inject.Inject;
import nl.hsleiden.model.Product;

import nl.hsleiden.persistence.ProductDAO;

/**
 *
 * @author Enzo
 */
@javax.inject.Singleton
public class ProductService {

    private final ProductDAO dao;

    @Inject
    public ProductService(ProductDAO dao) {
        this.dao = dao;
    }

    public Collection<Product> getAll() {
        System.out.println("Size inside the product service : " + dao.getAll().size());
        return dao.getAll();
    }

    public Collection<Product> getProducten(int id) {
        System.out.println("Getting the specifik products");

        return dao.getProductByID(id);
    }

    public void delete(int id) {
        // Controleren of deze gebruiker wel bestaat
        System.out.println("HI I AM ID : " + id + " INSIDE THE PRODUCT SERVICE");

        dao.delete(id);
    }
    
    public void updateProduct(Product product) {
        dao.updateProduct(product);
    }
    
    public void addProduct(Product product) {
        
        System.out.println("INSIDE SERVICE PRODUCT ADD");
        dao.addProduct(product);
    }

}
