package net.ensode.javaee8book.jsonbjavatojson.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import net.ensode.javaee8book.jsonbjavatojson.dto.Customer;

@Path("/customersearchcontroller")
public class CustomerSearchControllerService {
    private final List<Customer> customerList = new ArrayList<>();

    @GET
    @Path("{firstName}")
    public Response getCustomerByFirstName(@PathParam("firstName") String firstName) {
        List<Customer> filteredCustomerList;
        String jsonString;
        
        initializeCustomerList();

        Jsonb jsonb = JsonbBuilder.create();

        filteredCustomerList = customerList.stream().filter(
                customer -> customer.getFirstName().equals(firstName)).
                collect(Collectors.toList());

        jsonString = jsonb.toJson(filteredCustomerList);

        return Response.ok(jsonString).build();
    }


    private void initializeCustomerList() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        customerList.add(new Customer("Mr", "David", null, "Delabassee", LocalDate.parse("01/01/1997", dateTimeFormatter)));
        customerList.add(new Customer("Mr", "Michael", "Nascimento", "Santos", LocalDate.parse("02/02/1997", dateTimeFormatter)));
        customerList.add(new Customer("Mr", "David", "Raymond", "Heffelfinger", LocalDate.parse("03/03/1997", dateTimeFormatter)));
    }

}
