package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import io.dropwizard.auth.Auth;
import java.util.Collection;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
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
import nl.hsleiden.model.CartItem;
import nl.hsleiden.service.CartService;

import nl.hsleiden.model.User;

/**
 * Meer informatie over resources:
 *  https://jersey.java.net/documentation/latest/user-guide.html#jaxrs-resources
 * 
 * @author Peter van Vliet
 */
@Singleton
@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
public class CartResource
{
    private final CartService service;
    
    @Inject
    public CartResource(CartService service)
    {
        this.service = service;
    }
    
    @POST
    @JsonView(View.Public.class)
    public int addCartItem(CartItem item) {
        
        System.out.println("Adding cartitem : " + item.toString());
        
         return this.service.addCartItem(item);
    }
    
    @PUT
    @JsonView(View.Public.class)
    public int updateCart(CartItem item) {
        
        System.out.println("Updating cartitem : " + item.toString());
        
        return this.service.updateCartItem(item);
    }
    
    @DELETE
    @Path("/{id}")
    @JsonView(View.Public.class)
    public void removeCartItem(@PathParam("id") int id) {
        
        System.out.println("removing id : " + id);
        
        this.service.deleteCartItem(id);
        
    }
    
    @DELETE
    @Path("empty/{id}")
    @JsonView(View.Public.class)
    public void emptyCart(@PathParam("id") int id) {
        
        System.out.println("user : " + id);
        
        this.service.emptyCart(id);
        
    }
    
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    public List<CartItem> getTotalCurrentProducts(@PathParam("id") int id) {
        
        System.out.println("getting items with id: " + id);
        
        return this.service.getAllCartProducts(id);
    }

    
//    @GET
//    @JsonView(View.Public.class)
////    @RolesAllowed("GUEST")
//    public Collection<User> retrieveAll()
//    {
//        return service.getAll();
//    }
//    
//    @GET
//    @Path("/{id}")
//    @JsonView(View.Public.class)
//    @RolesAllowed("GUEST")
//    public User retrieve(@PathParam("id") int id)
//    {
//        return service.get(id);
//    }
//    
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @JsonView(View.Protected.class)
//    public void create(@Valid User user)
//    {
//        service.add(user);
//    }
//    
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @JsonView(View.Protected.class)
//    public void update(@PathParam("id") int id, User user)
//    {
//        System.out.println("INSIDE THE USER RESOURCE PUT : " + user.getFirstName());
////        System.out.println("INSIDE THE USER RESOURCE PUT AUTHER : " + authenticator.getFirstName());
//        service.update( id, user);
//        System.out.println("AFTER THE SERVICE UPDATE IN THE USER RESOURCE");
//    }
//    
//    @DELETE
//    @Path("/{id}")
//    public void delete(@PathParam("id") int id)
//    {
//        System.out.println("deleted the person");
//        service.delete(id);
//    }
//    
//    @GET
//    @Path("/me")
//    @JsonView(View.Private.class)
//    public User authenticate(@Auth User authenticator)
//    {
//        return authenticator;
//    }
}
