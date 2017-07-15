package net.ensode.javaee8book.asynchronousmethods;

import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

/**
 *
 * @author David R. Heffelfinger <dheffelfinger@ensode.com>
 */
@Stateless
public class AsynchronousSessionBean implements
    AsynchronousSessionBeanRemote {

  private static Logger logger = Logger.getLogger(
      AsynchronousSessionBean.class.getName());

  @Asynchronous
  @Override
  public void slowMethod() {
    long startTime = System.currentTimeMillis();
    logger.info("entering " + this.getClass().getCanonicalName()
        + ".slowMethod()");
    try {
      Thread.sleep(10000); //simulate processing for 10 seconds
    } catch (InterruptedException ex) {
      Logger.getLogger(AsynchronousSessionBean.class.getName()).
          log(Level.SEVERE, null, ex);
    }
    logger.info("leaving " + this.getClass().getCanonicalName()
        + ".slowMethod()");
    long endTime = System.currentTimeMillis();
    logger.info("execution took " + (endTime - startTime)
        + " milliseconds");
  }

  @Asynchronous
  @Override
  public Future<Long> slowMethodWithReturnValue() {

    try {
      Thread.sleep(15000); //simulate processing for 15 seconds
    } catch (InterruptedException ex) {
      Logger.getLogger(AsynchronousSessionBean.class.getName()).
          log(Level.SEVERE, null, ex);
    }

    return new AsyncResult<Long>(42L);
  }
}
