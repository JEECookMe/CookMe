package cookMe.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class UserSubmissionModelBean extends UserModelBean {
	
	String repwd;
	
	public UserSubmissionModelBean() {
	}
	
	public UserSubmissionModelBean(String firstname, String lastname, int age, String email, String login, String pwd, String repwd) {
        super(firstname, lastname, age, email, login, pwd);
        this.repwd = repwd;
    }

	public String getRepwd() {
		return repwd;
	}

	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}
	
}