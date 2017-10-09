package net.ensode.javaee8book.beanvalidation.namedbean;

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
import javax.validation.ConstraintViolationException;
import net.ensode.javaee8book.beanvalidation.entity.Customer;

@Named
@RequestScoped
public class JpaBeanValidationDemoBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    public String beanValidationDemo() {
        String retVal = "confirmation";

        Customer customer = new Customer();
        Customer customer2 = new Customer();
        Customer customer3;

        customer.setCustomerId(10L);
        customer.setFirstName(null);
        customer.setLastName("McKenzie");
        customer.setEmail("jamesm@notreal.com");

        customer2.setCustomerId(11L);
        customer2.setFirstName("Charles");
        customer2.setLastName("Johnson");
        customer2.setEmail("cjohnson@phony.org");

        try {
            userTransaction.begin();
            entityManager.persist(customer);
            entityManager.persist(customer2);

            customer3 = entityManager.find(Customer.class, 4L);
            customer3.setLastName("Thispersonhasareallylonglastname");
            entityManager.persist(customer3);

            entityManager.remove(customer);

            userTransaction.commit();
        } catch (NotSupportedException |
                SystemException |
                SecurityException |
                IllegalStateException |
                RollbackException |
                HeuristicMixedException |
                HeuristicRollbackException |
                ConstraintViolationException e) {
            retVal = "error";
            e.printStackTrace();
        }

        return retVal;
    }

}
