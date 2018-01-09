package nl.hsleiden.service;

import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import nl.hsleiden.model.User;
import nl.hsleiden.persistence.UserDAO;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class UserService extends BaseService<User>
{
    private final UserDAO dao;
    
    @Inject
    public UserService(UserDAO dao)
    {
        this.dao = dao;
    }
    
    public Collection<User> getAll()
    {
        return dao.getAll();
    }
    
    public User get(int id)
    {
        return requireResult(dao.get(id));
    }
    
    public void add(User user)
    {
//        user.setRole("GUEST");
        dao.add(user);
    }
    
    public void update(int id, User user)
    {
//        // Controleren of deze gebruiker wel bestaat
//        System.out.println("BEFORE THE CHECK OF THE OLD USER");
//        
////        User oldUser = get(id);
//        
//        System.out.println("AFTER THE CHECK OF THE OLD USER");
//        
//        if (!authenticator.hasRole("ADMIN"))
//        {
//            // Vaststellen dat de geauthenticeerde gebruiker
//            // zichzelf aan het aanpassen is
//            assertSelf(authenticator);
//        }
//        
//        System.out.println("INSIDE THE UPDATE USER SERVICE");
        
        dao.update(id, user);
    }
    
    public void delete(int id)
    {
        // Controleren of deze gebruiker wel bestaat
        System.out.println("HI I AM ID : " + id + " INSIDE THE USER SERVICE");
        User user = get(id);
        
        dao.delete(id);
    }
}
