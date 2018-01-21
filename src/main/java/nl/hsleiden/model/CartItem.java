/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.security.Principal;
import nl.hsleiden.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Enzo
 */
public class CartItem {

    @NotEmpty
    @JsonView(View.Public.class)
    private int user_id;

    @NotEmpty
    @JsonView(View.Public.class)
    private int product_id;

    @NotEmpty
    @JsonView(View.Public.class)
    private String merk;

    @NotEmpty
    @JsonView(View.Public.class)
    private double prijs;

    @NotEmpty
    @JsonView(View.Public.class)
    private String img_link;

    public CartItem(int user_id, int product_id, String merk, double prijs, String img) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.merk = merk;
        this.prijs = prijs;
        this.img_link = img;
    }

    public void setUserID(int user_id) {
        this.user_id = user_id;
    }

    public int getUserID() {
        return this.user_id;
    }

    public void setProductID(int product_id) {
        this.product_id = product_id;
    }

    public int getProductID() {
        return this.product_id;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getMerk() {
        return this.merk;
    }

    public void setPrice(double prijs) {
        this.prijs = prijs;
    }

    public double getPrice() {
        return this.prijs;
    }

    public void setImg(String img) {
        this.img_link = img;
    }

    public String getImg() {
        return this.img_link;
    }

    public CartItem() {

    }

    public String toString() {
        return this.user_id + " " + this.product_id + " ";
    }
}
