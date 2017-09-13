package com.ensode.jaxrsintroclient;

import com.ensode.jaxbxmlconversion.entity.Customer;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;


public class App {

    public static void main(String[] args) {
        App app = new App();
        app.insertCustomer();
    }

    public void insertCustomer() {
        Client client = ClientBuilder.newClient();
        Customer customer = new Customer(234L, "Tamara", "A",
                "Graystone");
        client.target(
                "http://localhost:8080/jaxbxmlconversion/resources/customer").
                request().put(
                        Entity.entity(customer, "text/xml"),
                        Customer.class);
    }
}
