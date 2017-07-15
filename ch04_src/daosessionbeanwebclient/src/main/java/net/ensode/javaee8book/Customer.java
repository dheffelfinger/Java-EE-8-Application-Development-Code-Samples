package net.ensode.javaee8book;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMERS")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    private String email;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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

    @Override
    public String toString() {
        Long localCustomerId = customerId;
        String localFirstName = firstName;
        String localLastName = lastName;
        String localEmail = email;

        if (localCustomerId == null) {
            localCustomerId = 0L;
        }

        if (localEmail == null) {
            localEmail = "";
        }

        if (localFirstName == null) {
            localFirstName = "";
        }
        if (localLastName == null) {
            localLastName = "";
        }

        String toString = "customerId = " + customerId + "\n";
        toString += "firstName = " + localFirstName + "\n";
        toString += "lastName = " + localLastName + "\n";
        toString += "email = " + localEmail;

        return toString;
    }
}
