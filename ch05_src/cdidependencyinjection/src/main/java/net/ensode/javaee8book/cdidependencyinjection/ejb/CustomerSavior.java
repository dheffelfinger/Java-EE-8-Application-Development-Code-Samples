package net.ensode.javaee8book.cdidependencyinjection.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import net.ensode.javaee8book.cdidependencyinjection.beans.Customer;

@Stateless
@LocalBean
@Named
public class CustomerSavior {

  private static final Logger logger = Logger.getLogger(
      CustomerSavior.class.getName());
  @Inject
  private Customer customer;

  public String saveCustomer() {

    logger.log(Level.INFO, "Saving the following information \n{0}", customer.
            toString());

    return "confirmation";
  }
}
