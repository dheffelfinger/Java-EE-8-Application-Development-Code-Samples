package net.ensode.javaee8book.qualifiers.beans;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.ensode.javaee8book.qualifiers.Premium;

@Named
@RequestScoped
public class CustomerController {

    private static final Logger logger = Logger.getLogger(
            CustomerController.class.getName());
    @Inject
    @Premium
    private Customer customer;

    public String saveCustomer() {

        PremiumCustomer premiumCustomer = (PremiumCustomer) customer;

        logger.info("Saving the following information \n"
                + premiumCustomer.getFirstName() + " "
                + premiumCustomer.getLastName()
                + ", discount code = "
                + premiumCustomer.getDiscountCode());

        return "confirmation";
    }
}
