package net.ensode.javaee8book.conversationscope.controller;

import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.ensode.javaee8book.conversationscope.model.Customer;

@Named
@RequestScoped
public class CustomerInfoController implements Serializable {

    @Inject
    private Conversation conversation;
    @Inject
    private Customer customer;

    public String customerInfoEntry() {
        conversation.begin();
        System.out.println(customer);
        return "page1";
    }

    public String navigateToPage1() {
        System.out.println(customer);
        return "page1";
    }

    public String navigateToPage2() {
        System.out.println(customer);
        return "page2";
    }

    public String navigateToPage3() {
        System.out.println(customer);
        return "page3";
    }

    public String navigateToConfirmationPage() {
        System.out.println(customer);
        conversation.end();
        return "confirmation";
    }
}
