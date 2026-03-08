package diggyoptimizer;

public class Equip {
	private final String name;
	private final boolean capacity;
	private final int power;
	private final int width;
	private final int height;
	private final int quantity;

	public Equip(String name, boolean capacity, int power, int width, int height, int quantity){
		this.name = name;
		this.capacity = capacity;
		this.power = power;
		this.width = width;
		this.height = height;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public boolean isCapacity() {
		return capacity;
	}

	public int getPower() {
		return power;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getQuantity() {
		return quantity;
	}
}