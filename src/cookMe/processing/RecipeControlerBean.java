package step5.processing;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;
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