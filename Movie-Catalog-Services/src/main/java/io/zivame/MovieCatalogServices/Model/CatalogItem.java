package io.zivame.MovieCatalogServices.Model;

public class CatalogItem {
	
	private String name;
	
	private String desc;
	
	private String rating;
	
	

	public CatalogItem() {
		super();
	}

	public CatalogItem(String name, String desc, String rating) {
		super();
		this.name = name;
		this.desc = desc;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	

}
