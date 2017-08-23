package net.ensode.javaee8book.jaxrs21example.service;

import net.ensode.javaee8book.jaxrs21example.dto.Customer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customercontroller")
public class CustomerControllerService {

    private static final Logger LOG = Logger.getLogger(CustomerControllerService.class.getName());

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(String customerJson) {
        Response response;
        Jsonb jsonb = JsonbBuilder.create();

        Customer customer = jsonb.fromJson(customerJson, Customer.class);
        LOG.log(Level.INFO, "Customer object populated from JSON");
        LOG.log(Level.INFO, String.format("%s %s %s %s %s", customer.getSalutation(),
                customer.getFirstName(),
                customer.getMiddleName(),
                customer.getLastName(),
                customer.getDateOfBirth()));

        response = Response.ok("{}").build();

        return response;
    }

}
