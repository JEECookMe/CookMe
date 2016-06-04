package cookMe.model;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class LoggedUserCounter implements Serializable {
	private int count;
	
	public LoggedUserCounter() {
		count = 0;
	}
	
	public void add(){
		this.count++;
	}
	
	public void delete(){
		this.count--;
	}
	
	public int getCount(){
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}

