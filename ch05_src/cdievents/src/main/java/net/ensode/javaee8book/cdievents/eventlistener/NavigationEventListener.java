package net.ensode.javaee8book.cdievents.eventlistener;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import net.ensode.javaee8book.cdievents.event.NavigationInfo;

@SessionScoped
public class NavigationEventListener implements Serializable {

    public void handleNavigationEvent(@Observes NavigationInfo navigationInfo) {
        System.out.println("Navigation event fired");
        System.out.println("Page: " + navigationInfo.getPage());
        System.out.println("Customer: " + navigationInfo.getCustomer());
    }
}
