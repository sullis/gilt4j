package gilt4j;

import java.util.List;
import java.util.ArrayList;

public class Product {
	private String name;
	private String product;
	private String id;
	private String brand;
	private String url;
	private ImageUrlMap imageUrls;
	private List<Sku> skus = new ArrayList<Sku>();
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


	@Override
	public String toString() {
		return "id=" + getId() + ", name=" + this.getName();
	}

	static class Content {
		private String description;
		private String material;
		private String origin;
		private String fitNotes;
		private String careInstructions;
		
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		public String getMaterial() {
			return material;
		}
		public void setMaterial(String material) {
			this.material = material;
		}
		public String getOrigin() {
			return origin;
		}
		public void setOrigin(String origin) {
			this.origin = origin;
		}
		public String getFitNotes() {
			return fitNotes;
		}
		public void setFitNotes(String fitNotes) {
			this.fitNotes = fitNotes;
		}
		public String getCareInstructions() {
			return careInstructions;
		}
		public void setCareInstructions(String careInstructions) {
			this.careInstructions = careInstructions;
		}

		@Override
		public String toString() {
			return "description=" + this.getDescription();
		}
	}
}
