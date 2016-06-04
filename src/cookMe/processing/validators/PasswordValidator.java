package cookMe.processing.validators;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

@FacesValidator("validator.pwd")
public class PasswordValidator implements Validator, ClientValidator {

	@Override
	public void validate(FacesContext context, UIComponent component,
		Object value) throws ValidatorException {

	  String password = value.toString();
	  UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("confirmPassword");
	  String confirmPassword = uiInputConfirmPassword.getSubmittedValue().toString();

	  if (password == null || password.isEmpty() || confirmPassword == null
		|| confirmPassword.isEmpty()) {
			return;
	  }

	  if (!password.equals(confirmPassword)) {
		uiInputConfirmPassword.setValid(false);
		throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur de mot de passe", 
                "Les mots de passe ne sont pas identiques."));
	  }

	}

	@Override
	public Map<String, Object> getMetadata() {
		return null;
	}

	@Override
	public String getValidatorId() {
		return "validator.pwd";
	}
}