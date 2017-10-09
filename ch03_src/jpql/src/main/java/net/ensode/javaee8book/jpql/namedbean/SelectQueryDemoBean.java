package net.ensode.javaee8book.jpql.namedbean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.ensode.javaee8book.jpql.entity.UsState;

@Named
@RequestScoped
public class SelectQueryDemoBean {

    @PersistenceContext
    private EntityManager entityManager;

    private Stream<UsState> matchingStatesStream;
    private List<UsState> matchingStatesList;

    public String findStates() {
        String retVal = "confirmation";

        try {
            Query query = entityManager
                    .createQuery(
                            "SELECT s FROM UsState s WHERE s.usStateNm "
                            + "LIKE :name");

            query.setParameter("name", "New%");
            
            matchingStatesStream = query.getResultStream();

            if (matchingStatesStream != null) {
                matchingStatesList = matchingStatesStream.collect(Collectors.toList());
            }

        } catch (Exception e) {
            retVal = "error";
            e.printStackTrace();
        }

        return retVal;
    }

    public List<UsState> getMatchingStatesList() {
        return matchingStatesList;
    }

    public void setMatchingStatesList(List<UsState> matchingStatesList) {
        this.matchingStatesList = matchingStatesList;
    }

}
