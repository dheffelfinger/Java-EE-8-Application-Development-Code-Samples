package net.ensode.javaee8book.jsfwebsocket;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class JsfWebSocketMessageSender {

    @Inject
    @Push
    private PushContext myChannel;

    private String chatWindowContents = "";

    public void send(String message) {
        System.out.println("Sending message: " + message);
        chatWindowContents += message + "\n";
        myChannel.send(message);
    }

    public String getChatWindowContents() {
        return chatWindowContents;
    }

    public void setChatWindowContents(String chatWindowContents) {
        this.chatWindowContents = chatWindowContents;
    }

}
