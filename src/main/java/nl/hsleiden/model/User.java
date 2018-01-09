package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.security.Principal;
import nl.hsleiden.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Meer informatie over validatie: http://hibernate.org/validator/
 *
 * @author Peter van Vliet
 */
public class User implements Principal {
//    @NotEmpty
    @JsonView(View.Public.class)
    private int id;
    
    @NotEmpty
    @Length(min = 3, max = 100)
    @JsonView(View.Public.class)
    private String firstName;

    @JsonView(View.Public.class)
    private String middleName;

    @NotEmpty
    @Length(min = 3, max = 100)
    @JsonView(View.Public.class)
    private String lastName;
//    
    @NotEmpty
    @Length(min = 6, max = 7)
    @JsonView(View.Public.class)
    private String postcode;

    @NotEmpty
    @Length(min = 1, max = 10)
    @JsonView(View.Public.class)
    private String streetnumber;

    @NotEmpty
    @Email
    @JsonView(View.Public.class)
    private String emailAddress;

    @NotEmpty
    @Length(min = 8)
    @JsonView(View.Protected.class)
    private String password;

    @NotEmpty
    @JsonView(View.Public.class)
    private String role;

    public User(int id, String firstname, String middlename, String lastname,
            String postcode, String streetnumber, String email, String password, String role) {
        
        this.setID(id);
        this.setFirstName(firstname);
        this.setMiddleName(middlename);
        this.setLastName(lastname);
        this.setPostcode(postcode);
        this.setStreetnumber(streetnumber);
        this.setEmailAddress(email);
        this.setPassword(password);
        this.setRole(role);

    }

    public User() {

    }
    
    public int getID() {
        return id;
    }
    
    public void setID(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {

        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public String getFullName() {
//        return fullName;
//    }
//    
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    @JsonIgnore
    public String getName() {
        return firstName + " " + middleName + " " + lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean hasRole(String roleName) {
        if (role != null) {
            if (roleName.equals(role)) {
                return true;
            }
        }

        return false;
    }

    public boolean equals(User user) {
        return emailAddress.equals(user.getEmailAddress());
    }

    public void print() {
        System.out.println(this.id + " " + this.firstName + " " + this.middleName + " " + this.lastName + " " + this.postcode + " " + this.streetnumber + " " + this.emailAddress + " " + this.password + " " + this.role);
    }
}
