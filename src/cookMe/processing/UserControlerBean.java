package cookMe.processing;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.UserDao;
import cookMe.model.LoginBean;
import cookMe.model.UserModelBean;
import cookMe.model.UserSubmissionModelBean;

@ManagedBean
@ApplicationScoped
public class UserControlerBean {
	private UserDao userDao;
	public int loggedUserCounter;

	public UserControlerBean() {
		this.userDao = DaoFabric.getInstance().createUserDao();
		loggedUserCounter = 0;
	}

	public void checkUser(LoginBean loginBean) {
		UserModelBean user = this.userDao.checkUser(loginBean.getLogin(), loginBean.getPwd());
		if (user != null) {

			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			sessionMap.put("loggedUser", user);
			loggedUserCounter++;

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Connexion", "Connexion réussie");
	        FacesContext.getCurrentInstance().addMessage("msg", facesMsg);
		} else {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec de connexion", "Les identifiants saisies sont incorrects");
	        FacesContext.getCurrentInstance().addMessage("msg", facesMsg);
		}
	}

	public void checkAndAddUser(UserSubmissionModelBean userSubmitted) {
		String fn	=	userSubmitted.getFirstName();
		String ln	=	userSubmitted.getLastName();
		int a		=	userSubmitted.getAge();
		String e	=	userSubmitted.getEmail();
		String l	=	userSubmitted.getLogin();
		String p1	=	userSubmitted.getPwd();
		String p2	=	userSubmitted.getRepwd();
		
		if(		fn != null && Pattern.compile("[a-zA-Z0-9]+").matcher(fn).matches()
			&&	ln != null && Pattern.compile("[a-zA-Z0-9]+").matcher(ln).matches()
			&&	a > 0 && a < 100
			&&	e  != null && Pattern.compile("[a-zA-Z0-9-._]+@[a-zA-Z0-9-._]+.[a-z]+").matcher(e).matches()
			&&	l  != null && Pattern.compile("[a-zA-Z0-9-._]+").matcher(l).matches()
			&&	p1 != null && p2 != null && p1.equals(p2)
			&& !this.userDao.alreadyExist(l, e)
				){
			this.userDao.addUser(userSubmitted);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void logout() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("loggedUser", null);
		if(loggedUserCounter>0){
			loggedUserCounter--;
		}
	}

	public int getLoggedUserCounter() {
		return loggedUserCounter;
	}

	public void setLoggedUserCounter(int loggedUserCounter) {
		this.loggedUserCounter = loggedUserCounter;
	}
	
}