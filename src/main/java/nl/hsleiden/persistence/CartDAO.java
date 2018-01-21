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
import nl.hsleiden.model.CartItem;
import nl.hsleiden.model.User;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class CartDAO {

    private Database DB;
    private final List<CartItem> cartItems;

    public CartDAO() {
        cartItems = new ArrayList<>();
        DB = new Database();

    }

//    public void fillList() {
//
//        Connection conn = DB.getConnection();
//        String query = "SELECT * FROM user";
//        try {
//            PreparedStatement allUsers = conn.prepareStatement(query);
//            ResultSet rs = allUsers.executeQuery();
//
//            while (rs.next()) {
//                users.add(new User(rs.getInt("id"), rs.getString("firstname"), rs.getString("middlename"), rs.getString("lastname"), rs.getString("postcode"), rs.getString("streetnumber"), rs.getString("email"), rs.getString("password"), rs.getString("role")));
//            }
//
//            for (User user : users) {
//                user.print();
//            }
//
//            System.out.println("AANTAL USERS : " + users.size());
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public List<CartItem> getAllCartProducts(int id) {
       
        System.out.println("IN THE GETALLCARTPRODUCTS");
        Connection conn = DB.getConnection();
        String query = "SELECT * FROM cart WHERE user_id = ?";

        synchronized (cartItems) {

            try {

                cartItems.clear();

                PreparedStatement checkCartItem = conn.prepareStatement(query);
                checkCartItem.setInt(1, id);

                ResultSet rs = checkCartItem.executeQuery();

                while (rs.next()) {
                    this.cartItems.add(new CartItem(rs.getInt("user_id"), rs.getInt("product_id"), rs.getString("merk"), rs.getDouble("prijs"), rs.getString("img_link")));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            System.out.println("IN THE GETALLCARTPRODUCTS 2 : " + this.cartItems);
            System.out.println("IN THE GETALLCARTPRODUCTS 3 AANTAL : " + this.cartItems.size());
            return this.cartItems;
        }
    }

    public int addCartItem(CartItem item) {

        boolean firstProduct = this.firstTimeCartItem(item.getProductID());

        int amount = 0;

        Connection conn = DB.getConnection();
        String query = "INSERT INTO cart(user_id, product_id, merk, prijs, img_link) VALUES(?,?,?,?,?)";

        System.out.println(" going to add a product? : " + firstProduct);

        try {

            PreparedStatement addCartItem = conn.prepareStatement(query);

            addCartItem.setInt(1, item.getUserID());
            addCartItem.setInt(2, item.getProductID());
            addCartItem.setString(3, item.getMerk());
            addCartItem.setDouble(4, item.getPrice());
            addCartItem.setString(5, item.getImg());

            addCartItem.executeUpdate();

            DB.closeConnection(conn);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        amount = 1;

        return amount;
    }

    public int updateCartItem(CartItem item) {

        Connection conn = DB.getConnection();
        String query = "UPDATE cart SET product_aantal = ? WHERE product_id = ?;";

        int amount = this.getProductAmount(item.getProductID());

        int new_amount = 0;

        System.out.println("before the prepared statement");

        try {

            PreparedStatement updateCartItem = conn.prepareStatement(query);

            updateCartItem.setInt(1, amount + 1);
            updateCartItem.setInt(2, item.getProductID());

            new_amount = updateCartItem.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("updated, aount now = " + amount);

        return new_amount;
    }

    public void deleteCartItem(int id) {

        Connection conn = DB.getConnection();
        String query = "DELETE FROM cart WHERE product_id = ? LIMIT 1";

        try {

            PreparedStatement removeCartItem = conn.prepareStatement(query);
            
            removeCartItem.setInt(1, id);
            
            removeCartItem.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
        public void emptyCart(int id) {

        Connection conn = DB.getConnection();
        String query = "DELETE FROM cart WHERE user_id = ?";

        try {

            PreparedStatement emptyCart = conn.prepareStatement(query);
            
            emptyCart.setInt(1, id);
            
            emptyCart.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public int getProductAmount(int prod_id) {

        Connection conn = DB.getConnection();
        String query = "SELECT product_aantal FROM cart WHERE product_id = ?";

        int amount = 0;

        try {
            PreparedStatement getProdAmount = conn.prepareStatement(query);
            getProdAmount.setInt(1, prod_id);
            ResultSet rs = getProdAmount.executeQuery();

            if (rs.first()) {
                amount = rs.getInt("product_aantal");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return amount;
    }

    public boolean firstTimeCartItem(int product_id) {
        // This function is used in the addCartItem function.
        // This function will check if a product exists in the list already

        boolean addProduct = false;

        Connection conn = DB.getConnection();
        String query = "SELECT * FROM cart WHERE product_id = ?";

        try {

            PreparedStatement checkCartItem = conn.prepareStatement(query);
            checkCartItem.setInt(1, product_id);

            ResultSet rs = checkCartItem.executeQuery();

            if (rs.first()) {
                addProduct = false;
            } else {
                addProduct = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return addProduct;
    }

}
