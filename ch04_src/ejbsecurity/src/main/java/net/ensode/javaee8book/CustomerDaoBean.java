package net.ensode.javaee8book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Stateless
@RolesAllowed("appadmin")
public class CustomerDaoBean implements CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource(name = "jdbc/__CustomerDBPool")
    private DataSource dataSource;

    public void saveCustomer(Customer customer) {
        if (customer.getCustomerId() == null) {
            saveNewCustomer(customer);
        } else {
            updateCustomer(customer);
        }
    }

    public Long saveNewCustomer(Customer customer) {
        entityManager.persist(customer);

        return customer.getCustomerId();
    }

    public void updateCustomer(Customer customer) {
        entityManager.merge(customer);
    }

    @RolesAllowed(
            {"appuser", "appadmin"})
    public Customer getCustomer(Long customerId) {
        Customer customer;

        customer = entityManager.find(Customer.class, customerId);

        return customer;
    }

    public void deleteCustomer(Customer customer) {
        entityManager.remove(customer);
    }
}
