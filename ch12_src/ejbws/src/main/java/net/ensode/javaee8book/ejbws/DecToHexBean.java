package net.ensode.javaee8book.ejbws;

import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService
public class DecToHexBean {

  public String convertDecToHex(Integer i) {
    return Integer.toHexString(i);
  }
}
