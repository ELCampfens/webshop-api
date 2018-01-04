/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.service;

import java.util.Collection;
import javax.inject.Inject;
import nl.hsleiden.model.Broek;

import nl.hsleiden.persistence.ProductDAO;

/**
 *
 * @author Enzo
 */
@javax.inject.Singleton
public class ProductService
{
    private final ProductDAO dao;
    
    @Inject
    public ProductService(ProductDAO dao)
    {
        this.dao = dao;
    }
    
    public Collection<Broek> getAll()
    {
        System.out.println("Size inside the product service : " + dao.getAll().size());
        return dao.getAll();
    }
    
}
