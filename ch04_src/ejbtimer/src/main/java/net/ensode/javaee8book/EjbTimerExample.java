package net.ensode.javaee8book;

import java.io.Serializable;

import javax.ejb.Remote;
import javax.ejb.Timer;

@Remote
public interface EjbTimerExample
{
  public void startTimer(Serializable info);
  public void stopTimer(Serializable info);
  public void logMessage(Timer timer);
}