package diggyoptimizer;

public class SetupHalf {
	private String contents;
	private final boolean capacityHalf;
	private int totalPower;
	private int slotsTaken;
	private int height3Taken;
	private int height4Taken;

	public SetupHalf(boolean capacityHalf) {
		this.capacityHalf = capacityHalf;
	}

	public String getContents() {
		return contents;
	}

	public boolean isCapacityHalf() {
		return capacityHalf;
	}

	public int getTotalPower() {
		return totalPower;
	}

	public int getSlotsTaken() {
		return slotsTaken;
	}

	public int getHeight3Taken() {
		return height3Taken;
	}

	public int getHeight4Taken() {
		return height4Taken;
	}
}