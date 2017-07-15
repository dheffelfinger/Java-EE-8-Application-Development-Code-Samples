package net.ensode.javaee8book.jmsqueuebrowser;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.TextMessage;

@Named
@RequestScoped
public class MessageQueueBrowser {

    @Resource
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/JavaEE8BookQueue")
    private Queue queue;
    private static final Logger LOG = Logger.getLogger(MessageQueueBrowser.class.getName());

    public void browseMessages() {
        try {
            Enumeration messageEnumeration;
            TextMessage textMessage;
            JMSContext jmsContext = connectionFactory.createContext();
            QueueBrowser browser = jmsContext.createBrowser(queue);

            messageEnumeration = browser.getEnumeration();

            if (messageEnumeration != null) {
                if (!messageEnumeration.hasMoreElements()) {
                    LOG.log(Level.INFO, "There are no messages "
                            + "in the queue.");
                } else {
                    LOG.log(Level.INFO,
                            "The following messages are in the queue:");
                    while (messageEnumeration.hasMoreElements()) {
                        textMessage = (TextMessage) messageEnumeration.
                                nextElement();
                        LOG.log(Level.INFO, textMessage.getText());
                    }
                }
            }
        } catch (JMSException e) {
            LOG.log(Level.SEVERE, "JMS Exception caught", e);
        }
    }

    public static void main(String[] args) {
        new MessageQueueBrowser().browseMessages();
    }
}
