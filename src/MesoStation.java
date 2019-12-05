public class MesoStation 
{
	private String stID;
	
	public MesoStation(String stId)
	{
		this.stID = stId;
	}

	public String getStID() {
		return stID;
	}
	
	@Override
	public String toString()	{
		return stID;
	}
}