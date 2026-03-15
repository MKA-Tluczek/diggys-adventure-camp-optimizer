package diggyoptimizer;

public class SetupFull {
	private final SetupHalf halfRegen;
	private final SetupHalf halfCapacity;

	public SetupFull(SetupHalf halfRegen, SetupHalf halfCapacity) {
		this.halfRegen = halfRegen;
		this.halfCapacity = halfCapacity;
	}

	public int getRegenValue(){
		return halfRegen.getTotalPower();
	}

	public int getCapacityValue(){
		return halfCapacity.getTotalPower();
	}

	public int getEnergyAfterNHours(int hours){
		return Math.min(getRegenValue() * hours, getCapacityValue());
	}

	public int getHeight2Taken(){
		return halfRegen.getHeight2Taken() + halfCapacity.getHeight2Taken();
	}

	public int getHeight3Taken(){
		return halfRegen.getHeight3Taken() + halfCapacity.getHeight3Taken();
	}

	public int getHeight4Taken(){
		return halfRegen.getHeight4Taken() + halfCapacity.getHeight4Taken();
	}
}