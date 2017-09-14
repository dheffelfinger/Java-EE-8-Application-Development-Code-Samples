package com.ensode.javaee8book.jaxrsintro.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("customer")
public class CustomerResource {

  @GET
  @Produces(MediaType.TEXT_XML)
  public String getCustomer() {
    //in a "real" RESTful service, we would retrieve data from a database
    //then return an XML representation of the data.

    System.out.println("--- " + this.getClass().getCanonicalName()
        + ".getCustomer() invoked");

    return "<customer>\n"
        + "<id>123</id>\n"
        + "<firstName>Joseph</firstName>\n"
        + "<middleName>William</middleName>\n"
        + "<lastName>Graystone</lastName>\n"
        + "</customer>\n";
  }

  /**
   * Create a new customer
   * @param customerXML representation of the customer to create
   */
  @PUT
  @Consumes(MediaType.TEXT_XML)
  public void createCustomer(String customerXML) {
    //in a "real" RESTful service, we would parse the XML
    //received in the customer XML parameter, then insert
    //a new row into the database.

    System.out.println("--- " + this.getClass().getCanonicalName()
        + ".createCustomer() invoked");

    System.out.println("customerXML = " + customerXML);
  }

  @POST
  @Consumes(MediaType.TEXT_XML)
  public void updateCustomer(String customerXML) {
    //in a "real" RESTful service, we would parse the XML
    //received in the customer XML parameter, then update
    //a row in the database.

    System.out.println("--- " + this.getClass().getCanonicalName()
        + ".updateCustomer() invoked");

    System.out.println("customerXML = " + customerXML);
  }

  @DELETE
  @Consumes(MediaType.TEXT_XML)
  public void deleteCustomer(String customerXML) {
    //in a "real" RESTful service, we would parse the XML
    //received in the customer XML parameter, then delete
    //a row in the database.

    System.out.println("--- " + this.getClass().getCanonicalName()
        + ".deleteCustomer() invoked");

    System.out.println("customerXML = " + customerXML);
  }
}
