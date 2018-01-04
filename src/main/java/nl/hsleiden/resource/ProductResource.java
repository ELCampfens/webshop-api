/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.hsleiden.View;
import nl.hsleiden.model.Broek;
import nl.hsleiden.model.User;
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
    public Collection<Broek> retrieveAll()
    {
        System.out.println("size inside the resource : " + service.getAll().size());
        return service.getAll();
    }
}