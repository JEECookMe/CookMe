package cookMe.processing.validators;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

@FacesValidator("validator.age")
public class AgeValidator implements Validator, ClientValidator {

	@Override
	public void validate(FacesContext context, UIComponent component,
		Object value) throws ValidatorException {

	  String age = value.toString();

	  if (age == null || age.isEmpty()) {
			return;
	  }

	  if (Integer.parseInt(age) <= 0 || Integer.parseInt(age) > 100) {
		throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur d'age", 
                "L'age saisi n'est pas valide, il ne doit pas dépasser 100 et ne doit pas contenir des lettres"));
	  }

	}

	@Override
	public Map<String, Object> getMetadata() {
		return null;
	}

	@Override
	public String getValidatorId() {
		return "validator.age";
	}
}