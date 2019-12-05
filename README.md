# Elst0000--Project-5
CS 2334 Project 5 Code



### Classes

**MesoStation.java**

Variables:
	
	private String stID					Private field to hold the object's station ID.
	
	private ArrayList<String> stationID			Private ArrayList to hold station strings

Methods:

	public MesoStation(String stId)				Constructor for MesoStation object, sets private field stID.
	
	public String getStID()					Getter method to retrieve stID.
	
	public int compareID(String id)				This method compares a string input to the stID of the current 
															MesoStation object and returns the Hamming Distance between the two.
	
	public ArrayList<String> compareAllID(int distance)		Compares ID to all others and returns those that 
																	match the specified distance.
																	
**Driver.java**				*Extends Application*

Variables:
	
	private ArrayList<MesoStation> stations			Private ArrayList to hold MesoStations

Methods:

	public MesoStation(String stId)				Constructor for MesoStation object, sets private field stID.	
	
	public void start(Stage primaryStage) throws Exception		Implementation of method from the 
	
	public ArrayList<MesoStation> read(String fileName)	This method reads from a file and sorts through to find the station 
															IDs in the document.  It then stores each station ID in the stationID 
															array, enlarging it when necessary.									
