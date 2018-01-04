/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.persistence;

import nl.hsleiden.model.Broek;
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
    private List<Broek> broeken;

    public ProductDAO() {
        System.out.println("inside productDAO constructor");
        this.DB = new Database();
    }

    public List<Broek> getAll() {
        
        System.out.println("inside productDAO getAll");

        Connection conn = DB.getConnection();
        broeken = new ArrayList<>();
        String query = "SELECT * FROM broeken";
        try {
            PreparedStatement allBroeken = conn.prepareStatement(query);
            ResultSet rs = allBroeken.executeQuery();
            
            
            while(rs.next()) {
                broeken.add(new Broek(rs.getInt("id") ,rs.getString("merk"), rs.getDouble("prijs"), rs.getString("img_link")));
            }
            
//            for(int i = 0; i < this.broeken.size(); i++) {
//                System.out.println(broeken.get(i).toString());
//            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return broeken;

    }

}
