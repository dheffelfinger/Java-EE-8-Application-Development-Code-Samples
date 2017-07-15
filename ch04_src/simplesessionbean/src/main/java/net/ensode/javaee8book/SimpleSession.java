package net.ensode.javaee8book;

import javax.ejb.Remote;

@Remote
public interface SimpleSession {
    public String getMessage();
}
