package com.ensode.javaee8book.jaxbxmlconversion.service;

import com.ensode.javaee8book.jaxbxmlconversion.entity.Customer;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author David R. Heffelfinger <dheffelfinger@ensode.com>
 */
@Path("customer")
public class CustomerResource {

  private Customer customer;

  public CustomerResource() {
    customer = new Customer(1L, "David",
        "Raymond", "Heffelfinger");
  }

  @GET
  @Produces(MediaType.TEXT_XML)
  public Customer getCustomer(Long customerId) {
    //in a "real" RESTful service, we would retrieve data from a database
    //then return an XML representation of the data.

    System.out.println("--- " + this.getClass().getCanonicalName()
        + ".getCustomer() invoked");

    return customer;
  }

  @POST
  @Consumes(MediaType.TEXT_XML)
  public void updateCustomer(Customer customer) {
    //in a "real" RESTful service, we would parse the XML
    //received in the customer XML parameter, then update
    //a row in the database.

    System.out.println("--- " + this.getClass().getCanonicalName()
        + ".updateCustomer() invoked");


    System.out.println("---- got the following customer: "
        + customer);
  }

  @PUT
  @Consumes(MediaType.TEXT_XML)
  public void createCustomer(Customer customer) {
    //in a "real" RESTful service, we would insert
    //a new row into the database with the data in the
    //customer parameter

    System.out.println("--- " + this.getClass().getCanonicalName()
        + ".createCustomer() invoked");

    System.out.println("customer = " + customer);

  }

  @DELETE
  @Consumes(MediaType.TEXT_XML)
  public void deleteCustomer(Customer customer) {
    //in a "real" RESTful service, we would delete a row
    //from the database corresponding to the customer parameter
    System.out.println("--- " + this.getClass().getCanonicalName()
        + ".deleteCustomer() invoked");

    System.out.println("customer = " + customer);
  }
}
