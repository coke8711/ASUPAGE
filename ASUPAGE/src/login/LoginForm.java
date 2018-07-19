package login;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class LoginForm extends ValidatorForm {
	private int adminId;
	private String firstName;
	private String lastName;
	private  Date registDate;
	private Date updateDate;
	private String email;
	private String passWord;
	private int loginMissCount;
	private String loginDate;
	private int Select = 0;


	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getLoginMissCount() {
		return loginMissCount;
	}
	public void setLoginMissCount(int loginMissCount) {
		this.loginMissCount = loginMissCount;
	}

	public int getSelect() {
		return Select;
	}
	public void setSelect(int select) {
		Select = select;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

}