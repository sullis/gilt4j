
package gilt4j;

import java.util.Calendar;
import java.util.List;

public class Sale {
	private String name;
	private String sale; // url to single sale object
	private String saleKey; // unique identifier for sale
	private String store; // store key
	private String description;
	private String saleUrl; // permanent link to sale website
	private Calendar begins;
	private Calendar ends;
	private ImageUrl imageUrls;
	private List<String> products; // product urls
	
	public Calendar getEnds() {
		return ends;
	}
	
	public Calendar getBegins() {
		return begins;
	}
	
	public boolean isActive() {
		long now = System.currentTimeMillis();
		return (getBegins().getTime().getTime() <= now) && (now <= getEnds().getTime().getTime());
	}
	
	public boolean isOver() {
		return (System.currentTimeMillis() > getEnds().getTime().getTime());
	}
	
	public int getDaysLeft() {
		if (isOver()) {
			return 0;
		}
		else if (isActive()) {
			return 1; // todo
		}
		else {
			throw new IllegalStateException("this sale has not started");
		}
	}
	
	public boolean isUpcoming() {
		return getBegins().getTimeInMillis() > System.currentTimeMillis();
	}
	
	public int getHoursLeft() {
		if (isOver()) {
			return 0;
		}
		else if (isActive()) {
			return 1; // todo
		}
		else {
			throw new IllegalStateException("this sale has not started");
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getSaleKey() {
		return saleKey;
	}
	
	public String getStore() {
		return store;
	}
	
	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSaleUrl() {
		return saleUrl;
	}

	public void setSaleUrl(String saleUrl) {
		this.saleUrl = saleUrl;
	}

	public ImageUrl getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(ImageUrl imageUrls) {
		this.imageUrls = imageUrls;
	}

	/**
	 * 
	 * @return list of product URL's
	 * 
	 */
	public List<String> getProducts() {
		return products;
	}

	public void setProducts(List<String> products) {
		this.products = products;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSaleKey(String saleKey) {
		this.saleKey = saleKey;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public void setBegins(Calendar begins) {
		this.begins = begins;
	}

	public void setEnds(Calendar ends) {
		this.ends = ends;
	}

	@Override
	public String toString() {
		return "Sale: [" + getName() + "], saleKey=[" + getSaleKey() + "] in store [" + getStore() + "]";
	}

	public boolean hasProducts() {
		return (products != null) && (!products.isEmpty());
	}
}
