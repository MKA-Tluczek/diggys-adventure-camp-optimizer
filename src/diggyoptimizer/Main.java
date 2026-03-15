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
		 */
		List<Integer> parameters = DataReader.readCampParameters(filenames[0]);
		if(parameters.size() < 5){
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

		//TODO: Connect and compare halves into full setups
	}
}