package gilt4j;

public enum ImageSize {
	SIZE_100_BY_93(100, 93),
	SIZE_300_BY_184(300, 184),
	SIZE_300_BY_280(300, 280),
	SIZE_370_BY_345(370, 345),
	SIZE_455_BY_172(455, 172),
	SIZE_636_BY_400(636, 400);

	private int width;
	private int height;

	ImageSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
