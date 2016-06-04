package cookMe.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class UserModelBean implements Serializable {
	private String firstName;
	private String lastName;
	private int age;
	private String email;
	private String login;
	private String pwd;

	public UserModelBean() {
	}

	public UserModelBean(String firstName, String lastName, int age,
			String email, String login, String pwd) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.login = login;
		this.pwd = pwd;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "UserModelBean [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", email=" + email
				+ ", login=" + login + ", pwd=" + pwd + "]";
	}
	
}

