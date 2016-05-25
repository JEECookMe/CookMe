package cookMe.processing;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class SplashScreen {
	private boolean renderDone;
	
	public void renderLotOfData() {
	    renderDone = false;
	    try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    renderDone = true;
	  }

	  public boolean isRenderDone() {
	    return this.renderDone;
	  }
}
