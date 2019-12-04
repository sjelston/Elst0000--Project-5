import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Driver extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// read in the Mesonet text file
		ArrayList<String> stations = read("Mesonet.txt");
		 
		Scene scene = null;              			 	// Scene contains all content
		GridPane pane = null;  		 					// Positions components within scene
		Scene scene2 = null;              			  	// Scene contains all content
		GridPane gridPane2 = null;  	 				// Positions components within scene
		Scene scene3 = null;              				// Scene contains all content
		GridPane gridPane3 = null; 						// Positions components within scene
		FlowPane flow = null;
	     
	     
	    Label enterDist = null;     					// Label for "Enter Hamming Dist: "
	    Label compare = null;     						// Label for "Compare with: "
	    Label dist0 = null;								// Label for "Distance 0"
	    Label dist1 = null;								// Label for "Distance 1"
	    Label dist2 = null;								// Label for "Distance 2"
	    Label dist3 = null;								// Label for "Distance 3"
	    Label dist4 = null;								// Label for "Distance 4"
	    TextArea outputTxt = null;						// TextArea for Show Station output
	    TextArea slideTxt = null; 						// TextArea for Slider output
	    TextArea dist0Txt = null;						// TextArea for dist0
	    TextArea dist1Txt = null;						// TextArea for dist1
	    TextArea dist2Txt = null;						// TextArea for dist2
	    TextArea dist3Txt = null;						// TextArea for dist3
	    TextArea dist4Txt = null;						// TextArea for dist4
	    TextArea inputTxt = null;						// TextArea for input at the bottom
	    Slider slide = null;							// Slider to determine distance
	    ComboBox<MesoStation> dropDown = null;						// Drop down box
	    Button showStation = null;						// Show Station Button
	    Button hd = null;								// Calculate HD Button
	    Button add = null;								// Add Station Button
	    
	    //Insets gridPadding = null;
	    
	    pane = new GridPane();   						// Create an empty pane
	    pane.setVgap(10);
	    scene = new Scene(pane); 						// Create scene containing the grid pane
	    
	    enterDist = new Label("Enter Hamming Dist: ");
	    pane.add(enterDist, 0, 0);
	    
	    slideTxt = new TextArea();						// Creates the text area for slide output
	    slideTxt.setEditable(false); 					// Disables user-editting of the TextArea
	    pane.add(slideTxt, 1, 0);						// Adds the TextArea to the GridPane
	    
	    slide = new Slider();							// Creates the Slider
	    slide.setShowTickLabels(true);
	    slide.setShowTickMarks(true);
	    slide.setMajorTickUnit(1.0);
	    slide.setMinorTickCount(4);
	    slide.setBlockIncrement(1);
	    slide.setMin(1.0);
	    slide.setMax(4.0);
	    pane.add(slide, 0, 1);							// Adds Slider to the GridPane
	    
	    showStation = new Button("Show Station");		// Creates the Show Station Button
	    pane.add(showStation, 0, 2);					// Adds button to the GridPane
	    
	    outputTxt = new TextArea();						// Creates the text area for Show Station output
	    outputTxt.setMinSize(6,10);						// Sets the minimum size of the TextArea
	    outputTxt.setEditable(false); 					// Disables user-editting of the TextArea
	    pane.add(outputTxt, 1, 0);						// Adds the TextArea to the GridPane
	    
	    compare = new Label("Compare with: ");			// Creates Compare with: Label
	    pane.add(compare, 0, 5);						// Adds the Label to the GridPane
	    
	    dropDown = new ComboBox<MesoStation>();			// Creates the ComboBox
	    
	    for (int i = 0; i < stations.size(); i++)	{
	    	dropDown.getItems().add(new MesoStation(stations.get(i)));
	    }
	    pane.add(dropDown, 1, 5);
	    
	    // CREATE DROP DOWN BOX
	    
	    hd = new Button("Calculate HD");				// Creates the Calculate HD Button
	    pane.add(hd, 0, 6);								// Adds the Button to the GridPane
	    
	    dist0 = new Label("Distance 0");				// Creates Label for "Distance 0"
	    dist1 = new Label("Distance 1");				// Creates Label for "Distance 1"
	    dist2 = new Label("Distance 2");				// Creates Label for "Distance 2"
	    dist3 = new Label("Distance 3");				// Creates Label for "Distance 3"
	    dist4 = new Label("Distance 4");				// Creates Label for "Distance 4"
	 // Add Labels to GridPane
	    pane.add(dist0, 0, 7);
	    pane.add(dist1, 0, 8);
	    pane.add(dist2, 0, 9);
	    pane.add(dist3, 0, 10);
	    pane.add(dist4, 0, 11);
	    
	    dist0Txt = new TextArea();						// TextArea for dist0
	    dist0Txt.setEditable(false); 					// Disables user-editting of the TextArea
	    dist1Txt = new TextArea();						// TextArea for dist1
	    dist1Txt.setEditable(false); 					// Disables user-editting of the TextArea
	    dist2Txt = new TextArea();						// TextArea for dist2
	    dist2Txt.setEditable(false); 					// Disables user-editting of the TextArea
	    dist3Txt = new TextArea();						// TextArea for dist3
	    dist3Txt.setEditable(false); 					// Disables user-editting of the TextArea
	    dist4Txt = new TextArea();						// TextArea for dist4
	    dist4Txt.setEditable(false); 					// Disables user-editting of the TextArea
	 // Add TextAreas to GridPane
	    pane.add(dist0Txt, 1, 7);
	    pane.add(dist1Txt, 1, 8);
	    pane.add(dist2Txt, 1, 9);
	    pane.add(dist3Txt, 1, 10);
	    pane.add(dist4Txt, 1, 11);
	    
	    add = new Button("Add Station");				// Creates Add Station Button
	    pane.add(add, 0, 12);							// Adds the Button to the GridPane
	    
	    inputTxt = new TextArea();						// Creates the TextArea for input
	    inputTxt.setEditable(true);						// Enables user-editting of the TextArea
	    pane.add(inputTxt, 1, 12);						// Adds the TextArea to the GridPane
	     
	     flow = new FlowPane();							// Creates the FlowPane
	     flow.setVgap(8);								// Sets the vertical gap
	     flow.setHgap(4);								// Sets the horizontal gap
	     flow.setPrefWrapLength(300); 					// Preferred width = 300
	     flow.getChildren().add(pane);					// Adds the GridPane to the FlowPane
	     
	     primaryStage.setScene(scene);    										// Sets window's scene  
	     primaryStage.setTitle("Elst0000 - CS 2334 - Hamming Distance"); 		// Sets window's title
	     primaryStage.show();             										// Displays window
		
	}
	
	/**
	 * This method reads from a file and sorts through to find the 
	 * station IDs in the document.  It then stores each station 
	 * ID in the stationID array, enlarging it when necessary.
	 * 
	 * @param fileName The file to be read into stationID
	 * @throws IOException 
	 * @throws Exception
	 */
	public ArrayList<String> read(String fileName) throws IOException
	{
		ArrayList<String> stationID = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String ID = "";
		//String strg = br.readLine();
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
	
	public static void main(String[] args) {
		launch(args);
	}

}
