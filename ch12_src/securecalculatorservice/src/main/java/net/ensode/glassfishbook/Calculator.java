package net.ensode.glassfishbook;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Calculator {

  @WebMethod
  public int add(int first, int second) {
    return first + second;
  }

  @WebMethod
  public int subtract(int first, int second) {
    return first - second;
  }
}
