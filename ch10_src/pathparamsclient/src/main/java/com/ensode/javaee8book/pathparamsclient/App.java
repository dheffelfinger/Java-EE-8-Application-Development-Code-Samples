package com.ensode.javaee8book.pathparamsclient;

import com.ensode.javaee8book.pathparamsclient.entity.Customer;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class App {

    public static void main(String[] args) {
        App app = new App();
        app.getCustomer();
    }

    public void getCustomer() {
        Client client = ClientBuilder.newClient();
        Customer customer = client.target(
                "http://localhost:8080/pathparams/resources/customer").path("{id}").
                resolveTemplate("id", 1L).
                request().get(Customer.class);

        System.out.println("Received the following customer information:");
        System.out.println("Id: " + customer.getId());
        System.out.println("First Name: " + customer.getFirstName());
        System.out.println("Middle Name: " + customer.getMiddleName());
        System.out.println("Last Name: " + customer.getLastName());
    }
}
