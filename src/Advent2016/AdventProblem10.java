package Advent2016;

import java.io.File;
import java.util.*;
import java.io.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

/** This class solves Bots and microchips problem (Advent of code 2016-Problem 10A & 10B) 
 * @author Pankaj Goyal
 * @version 1.0
 * @since 1.0
*/
public class AdventProblem10 {

	private ArrayList<ChipFromInputToBot> chipFromInputToBotInstructList = new ArrayList<>();

	// HashMap Key contains Bots' details e.g. chip(s) value etc
	private HashMap<Integer, BotDetails> botsExistingChipsDetailsHashMap = new HashMap<>();

	// HashMap Key contains instructions for chips to given by Bots
	private HashMap<Integer, ChipsGivenByBotInstruct> chipGivenByBotInstructHashMap = new HashMap<>();

	// HashMap Key contains Output bins chips
	private HashMap<Integer, ArrayList<Integer>> outputBinsHashMap = new HashMap<>();

	// Create and initialize a Queue containing Bot Ids with 2 chips to be processed using a LinkedList
	private Queue<Integer> waitingQueue = new LinkedList<>();
	
	// HashMap contains first bot Id which contained 2 chips with specific chip values. Key is in form "[lowChipValue, highChipVal]
	private HashMap<String, Integer> listOfBotIdWithChipValuesHashMap = new HashMap<>();	

	//private boolean foundTheBot = false;
	
	/**
	* Solves Bots and microchips problem (Advent of code 2016-Problem 10A & 10B)
	* Parameters chipValue1 and chipValue2 are for problem's part A and 
	* listOutBinIdsForMultipliCalc is for problem's part B
	*
	* @param  String  				dataFileName
	* @param  int  	  				chipValue1
	* @param  int  	  				chipValue2
	* @param  ArrayList<Integer>  	listOutBinIdsForMultipliCalc
	*  
	* @see         Problem10Results
	*/	
	public  AdventProblem10(String dataFileName) throws FileNotFoundException, IllegalArgumentException {
		Scanner fileReader = null;
		try {
			//System.out.println("********************************************************************");
			//System.out.println("Problem 2016-10");
			if(dataFileName == null || dataFileName.isEmpty())
				throw new IllegalArgumentException("Invalid data file name");		

			// "AppData\\Prob10Input.txt"
			File fileObj = new File(dataFileName);
	        if (!fileObj.exists()) {
	            System.out.println("Data file does not Exists");
	            throw new IllegalArgumentException("Invalid data file name");
	        }				
			fileReader = new Scanner(fileObj);
			String instruction = "";
			String instructBeginningWord1 = "value";
			String instructBeginningWord2 = "bot";
			
			ArrayList<String> allInstructions = new ArrayList<String>();			

			while (fileReader.hasNextLine()) {
				instruction = fileReader.nextLine();
				allInstructions.add(instruction.toLowerCase());
			}
			fileReader.close();
			fileReader = null;
			
			for (int i = 0; i < allInstructions.size(); i++) {
				instruction = allInstructions.get(i);			
				// System.out.println(instruction);
				String[] arrOfStr = instruction.strip().split(" ");
				if (arrOfStr[0].strip().contentEquals(instructBeginningWord1)) {
					// Example: value 23 goes to bot 138
					ChipFromInputToBot chipFromInputToBot = new ChipFromInputToBot();
					chipFromInputToBot.chipVal = Integer.parseInt(arrOfStr[1].strip());
					chipFromInputToBot.givenToBotId = Integer.parseInt(arrOfStr[5].strip());

					chipFromInputToBotInstructList.add(chipFromInputToBot);
				} else if (arrOfStr[0].strip().contentEquals(instructBeginningWord2)) {
					// Example: bot 127 gives low to output 1 and high to bot 180
					ChipsGivenByBotInstruct chipsGivenByBotInstruct = new ChipsGivenByBotInstruct();
					chipsGivenByBotInstruct.donorBotId = Integer.parseInt(arrOfStr[1].strip());
					chipsGivenByBotInstruct.lowValChipGivenToCategory = arrOfStr[5].strip();
					if (arrOfStr[5].strip().contentEquals("bot") == false
							&& arrOfStr[5].strip().contentEquals("output") == false)
						System.out.println("Not output or bot");

					chipsGivenByBotInstruct.lowValChipGivenToBotOrOutputId = Integer.parseInt(arrOfStr[6].strip());
					chipsGivenByBotInstruct.highValChipGivenToCategory = arrOfStr[10].strip();
					if (arrOfStr[10].strip().contentEquals("bot") == false
							&& arrOfStr[10].strip().contentEquals("output") == false)
						System.out.println("2 Not output or bot");
					chipsGivenByBotInstruct.highValChipGivenToBotOrOutputId = Integer.parseInt(arrOfStr[11].strip());

					chipGivenByBotInstructHashMap.put(chipsGivenByBotInstruct.donorBotId, chipsGivenByBotInstruct);
				} 
			}
			//int totalInstructionLines = chipFromInputToBotInstructList.size() + chipGivenByBotInstructHashMap.size();
			//System.out.println("Total parsed instuction lines are: " + totalInstructionLines);
			for (int i = 0; i < chipFromInputToBotInstructList.size(); i++) {
				processBotsQueueBotWith2Chips();
				ChipFromInputToBot chipFromInputToBot = chipFromInputToBotInstructList.get(i);
				processBotGotChip(chipFromInputToBot.givenToBotId, chipFromInputToBot.chipVal);
			}
		} catch (FileNotFoundException e) {
			System.out.println("An file related exception occurred.");
			e.printStackTrace();
			throw new IllegalArgumentException("Make sure data file exists");
		} catch (NumberFormatException e) {
			System.out.println("Invalid data format in data file");
			e.printStackTrace();
			throw new IllegalArgumentException("Invalid data format in data file");			
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid data file name");
			e.printStackTrace();
			throw new IllegalArgumentException("Illegal Argument were passed");
		} catch (NullPointerException e) {
			System.out.println("Null pointer exception occurred.");
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.out.println("An exception occurred.");
			e.printStackTrace();
			throw e;
		}
		finally {
			if(fileReader != null)
				fileReader.close();
		}		
	}
	
	public int findBotIdsWithSpecificChipValues(int chipValue1, int chipValue2)	{
		if(chipValue1 < 0 || chipValue2 < 0)
			throw new IllegalArgumentException("Invalid argument(s)");			
		int minChipValue = chipValue1 < chipValue2? chipValue1: chipValue2;
		int maxChipValue = chipValue1 < chipValue2? chipValue2: chipValue1;	
		
		String hashMapKey = "[" + Integer.toString(minChipValue) + "," + Integer.toString(maxChipValue)  + "]";		
		Integer firstBotIdWithSpecificChipIds = listOfBotIdWithChipValuesHashMap.get(hashMapKey);
		if(firstBotIdWithSpecificChipIds != null) 
			return firstBotIdWithSpecificChipIds;
		else
			return -1;
	}	

	public long multipliOfOutBinsChipsVal(ArrayList<Integer> listOutBinIdsForMultipliCalc){
		if(listOutBinIdsForMultipliCalc == null || listOutBinIdsForMultipliCalc.size() == 0)
			throw new IllegalArgumentException("Invalid argument(s)");			
		long value = 1;
		if(listOutBinIdsForMultipliCalc != null) {
			for (int i = 0; i < listOutBinIdsForMultipliCalc.size(); i++) {
				if(outputBinsHashMap.get(listOutBinIdsForMultipliCalc.get(i)) != null) {
					 value *= outputBinsHashMap.get(listOutBinIdsForMultipliCalc.get(i)).get(0); 
				}
				else
					return -1;
			}
			//System.out.println("********************************************************************");
			//System.out.println("Multiplication of output bins' first chip's value is " + value);
			return value;			
		}
		return -1;
	}	

	private void processBotGotChip(int botId, int chipVal) {
		//System.out.println("Bot number " + botId + " is getting microchip with value " + chipVal);
		BotDetails botCurrentState = botsExistingChipsDetailsHashMap.get(botId);
		if (botCurrentState != null) {
			if (botCurrentState.chipCount == 0) {
				botCurrentState.lowChipVal = chipVal;
				botCurrentState.chipCount++;
					System.out.println("Bot number " + botCurrentState.id + " chipCount: " + botCurrentState.chipCount 	+ " lowChipVal:" + botCurrentState.lowChipVal + " highChipVal:"	+ botCurrentState.highChipVal);

			} else if (botCurrentState.chipCount == 1) {
				if (botCurrentState.lowChipVal > chipVal) {
					botCurrentState.highChipVal = botCurrentState.lowChipVal;
					botCurrentState.lowChipVal = chipVal;
				} else
					botCurrentState.highChipVal = chipVal;
				botCurrentState.chipCount++;
				//System.out.println("Bot number " + botCurrentState.id + " chipCount: " + botCurrentState.chipCount 	+ " lowChipVal:" + botCurrentState.lowChipVal + " highChipVal:" + botCurrentState.highChipVal);
				// Now bot has 2 chips so store botId with these chips values and then it can distribute chips
				storeBotIdsWithSpecificChipValues(botCurrentState);
				waitingQueue.add(botCurrentState.id);
				processBotsQueueBotWith2Chips();
			}
		} else {
			BotDetails botDetails = new BotDetails();
			botDetails.id = botId;
			botDetails.lowChipVal = chipVal;
			botDetails.chipCount = 1;
			//System.out.println("Bot number " + botDetails.id + " chipCount: " + botDetails.chipCount + " lowChipVal:" + botDetails.lowChipVal + " highChipVal:" + botDetails.highChipVal);
			// Add keys and values (BotId, BotDetails)
			botsExistingChipsDetailsHashMap.put(botDetails.id, botDetails);
		}
	}

	private void processBotsQueueBotWith2Chips() {
		Integer botId = null;
		while (true) {
			botId = waitingQueue.poll();
			if (botId == null)
				return;
			BotDetails botCurrentState = botsExistingChipsDetailsHashMap.get(botId);
			//System.out.println("Bot Id " + botCurrentState.id + " has microchips with values " + botCurrentState.lowChipVal + " and " + botCurrentState.highChipVal);
			ChipsGivenByBotInstruct chipsGivenByBotInstruct = chipGivenByBotInstructHashMap.get(botId);

			if (chipsGivenByBotInstruct != null) {
				int tempLowChipVal = botCurrentState.lowChipVal;
				botCurrentState.lowChipVal = -1;
				int tempHighChipVal = botCurrentState.highChipVal;
				botCurrentState.highChipVal = -1;
				botCurrentState.chipCount = 0;
				if (chipsGivenByBotInstruct.lowValChipGivenToCategory.contentEquals("bot"))
					processBotGotChip(chipsGivenByBotInstruct.lowValChipGivenToBotOrOutputId, tempLowChipVal);
				else
					processOutputBinGotChip(chipsGivenByBotInstruct.lowValChipGivenToBotOrOutputId, tempLowChipVal);
				if (chipsGivenByBotInstruct.highValChipGivenToCategory.contentEquals("bot")) 
					processBotGotChip(chipsGivenByBotInstruct.highValChipGivenToBotOrOutputId, tempHighChipVal);
				else
					processOutputBinGotChip(chipsGivenByBotInstruct.highValChipGivenToBotOrOutputId, tempHighChipVal);
			}
		}
	}

	private void processOutputBinGotChip(int outputBinId, int chipVal)
	{	
		ArrayList<Integer> outputBinChipsList = outputBinsHashMap.get(outputBinId);
		if(outputBinChipsList != null) {
			outputBinChipsList.add(chipVal);
		}
		else {
			outputBinChipsList = new ArrayList<Integer>();
			outputBinChipsList.add(chipVal);
			outputBinsHashMap.put(outputBinId, outputBinChipsList);// Add keys and values (outputBinId, ArrayListOfChipsInOutputBin)
		}				
	}

	private void storeBotIdsWithSpecificChipValues(BotDetails botWith2Chips)
	{	
		String hashMapKey = "[" + Integer.toString(botWith2Chips.lowChipVal) + "," + Integer.toString(botWith2Chips.highChipVal)  + "]";	
		//System.out.println("Bot Id " + botWith2Chips.id + " has microchips with values " + botWith2Chips.lowChipVal + " & " + botWith2Chips.highChipVal);
		
		Integer firstBotIdWithSpecificChipIds = listOfBotIdWithChipValuesHashMap.get(hashMapKey);
		if(firstBotIdWithSpecificChipIds == null)
			listOfBotIdWithChipValuesHashMap.put(hashMapKey, (Integer)botWith2Chips.id);//
	}
	
	public static Integer tryParse(String text) {
		  try {
		    return Integer.parseInt(text);
		  } catch (NumberFormatException e) {
		    return null;
		  }
	}
}
