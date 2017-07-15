package net.ensode.javaee8book;

import javax.ejb.EJB;

public class Client
{
  @EJB
  private static CustomerDao customerDao;

  public static void main(String[] args)
  {
    Long newCustomerId;

    Customer customer = new Customer();
    customer.setFirstName("Mark");
    customer.setLastName("Butcher");
    customer.setEmail("butcher@phony.org");

    System.out.println("Saving New Customer...");
    newCustomerId = customerDao.saveNewCustomer(customer);

    System.out.println("Retrieving customer...");
    customer = customerDao.getCustomer(newCustomerId);
    System.out.println(customer);
  }
}
