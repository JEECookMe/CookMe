package cookMe.processing.validators;

import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

@FacesValidator("validator.lastname")
public class LastnameValidator implements Validator, ClientValidator {

	@Override
	public void validate(FacesContext context, UIComponent component,
		Object value) throws ValidatorException {

	  String lastname = value.toString();

	  if (lastname == null || lastname.isEmpty() ) {
			return;
	  }

	  if (!Pattern.compile("[a-zA-Z0-9]+").matcher(lastname).matches()) {
		throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur de nom", 
                "Il contient un ou plusieurs caractères non autorisés"));
	  }

	}

	@Override
	public Map<String, Object> getMetadata() {
		return null;
	}

	@Override
	public String getValidatorId() {
		return "validator.lastname";
	}
}