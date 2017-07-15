package net.ensode.javaee8book;

import javax.ejb.EJB;
import javax.naming.NamingException;

public class SessionBeanClient {

    @EJB
    private static SimpleSession simpleSession;

    private void invokeSessionBeanMethods() throws NamingException {
        System.out.println(simpleSession.getMessage());

        System.out.println("\nSimpleSession is of type: "
                + simpleSession.getClass().getName());
    }

    public static void main(String[] args) throws NamingException {
        new SessionBeanClient().invokeSessionBeanMethods();
    }
}
