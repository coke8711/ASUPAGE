package join;

import org.apache.struts.validator.ValidatorForm;

public class UpdateActionForm extends ValidatorForm{
	
	private String email;
	private String password;
	private String firstname;
	private String lastname;
	private String newPassword1;
	private String newPassword2;

	public String getNewPassword1() {
		return newPassword1;
	}
	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}
	public String getNewPassword2() {
		return newPassword2;
	}
	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/*public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		if(newPassword1 == newPassword2) {
			errors.add("invaildPasswordError", new ActionMessage("error.invaildPassword1"));
		}
		
		return errors;
	}*/
	

}
