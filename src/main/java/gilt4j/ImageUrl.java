package gilt4j;

public class ImageUrl {
	private String url;
	private Integer width;
	private Integer height;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "url=[" + getUrl() + "], width=[" + getWidth() + "], height=[" + getHeight() + "]";
	}

}
