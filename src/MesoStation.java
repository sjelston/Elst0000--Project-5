import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class MesoStation implements Comparator<MesoStation> 
{
	private String stID;
	private ArrayList<String> stationID;
	
	public MesoStation(String stId) throws IOException
	{
		this.stID = stId;
		stationID = read("Mesonet.txt");
	}

	public String getStID() {
		return stID;
	}
	
	@Override
	public String toString()	{
		return stID;
	}
	
	/**
	 * This method compares a string input to the stID of
	 * the current MesoStation object and returns the 
	 * Hamming Distance between the two.
	 * 
	 * @param id The string to be compared
	 * @return The Hamming Distance between the two strings.
	 */
	public int compareID(String id)
	{
		int count = 0;
		for(int i = 0; i < 4; i++)
		{
			if (!((id.charAt(i)) == (stID.charAt(i))))
			{
				count += 1;
			}
		}
		return count;
	}
	
	public ArrayList<String> compareAllID(int distance)
	{
		ArrayList<String> id = new ArrayList<String>();
		int count = 0;
		for(int i = 0; i < stationID.size(); i++)
		{
			String temp = stationID.get(i);
			count = 0;
			for(int j = 0; j < 4; j++)
			{
				if ((stID.charAt(j)) != (temp.charAt(j)))
				{
					count += 1;
				}
			}
			if (count == distance)
			{
				id.add(temp);
			}
		}
		return id;
	}
	
	public int compareNum(int distance)
	{
		int count = 0;
		int stations = 0;
		for(int i = 0; i < stationID.size(); i++)
		{
			String temp = stationID.get(i);
			count = 0;
			for(int j = 0; j < 4; j++)
			{
				if ((stID.charAt(j)) != (temp.charAt(j)))
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
	
	public ArrayList<String> read(String fileName) throws IOException
	{
		ArrayList<String> stationID = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String ID = "";
		String strg = "";

		while (!((strg = br.readLine()) == null))	{
			for (int j = 0; j < 4; j++)	{
				ID += strg.charAt(j);
			}
			stationID.add(ID);
			ID = "";
		}
		br.close();
		return stationID;
	}

	@Override
	public int compare(MesoStation o1, MesoStation o2) {
		return (o1.getStID().compareTo(o2.getStID()));
	}
}