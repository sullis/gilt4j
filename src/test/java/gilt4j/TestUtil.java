package gilt4j;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class TestUtil {

	private static Properties testProps;

	static {
		testProps = new Properties();
		InputStream input = TestUtil.class.getResourceAsStream("/test.properties");
		if (input == null) {
			throw new RuntimeException("can't find test.properties");
		}
		else {
			try {
				testProps.load(input);
			}
			catch (Exception ex) {
				throw new RuntimeException("fail", ex);
			}
		}
	}

	public static String getApiKey() {
		return testProps.getProperty("apikey");
	}

	public static void assertValid(List<Sale> sales) {
		assertNotNull(sales);
		for (Sale s : sales) {
			assertValid(s);
		}
	}

	public static void assertValid(Product product) {
		assertNotNull(product);
		assertNotNull(product.getBrand());
		assertNotNull(product.getId());
		assertNotNull(product.getName());
		assertNotNull(product.getProduct());
		assertValidSkuList(product.getSkus());
		assertNotNull(product.getUrl());
		assertTrue(product.getUrl().startsWith("http"));
		assertValid(product.getImageUrls());
	}

	public static void assertValidSkuList(List<Sku> skus) {
		assertNotNull(skus);
		assertTrue(skus.size() > 0);
		for (Sku sku : skus) {
			assertValid(sku);
		}
	}

	public static void assertValid(Sku sku) {
		assertNotNull(sku);
		assertNotNull(sku.getId());
		assertNotNull(sku.getInventoryStatus());
		assertNotNull(sku.getColor());
		assertNotNull(sku.getMsrpPrice());
		assertEquals(sku.getMsrpPrice().signum(), 1);
		assertNotNull(sku.getSalePrice());
		assertEquals(sku.getSalePrice().signum(), 1);
		assertNotNull(sku.getShippingSurcharge());
		assertTrue(sku.getShippingSurcharge().signum() >= 0);
		assertNotNull(sku.getAttributes());
	}

	public static void assertValidImageDetailList(List<ImageUrl> details) {
		assertNotNull(details);
		for (ImageUrl detail : details) {
			assertValid(detail);
		}
	}

	public static void assertValid(ImageUrl detail) {
		assertNotNull(detail);
		assertNotNull(detail.getUrl());
		assertTrue(detail.getUrl().startsWith("http"));
		assertNotNull(detail.getHeight());
		assertNotNull(detail.getWidth());
	}

	public static void assertValid(Sale sale) {
		assertNotNull(sale);
		assertNotNull(sale.getStore());
		assertNotNull(sale.getName());
		assertNotNull(sale.getSaleKey());
		assertNotNull(sale.getBegins());
		assertNotNull(sale.getEnds());
		assertTrue(sale.getBegins().getTimeInMillis() < sale.getEnds().getTimeInMillis());
		if (sale.isActive()) {
			assertFalse(sale.isOver());
			assertFalse(sale.isUpcoming());
		}
		if (sale.isOver()) {
			assertFalse(sale.isActive());
			assertFalse(sale.isUpcoming());
		}
		if (sale.isUpcoming()) {
			assertFalse(sale.isOver());
			assertFalse(sale.isActive());
		}
		assertNotNull(sale.getSaleUrl());
		assertTrue(sale.getSaleUrl().startsWith("http"));
		assertValid(sale.getImageUrls());

	}

	public static void assertValid(ImageUrlMap imageUrls) {
		assertNotNull(imageUrls);

		for (String imageKey : imageUrls.keySet()) {
			// System.out.println("imageKey: " + imageKey);
			assertNotNull(imageKey);
			List<ImageUrl> details = imageUrls.get(imageKey);
			assertValidImageDetailList(details);
		}

	}
}
