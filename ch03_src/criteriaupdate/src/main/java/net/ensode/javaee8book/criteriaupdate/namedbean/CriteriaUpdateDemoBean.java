package net.ensode.javaee8book.criteriaupdate.namedbean;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import net.ensode.javaee8book.criteriaupdate.entity.Address;
import net.ensode.javaee8book.criteriaupdate.entity.AddressType;
import net.ensode.javaee8book.criteriaupdate.entity.Customer;
import net.ensode.javaee8book.criteriaupdate.entity.UsState;

@Named
@RequestScoped
public class CriteriaUpdateDemoBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    private int updatedRows;

    public String updateData() {
        String retVal = "confirmation";

        try {

            userTransaction.begin();
            insertTempData();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Address> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Address.class);
            Root<Address> root = criteriaUpdate.from(Address.class);
            criteriaUpdate.set("city", "New York");
            criteriaUpdate.where(criteriaBuilder.equal(root.get("city"), "New Yorc"));

            Query query = entityManager.createQuery(criteriaUpdate);

            updatedRows = query.executeUpdate();
            userTransaction.commit();
        } catch (Exception e) {
            retVal = "error";
            e.printStackTrace();
        }
        return retVal;
    }

    public int getUpdatedRows() {
        return updatedRows;
    }

    public void setUpdatedRows(int updatedRows) {
        this.updatedRows = updatedRows;
    }

    private void insertTempData() throws NotSupportedException,
            SystemException, RollbackException, HeuristicMixedException,
            HeuristicRollbackException {
        Customer customer = entityManager.find(Customer.class, 4);

        AddressType homeAddressType = entityManager.find(AddressType.class, 1);
        AddressType mailingAddressType = entityManager.find(AddressType.class, 2);
        AddressType shippingAddressType = entityManager.find(AddressType.class, 3);

        UsState nyUsState = entityManager.find(UsState.class, 31);

        Address homeAddress = new Address(1, "Line 1 Home", "Line 2 Home", "New Yorc", "10453", nyUsState, customer, homeAddressType);
        Address mailingAddress = new Address(2, "Line 1 Mailing", "Line 2 Mailing", "New Yorc", "10453", nyUsState, customer, mailingAddressType);
        Address shippingAddress = new Address(3, "Line 1 Shipping", "Line 2 Shipping", "New Yorc", "10453", nyUsState, customer, shippingAddressType);

        entityManager.persist(homeAddress);
        entityManager.persist(mailingAddress);
        entityManager.persist(shippingAddress);

    }
}
