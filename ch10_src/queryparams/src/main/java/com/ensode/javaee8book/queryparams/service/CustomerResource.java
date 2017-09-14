package com.ensode.javaee8book.queryparams.service;

import com.ensode.javaee8book.queryparams.entity.Customer;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("customer")
public class CustomerResource {

    private Customer customer;

    public CustomerResource() {
        customer = new Customer(1L, "Samuel",
                "Joseph", "Willow");
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public Customer getCustomer(@QueryParam("id") Long id) {
        //in a "real" RESTful service, we would retrieve data from a database
        //using the supplied id.

        System.out.println("--- " + this.getClass().getCanonicalName()
                + ".getCustomer() invoked, id = " + id);
        return new Customer(id, "Dummy", null, "Customer");
    }

    /**
     * Create a new customer
     *
     * @param customer XML representation of the customer to create
     */
    @PUT
    @Consumes(MediaType.TEXT_XML)
    public void createCustomer(Customer customer) {
        //in a "real" RESTful service, we would parse the XML
        //received in the customer XML parameter, then insert
        //a new row into the database.

        System.out.println("--- " + this.getClass().getCanonicalName()
                + ".createCustomer() invoked");

        System.out.println("customer = " + customer);

    }

    @POST
    @Consumes(MediaType.TEXT_XML)
    public void updateCustomer(Customer customer) {
        //in a "real" RESTful service, we would parse the XML
        //received in the customer XML parameter, then update
        //a row in the database.

        System.out.println("--- " + this.getClass().getCanonicalName()
                + ".updateCustomer() invoked");

        System.out.println("customer = " + customer);

        System.out.println("customer= " + customer);
    }

    @DELETE
    @Consumes(MediaType.TEXT_XML)
    public void deleteCustomer(@QueryParam("id") Long id) {
        //in a "real" RESTful service, we would invoke
        //a DAO and delete the row in the database with the
        //primary key passed as the "id" parameter.

        System.out.println("--- " + this.getClass().getCanonicalName()
                + ".deleteCustomer() invoked, id = " + id);

        System.out.println("customer = " + customer);
    }
}
