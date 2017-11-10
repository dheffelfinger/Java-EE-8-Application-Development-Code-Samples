/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ensode.javaee8book.databaseidentitystorepopulator.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.ensode.javaee8book.databaseidentitystorepopulator.entity.Role;
import net.ensode.javaee8book.databaseidentitystorepopulator.entity.User;

/**
 *
 * @author heffel
 */
@Stateless
public class RoleFacade extends AbstractFacade<Role> {

    @PersistenceContext(unitName = "net.ensode.javaee8book_databaseidentitystorepopulator_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoleFacade() {
        super(Role.class);
    }

    public List<Role> findByRoleNames(List<String> roleStrList) {
        List<Role> roleList;
        Query query = em.createNamedQuery("Role.findByGroupNames", Role.class);

        query.setParameter("groupNames", roleStrList);
        roleList = query.getResultList();
        
        return roleList;
    }

}
