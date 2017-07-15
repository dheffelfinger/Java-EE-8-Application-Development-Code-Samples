package net.ensode.javaee8book;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

@Stateless
public class EjbTimerExampleBean implements EjbTimerExample
{
  private static Logger logger = Logger.getLogger(EjbTimerExampleBean.class
      .getName());
  @Resource
  TimerService timerService;

  public void startTimer(Serializable info)
  {
    Timer timer = timerService.createTimer(new Date(), 5000, info);
  }

  public void stopTimer(Serializable info)
  {
    Timer timer;
    Collection timers = timerService.getTimers();

    for (Object object : timers)
    {
      timer = ((Timer) object);

      if (timer.getInfo().equals(info))
      {
        timer.cancel();
        break;
      }
    }
  }

  @Timeout
  public void logMessage(Timer timer)
  {
    logger.info("This message was triggered by :" + timer.getInfo() + " at "
        + System.currentTimeMillis());
  }
}
