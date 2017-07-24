package net.ensode.javaee8book.jsfarbitrarymess;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ArbitraryMessageController implements Serializable {

    @Inject
    FacesContext facesContext;

    public void saveData() {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Data saved successfully", "All Data successfully saved.");
        facesContext.addMessage(null, facesMessage);
    }

}
