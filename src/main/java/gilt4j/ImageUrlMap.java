
package gilt4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImageUrlMap extends HashMap<String, List<ImageUrl>> {  // TODO : don't extend HashMap?

	public List<ImageUrl> get100x93() {
		return getImageUrls(ImageSize.SIZE_100_BY_93);
	}

	public List<ImageUrl> get300x280() {
		return getImageUrls(ImageSize.SIZE_300_BY_280);
	}

	public List<ImageUrl> get370x345() {
		return getImageUrls(ImageSize.SIZE_370_BY_345);
	}

	public List<ImageUrl> get455x172() {
		return getImageUrls(ImageSize.SIZE_455_BY_172);
	}

	public List<ImageUrl> get636x400() {
		return getImageUrls(ImageSize.SIZE_636_BY_400);
	}

	public boolean hasImageUrls(ImageSize size) {
		List<ImageUrl> urls = getImageUrls(size);
		return (urls != null) && (urls.size() > 0);
	}

	public boolean hasImageUrls(int width, int height) {
		List<ImageUrl> urls = getImageUrls(width, height);
		return (urls != null) && (urls.size() > 0);
	}

	public List<ImageUrl> getImageUrls(int width, int height) {
		List<ImageUrl> urls = get(width + "x" + height);
		if (urls == null) {
			urls = new ArrayList<ImageUrl>();
		}
		return urls;
	}

	public List<ImageUrl> getImageUrls(ImageSize size) {
		return getImageUrls(size.getWidth(), size.getHeight());
	}


}
