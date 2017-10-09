package net.ensode.javaee8book.jpaintro.namedbean;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import net.ensode.javaee8book.jpaintro.entity.Customer;

/**
 *
 * @author heffel
 */
@Named
@RequestScoped
public class JpaDemoBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    public String updateDatabase() {

        String retVal = "confirmation";

        Customer customer = new Customer();
        Customer customer2 = new Customer();
        Customer customer3;

        customer.setCustomerId(3L);
        customer.setFirstName("James");
        customer.setLastName("McKenzie");
        customer.setEmail("jamesm@notreal.com");

        customer2.setCustomerId(4L);
        customer2.setFirstName("Charles");
        customer2.setLastName("Jonson");
        customer2.setEmail("cjohnson@phony.org");

        try {
            userTransaction.begin();
            entityManager.persist(customer);
            entityManager.persist(customer2);
            customer3 = entityManager.find(Customer.class, 4L);
            customer3.setLastName("Johnson");
            entityManager.persist(customer3);
            entityManager.remove(customer);

            userTransaction.commit();
        } catch (HeuristicMixedException |
                HeuristicRollbackException |
                IllegalStateException |
                NotSupportedException |
                RollbackException |
                SecurityException |
                SystemException e) {
            retVal = "error";
            e.printStackTrace();
        }

        return retVal;
    }
}
