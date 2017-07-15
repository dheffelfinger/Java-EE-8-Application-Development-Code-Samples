package net.ensode.javaee8book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Stateless
public class CustomerDaoRollbackBean implements CustomerDaoRollback
{
  @Resource
  private EJBContext ejbContext;

  @PersistenceContext
  private EntityManager entityManager;

  @Resource(name = "jdbc/__CustomerDBPool")
  private DataSource dataSource;

  public void saveNewCustomer(Customer customer)
  {
    if (customer == null || customer.getCustomerId() != null)
    {
      ejbContext.setRollbackOnly();
    }
    else
    {
      customer.setCustomerId(getNewCustomerId());
      entityManager.persist(customer);
    }
  }

  public void updateCustomer(Customer customer)
  {
    if (customer == null || customer.getCustomerId() == null)
    {
      ejbContext.setRollbackOnly();
    }
    else
    {
      entityManager.merge(customer);
    }
  }

  public Customer getCustomer(Long customerId)
  {
    Customer customer;

    customer = entityManager.find(Customer.class, customerId);

    return customer;
  }

  public void deleteCustomer(Customer customer)
  {
    entityManager.remove(customer);
  }

  private Long getNewCustomerId()
  {
    Connection connection;
    Long newCustomerId = null;
    try
    {
      connection = dataSource.getConnection();
      PreparedStatement preparedStatement = connection
          .prepareStatement("select max(customer_id)+1 as new_customer_id from customers");

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet != null && resultSet.next())
      {
        newCustomerId = resultSet.getLong("new_customer_id");
      }
      connection.close();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return newCustomerId;
  }
}
