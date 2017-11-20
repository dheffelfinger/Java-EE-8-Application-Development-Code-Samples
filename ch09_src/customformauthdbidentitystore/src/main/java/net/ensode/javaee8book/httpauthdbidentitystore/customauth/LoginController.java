package net.ensode.javaee8book.httpauthdbidentitystore.customauth;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named
@RequestScoped
public class LoginController {

    @Inject
    private SecurityContext securityContext;

    @Inject
    private User user;

    public void login() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest httpServletRequest = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse httpServletResponse = (HttpServletResponse) externalContext.getResponse();
        UsernamePasswordCredential usernamePasswordCredential = new UsernamePasswordCredential(user.getUserName(), user.getPassword());

        AuthenticationParameters authenticationParameters = AuthenticationParameters.withParams().credential(usernamePasswordCredential);

        AuthenticationStatus authenticationStatus = securityContext.authenticate(httpServletRequest, httpServletResponse, authenticationParameters);

        if (authenticationStatus.equals(AuthenticationStatus.SEND_CONTINUE)) {
            facesContext.responseComplete();
        } else if (authenticationStatus.equals(AuthenticationStatus.SEND_FAILURE)) {
            FacesMessage facesMessage = new FacesMessage("Login error");
            facesContext.addMessage(null, facesMessage);
        }

    }
}
