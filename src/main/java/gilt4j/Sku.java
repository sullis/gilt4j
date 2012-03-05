package gilt4j;

import java.math.*;
import java.util.List;

public class Sku {
	private String id;
	private String inventoryStatus;
	private BigDecimal msrpPrice;
	private BigDecimal salePrice;
	private BigDecimal shippingSurcharge;
	private List<SkuAttribute> attributes;
	
	public boolean isSoldOut() {
		return "sold out".equals(getInventoryStatus());
	}
	
	public String getId() {
		return id;
	}
	
	public String getInventoryStatus() {
		return inventoryStatus;
	}
	
	static class SkuAttribute {
		private String name;
		private String value;
		
	}

	public String getColor() {
		return "todo"; // todo
	}
	
	public BigDecimal getShippingSurcharge() {
		return shippingSurcharge;
	}
	
	public boolean hasShippingSurcharge() {
		return (getShippingSurcharge() != null) && (getShippingSurcharge().signum() > 0);
	}
	
	@Override
	public String toString() {
		return "Sku: id=" + getId() + ", inventoryStatus=[" + getInventoryStatus() + "]";
	}
	
}
