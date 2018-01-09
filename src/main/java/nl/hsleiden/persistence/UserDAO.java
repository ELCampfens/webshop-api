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
public class UserDAO {

    private Database DB;
    private List<User> users;

    public UserDAO() {
        users = new ArrayList<>();
        DB = new Database();

        fillList();

    }

    public void fillList() {

        Connection conn = DB.getConnection();
        String query = "SELECT * FROM user";
        try {
            PreparedStatement allUsers = conn.prepareStatement(query);
            ResultSet rs = allUsers.executeQuery();

            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("firstname"), rs.getString("middlename"), rs.getString("lastname"), rs.getString("postcode"), rs.getString("streetnumber"), rs.getString("email"), rs.getString("password"), rs.getString("role")));
            }

            for (User user : users) {
                user.print();
            }

            System.out.println("AANTAL USERS : " + users.size());

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<User> getAll() {
        users.clear();
        fillList();
        return users;
    }

    public User get(int id) {
        try {
            return users.get(id);
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

    public User getByEmailAddress(String emailAddress) {
        Optional<User> result = users.stream()
                .filter(user -> user.getEmailAddress().equals(emailAddress))
                .findAny();

        return result.isPresent()
                ? result.get()
                : null;
    }

    public void add(User user) {
        try {

            Connection conn = DB.getConnection();
            String query = "INSERT INTO user(firstname, middlename, lastname, postcode, streetnumber, email, password, role)"
                    + "VALUE(?,?,?,?,?,?,?,?)";

            PreparedStatement add_user = conn.prepareStatement(query);

            add_user.setString(1, user.getFirstName());
            add_user.setString(2, user.getMiddleName());
            add_user.setString(3, user.getLastName());
            add_user.setString(4, user.getPostcode());
            add_user.setString(5, user.getStreetnumber());
            add_user.setString(6, user.getEmailAddress());
            add_user.setString(7, user.getPassword());
            add_user.setString(8, user.getRole());

            add_user.executeUpdate();

            DB.closeConnection(conn);

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        fillList();

//        users.add(user);
    }

    public void update(int id, User user) {
        System.out.println("INSIDE THE DAO UPDATE PART : " + user);
//        users.set(id, user);

        try {

            Connection conn = DB.getConnection();
            String query = "UPDATE user SET firstname=?, middlename=?, lastname=?, postcode=?, streetnumber=?, email=?, password=?"
                    + "WHERE id=?";

            PreparedStatement update_user = conn.prepareStatement(query);

            update_user.setString(1, user.getFirstName());
            update_user.setString(2, user.getMiddleName());
            update_user.setString(3, user.getLastName());
            update_user.setString(4, user.getPostcode());
            update_user.setString(5, user.getStreetnumber());
            update_user.setString(6, user.getEmailAddress());
            update_user.setString(7, user.getPassword());
            
            update_user.setInt(8, id);

            update_user.executeUpdate();
            
            System.out.println("UPDATED USER : " + user.getFirstName());

            DB.closeConnection(conn);

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        fillList();
    }

    public void delete(int id) {
//        // GOTTA MAKE IT FOR THE DB HERE
//        System.out.println("HI I AM ID : " + id + " IN THE DELETE FUNCTION OF THE USER DAO.");
//        System.out.println("AANTAL USERS IN DE DELETE : " + this.users.size());
//        
//        for(User user: this.users) {
//            System.out.println(user);
//        }
//        
//        users.remove(id);

        try {

            Connection conn = DB.getConnection();
            String query = "DELETE FROM user WHERE id = ?";

            PreparedStatement removeUser = conn.prepareStatement(query);

            removeUser.setInt(1, id);

            removeUser.executeUpdate();

            System.out.println("REMOVED THE USER!");

            DB.closeConnection(conn);

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
