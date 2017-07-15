package net.ensode.javaee8book.jsfjpa;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import net.ensode.javaee8book.Customer;
import net.ensode.javaee8book.CustomerDaoBean;

@Named
@RequestScoped
public class CustomerController implements Serializable {
    
    @EJB
    private CustomerDaoBean customerDaoBean;
    
    private Customer customer;
    
    private String firstName;
    private String lastName;
    private String email;
    
    public CustomerController() {
        customer = new Customer();
    }
    
    public String saveCustomer() {
        String returnValue = "customer_saved";
        
        try {
            populateCustomer();
            customerDaoBean.saveCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
            returnValue = "error_saving_customer";
        }
        
        return returnValue;
    }
    
    private void populateCustomer() {
        if (customer == null) {
            customer = new Customer();
        }
        customer.setFirstName(getFirstName());
        customer.setLastName(getLastName());
        customer.setEmail(getEmail());
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
    
}
