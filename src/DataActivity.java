package application;

import java.util.ArrayList;
import java.util.List;

public class DataActivity {
	private static final DataActivity instance = new DataActivity();
	List<Activity> activity = new ArrayList<>();
	
	public DataActivity() {
		
	}
	public static DataActivity getInstance() {
	        return instance;
	}

	public List<Activity> getActivity() {
	        return activity;
	}
}
