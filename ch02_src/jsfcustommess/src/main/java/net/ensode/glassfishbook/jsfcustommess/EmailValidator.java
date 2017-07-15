package net.ensode.glassfishbook.jsfcustommess;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.commons.lang.StringUtils;

@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {

  @Override
  public void validate(FacesContext facesContext,
      UIComponent uiComponent,
      Object value) throws ValidatorException {
    org.apache.commons.validator.EmailValidator emailValidator =
        org.apache.commons.validator.EmailValidator.getInstance();
    HtmlInputText htmlInputText = (HtmlInputText) uiComponent;

    String email = (String) value;

    if (!StringUtils.isEmpty(email)) {
      if (!emailValidator.isValid(email)) {
        FacesMessage facesMessage = new FacesMessage(htmlInputText.
            getLabel()
            + ": email format is not valid");
        throw new ValidatorException(facesMessage);
      }
    }
  }
}
