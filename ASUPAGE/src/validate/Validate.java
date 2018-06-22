package validate;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

public class Validate implements Serializable {
	public static boolean validateNoHalfSizeKatakana(Object bean, ValidatorAction va, Field field,
			ActionMessages errors, Validator validator, HttpServletRequest request) {
		boolean bRet = true;
		boolean flgHankana = false;
		String value = null;

		if (isString(bean)) {
			value = (String) bean;
		} else {
			value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		}

		for (int i = 0; i < value.length(); i++) {
			if (isHalfSizeKatakana(value.charAt(i))) {
				flgHankana = true;
				break;
			}
		}
		if (flgHankana) {
			errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
			bRet = false;
		}
		return bRet;
	}
	
	protected static boolean isHalfSizeKatakana(char c) {
		return('\uff61' <= c && c <= '\uff9f');
	}

	protected static boolean isString(Object o) {
		return(o==null)? true : String.class.isInstance(o);
	}
	
	
	/*public static void validate(Object bean, ValidatorAction action, Field field, ActionErrors errors, HttpServletRequest request) {
		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		
		if(!GenericValidator.isBlankOrNull(value)) {
			
			if(!value.equals("a")) {
				errors.add(field.getKey(), Resources.(request, action, field));
			}
		}
	}*/

}
