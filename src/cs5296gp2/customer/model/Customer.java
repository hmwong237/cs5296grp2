package cs5296gp2.customer.model;

import java.io.Serializable;

/**
 * JavaBean class used in jsp action tags.
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String contact_no;

    public Customer(String email) {
    	this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getContactNo() {
        return contact_no;
    }
    public void setContactNo(String contact_no) {
        this.contact_no = contact_no;
    }
}
