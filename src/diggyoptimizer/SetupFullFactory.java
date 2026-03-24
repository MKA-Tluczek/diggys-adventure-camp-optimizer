package diggyoptimizer;

import java.util.ArrayList;
import java.util.List;

public class SetupFullFactory {
	private final List<SetupHalf> regenHalves;
	private final List<SetupHalf> capacityHalves;
	private SetupFull solution;
	private final int hours;
	private final int slots2;
	private final int slots3;
	private final int slots4;

	public SetupFullFactory(List<SetupHalf> regenHalves, List<SetupHalf> capacityHalves, int hours, int slots2, int slots3, int slots4) {
		this.regenHalves = new ArrayList<>(regenHalves);
		this.capacityHalves = new ArrayList<>(capacityHalves);
		this.hours = hours;
		this.slots2 = slots2;
		this.slots3 = slots3;
		this.slots4 = slots4;
	}

	public SetupFull solve(){
		solution = new SetupFull(new SetupHalf(false), new SetupHalf(true));
		SetupHalf currentRegenHalf;
		int solutionEnergy = solution.getEnergyAfterNHours(hours);
		int solutionRegen = solution.getRegenValue();
		int solutionCapacity = solution.getCapacityValue();
		boolean updateSolution;

		while(!regenHalves.isEmpty()){
			currentRegenHalf = regenHalves.getFirst();

			for(SetupHalf currentCapacityHalf : capacityHalves){
				SetupFull full = new SetupFull(currentRegenHalf, currentCapacityHalf);

				updateSolution = false;
				if(full.getHeight2Taken() <= slots2 && full.getHeight3Taken() <= slots3 && full.getHeight4Taken() <= slots4){
					if(full.getEnergyAfterNHours(hours) > solutionEnergy){
						updateSolution = true;
					}
					else if(full.getEnergyAfterNHours(hours) == solutionEnergy){
						if(full.getCapacityValue() > solutionCapacity){
							updateSolution = true;
						}
						else if(full.getCapacityValue() == solutionCapacity){
							if(full.getRegenValue() > solutionRegen){
								updateSolution = true;
							}
						}
					}
				}

				if(updateSolution){
					solution = full;
					solutionEnergy = full.getEnergyAfterNHours(hours);
					solutionRegen = full.getRegenValue();
					solutionCapacity = full.getCapacityValue();
				}
			}

			regenHalves.removeFirst();

			for(int i = 0; i < regenHalves.size(); i++){
				if(regenHalves.get(i).getMaxEnergyAfterNHours(hours) < solutionEnergy){
					regenHalves.remove(i);
					i--;
				}
			}
			for(int i = 0; i < capacityHalves.size(); i++){
				if(capacityHalves.get(i).getMaxEnergyAfterNHours(hours) < solutionEnergy){
					capacityHalves.remove(i);
					i--;
				}
			}
		}

		return solution;
	}
}
