/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.persistence;

import nl.hsleiden.model.Product;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enzo
 */
public class ProductDAO {

    private Database DB;
    private List<Product> allProducten;
    private List<Product> allSelectiveProducten;

    public ProductDAO() {
        System.out.println("inside productDAO constructor");
        allSelectiveProducten = new ArrayList<>();
        this.DB = new Database();
    }

    public List<Product> getAll() {
        
        System.out.println("inside productDAO getAll");

        Connection conn = DB.getConnection();
        allProducten = new ArrayList<>();
        String query = "SELECT * FROM producten";
        try {
            PreparedStatement allItems = conn.prepareStatement(query);
            ResultSet rs = allItems.executeQuery();
            
            
            while(rs.next()) {
                allProducten.add(new Product(rs.getInt("id") ,rs.getString("merk"), rs.getDouble("prijs"), rs.getString("img_link")));
            }
            
//            for(int i = 0; i < this.broeken.size(); i++) {
//                System.out.println(broeken.get(i).toString());
//            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return allProducten;

    }
    
    public List<Product> getProductByID(int id) {
       
        Connection conn = DB.getConnection();
        
        try {
            String query = "SELECT * FROM producten WHERE shop_id = ?";
            
            PreparedStatement selectiveProducten = conn.prepareStatement(query);
            selectiveProducten.setInt(1, id);
            
            ResultSet rs = selectiveProducten.executeQuery();
            
            allSelectiveProducten.clear();
            
            while(rs.next()) {
                allSelectiveProducten.add(new Product(rs.getInt("id") ,rs.getString("merk"), rs.getDouble("prijs"), rs.getString("img_link")));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return allSelectiveProducten;
    }
    
        public void delete(int id) {

        try {

            Connection conn = DB.getConnection();
            String query = "DELETE FROM producten WHERE id = ?";

            PreparedStatement removeProduct = conn.prepareStatement(query);

            removeProduct.setInt(1, id);

            removeProduct.executeUpdate();

            System.out.println("REMOVED THE product!");

            DB.closeConnection(conn);

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void updateProduct(Product product) {
        try {

            Connection conn = DB.getConnection();
            String query = "UPDATE producten SET merk=?, prijs=?, img_link=? WHERE id=?";

            PreparedStatement updateProduct = conn.prepareStatement(query);
            
            System.out.println("Gonna update prod #" + product.getShopID() + " with the following items : \n" + product.getMerk() + "\n" + product.getPrice() + "\n" + product.getImg());

            updateProduct.setString(1, product.getMerk());
            updateProduct.setDouble(2, product.getPrice());
            updateProduct.setString(3, product.getImg());
            updateProduct.setInt(4, product.getID());

            updateProduct.executeUpdate();

            System.out.println("UPDATED THE product!"); 

            DB.closeConnection(conn);

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addProduct(Product product) {
        System.out.println("ENTERD THE DAO PRODUCT ADD");
        
        try {

            Connection conn = DB.getConnection();
            String query = "INSERT INTO producten(merk, prijs, img_link, shop_id) "
                    + "VALUES(?,?,?,?)";

            PreparedStatement addProduct = conn.prepareStatement(query);

            addProduct.setString(1, product.getMerk());
            addProduct.setDouble(2, product.getPrice());
            addProduct.setString(3, product.getImg());
            addProduct.setInt(4, product.getShopID());

            addProduct.executeUpdate();

            System.out.println("UPDATED THE product!");

            DB.closeConnection(conn);

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
