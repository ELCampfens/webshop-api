package nl.hsleiden.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Singleton;
import nl.hsleiden.model.User;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class UserDAO
{
    private Database DB;
    private List<User> users;
    
    public UserDAO()
    {   
        users = new ArrayList<>();
        DB = new Database();
        
        fillList();

    }
    
    public void fillList() {
                
        System.out.println("inside productDAO getAll");
        
//        users.clear();
        
        Connection conn = DB.getConnection();
        String query = "SELECT * FROM user";
        try {
            PreparedStatement allUsers = conn.prepareStatement(query);
            ResultSet rs = allUsers.executeQuery();
            
            
            while(rs.next()) {
//                broeken.add(new Broek(rs.getInt("id") ,rs.getString("merk"), rs.getDouble("prijs"), rs.getString("img_link")));
                users.add(new User(rs.getString("firstname"), rs.getString("middlename"), rs.getString("lastname"), rs.getString("postcode"), rs.getString("streetnumber"), rs.getString("email"), rs.getString("password")));
            }
            
            System.out.println("AANTAL USERS : " + users.size());
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<User> getAll()
    {     
        users.clear();
        fillList();
        return users;
    }
    
    public User get(int id)
    {
        try
        {
            return users.get(id);
        }
        catch(IndexOutOfBoundsException exception)
        {
            return null;
        }
    }
    
    public User getByEmailAddress(String emailAddress)
    {
        Optional<User> result = users.stream()
            .filter(user -> user.getEmailAddress().equals(emailAddress))
            .findAny();
        
        return result.isPresent()
            ? result.get()
            : null;
    }
    
    public void add(User user)
    {
        
        
        
        
        
        users.add(user);
    }
    
    public void update(int id, User user)
    {
        users.set(id, user);
    }
    
    public void delete(int id)
    {
        users.remove(id);
    }
}
