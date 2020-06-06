package Advent2016;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AdventProblem4Test {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProblem4AB() {
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
			
			assertEquals(278221, sumOfSectorIdsOfValidRoomName);
			assertEquals(267, secIdOfRmSpecificDecryptName);			
		} catch (FileNotFoundException e) {
			System.out.println("Data file does not exist");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument were passed");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testProblem4ABInvalidFile() {
		long sumOfSectorIdsOfValidRoomName = -1;
		int secIdOfRmSpecificDecryptName = -1;
		try {
			System.out.println("******************************************************************************************");
			System.out.println("Problem 2016-4");		
			String rmSpecificDecryptName = "Northpole Object Storage";		
			AdventProblem4 adventProblem4 = new AdventProblem4("AppData\\fileDoesNotExist.txt");
			System.out.println("******************** Answer to Problem 4A*************************************************");
			sumOfSectorIdsOfValidRoomName = adventProblem4.getSumOfSecIdsOfValidRooms();
			System.out.println("Sum of Sector Ids of Rooms with valid room names: " + sumOfSectorIdsOfValidRoomName);
			secIdOfRmSpecificDecryptName = adventProblem4.getSecIdOfRmSpecificDecryptName(rmSpecificDecryptName);
			System.out.println("******************** Answer to Problem 4B*************************************************");
			System.out.println("SectorId of the room with name '" + rmSpecificDecryptName + "' is " + secIdOfRmSpecificDecryptName + "\n");
		} catch (FileNotFoundException e) {
			System.out.println("Data file does not exist");
			e.printStackTrace();
			assertEquals(-1, sumOfSectorIdsOfValidRoomName);
			assertEquals(-1, secIdOfRmSpecificDecryptName);			
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument were passed");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testProblem4ABRoomNameDoesNotExist() {
		try {
			System.out.println("******************************************************************************************");
			System.out.println("Problem 2016-4");		
			String rmSpecificDecryptName = "Pankaj Goyal Room";		
			AdventProblem4 adventProblem4 = new AdventProblem4("AppData\\Prob4AInput.txt");
			System.out.println("******************** Answer to Problem 4A*************************************************");
			long sumOfSectorIdsOfValidRoomName = adventProblem4.getSumOfSecIdsOfValidRooms();
			System.out.println("Sum of Sector Ids of Rooms with valid room names: " + sumOfSectorIdsOfValidRoomName);
			int secIdOfRmSpecificDecryptName = adventProblem4.getSecIdOfRmSpecificDecryptName(rmSpecificDecryptName);
			System.out.println("******************** Answer to Problem 4B*************************************************");
			System.out.println("SectorId of the room with name '" + rmSpecificDecryptName + "' is " + secIdOfRmSpecificDecryptName + "\n");
			
			assertEquals(278221, sumOfSectorIdsOfValidRoomName);
			assertEquals(-1, secIdOfRmSpecificDecryptName);			
		} catch (FileNotFoundException e) {
			System.out.println("Data file does not exist");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument were passed");
			e.printStackTrace();
		}
	}	
}
