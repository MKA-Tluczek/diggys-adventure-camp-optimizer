package diggyoptimizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataReader {
	public static List<Integer> readCampParameters(String filename){
		File parameterFile = new File(filename);
		List<Integer> parameterList = new ArrayList<>();

		try(Scanner scanner = new Scanner(parameterFile)){
			while(scanner.hasNext()) parameterList.add(scanner.nextInt());
		}
		catch(FileNotFoundException ex){
			System.out.println("Parameter file not found.");
		}

		return parameterList;
	}

	public static List<Equip> readEquipList(String filename){
		File equipFile = new File(filename);
		List<Equip> equipList = new ArrayList<>();

		try(Scanner scanner = new Scanner(equipFile)){
			String[] nextEquip;
			boolean nextIsCapacity;
			while(scanner.hasNext()){
				nextEquip = scanner.nextLine().split(",");
				if(nextEquip.length < 6){
					System.out.println("Faulty line in equip file detected.");
					continue;
				}
				nextIsCapacity = nextEquip[1].equals("1");

				try{
					equipList.add(new Equip(nextEquip[0], nextIsCapacity, Integer.parseInt(nextEquip[2]),
							Integer.parseInt(nextEquip[3]), Integer.parseInt(nextEquip[4]),
							Integer.parseInt(nextEquip[5])));
				}
				catch(NumberFormatException ex){
					System.out.println("Faulty line in equip file detected.");
				}
			}
		}
		catch(FileNotFoundException ex){
			System.out.println("Equip file not found.");
		}

		return equipList;
	}
}