package step5.processing;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import step5.dao.fabric.DaoFabric;
import step5.dao.instance.RecipesDao;
import step5.model.RecipeListModelBean;
import step5.model.RecipeModel;
import step5.model.RecipeSubmissionModelBean;

@ManagedBean
@ApplicationScoped
public class RecipeControlerBean {
	private RecipesDao recipeDao;

	public RecipeControlerBean() {
		this.recipeDao = DaoFabric.getInstance().createRecipesDao();
	}

	public void loadAllRecipe() {
		ArrayList<RecipeModel> list = this.recipeDao.getAllRecipes();
		RecipeListModelBean recipeList = new RecipeListModelBean();
		for (RecipeModel recipe : list) {
			recipeList.addRecipeList(recipe);
		}

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();

		sessionMap.put("recipeList", recipeList);
	}

	public String addRecipe(RecipeModel recipe) {
		recipeDao.addRecipe(recipe);
		return "successfulRegister.xhtml";
	}

	public String searchRecipe(RecipeSubmissionModelBean recipe) {
		
		RecipeListModelBean list  = new RecipeListModelBean(); 
		for (RecipeModel r : this.recipeDao.getAllRecipes()) {
			if ((recipe.getDescription()==null || recipe.getDescription()==r.getDescription()) && (recipe.getDuration()==0 || recipe.getDuration()==r.getDuration()) && (recipe.getExpertise()==0 || recipe.getExpertise()==r.getExpertise()) && (recipe.getNbpeople()==0 || recipe.getNbpeople()==r.getNbpeople()) && recipe.getTitle()==null || recipe.getTitle() == r.getTitle() && (recipe.getType()==null || recipe.getType()==r.getType())) {
				list.addRecipeList(r);
			}
		}
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();

		sessionMap.put("listRecipeSearch", list);
		
		return "recipeResultList.xhtml";
	}

	public String displayRecipeDetail(RecipeModel recipe) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();

		sessionMap.put("detailRecipe", recipe);
		return "recipeDetail.xhtml";
	}
}