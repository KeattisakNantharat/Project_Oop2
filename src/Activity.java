package application;
import java.io.File;

import javafx.scene.control.TextField;

public class Activity {
	private File UrlPic;
	private String InfoActivity;
	private String InfoPlace;
	private String InfoOther;
	
	public Activity(File U,String A,String P,String O) {
		UrlPic = U;
		InfoActivity = A;
		InfoPlace = P;
		InfoOther = O;
	}
	
	public File getUrlPic() {
		return UrlPic;
	}
	public String getInfoActivity() {
		return InfoActivity;
	}
	public String getInfoPlace() {
		return InfoPlace;
	}
	public String getInfoOther () {
		return InfoOther;
	}
}
