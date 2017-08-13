package net.ensode.javaee8book.jsfwebsocket;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class JsfWebSocketMessageSender implements Serializable {

    @Inject
    @Push
    private PushContext pushContext;

    public void send(String message) {
        System.out.println("Sending message: " + message);
        pushContext.send(message);
    }
}
