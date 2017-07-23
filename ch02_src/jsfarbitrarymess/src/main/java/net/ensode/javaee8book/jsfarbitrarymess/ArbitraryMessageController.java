package net.ensode.javaee8book.jsfarbitrarymess;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ArbitraryMessageController {
    
    @Inject
    FacesContext facesContext;

    public void saveData() {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Data saved successfully", "All Data successfully saved.");
        facesContext.addMessage(null, facesMessage);
    }

}
