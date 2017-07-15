package net.ensode.javaee8book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

@Stateless
@TransactionManagement(value = TransactionManagementType.BEAN)
public class CustomerDaoBmtBean implements CustomerDaoBmt
{
  @Resource
  private UserTransaction userTransaction;

  @PersistenceContext
  private EntityManager entityManager;

  @Resource(name = "jdbc/__CustomerDBPool")
  private DataSource dataSource;
  
  public void saveMultipleNewCustomers(List<Customer> customerList)
  {
    for (Customer customer : customerList)
    {
      try
      {
        userTransaction.begin();
        customer.setCustomerId(getNewCustomerId());
        entityManager.persist(customer);
        userTransaction.commit();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
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
