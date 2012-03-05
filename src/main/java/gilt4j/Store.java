
package gilt4j;

public enum Store {
	WOMEN("women"), MEN("men"), KIDS("kids"), HOME("home");
	
	private String _storeKey;
	
	Store(String storeKey) {
		_storeKey = storeKey;
	};
	
	public String getKey() {
		return _storeKey;
	}
}
