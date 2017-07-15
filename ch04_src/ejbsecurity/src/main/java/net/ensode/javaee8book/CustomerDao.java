package net.ensode.javaee8book;

import javax.ejb.Remote;

@Remote
public interface CustomerDao
{

  public void saveCustomer(Customer customer);
  
  public Long saveNewCustomer(Customer customer);
  
  public void updateCustomer(Customer customer);

  public Customer getCustomer(Long customerId);

  public void deleteCustomer(Customer customer);

}