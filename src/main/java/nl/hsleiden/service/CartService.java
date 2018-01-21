package nl.hsleiden.service;

import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import nl.hsleiden.model.CartItem;
import nl.hsleiden.model.User;
import nl.hsleiden.persistence.CartDAO;
import nl.hsleiden.persistence.UserDAO;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class CartService extends BaseService<User>
{
    private final CartDAO dao;
    
    @Inject
    public CartService(CartDAO dao)
    {
        this.dao = dao;
    }
    
    public int addCartItem(CartItem item) {
        
        return this.dao.addCartItem(item);
    }
    
    public int updateCartItem(CartItem item) {
        
        return this.dao.updateCartItem(item);
    }
    
    public void deleteCartItem(int id) {
        System.out.println("In the cart serivce delete : " + id);
        this.dao.deleteCartItem(id);
    }
    
    public List<CartItem> getAllCartProducts(int id) {
        return this.dao.getAllCartProducts(id);
    }
    
    public void emptyCart(int id) {
        this.dao.emptyCart(id);
    }
}
