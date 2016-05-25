package step5.processing;

import java.util.Map;

import javax.annotation.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import step5.dao.fabric.DaoFabric;
import step5.dao.instance.UserDao;
import step5.model.LoginBean;
import step5.model.UserModelBean;
import step5.model.UserSubmissionModelBean;

@ManagedBean
@ApplicationScoped
public class UserControlerBean {
	private UserDao userDao;

	public UserControlerBean() {
		this.userDao = DaoFabric.getInstance().createUserDao();
	}

	public String checkUser(LoginBean loginBean) {
		UserModelBean user = this.userDao.checkUser(loginBean.getLogin(),
				loginBean.getPwd());
		if (user != null) {

			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();

			sessionMap.put("loggedUser", user);
			// redirect the current page
			return "userdisplay.xhtml";
		} else {
			// redirect the current page
			return "userLogin.xhtml";
		}
	}

	public void checkAndAddUser(UserSubmissionModelBean userSubmitted) {
		if (userSubmitted.getAge() > 0 && userSubmitted.getAge() < 200) {
			this.userDao.addUser(userSubmitted);
		}
	}
}