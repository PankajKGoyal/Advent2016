package Advent2016;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Pankaj
 *
 */
//Main class for Advent2016 problems
public class Advent2016 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			System.out.println("******************************************************************************************");
			System.out.println("Problem 2016-10");	
			ArrayList<Integer> outputBinIdsForMultipliCalc = new ArrayList<Integer>();
			outputBinIdsForMultipliCalc.add(0);
			outputBinIdsForMultipliCalc.add(1);
			outputBinIdsForMultipliCalc.add(2);
			int chipValue1 = 61;
			int chipValue2 = 17;		
			AdventProblem10 adventProblem10 = new AdventProblem10("AppData\\Prob10Input.txt");
			System.out.println("******************** Answer to Problem 10A*************************************************");			
			int botIdWithSpecificChipsValues = adventProblem10.findBotIdsWithSpecificChipValues(chipValue1, chipValue2);	
			System.out.println("Bot Id " + botIdWithSpecificChipsValues + " has microchips with values " + chipValue1 + " & " + chipValue2);			
			System.out.println("******************** Answer to Problem 10B*************************************************");
			long multiplicationOfOutBinsChips = adventProblem10.multipliOfOutBinsChipsVal(outputBinIdsForMultipliCalc);			
			System.out.println("Multiplication of output bins' first chip's value is " + multiplicationOfOutBinsChips + "\n");		
		} catch (FileNotFoundException e) {
			System.out.println("Data file does not exist");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument were passed");
			e.printStackTrace();
		}	

		try {
			System.out.println("******************************************************************************************");
			System.out.println("Problem 2016-4");		
			String rmSpecificDecryptName = "Northpole Object Storage";		
			AdventProblem4 adventProblem4 = new AdventProblem4("AppData\\Prob4AInput.txt");
			System.out.println("******************** Answer to Problem 4A*************************************************");
			long sumOfSectorIdsOfValidRoomName = adventProblem4.getSumOfSecIdsOfValidRooms();
			System.out.println("Sum of Sector Ids of Rooms with valid room names: " + sumOfSectorIdsOfValidRoomName);
			int secIdOfRmSpecificDecryptName = adventProblem4.getSecIdOfRmSpecificDecryptName(rmSpecificDecryptName);
			System.out.println("******************** Answer to Problem 4B*************************************************");
			System.out.println("SectorId of the room with name '" + rmSpecificDecryptName + "' is " + secIdOfRmSpecificDecryptName + "\n");		
		} catch (FileNotFoundException e) {
			System.out.println("Data file does not exist");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument were passed");
			e.printStackTrace();
		}			
		
		try {
		System.out.println("******************************************************************************************");
		System.out.println("Problem 2016-1");			
		Problem1Results problem1Results = AdventProblem1.problem1AB("AppData\\Prob1AInput.txt");
		System.out.println("******************** Answer to Problem 1A*************************************************");	
		System.out.println("Total net blocks distance travelled is " + problem1Results.totalNetBlocksDis);
		System.out.println("******************** Answer to Problem 1B**************************************************");			    		
		System.out.println("# of blocks need to walk to already travelled block junction from starting point is " + problem1Results.alreadyTraveledBlockNetDis );
		} catch (FileNotFoundException e) {
			System.out.println("Data file does not exist");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument were passed");
			e.printStackTrace();
		}			
	}
}
