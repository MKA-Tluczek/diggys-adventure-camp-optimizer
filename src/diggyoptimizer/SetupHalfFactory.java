package diggyoptimizer;

import java.util.ArrayList;
import java.util.List;

public class SetupHalfFactory {
	private final List<Equip> equips;
	private final List<SetupHalf> halves;
	private final int slots2;
	private final int slots3;
	private final int slots4;

	public SetupHalfFactory(List<Equip> equips, int slots2, int slots3, int slots4) {
		this.equips = equips;
		halves = new ArrayList<>();
		this.slots2 = slots2;
		this.slots3 = slots3;
		this.slots4 = slots4;
	}

	public void createHalves(boolean capacity){
		halves.clear();
		halves.add(new SetupHalf(capacity));

		SetupHalf add;
		List<SetupHalf> newHalves = new ArrayList<>();

		for(Equip equip : equips){
			if(equip.isCapacity() != capacity) continue;

			for(SetupHalf half : halves){
				for(int q = 1; q <= equip.getQuantity(); q++){
					add = new SetupHalf(half);
					add.addEquip(equip, q);
					newHalves.add(add);
				}
			}

			//remove any SetupHalf that exceeds camp size
			for(int i = 0; i < newHalves.size();){
				SetupHalf half = halves.get(i);
				if(half.getHeight2Taken() > slots2
						|| half.getHeight3Taken() > slots3
						|| half.getHeight4Taken() > slots4) halves.remove(i);
				else i++;
			}

			nextNew: for(SetupHalf potentialHalf : newHalves){
				for(int i = 0; i < halves.size(); i++){
					SetupHalf currentHalf = halves.get(i);

					//stop considering this SetupHalf if there's nothing better about it
					//remove new SetupHalf when it's equal too to prevent duplicates
					if(potentialHalf.getHeight2Taken() >= currentHalf.getHeight2Taken()
							&& potentialHalf.getHeight3Taken() >= currentHalf.getHeight3Taken()
							&& potentialHalf.getHeight4Taken() >= currentHalf.getHeight4Taken()
							&& potentialHalf.getTotalPower() <= currentHalf.getTotalPower()){
						continue nextNew;
					}

					//remove old SetupHalf is new SetupHalf is better or equal in all cases
					if(potentialHalf.getHeight2Taken() <= currentHalf.getHeight2Taken()
							&& potentialHalf.getHeight3Taken() <= currentHalf.getHeight3Taken()
							&& potentialHalf.getHeight4Taken() <= currentHalf.getHeight4Taken()
							&& potentialHalf.getTotalPower() >= currentHalf.getTotalPower()){
						halves.remove(i);
						i--;
					}
				}

				halves.add(potentialHalf);
			}

			newHalves.clear();
		}
	}

	public List<SetupHalf> getHalves(){
		return halves;
	}
}
