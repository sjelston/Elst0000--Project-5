# Elst0000--Project-5
CS 2334 Project 5 Code

**General Behavior**

This project was designed to compute Hamming Distances for a given 4-character station ID as well as give some additional relevant information based on station IDs from the Mesonet.txt file.  This program was designed to do so with the use of multiple GUI components from JavaFX.

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
																	
	public String[] increaseArray(String[] array)			Enlarges the Array by one
	
	public int compare(MesoStation o1, MesoStation o2)		Comparator for MesoStation objects
																	
**Driver.java**				*Extends Application* 

Variables:
	
	private ArrayList<MesoStation> stations			Private ArrayList to hold MesoStations

Methods:

	public MesoStation(String stId)				Constructor for MesoStation object, sets private field stID.	
	
	public void start(Stage primaryStage) throws Exception		Implementation of method from the 
	
	public ArrayList<MesoStation> read(String fileName)	This method reads from a file and sorts through to find the station 
															IDs in the document.  It then stores each station ID in the stationID 
															array, enlarging it when necessary.									
