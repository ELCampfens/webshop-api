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
public class Broek {
    
    @NotEmpty
    @JsonView(View.Public.class)
    private int id;
    
    @NotEmpty
    @Length(min = 0, max = 150)
    @JsonView(View.Public.class)
    private String merk;
    
    @NotEmpty
    @Length(min = 3, max = 100)
    @JsonView(View.Public.class)
    private double prijs;
    
    @NotEmpty
    @JsonView(View.Public.class)
    private String img;
    
    public int getID() {
        return this.id;
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
        this.img = img;
    }
    
    public String getImg() {
        return this.img;
    }
    
    public Broek(int id, String merk, double prijs, String img) {
        this.id = id;
        this.merk = merk;
        this.prijs = prijs;
        this.img = img;
    }
    
    public String toString() {
        return this.id + " " + this.merk + " " + this.prijs + " " + this.img;
    }
}
