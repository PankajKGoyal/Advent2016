package Advent2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/** This class solves Easter Bunny blocks problem (Advent of code 2016-Problem 1A & 1B)
 * @author Pankaj Goyal
 * @version 1.0
 * @since 1.0
*/
public class AdventProblem1{
	
	/**
	* Solves Easter Bunny blocks problem (Advent of code 2016-Problem 1A & 1B)
	*
	* @param  String  dataFileName
	* @return      answers to part A & B
	* @see         Problem1Results
	*/	
	public static Problem1Results problem1AB(String dataFileName) throws FileNotFoundException, IllegalArgumentException {
		Problem1Results problem1Results = new Problem1Results();
		Scanner fileReader = null;
		try {
			//System.out.println("Problem 2016-1A & 1B");
			if(dataFileName == null || dataFileName.isEmpty())
				throw new IllegalArgumentException("Invalid data file name");			
			File fileObj = new File(dataFileName);//"AppData\\Prob1AInput.txt"
	        if (!fileObj.exists()) {
	            System.out.println("Data file does not Exists");
	            throw new IllegalArgumentException("Invalid data file name");
	        }
			fileReader = new Scanner(fileObj);
			String stepsData = "";
			while (fileReader.hasNextLine()) {
				stepsData += fileReader.nextLine();
				//System.out.println(stepsData);
			}
			fileReader.close();
			fileReader = null;
			
			Set<String> traversedPath = new HashSet<>();
			boolean pathTraveledAlready = false;
	        String[] steps = stepsData.strip().toUpperCase().split(",");
			char curDirection = 'N'; //'N' = North, 'E' = East, 'S' = South, 'W' = West
			int netXAxisMovement = 0;
			int netYAxisMovement = 0;
			for (String step : steps) {
			    step = step.strip();
			    if (step.charAt(0) == 'R') { //Right direction turn movement
			        switch (curDirection) {
			            case 'N':  	curDirection = 'E';
			                     	break;
			            case 'E':  	curDirection = 'S';
			                     	break;
			            case 'S':  	curDirection = 'W';
			                     	break;
			            case 'W':  	curDirection = 'N';
			            			break;
			        }	
			    }
 			    else {//Left direction turn movement
			        switch (curDirection) {
			            case 'N':  	curDirection = 'W';
			                     	break;
			            case 'E':  	curDirection = 'N';
			                     	break;
			            case 'S':  	curDirection = 'E';
			                     	break;
			            case 'W':  	curDirection = 'S';
			            			break;
			        }	
 			    }
			    int dist = Integer.parseInt(step.substring(1));
			    for (int i = 1; i <= dist; i++){
			    	if(curDirection == 'N')
			    	   	netYAxisMovement += 1;
			    	else if(curDirection == 'S')
			    		netYAxisMovement += -1;
			    	else if(curDirection == 'E')
			    		netXAxisMovement += 1;
			    	else if(curDirection == 'W')
			    		netXAxisMovement += -1;			    		
		    	
			    	if(!pathTraveledAlready)
			    	{
				    	if (traversedPath.contains("[" + Integer.toString(netXAxisMovement) + "," + Integer.toString(netYAxisMovement)  + "]"))
				    	{ 
				    		//System.out.println("******************** Answer to Problem 1B****************************************************");			    		
				    		//System.out.println("Already traveled this path junction with block coordinates(X,Y): " + Integer.toString(netXAxisMovement) + "," + Integer.toString(netYAxisMovement));
				    		problem1Results.alreadyTraveledBlockNetDis = Math.abs(netXAxisMovement) + Math.abs(netYAxisMovement);
				    		//System.out.println("# of blocks need to walk to already traveled block junction from starting point is " + problem1Results.alreadyTraveledBlockNetDistance );
				    		pathTraveledAlready = true;
				    	}
			    	}
			    	traversedPath.add("[" + Integer.toString(netXAxisMovement) + "," + Integer.toString(netYAxisMovement) + "]");
			    }
			}
			//System.out.println("Path coordinates already crossed: " + traversedPath);
			//System.out.println("******************** Answer to Problem 1A***************************");	
			problem1Results.totalNetBlocksDis = Math.abs(netXAxisMovement) + Math.abs(netYAxisMovement);
			//System.out.println("Total net blocks distance traveled is " + problem1Results.totalNetBlocksDistance);
		} catch (FileNotFoundException e) {
			System.out.println("An file related exception occurred.");
			e.printStackTrace();
			throw new IllegalArgumentException("Make sure data file exists");
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid data file name");
			e.printStackTrace();			
			throw new IllegalArgumentException("Illegal Argument were passed");			
		} catch (NullPointerException e) {
			System.out.println("Null pointer exception occurred.");
			e.printStackTrace();
			throw new NullPointerException("Make sure data file exists");
		} catch (Exception e) {
			System.out.println("An exception occurred.");
			e.printStackTrace();
		}
		finally {
			if(fileReader != null)
				fileReader.close();
		}		
		return problem1Results;
	}	
}
