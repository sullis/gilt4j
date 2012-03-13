package gilt4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Sku {
	private String id;
	private String inventoryStatus;
	private BigDecimal msrpPrice;
	private BigDecimal salePrice;
	private BigDecimal shippingSurcharge;
	private final List<SkuAttribute> attributes = new ArrayList<SkuAttribute>();

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

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		@Override
		public String toString() {
			return "name=" + getName() + ", value=" + getValue();
		}


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
