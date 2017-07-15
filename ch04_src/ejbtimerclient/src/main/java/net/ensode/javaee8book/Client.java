package net.ensode.javaee8book;

import net.ensode.javaee8book.EjbTimerExample;
import javax.ejb.EJB;

public class Client
{
  @EJB
  private static EjbTimerExample ejbTimerExample;

  public static void main(String[] args)
  {
    try
    {
      System.out.println("Starting timer 1...");
      ejbTimerExample.startTimer("Timer 1");
      System.out.println("Sleeping for 2 seconds...");
      Thread.sleep(2000);
      System.out.println("Starting timer 2...");
      ejbTimerExample.startTimer("Timer 2");
      System.out.println("Sleeping for 30 seconds...");
      Thread.sleep(30000);
      System.out.println("Stopping timer 1...");
      ejbTimerExample.stopTimer("Timer 1");
      System.out.println("Stopping timer 2...");
      ejbTimerExample.stopTimer("Timer 2");
      System.out.println("Done.");
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

}
