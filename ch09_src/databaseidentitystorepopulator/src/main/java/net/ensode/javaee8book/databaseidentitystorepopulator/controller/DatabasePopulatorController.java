package net.ensode.javaee8book.databaseidentitystorepopulator.controller;

import java.util.List;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import net.ensode.javaee8book.databaseidentitystorepopulator.dao.RoleFacade;
import net.ensode.javaee8book.databaseidentitystorepopulator.dao.UserFacade;
import net.ensode.javaee8book.databaseidentitystorepopulator.entity.Role;
import net.ensode.javaee8book.databaseidentitystorepopulator.entity.User;
import net.ensode.javaee8book.databaseidentitystorepopulator.model.LoginInfo;
import net.ensode.javaee8book.databaseidentitystorepopulator.util.PasswordHasher;

@Named
@RequestScoped
public class DatabasePopulatorController {

    @Inject
    private LoginInfo loginInfo;
    @Inject
    private UserFacade userFacade;
    @Inject
    private RoleFacade roleFacade;
    @Inject
    private PasswordHasher passwordHasher;

    private boolean userValid(User user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        if (constraintViolations.isEmpty()) {
            return true;
        } else {
            //userId property has a @NotNull validation, the value will be set by JPA before inserting into the database
            //ignore this constraint since it will be dealt with.
            if (constraintViolations.size() == 1) {
                ConstraintViolation<User> constraintViolation = constraintViolations.iterator().next();
                if (constraintViolation.getPropertyPath().toString().equals("userId")
                        && constraintViolation.getMessage().equals("must not be null")) {
                    return true;
                }
            }
            constraintViolations.forEach((constraintViolation) -> {
                System.out.println(constraintViolation.getPropertyPath().toString() + " : " + constraintViolation.getMessage());
            });
            return false;
        }

    }

    @Transactional
    private boolean saveData() {
        User user;
        List<Role> roleList = roleFacade.findByRoleNames(loginInfo.getRoleList());
        user = new User();
        user.setUsername(loginInfo.getUserName());
        user.setPassword(passwordHasher.hashPassword(loginInfo.getClearTextPassword()));
        user.setRoleCollection(roleList);

        if (userValid(user)) {
            userFacade.create(user);
            return true;
        } else {
            return false;
        }

    }

    public String populateUserInfo() {
        if (saveData()) {
            return "success";
        } else {
            return "error";
        }
    }
}
