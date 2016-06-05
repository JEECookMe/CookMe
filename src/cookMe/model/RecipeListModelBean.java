package cookMe.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RecipeListModelBean {
	private List<RecipeModel> recipeList;

	public RecipeListModelBean() {
		recipeList = new ArrayList<RecipeModel>();
	}

	public void addRecipeList(RecipeModel recipe) {
		this.recipeList.add(recipe);
	}

	public List<RecipeModel> getRecipeList() {
		return recipeList;
	}
}