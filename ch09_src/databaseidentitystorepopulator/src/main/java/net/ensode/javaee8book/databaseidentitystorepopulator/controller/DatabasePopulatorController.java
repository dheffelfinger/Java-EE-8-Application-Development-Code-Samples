package net.ensode.javaee8book.databaseidentitystorepopulator.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import net.ensode.javaee8book.databaseidentitystorepopulator.dao.RoleFacade;
import net.ensode.javaee8book.databaseidentitystorepopulator.dao.UserFacade;
import net.ensode.javaee8book.databaseidentitystorepopulator.entity.Role;
import net.ensode.javaee8book.databaseidentitystorepopulator.entity.User;
import net.ensode.javaee8book.databaseidentitystorepopulator.model.LoginInfo;

@Named
@RequestScoped
public class DatabasePopulatorController {

    @PersistenceContext(unitName = "net.ensode.javaee8book_databaseidentitystorepopulator_war_1.0PU")
    private EntityManager em;

    @Inject
    private LoginInfo loginInfo;

    @Inject
    private UserFacade userFacade;

    @Inject
    private RoleFacade roleFacade;

    @Transactional
    private void saveData() {
        User user;
        List<Role> roleList = roleFacade.findByRoleNames(loginInfo.getRoleList());
        user = new User();
        user.setUsername(loginInfo.getUserName());
        user.setPassword(loginInfo.getClearTextPassword());
        user.setRoleCollection(roleList);

        userFacade.create(user);

    }

    public String populateUserInfo() {
        saveData();
        return "success";
    }
}
