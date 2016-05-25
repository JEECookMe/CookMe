package cookMe.processing;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class SplashScreen implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean renderDone;
	
	@PostConstruct
	public void renderLotOfData() {
	    renderDone = false;
	    System.out.println("deb");
	    /*try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	    for(int i=0;i<999999;i++){System.out.println(i);}
	    System.out.println("fin");
	    renderDone = true;
	  }

	  public boolean isRenderDone() {
	    return this.renderDone;
	  }
}
