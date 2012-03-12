package gilt4j;

import java.util.List;
import static org.testng.Assert.*;
import java.util.Properties;
import java.io.InputStream;

import gilt4j.ImageUrl;
import gilt4j.ImageUrlMap;
import gilt4j.Product;
import gilt4j.Sale;
import gilt4j.Sku;

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
		
		ImageUrlMap imageUrls = product.getImageUrls();
		
		assertNotNull(imageUrls);
		
		for (String imageKey : imageUrls.keySet()) {
			assertNotNull(imageKey);
			List<ImageUrl> details = imageUrls.get(imageKey);
			assertValidImageDetailList(details);
		}
		
	}
	
	public static void assertValidSkuList(List<Sku> skus) {
		assertNotNull(skus);
		for (Sku sku : skus) {
			assertValid(sku);
		}
	}

	public static void assertValid(Sku sku) {
		assertNotNull(sku);
		assertNotNull(sku.getId());
		assertNotNull(sku.getInventoryStatus());
		assertNotNull(sku.getColor());
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
		assertNotNull(sale.getSaleUrl());
		assertTrue(sale.getSaleUrl().startsWith("http"));
		assertNotNull(sale.getImageUrls());
		assertTrue(sale.getImageUrls().size() > 0);
	}
}
