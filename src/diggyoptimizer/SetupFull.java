package diggyoptimizer;

public class SetupFull {
	private static int windmillBonus;
	private final SetupHalf halfRegen;
	private final SetupHalf halfCapacity;

	public SetupFull(SetupHalf halfRegen, SetupHalf halfCapacity) {
		this.halfRegen = halfRegen;
		this.halfCapacity = halfCapacity;
	}

	public static void setWindmillBonus(int windmillBonus) {
		SetupFull.windmillBonus = windmillBonus;
	}

	public int getRegenValue(){
		return halfRegen.getTotalPower() + 60 + windmillBonus;
	}

	public int getCapacityValue(){
		return halfCapacity.getTotalPower() + 230;
	}

	public int getEnergyAfterNHours(int hours){
		return Math.min(getRegenValue() * hours, getCapacityValue());
	}

	public int getSlotsTaken(){
		return halfRegen.getSlotsTaken() + halfCapacity.getSlotsTaken();
	}

	public int getHeight3Taken(){
		return halfRegen.getHeight3Taken() + halfCapacity.getHeight3Taken();
	}

	public int getHeight4Taken(){
		return halfRegen.getHeight4Taken() + halfCapacity.getHeight4Taken();
	}
}