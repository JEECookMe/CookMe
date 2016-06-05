package cookMe.processing;

import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.UserDao;
import cookMe.model.LoginBean;
import cookMe.model.UserModelBean;
import cookMe.model.UserSubmissionModelBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Floe on 6/5/2016.
 */
@ManagedBean
@ApplicationScoped
public class AdminControlerBean {
    private UserDao userDao;


    public AdminControlerBean() {
        this.userDao = DaoFabric.getInstance().createUserDao();
    }

    public void CheckAdmin(LoginBean loginBean) {

        UserModelBean admin = this.userDao.checkUser(loginBean.getLogin(), loginBean.getPwd());
        if (admin != null && admin.getAdmin() == true) {

            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            sessionMap.put("loggedAdmin", admin);


            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Connexion", "Connexion réussie");
            FacesContext.getCurrentInstance().addMessage("msg", facesMsg);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec de connexion", "Echec de connexion");
            FacesContext.getCurrentInstance().addMessage("msg", facesMsg);
        }
    }

    public List<UserModelBean> getAllUser() {
        return userDao.getAllUser();
    }

    public void displayUser(UserModelBean user) {
        updateDisplay(user);

    }

    public void Delete(UserModelBean user) {
        userDao.deleteUser(user.getLogin());
    }

    public void insertOrUpdate(UserSubmissionModelBean userSubmissionModelBean) {
        if (userDao.alreadyExist(userSubmissionModelBean.getLogin(), userSubmissionModelBean.getEmail())) {
            userDao.updateUser(userSubmissionModelBean);
        } else {
            userDao.addUser(userSubmissionModelBean);
        }



    }
    public void addDisplay(){
        UserModelBean temp = new  UserModelBean();
        updateDisplay(temp);
    }
    private void updateDisplay(UserModelBean user ){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        UserSubmissionModelBean temp = new UserSubmissionModelBean(user.getFirstName(), user.getLastName(), user.getAge(), user.getEmail(), user.getLogin(), user.getPwd(), user.getPwd(), user.getAdmin());
        sessionMap.put("detailUser", temp);
    }

}
