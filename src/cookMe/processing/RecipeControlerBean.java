package cookMe.processing;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.RecipesDao;
import cookMe.model.RecipeListModelBean;
import cookMe.model.RecipeModel;
import cookMe.model.RecipeSubmissionModelBean;

@ManagedBean
@ApplicationScoped
public class RecipeControlerBean {
	private boolean renderDone = false;

	  @PostConstruct
	  public void renderLotOfData() {
	    renderDone = false;
	    // do something which takes a long time here
	    renderDone = true;
	  }

	  public boolean isRenderDone() {
	    return this.renderDone;
	  }
}