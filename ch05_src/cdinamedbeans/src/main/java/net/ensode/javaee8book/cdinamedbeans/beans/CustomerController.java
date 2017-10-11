package net.ensode.javaee8book.cdinamedbeans.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CustomerController {

  private static final Logger logger = Logger.getLogger(
      CustomerController.class.getName());
  @Inject
  private Customer customer;

  public String saveCustomer() {

    logger.log(Level.INFO, "Saving the following information \n{0}", customer.
            toString());

    return "confirmation";
  }
}
