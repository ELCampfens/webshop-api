/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.hsleiden.View;
import nl.hsleiden.model.Product;
import nl.hsleiden.service.ProductService;

/**
 *
 * @author Enzo
 */
@Singleton
@Path("/producten")
public class ProductResource
{
    private final ProductService service;
    
    @Inject
    public ProductResource(ProductService service)
    {
        System.out.println("hello world!");
        this.service = service;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    public Collection<Product> retrieveAll()
    {
        System.out.println("size inside the resource : " + service.getAll().size());
        return service.getAll();
    }
    
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    public Collection<Product> retrieve(@PathParam("id") int id)
    {
        return service.getProducten(id);
    }
    
    @POST
    @JsonView(View.Public.class)
    public void addProduct(Product product)
    {
        System.out.println("IN RESOURCE ADD");
         this.service.addProduct(product);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void updateProduct(@PathParam("id") int id, Product product)
    {
        this.service.updateProduct(product);
    }
    
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id)
    {
        System.out.println("deleted the product");
        service.delete(id);
    }
}