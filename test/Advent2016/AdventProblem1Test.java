package Advent2016;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AdventProblem1Test {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProblem1AB() {
		Problem1Results problem1Results = null;
		try {
			System.out.println("******************************************************************************************");
			System.out.println("Problem 2016-1");			
			problem1Results = AdventProblem1.problem1AB("AppData\\Prob1AInput.txt");
			System.out.println("******************** Answer to Problem 1A*************************************************");	
			System.out.println("Total net blocks distance traveled is " + problem1Results.totalNetBlocksDis);
			System.out.println("******************** Answer to Problem 1B**************************************************");			    		
			System.out.println("# of blocks need to walk to already traveled block junction from starting point is " + problem1Results.alreadyTraveledBlockNetDis);
			
			assertEquals(273, problem1Results.totalNetBlocksDis);
			assertEquals(115, problem1Results.alreadyTraveledBlockNetDis);
		} catch (FileNotFoundException e) {
			System.out.println("Data file does not exist");
			e.printStackTrace();
			assertEquals(null, problem1Results);		
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument were passed");
			e.printStackTrace();
			assertEquals(null, problem1Results);			
		}			
	}
	
	@Test
	public void testProblem1ABInvalidFile() {
		Problem1Results problem1Results = null;
		try {
			System.out.println("******************************************************************************************");
			System.out.println("Problem 2016-1");			
			problem1Results = AdventProblem1.problem1AB("AppData\\fileDoesNotExists.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Data file does not exist");
			e.printStackTrace();
			assertEquals(null, problem1Results);
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument were passed");
			e.printStackTrace();
			assertEquals(null, problem1Results);		
		}		
	}	
}
