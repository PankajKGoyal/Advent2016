package Advent2016;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AdventProblem10Test {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProblem10AB() {
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
			//Problem 2016-10B
			long multiplicationOfOutBinsChips = adventProblem10.multipliOfOutBinsChipsVal(outputBinIdsForMultipliCalc);			
			System.out.println("Multiplication of output bins' first chip's value is " + multiplicationOfOutBinsChips + "\n");	
			
			assertEquals(98, botIdWithSpecificChipsValues);
			assertEquals(4042, multiplicationOfOutBinsChips);				
		} catch (FileNotFoundException e) {
			System.out.println("Data file does not exist");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument were passed");
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testProblem10ABInvalidFile() {
		int botIdWithSpecificChipsValues = 0;
		long multiplicationOfOutBinsChips = 0;
		try{
			System.out.println("******************************************************************************************");
			System.out.println("Problem 2016-10");	
			ArrayList<Integer> outputBinIdsForMultipliCalc = new ArrayList<Integer>();
			outputBinIdsForMultipliCalc.add(0);
			outputBinIdsForMultipliCalc.add(1);
			outputBinIdsForMultipliCalc.add(2);
			int chipValue1 = 61;
			int chipValue2 = 17;		
			AdventProblem10 adventProblem10 = new AdventProblem10("AppData\\InvalidFile.txt");
			System.out.println("******************** Answer to Problem 10A*************************************************");			
			botIdWithSpecificChipsValues = adventProblem10.findBotIdsWithSpecificChipValues(chipValue1, chipValue2);	
			System.out.println("Bot Id " + botIdWithSpecificChipsValues + " has microchips with values " + chipValue1 + " & " + chipValue2);			
			System.out.println("******************** Answer to Problem 10B*************************************************");
			//Problem 2016-10B
			multiplicationOfOutBinsChips = adventProblem10.multipliOfOutBinsChipsVal(outputBinIdsForMultipliCalc);			
			System.out.println("Multiplication of output bins' first chip's value is " + multiplicationOfOutBinsChips + "\n");	
		} catch (FileNotFoundException e) {
			System.out.println("Data file does not exist");
			e.printStackTrace();
			assertEquals(-1, botIdWithSpecificChipsValues);
			assertEquals(-1, multiplicationOfOutBinsChips);				
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument were passed");
			e.printStackTrace();
		}			
	}

	@Test
	public void testProblem10ABInvalidChipValParam() {
		try{
			System.out.println("******************************************************************************************");
			System.out.println("Problem 2016-10");	
			ArrayList<Integer> outputBinIdsForMultipliCalc = new ArrayList<Integer>();
			outputBinIdsForMultipliCalc.add(0);
			outputBinIdsForMultipliCalc.add(1);
			outputBinIdsForMultipliCalc.add(2);
			int chipValue1 = 444;
			int chipValue2 = 17;		
			AdventProblem10 adventProblem10 = new AdventProblem10("AppData\\Prob10Input.txt");
			System.out.println("******************** Answer to Problem 10A*************************************************");			
			int botIdWithSpecificChipsValues = adventProblem10.findBotIdsWithSpecificChipValues(chipValue1, chipValue2);	
			System.out.println("Bot Id " + botIdWithSpecificChipsValues + " has microchips with values " + chipValue1 + " & " + chipValue2);			
			System.out.println("******************** Answer to Problem 10B*************************************************");
			//Problem 2016-10B
			long multiplicationOfOutBinsChips = adventProblem10.multipliOfOutBinsChipsVal(outputBinIdsForMultipliCalc);			
			System.out.println("Multiplication of output bins' first chip's value is " + multiplicationOfOutBinsChips + "\n");	
			
			assertEquals(-1, botIdWithSpecificChipsValues);
			assertEquals(4042, multiplicationOfOutBinsChips);				
		} catch (FileNotFoundException e) {
			System.out.println("Data file does not exist");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument were passed");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testProblem10ABInvalidOutputBinIds() {
		try{
			System.out.println("******************************************************************************************");
			System.out.println("Problem 2016-10");	
			ArrayList<Integer> outputBinIdsForMultipliCalc = new ArrayList<Integer>();
			outputBinIdsForMultipliCalc.add(0);
			outputBinIdsForMultipliCalc.add(88888);
			outputBinIdsForMultipliCalc.add(99999);
			int chipValue1 = 61;
			int chipValue2 = 17;		
			AdventProblem10 adventProblem10 = new AdventProblem10("AppData\\Prob10Input.txt");
			System.out.println("******************** Answer to Problem 10A*************************************************");			
			int botIdWithSpecificChipsValues = adventProblem10.findBotIdsWithSpecificChipValues(chipValue1, chipValue2);	
			System.out.println("Bot Id " + botIdWithSpecificChipsValues + " has microchips with values " + chipValue1 + " & " + chipValue2);			
			System.out.println("******************** Answer to Problem 10B*************************************************");
			//Problem 2016-10B
			long multiplicationOfOutBinsChips = adventProblem10.multipliOfOutBinsChipsVal(outputBinIdsForMultipliCalc);			
			System.out.println("Multiplication of output bins' first chip's value is " + multiplicationOfOutBinsChips + "\n");	
			
			assertEquals(98, botIdWithSpecificChipsValues);
			assertEquals(-1, multiplicationOfOutBinsChips);				
		} catch (FileNotFoundException e) {
			System.out.println("Data file does not exist");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument were passed");
			e.printStackTrace();
		}		
	}		
}		