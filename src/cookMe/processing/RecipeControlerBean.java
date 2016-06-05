package cookMe.processing;

import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.RecipesDao;
import cookMe.model.RecipeModelBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

@ManagedBean
@ApplicationScoped
public class RecipeControlerBean {
    private RecipesDao recipesDao;

    public  RecipeControlerBean() {
        this.recipesDao = DaoFabric.getInstance().createRecipesDao();

    }


    public void delete(RecipeModelBean recipeModelBean) {
        recipesDao.deleteRecipe(recipeModelBean.getId());

    }

    public List<RecipeModelBean> getAllRecipe() {
        return recipesDao.getAllRecipes();


    }

    public void addDisplay() {
        RecipeModelBean temp = new RecipeModelBean();
        updateDisplay(temp);
    }

    public void displayRecipe(RecipeModelBean recipeModelBean) {
        updateDisplay(recipeModelBean);

    }

    private void updateDisplay(RecipeModelBean recipeModelBean) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();

        sessionMap.put("detailRecipe", recipeModelBean);
    }
}