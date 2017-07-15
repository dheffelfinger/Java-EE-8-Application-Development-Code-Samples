package net.ensode.javaee8book.jmsptpconsumer;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Named
@RequestScoped
public class MessageReceiver implements Serializable{

    @Resource
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/JavaEE8BookQueue")
    private Queue queue;
    private static final Logger LOG = Logger.getLogger(MessageReceiver.class.getName());
    

    public void receiveMessages() {
        String message;
        boolean goodByeReceived = false;

        JMSContext jmsContext = connectionFactory.createContext();
        JMSConsumer jMSConsumer = jmsContext.createConsumer(queue);

        LOG.log(Level.INFO, "Waiting for messages...");
        while (!goodByeReceived) {
            message = jMSConsumer.receiveBody(String.class);

            if (message != null) {
                LOG.log(Level.INFO, "Received the following message: {0}", message);
                if (message.equals("Good bye!")) {
                    goodByeReceived = true;
                }
            }
        }
    }
}
