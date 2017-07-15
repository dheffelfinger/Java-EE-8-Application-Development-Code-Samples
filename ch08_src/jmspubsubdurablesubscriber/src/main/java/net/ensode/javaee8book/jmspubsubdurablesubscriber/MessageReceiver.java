package net.ensode.javaee8book.jmspubsubdurablesubscriber;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Topic;

@Named
@ApplicationScoped
public class MessageReceiver {

    @Resource(mappedName = "jms/JavaEE8BookDurableConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/JavaEE8BookTopic")
    private Topic topic;
    private static final Logger LOG = Logger.getLogger(MessageReceiver.class.getName());

    public void receiveMessages() {
        String message;
        boolean goodByeReceived = false;

        JMSContext jmsContext = connectionFactory.createContext();
        JMSConsumer jMSConsumer = jmsContext.createDurableConsumer(topic, "Subscriber1");

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
