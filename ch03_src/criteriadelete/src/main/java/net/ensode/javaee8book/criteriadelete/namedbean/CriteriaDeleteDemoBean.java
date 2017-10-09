package net.ensode.javaee8book.criteriadelete.namedbean;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import net.ensode.javaee8book.criteriadelete.entity.Address;

@Named
@RequestScoped
public class CriteriaDeleteDemoBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    private int deletedRows;

    public String deleteData() {
        String retVal = "confirmation";

        try {

            userTransaction.begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaDelete<Address> criteriaDelete
                    = criteriaBuilder.createCriteriaDelete(Address.class);
            Root<Address> root = criteriaDelete.from(Address.class);
            criteriaDelete.where(criteriaBuilder.or(criteriaBuilder.equal(
                    root.get("city"), "New York"),
                    criteriaBuilder.equal(root.get("city"), "New York")));

            Query query = entityManager.createQuery(criteriaDelete);

            deletedRows = query.executeUpdate();
            userTransaction.commit();
        } catch (Exception e) {
            retVal = "error";
            e.printStackTrace();
        }
        return retVal;
    }

    public int getDeletedRows() {
        return deletedRows;
    }

    public void setDeletedRows(int updatedRows) {
        this.deletedRows = updatedRows;
    }
}
