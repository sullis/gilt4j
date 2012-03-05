package gilt4j;

import java.util.List;

public class Product {
	private String name;
	private String product;
	private String id;
	private String brand;
	private String url;
	private ImageUrlMap imageUrls;
	private List<Sku> skus;
	private Content content;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getProduct() {
		return product;
	}



	public void setProduct(String product) {
		this.product = product;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public ImageUrlMap getImageUrls() {
		return imageUrls;
	}



	public void setImageUrls(ImageUrlMap imageUrls) {
		this.imageUrls = imageUrls;
	}



	public List<Sku> getSkus() {
		return skus;
	}



	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}



	public Content getContent() {
		return content;
	}



	public void setContent(Content content) {
		this.content = content;
	}



	static class Content {
		private String description;
		private String material;
		private String origin;
		private String fitNotes;
		private String careInstructions;
		
	}
}
