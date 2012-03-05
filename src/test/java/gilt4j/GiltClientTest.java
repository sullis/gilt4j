package gilt4j;

import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertNotNull;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import gilt4j.GiltClient;
import gilt4j.Product;
import gilt4j.Sale;
import gilt4j.Store;

import java.util.*;

public class GiltClientTest {

	private GiltClient client;
	
	@BeforeMethod
	public void setup() {
		client = new GiltClient(TestUtil.getApiKey(), null);
	}
	
	@Test
	public void getUpcomingSales() {
		List<Sale> sales = client.getUpcomingSales();
		TestUtil.assertValid(sales);
	}
	
	@Test
	public void getActiveSales() {
		List<Sale> sales = client.getActiveSales();
		TestUtil.assertValid(sales);
	}
	
	@Test
	public void getUpcomingSalesByStore() {
		for (Store store : Store.values()) {
			List<Sale> sales = client.getUpcomingSales(store);
			TestUtil.assertValid(sales);
		}
	}
	
	@Test
	public void getActiveSalesByStore() {
		for (Store store : Store.values()) {
			List<Sale> sales = client.getActiveSales(store);
			TestUtil.assertValid(sales);
		}
	}
	
	@Test
	public void getProduct() {
		Sale sale = client.getActiveSales().iterator().next();
		assertTrue(sale.isActive());
		TestUtil.assertValid(sale);
		List<String> productUrls = sale.getProducts();
		assertNotNull(productUrls);
		for (String productUrl : productUrls) {
			Product product = client.getProductByUrl(productUrl);
			TestUtil.assertValid(product);
		}
	}
	
	@AfterMethod
	public void cleanup() {
		if (client != null) {
			client.shutdown();
			client = null;
		}
	}
}
