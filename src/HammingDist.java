import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HammingDist
{
	/**
	 * The initial array to hold stationIDs
	 */
	private String stationID[] = new String[15];
	
	/**
	 * Hold the string NRMN to compare to inputs
	 */
	private String norman = "NRMN";
	
	/**
	 * Holds the first input string to be compared
	 */
	private String one;
	
	/**
	 * Holds the second input string to be compared
	 */
	private String two;
	
	/**
	 * The Hamming Distance between the first ID and NRMN
	 */
	private int distOne;
	
	/**
	 * The Hamming Distance between the second ID and NRMN
	 */
	private int distTwo;
	
	/**
	 * Hold the number of stations that are the same Hamming 
	 * Distance away from one as NRMN is
	 */
	private int equalDistOne;
	
	/**
	 * Hold the number of stations that are the same Hamming 
	 * Distance away from two as NRMN is
	 */
	private int equalDistTwo;
	
	/**
	 * Constructor for HammingDist.  Takes in two strings and obtains the 
	 * Hamming Distance between a variety of other strings as well as "NRMN".
	 * Stores the information to the object's appropriate variables.
	 * @param one The first ID entered in the format "ABCD"
	 * @param two The second ID entered in the format "ABCD"
	 * @throws Exception 
	 */
	public HammingDist(String one, String two) throws Exception
	{
		this.one = one;
    	this.two = two;
    	this.distOne = compareID(one);
    	this.distTwo = compareID(two);
    	read("Mesonet.txt");
    	this.equalDistOne = compareAllID(distOne, one);
    	this.equalDistTwo = compareAllID(distTwo, two);
	}
	
	/**
	 * This method compares a string input to the string "NRMN"
	 * and returns the Hamming Distance between the two.
	 * @param id The string to be compared to "NRMN"
	 * @return The Hamming Distance between the two strings.
	 */
	public int compareID(String id)
	{
		int count = 0;
		for(int i = 0; i < 4; i++)
		{
			if (!((one.charAt(i)) == (norman.charAt(i))))
			{
				count += 1;
			}
		}
		return count;
	}
	
	/**
	 * This method compares a string input to all of the station
	 * IDs stored in the array stationID and determines how many
	 * station IDs are the same Hamming Distance from id that 
	 * "NRMN" is.
	 * @param distance The Hamming Distance between id and "NRMN"
	 * @param id The string to be compared to stationID
	 * @return The number of station IDs that share the same Hamming
	 * Distance with id that "NRMN" does.
	 */
	public int compareAllID(int distance, String id)
	{
		int count = 0;
		int stations = 0;
		for(int i = 0; i < stationID.length; i++)
		{
			String temp = stationID[i];
			count = 0;
			for(int j = 0; j < 4; j++)
			{
				if ((id.charAt(j)) != (temp.charAt(j)))
				{
					count += 1;
				}
			}
			if (count == distance)
			{
				stations += 1;
			}
		}
		return stations;
	}
	
	/**
	 * This method reads from a file and sorts through to find the 
	 * station IDs in the document.  It then stores each station 
	 * ID in the stationID array, enlarging it when necessary.
	 * @param fileName The file to be read into stationID
	 * @throws Exception
	 */
	public void read(String fileName) throws Exception
	{
		Scanner sc = new Scanner(new File (fileName));
		String strg = sc.nextLine();
		for(int j = 0; j < 5; j++)
		{
			strg = sc.nextLine();
		}

		int i = 0;
		while (sc.hasNextLine())
		{
			strg = sc.next();
				
			if (i < stationID.length)
			{
				stationID[i] = strg;
			}
			else
			{
				stationID = increaseArray(stationID);
				stationID[i] = strg;
			}
			i++;
			strg = sc.nextLine();
		}
		
		sc.close();
	}
	
	/**
	 * This methods makes an array one element larger while maintaining
	 * the original array's contents
	 * 
	 * @param array The array that will be incremented.
	 * @return A string array that contains the same contents as the input
	 * array, but is larger by one empty element.
	 */
	public String[] increaseArray(String[] array)
	{
		String[] newArray = new String[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, (array.length));
		return newArray;
	}
	
	/**
     * toString override. Gives all information about the Hamming Distance.
     * 
     * @return All information about The Hamming Distance in the format:
     * The Hamming Distance between Norman and <one> is <distOne> and for <two>: <distTwo>. 
     * For <one>, number of stations with Hamming Distance <distOne> is <equalDistOne>, and 
     * for <two>, number of stations with Hamming Distance <distTwo> is <equalDistTwo>.
     */
    @Override
	public String toString()
	{
		return String.format
				("The Hamming Distance between Norman and %s is %d and for %s: %d.\n" + 
				"For %s, number of stations with Hamming Distance %d is %d, and\n" + 
				"for %s, number of stations with Hamming Distance %d is %d.", 
				one, distOne, two, distTwo, one, distOne, equalDistOne,
				two, distTwo, equalDistTwo);
	}
}