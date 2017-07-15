package net.ensode.javaee8book;

import javax.ejb.Stateless;

@Stateless
public class SimpleSessionBean implements SimpleSession{

    private final String message = "If you don't see this, it didn't work!";

    public String getMessage() {
        return message;
    }
}
