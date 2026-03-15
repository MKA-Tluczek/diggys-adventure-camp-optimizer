package diggyoptimizer;

public class SetupHalf {
	private String contents;
	private final boolean capacityHalf;
	private int totalPower;
	private int height2Taken;
	private int height3Taken;
	private int height4Taken;
	private static int windmillBonus;

	public SetupHalf(boolean capacityHalf) {
		this.capacityHalf = capacityHalf;

		if(capacityHalf) totalPower = 230; //230 - free starting capacity
		else totalPower = 60 + windmillBonus; //60 - free starting regeneration

		contents = "";
		height2Taken = 0;
		height3Taken = 0;
		height4Taken = 0;
	}

	public SetupHalf(SetupHalf origin){
		contents = origin.contents;
		capacityHalf = origin.capacityHalf;
		totalPower = origin.totalPower;
		height2Taken = origin.height2Taken;
		height3Taken = origin.height3Taken;
		height4Taken = origin.height4Taken;
	}

	public void addEquip(Equip equip, int quantity){
		if(equip.isCapacity() != capacityHalf || quantity < 1 || quantity > equip.getQuantity()) return;

		contents = contents.concat(quantity + "x " + equip.getName() + "\n");
		totalPower += equip.getPower() * quantity;
		height2Taken += equip.getWidth() * quantity;
		if(equip.getHeight() >= 3) height3Taken += equip.getWidth() * quantity;
		if(equip.getHeight() == 4) height4Taken += equip.getWidth() * quantity;
	}

	public int getMaxEnergyAfterNHours(int hours){
		if(capacityHalf) return totalPower;
		return totalPower * hours;
	}

	public static void setWindmillBonus(int windmillBonus) {
		SetupHalf.windmillBonus = windmillBonus;
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

	public int getHeight2Taken() {
		return height2Taken;
	}

	public int getHeight3Taken() {
		return height3Taken;
	}

	public int getHeight4Taken() {
		return height4Taken;
	}
}