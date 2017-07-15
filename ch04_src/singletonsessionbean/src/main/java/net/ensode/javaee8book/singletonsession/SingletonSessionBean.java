package net.ensode.javaee8book.singletonsession;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.ensode.javaee8book.entity.UsStates;

@Singleton
public class SingletonSessionBean implements
    SingletonSessionBeanRemote {

  @PersistenceContext
  private EntityManager entityManager;
  private List<UsStates> stateList;

  @PostConstruct
  public void init() {
    Query query = entityManager.createQuery(
        "Select us from UsStates us");
    stateList = query.getResultList();
  }

  @Override
  public List<UsStates> getStateList() {
    return stateList;
  }
}
