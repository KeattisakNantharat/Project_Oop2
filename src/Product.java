package application;
import java.io.File;

public class Product {
	private File Urlpic;
	private String name;
	private Double cost;
	
	
	public Product(File U,String N,Double C) {
		Urlpic = U;
		name = N;
		cost = C;
	}
	
	public File getUrlpic() {
		return Urlpic;
	}
	public String getName() {
		return name;
	}
	public Double getCost() {
		return cost;
	}
	
}
