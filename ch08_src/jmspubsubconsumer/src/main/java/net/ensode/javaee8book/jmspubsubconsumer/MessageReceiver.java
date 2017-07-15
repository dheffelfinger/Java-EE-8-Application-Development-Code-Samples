package net.ensode.javaee8book.jmspubsubconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Topic;

@Named
@RequestScoped
public class MessageReceiver {

    @Resource
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/JavaEE8BookTopic")
    private Topic topic;
    private static final Logger LOG = Logger.getLogger(MessageReceiver.class.getName());

    public void receiveMessages() {
        String message;
        boolean goodByeReceived = false;

        JMSContext jmsContext = connectionFactory.createContext();
        JMSConsumer jMSConsumer = jmsContext.createConsumer(topic);

        LOG.log(Level.INFO, "Waiting for messages...");
        while (!goodByeReceived) {
            message = jMSConsumer.receiveBody(String.class);

            if (message != null) {
                System.out.print("Received the following message: ");
                LOG.log(Level.INFO, message);
                if (message.equals("Good bye!")) {
                    goodByeReceived = true;
                }
            }
        }
    }
}
