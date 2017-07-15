package com.ensode.javaee8book.calendarbasedtimer;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;

@Stateless
@LocalBean
public class CalendarBasedTimerEjbExampleBean {

  private static Logger logger = Logger.getLogger(
      CalendarBasedTimerEjbExampleBean.class.getName());

  @Schedule(hour = "20", minute = "10")
  public void logMessage() {
    logger.info("This message was triggered at:"
        + System.currentTimeMillis());
  }
}
