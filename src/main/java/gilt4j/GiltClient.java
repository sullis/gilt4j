package gilt4j;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.AllClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;

import gilt4j.internal.httpclient.GzipRequestInterceptor;
import gilt4j.internal.httpclient.GzipResponseInterceptor;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.*;

public class GiltClient {
	private static final String GILT_API_URL_ROOT = "https://api.gilt.com/v1/";
	private static final String RESPONSE_ENCODING = "UTF-8";
	private HttpClient _httpClient;
	private String _apiKey;
	private String _affiliateId;
	private Gson _gson = GsonFactory.create();
	
	/**
	 * 
	 * @param apiKey must be non-null
	 * @param affiliateId may be null
	 */
	public GiltClient(String apiKey, String affiliateId) {
		_apiKey = apiKey;
		_affiliateId = affiliateId;
		setConnectionTimeout(30000);
		setSocketTimeout(30000);
	}
	
	public List<Sale> getUpcomingSales() {
		return getUpcomingSales(null);
	}
	
	public List<Sale> getUpcomingSales(Store store) {
		String storePath = (store == null) ? "" : "/" + store.getKey() + "";
		UpcomingSalesResponse resp = get(GILT_API_URL_ROOT + "sales" + storePath + "/upcoming.json", UpcomingSalesResponse.class);
		return resp.sales;
	}
	
	public List<Sale> getActiveSales() {
		return getActiveSales(null);
	}
	
	public List<Sale> getActiveSales(Store store) {
		String storePath = (store == null) ? "" : "/" + store.getKey() + "";
		ActiveSalesResponse resp = get(GILT_API_URL_ROOT + "sales" + storePath + "/active.json", ActiveSalesResponse.class);
		return resp.sales;
	}
	
	public Sale getSale(String storeKey, String saleKey) {
		Sale s = get(GILT_API_URL_ROOT + "sales/" + storeKey + "/" + saleKey + "/detail.json", Sale.class);
		if (s == null) {
			throw new RuntimeException("invalid sale key [" + saleKey + "] for store [" + storeKey + "]");
		}
		return s;
	}
	
	public Product getProductById(String productId) {
		Product p = get(GILT_API_URL_ROOT + "products/" +  productId + "/detail.json", Product.class);
		if (p == null) {
			throw new RuntimeException("invalid product id: " + productId);
		}
		return p;
	}
	
	public Product getProductByUrl(String productUrl) {
		Product p = get(productUrl, Product.class);
		if (p == null) {
			throw new RuntimeException("invalid product url: " + productUrl);
		}
		return p;
	}
	
	protected <T> T get(String url, Type type) {
		HttpResponse response = null;
		try {
			url += "?apikey=" + _apiKey;
			if (_affiliateId != null) {
				url += "&affid=" + _affiliateId; 
			}
			
			HttpGet get = createHttpGet(url);
			response = execute(get);
			
			return fromJson(createReader(response), type);
		}
		catch (Exception ex) {
			throw new RuntimeException("HTTP GET failure and/or unexpected JSON. url=[" + url + "]", ex);
		}
		finally {
			if (response != null) {
				close(response);
			}
		}
	}
	
	protected void close(HttpResponse response) {
		if ( (response != null) && (response.getEntity() != null) ) {
			close(response.getEntity());
		}
	}
	
	protected void close(HttpEntity entity) {
		if (entity != null) {
			try {
				entity.consumeContent();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
	
	protected Reader createReader(HttpResponse response) throws UnsupportedEncodingException, IllegalStateException, IOException {
		InputStreamReader reader = new InputStreamReader(response.getEntity().getContent(), RESPONSE_ENCODING);
		return reader;
	}
	
	protected HttpGet createHttpGet(String url) {
		return new HttpGet(url);
	}

	protected HttpResponse execute(HttpRequestBase method) throws ClientProtocolException, IOException {
		return getHttpClient().execute(method);
	}
	
	protected HttpClient getHttpClient()
	{
		if (_httpClient == null)
		{
			_httpClient = createHttpClient();
		}
		return _httpClient;
	}
	
	protected HttpClient createHttpClient()
	{
		DefaultHttpClient client = new DefaultHttpClient();
		
		client.getParams().setParameter(AllClientPNames.USER_AGENT, "gilt4j library");

		client.addRequestInterceptor(GzipRequestInterceptor.getInstance());
		client.addResponseInterceptor(GzipResponseInterceptor.getInstance());
		
		return client;
	}
	
	public void setUserAgent(String agentString)
	{
		if (agentString == null)
		{
			getHttpClient().getParams().removeParameter(AllClientPNames.USER_AGENT);
		}
		else
		{
			getHttpClient().getParams().setParameter(AllClientPNames.USER_AGENT, agentString);
		}
	}
	
	public void shutdown()
	{
		if (getHttpClient() != null)
		{
			try
			{
				getHttpClient().getConnectionManager().shutdown();
			}
			catch (Exception ignored)
			{
				// ignored
			}
		}
	}
	
	public void setConnectionTimeout(int milliseconds)
	{
		getHttpClient().getParams().setIntParameter(AllClientPNames.CONNECTION_TIMEOUT, milliseconds);
	}
	
	public void setSocketTimeout(int milliseconds)
	{
		getHttpClient().getParams().setIntParameter(AllClientPNames.SO_TIMEOUT, milliseconds);
	}
	
	protected <T> T fromJson(Reader reader, Type type) {
		JsonReader jsonReader = new JsonReader(reader);
		return _gson.fromJson(jsonReader, type);		
	}
	
	static class UpcomingSalesResponse {
		public List<Sale> sales;
	}

	static class ActiveSalesResponse {
		public List<Sale> sales;
	}
	
}
