package Advent2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** This class solves Encrypted Rooms Name problem (Advent of code 2016-Problem 4A & 4B) 
 * @author Pankaj Goyal
 * @version 1.0
 * @since 1.0
*/
public class AdventProblem4 {
	private long sumOfSectorIdsOfValidRoomName = -1;
	private ArrayList<RoomNameDetails> roomsDetails = null;

	/**
	* Solves Encrypted Rooms Name problem (Advent of code 2016-Problem 4A & 4B)
	* Parameter rmSpecificDecryptName is for problem's part B
	*
	* @param  String  dataFileName
	* @param  String  rmSpecificDecryptName
	* 
	* @see         Problem4Results
	*/		
	public AdventProblem4(String dataFileName) throws FileNotFoundException, IllegalArgumentException{
		Scanner fileReader = null;
		try {
			if(dataFileName == null || dataFileName.isEmpty())
				throw new IllegalArgumentException("Invalid data file name");	
			
			File fileObj = new File(dataFileName); // "AppData\\Prob4AInput.txt"
	        if (!fileObj.exists()) {
	            System.out.println("Data file does not Exists");
	            throw new IllegalArgumentException("Invalid data file name");
	        }			
			fileReader = new Scanner(fileObj);
			roomsDetails = new ArrayList<RoomNameDetails>();
			long totalSectorIdsValidRmNames = 0;

			while (fileReader.hasNextLine()) {
				RoomNameDetails room = new RoomNameDetails();
				room.roomFullName = fileReader.nextLine().toLowerCase();
				//System.out.println(room.roomFullName);
				roomsDetails.add(room);
			}
			fileReader.close();
			fileReader = null;

			for (int i = 0; i < roomsDetails.size(); i++) {
				RoomNameDetails room = roomsDetails.get(i);
				String roomFullName = room.roomFullName;
				//System.out.println(room.roomFullName);
				int idxOfIstDashFromBk = roomFullName.lastIndexOf('-');
				int idxOfIstLeftBracketFromBk = roomFullName.lastIndexOf('[');
				String oriNameOnlyWithDashes = roomFullName.substring(0, idxOfIstDashFromBk);
				//System.out.println("Original Name Only:- " + room.oriNameOnlyWithDashes);

				String oriNameOnlyWithoutDashes = roomFullName.substring(0, idxOfIstDashFromBk).replace("-", "");
				//System.out.println("Original Name Only Without Dashes:- " + oriNameOnlyWithoutDashes);

				room.sectorId = Integer.parseInt(roomFullName.substring(idxOfIstDashFromBk + 1, idxOfIstLeftBracketFromBk));
				//System.out.println("Sector ID:- " + room.sectorId);

				room.checkSum = roomFullName.substring(idxOfIstLeftBracketFromBk + 1, idxOfIstLeftBracketFromBk + 6);
				//System.out.println("CheckSum from data file:- " + room.checkSum);
				ArrayList<CharsCountInString> arrCharsCountInStr = charsfreqCount(oriNameOnlyWithoutDashes);

				Comparator<CharsCountInString> compareByCountInAlphaOrder = Comparator
						.comparing(CharsCountInString::getCount).reversed()
						.thenComparing(CharsCountInString::getCharacter);

				List<CharsCountInString> sortedCharsCount = arrCharsCountInStr.stream()
						.sorted(compareByCountInAlphaOrder).collect(Collectors.toList());
				// System.out.println("Sorted list size:" + sortedCharsCount.size());
				//for (int i = 0; i < sortedCharsCount.size(); i++) 
				//	// System.out.println(sortedCharsCount.get(i).ch + ": " + sortedCharsCount.get(i).count);
				String strGenCheckSum = "";
				for (int j = 0; j <= 4; j++) {
					// System.out.println(sortedCharsCount.get(i).ch + ": " + sortedCharsCount.get(i).count);
					strGenCheckSum += sortedCharsCount.get(j).ch;
				}
				//System.out.println("Calculated Checksum:" + strGenCheckSum);
				if (room.checkSum.equals(strGenCheckSum)) {
					//System.out.println(room.checkSum + " in data file and generated checksum " + strGenCheckSum + " are same.");
					room.IsRoomReal = true;
					totalSectorIdsValidRmNames += room.sectorId;
				}
				String strTempDecryptedName = oriNameOnlyWithDashes.replace("-", " ");
				//System.out.println("Temp decrypted Original Name Only:- " + strTempDecryptedName);
				if(room.IsRoomReal) {
					room.decryptedName = shiftLowercaseStr(strTempDecryptedName, room.sectorId);
					//System.out.println("Decrypted Name Only:- " + room.decryptedName);
				}
				else
					room.decryptedName = strTempDecryptedName;
			}				
			//System.out.println("Number of Rooms:" + roomsDetails.size());
			sumOfSectorIdsOfValidRoomName = totalSectorIdsValidRmNames;
			//System.out.println("Sum of Sector Ids of Rooms with valid room names: " + totalSectorIdsValidRmNames);
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
	}

	public long getSumOfSecIdsOfValidRooms() {
		return sumOfSectorIdsOfValidRoomName;
	}
	
	public int getSecIdOfRmSpecificDecryptName(String rmSpecificDecryptName) throws IllegalArgumentException{
		int secIdOfRmSpecificDecryptName = -1;	
		try {
			if(rmSpecificDecryptName == null || rmSpecificDecryptName.isEmpty())
				throw new IllegalArgumentException("Invalid decrypted room name");	
			
			for (int i = 0; i < roomsDetails.size(); i++) {
				RoomNameDetails room = roomsDetails.get(i);	
				if(room.IsRoomReal) {
					if(room.decryptedName.equalsIgnoreCase(rmSpecificDecryptName.strip())) {
						//System.out.println("Found the room with specific decrypted room name: " + room.decryptedName);
						//System.out.println("SectorId of the room is " + room.sectorId);
						secIdOfRmSpecificDecryptName = room.sectorId;
						return secIdOfRmSpecificDecryptName;
					}					
				}				
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid data file name");
			e.printStackTrace();	
			throw new IllegalArgumentException("Illegal Argument were passed");
		} catch (Exception e) {
			System.out.println("An exception occurred in problem 4 in gettSecIdOfRmSpecificDecryptName method.");
			e.printStackTrace();
			throw e;
		}
		return secIdOfRmSpecificDecryptName;
	}	
	
	private static String shiftLowercaseStr(String str, int numOfCharsToShift) {
		byte actaulNumOfCharsToShift = (byte) (numOfCharsToShift % 26); // there are 26 alphabets
		int strLen = str.length();
		int idx = 0;
		while (idx < strLen) {
			char oriChar = str.charAt(idx);
			if (oriChar != ' ') {
				char newChar = (char) (oriChar + actaulNumOfCharsToShift);
				newChar = newChar > 122 ? (char) (newChar % 123 + 97) : newChar;
				str = str.substring(0, idx) + newChar + str.substring(idx + 1);
			}
			idx++;
		}
		return str;
	}

	private static ArrayList<CharsCountInString> charsfreqCount(String str) {
		ArrayList<CharsCountInString> arrCharsCountInStr = new ArrayList<CharsCountInString>();

		while (str.length() > 0) {
			char ch = str.charAt(0);
			CharsCountInString chCount = new CharsCountInString();

			chCount.ch = ch;
			chCount.count = countChar(str, ch);
			// System.out.println(ch + " " + chCount.count);
			arrCharsCountInStr.add(chCount);
			str = str.replace("" + ch, "");
		}
		// System.out.println("ArrayList Size = " + arrCharsCountInStr.size());
		return arrCharsCountInStr;
	}

	private static int countChar(String str, char ch) {
		int count = 0;
		while (str.indexOf(ch) != -1) {
			count++;
			str = str.substring(str.indexOf(ch) + 1);
		}
		return count;
	}
}
