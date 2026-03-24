package diggyoptimizer;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		String[] filenames = {"parameters.csv", "equips.csv"};
		if(args.length >= 2){
			filenames[0] = args[0];
			filenames[1] = args[1];
		}

		/* parameters:
		number of 2-height slots
		number of 3-height slots
		number of 4-height slots
		energy per hour per windmill
		number of active windmills
		number of idle hours for setup 1
		number of idle hours for setup 2
		...
		number of idle hours for setup N
		 */
		List<Integer> parameters = DataReader.readCampParameters(filenames[0]);
		if(parameters.size() < 6){
			System.out.println("Incorrect parameters file.");
			return;
		}

		int slots2 = parameters.get(0) + parameters.get(1) + parameters.get(2);
		int slots3 = parameters.get(1) + parameters.get(2);
		int slots4 = parameters.get(2);
		SetupHalf.setWindmillBonus(parameters.get(3) * parameters.get(4));

		List<Equip> allEquips = DataReader.readEquipList(filenames[1]);
		SetupHalfFactory setupFactory = new SetupHalfFactory(allEquips, slots2, slots3, slots4);

		setupFactory.createHalves(false);
		List<SetupHalf> regenHalves = new ArrayList<>(setupFactory.getHalves());
		setupFactory.createHalves(true);
		List<SetupHalf> capacityHalves = new ArrayList<>(setupFactory.getHalves());

		for(int setupNo = 0; setupNo < parameters.size()-5; setupNo++){
			int hours = parameters.get(setupNo+5);
			SetupFullFactory setupFullFactory = new SetupFullFactory(
					regenHalves, capacityHalves, hours, slots2, slots3, slots4);
			SetupFull solution = setupFullFactory.solve();

			System.out.println("Solution for " + hours + " hours:");
			System.out.println(solution.getEnergyAfterNHours(hours) + " energy");
			System.out.println("(" + solution.getRegenValue() + "/hour, " + solution.getCapacityValue() + " capacity)");
			System.out.println(solution.getContents());
		}
	}
}