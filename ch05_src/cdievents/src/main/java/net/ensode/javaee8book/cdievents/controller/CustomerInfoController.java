package net.ensode.javaee8book.cdievents.controller;

import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import net.ensode.javaee8book.cdievents.event.NavigationInfo;
import net.ensode.javaee8book.cdievents.model.Customer;

@Named
@RequestScoped
public class CustomerInfoController implements Serializable {

    @Inject
    private Conversation conversation;
    @Inject
    private Customer customer;
    @Inject
    private Event<NavigationInfo> navigationInfoEvent;

    public String customerInfoEntry() {
        conversation.begin();
        NavigationInfo navigationInfo = new NavigationInfo();
        navigationInfo.setPage("1");
        navigationInfo.setCustomer(customer);

        navigationInfoEvent.fire(navigationInfo);
        return "page1";
    }

    public String navigateToPage1() {
        NavigationInfo navigationInfo = new NavigationInfo();
        navigationInfo.setPage("1");
        navigationInfo.setCustomer(customer);

        navigationInfoEvent.fire(navigationInfo);

        return "page1";
    }

    public String navigateToPage2() {
        NavigationInfo navigationInfo = new NavigationInfo();
        navigationInfo.setPage("2");
        navigationInfo.setCustomer(customer);

        navigationInfoEvent.fire(navigationInfo);
        return "page2";
    }

    public String navigateToPage3() {
        NavigationInfo navigationInfo = new NavigationInfo();
        navigationInfo.setPage("3");
        navigationInfo.setCustomer(customer);

        navigationInfoEvent.fire(navigationInfo);
        return "page3";
    }

    public String navigateToConfirmationPage() {
        NavigationInfo navigationInfo = new NavigationInfo();
        navigationInfo.setPage("confirmation");
        navigationInfo.setCustomer(customer);

        navigationInfoEvent.fire(navigationInfo);
        conversation.end();
        return "confirmation";
    }
}
