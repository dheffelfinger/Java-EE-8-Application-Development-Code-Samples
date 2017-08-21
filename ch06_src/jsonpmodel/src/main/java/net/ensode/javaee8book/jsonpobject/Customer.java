package net.ensode.javaee8book.jsonpobject;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Customer implements Serializable {

    private String firstName;
    private String lastName;
    private String email;

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
        String localFirstName = firstName;
        String localLastName = lastName;
        String localEmail = email;

        if (localEmail == null) {
            localEmail = "";
        }

        if (localFirstName == null) {
            localFirstName = "";
        }
        if (localLastName == null) {
            localLastName = "";
        }

        String toString;
        toString = "firstName = " + localFirstName + "\n";
        toString += "lastName = " + localLastName + "\n";
        toString += "email = " + localEmail;

        return toString;
    }
}
