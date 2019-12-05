import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
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
	
	private ArrayList<MesoStation> stations;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// read in the Mesonet text file
		stations = read("Mesonet.txt");
				 
		GridPane base = null;
		GridPane pane1 = null;
		GridPane pane2 = null;
		GridPane pane3 = null;
		GridPane pane4 = null;	
		GridPane pane5 = null;
		FlowPane norm = null;							// Pane to hold all requirements for the project											
		// Pane to hold extra project
				
		Label enterDist = null;     					// Label for "Enter Hamming Dist: "
		Label compare = null;     						// Label for "Compare with: "
		Label dist0 = null;								// Label for "Distance 0"
		Label dist1 = null;								// Label for "Distance 1"
	    Label dist2 = null;								// Label for "Distance 2"
	    Label dist3 = null;								// Label for "Distance 3"
	    Label dist4 = null;								// Label for "Distance 4"
				
	    Slider slide = null;							// Slider to determine distance
			
		TextArea slideTxt = null; 						// TextArea for Slider output
		TextArea outputTxt = null;						// TextArea for Show Station output
		TextArea dist0Txt = null;						// TextArea for dist0
		TextArea dist1Txt = null;						// TextArea for dist1
	    TextArea dist2Txt = null;						// TextArea for dist2
	    TextArea dist3Txt = null;						// TextArea for dist3
	    TextArea dist4Txt = null;						// TextArea for dist4
	    TextArea inputTxt = null;						// TextArea for input for Add Station
			
	    Button showStation = null;						// Show Station Button
	    Button hd = null;								// Calculate HD Button
	    Button add = null;								// Add Station Button
			    
	    ComboBox<MesoStation> dropDown = null;			// Drop down box
				
			    
	    norm = new FlowPane(Orientation.VERTICAL);		// Creating FlowPane to hold panes for the normal project
		norm.setVgap(5);								// Setting vertical gap
	    norm.setPadding(new Insets(2, 3, 2, 3));		// Padding the edges of the pane
			    
	    /**
	     * Creating the first pane
	     * This will contain enterDist, the slider, and the text output for the slider
		*/
		pane1 = new GridPane();
		
		enterDist = new Label("Enter Hamming Dist: ");
		// Setting the GridPane positioning
		GridPane.setColumnIndex(enterDist, 0);
		GridPane.setRowIndex(enterDist, 0);
				
		slide = new Slider(1, 4, 1);					// Creates the Slider and sets options
	    slide.setShowTickLabels(true);
	    slide.setShowTickMarks(true);
	    slide.setMajorTickUnit(1.0);
	    slide.setMinorTickCount(0);
	    slide.setBlockIncrement(1.0);
	    slide.setSnapToTicks(true);
	 // Setting the GridPane positioning
	    GridPane.setColumnIndex(slide, 0);
	    GridPane.setRowIndex(slide, 2);
	    GridPane.setColumnSpan(slide, 10);
	    
	    slideTxt = new TextArea();						// Creates the text area for slide output
		slideTxt.textProperty().bind(slide.valueProperty().asString());
		slideTxt.setEditable(false); 					// Disables user-editting of the TextArea
	// Setting size fields for the text area
		slideTxt.setMaxHeight(1);
		slideTxt.setMaxWidth(100);
	    slideTxt.setMinWidth(30);
	// Setting the GridPane positioning
		GridPane.setColumnIndex(slideTxt, 1);
		GridPane.setColumnSpan(slideTxt, 3);
		GridPane.setRowIndex(slideTxt, 0);
				
	// Adding nodes to pane 1
	    pane1.setVgap(10);								// Set vertical gap
		pane1.setHgap(5);								// Set horizontal gap
		pane1.setPadding(new Insets(5, 2, 5, 2));		// Padding the edges of the pane
		pane1.getChildren().add(enterDist);
		pane1.getChildren().add(slide);
		pane1.getChildren().add(slideTxt);
				
				
	/**
	 * Creating the second pane
	 * This will contain the show station button and the text area for its output
	 */					
		pane2 = new GridPane();
				
		showStation = new Button("Show Station");					// Initializing the Show Station button
		showStation.setOnAction(new EventHandler<ActionEvent>() {
	        @Override 
	        public void handle(ActionEvent e) {
	            MesoStation current = dropDown.getValue();
	        }
	    });
	// Setting the GridPane positioning
		GridPane.setColumnIndex(showStation, 0);
	    GridPane.setRowIndex(showStation, 0);
				
		outputTxt = new TextArea();						// Initializes the output text area for showStation
		outputTxt.setEditable(false); 					// Disables user-editting of the TextArea
	// Setting size components
		outputTxt.setMinHeight(260);
		outputTxt.setMaxHeight(280);
		outputTxt.setMinWidth(230);
		outputTxt.setMaxWidth(250);
	// Setting the GridPane positioning
		GridPane.setColumnIndex(outputTxt, 0);
		GridPane.setRowIndex(outputTxt, 1);
		GridPane.setColumnSpan(outputTxt, 5);
				
	// Adding nodes to pane 2
		pane2.setVgap(15);								// Set vertical gap
		pane2.setHgap(5);								// Set horizontal gap
		pane2.setPadding(new Insets(5, 2, 5, 2));		// Padding the edges of the pane
		pane2.getChildren().add(showStation);
		pane2.getChildren().add(outputTxt);
				
				
	/**
	 * Creating the third pane
	 * This will contain the compare with: label and the drop down box
	 */					
		pane3 = new GridPane();
				
		compare = new Label("Compare with:");			// Creating Label for "Compare with: "
	// Setting the GridPane positioning
		GridPane.setColumnIndex(compare, 0);
		GridPane.setRowIndex(compare, 0);
				
		dropDown = new ComboBox<MesoStation>();			// Creating the drop down box
	// Filling the drop down box
		for(int i = 0; i < stations.size(); i++)	{
			MesoStation m = stations.get(i);
			dropDown.getItems().add(m);
		}
		dropDown.setMinWidth(80);
		dropDown.setMaxWidth(90);
	// Setting the GridPane positioning
		GridPane.setColumnIndex(dropDown, 18);
		GridPane.setColumnSpan(dropDown, 1);
		GridPane.setRowIndex(dropDown, 0);
				
		hd = new Button("Calculate HD");				// Creates the Calculate HD Button
	// Setting the GridPane positioning
		GridPane.setColumnIndex(hd, 0);	
		GridPane.setRowIndex(hd, 1);
				
	// Adding nodes to pane 3
		pane3.setVgap(10);								// Set vertical gap
		pane3.setHgap(5);								// Set horizontal gap
		pane3.setPadding(new Insets(5, 2, 5, 2));		// Padding the edges of the pane
		pane3.getChildren().add(compare);
		pane3.getChildren().add(dropDown);
		pane3.getChildren().add(hd);
				
					
				
	/**
	 * Creating the fourth pane
	 * This will contain the distance labels and text areas
	 */	
		pane4 = new GridPane();
				
	// Creating the distance Labels
		dist0 = new Label("Distance 0");				// Creates Label for "Distance 0"
	// Setting the GridPane positioning
		GridPane.setColumnIndex(dist0, 0);
		GridPane.setRowIndex(dist0, 0);
		dist1 = new Label("Distance 1");				// Creates Label for "Distance 1"
	// Setting the GridPane positioning
		GridPane.setColumnIndex(dist1, 0);
		GridPane.setRowIndex(dist1, 1);
		dist2 = new Label("Distance 2");				// Creates Label for "Distance 2"
	// Setting the GridPane positioning
		GridPane.setColumnIndex(dist2, 0);
		GridPane.setRowIndex(dist2, 2);
		dist3 = new Label("Distance 3");				// Creates Label for "Distance 3"
	// Setting the GridPane positioning
		GridPane.setColumnIndex(dist3, 0);
		GridPane.setRowIndex(dist3, 3);
		dist4 = new Label("Distance 4");				// Creates Label for "Distance 4"
	// Setting the GridPane positioning
		GridPane.setColumnIndex(dist4, 0);
		GridPane.setRowIndex(dist4, 4);
			    
	// Creating text areas and setting editting
		dist0Txt = new TextArea();						// TextArea for dist0
		dist0Txt.setEditable(false); 					// Disables user-editting of the TextArea
	// Setting size
		dist0Txt.setMaxHeight(1);
		dist0Txt.setMaxWidth(140);
	    dist0Txt.setMinWidth(100);
	// Setting the GridPane positioning
		GridPane.setColumnIndex(dist0Txt, 1);
		GridPane.setRowIndex(dist0Txt, 0);
	    
	    dist1Txt = new TextArea();						// TextArea for dist1
	    dist1Txt.setEditable(false); 					// Disables user-editting of the TextArea
	// Setting size
	    dist1Txt.setMaxHeight(1);
	    dist1Txt.setMaxWidth(140);
	    dist1Txt.setMinWidth(100);
	// Setting the GridPane positioning
		GridPane.setColumnIndex(dist1Txt, 1);
		GridPane.setRowIndex(dist1Txt, 1);
			   
		dist2Txt = new TextArea();						// TextArea for dist2
	    dist2Txt.setEditable(false); 					// Disables user-editting of the TextArea
	// Setting size
	    dist2Txt.setMaxHeight(1);
	    dist2Txt.setMaxWidth(140);
	    dist2Txt.setMinWidth(100);
	// Setting the GridPane positioning
		GridPane.setColumnIndex(dist2Txt, 1);
		GridPane.setRowIndex(dist2Txt, 2);
				
	    dist3Txt = new TextArea();						// TextArea for dist3
	    dist3Txt.setEditable(false); 					// Disables user-editting of the TextArea
	// Setting size
	    dist3Txt.setMaxHeight(1);
	    dist3Txt.setMaxWidth(140);
	    dist3Txt.setMinWidth(100);
	// Setting the GridPane positioning
		GridPane.setColumnIndex(dist3Txt, 1);
		GridPane.setRowIndex(dist3Txt, 3);
		
	    dist4Txt = new TextArea();						// TextArea for dist4
	    dist4Txt.setEditable(false); 					// Disables user-editting of the TextArea
	// Setting size
	    dist4Txt.setMaxHeight(1);
	    dist4Txt.setMaxWidth(140);
		dist4Txt.setMinWidth(100);
	// Setting the GridPane positioning
		GridPane.setColumnIndex(dist4Txt, 1);
		GridPane.setRowIndex(dist4Txt, 4);
			    
	// Adding nodes to pane 4
	    pane4.setVgap(10);								// Set vertical gap
		pane4.setHgap(50);								// Set horizontal gap
	    pane4.setPadding(new Insets(5, 5, 5, 20));		// Padding the edges of the pane
		pane4.getChildren().add(dist0);
		pane4.getChildren().add(dist1);
		pane4.getChildren().add(dist2);
		pane4.getChildren().add(dist3);
		pane4.getChildren().add(dist4);
		pane4.getChildren().add(dist0Txt);
		pane4.getChildren().add(dist1Txt);
		pane4.getChildren().add(dist2Txt);
		pane4.getChildren().add(dist3Txt);
		pane4.getChildren().add(dist4Txt);
				
				
	/**
	 * Creating the fifth pane
	 * This will contain the Add Station button and the text area for typing
	 */			
		pane5 = new GridPane();
		
		add = new Button("Add Station");				// Initializing the Add Station button
	// Setting the GridPane positioning
		GridPane.setColumnIndex(add, 0);
	    GridPane.setRowIndex(add, 0);
				
		inputTxt = new TextArea();						// Initializes the input text area for Add Station
		inputTxt.setEditable(true); 					// Enables user-editting of the TextArea
	// Setting size components
		inputTxt.setMaxHeight(1);
	    inputTxt.setMaxWidth(140);
	    inputTxt.setMinWidth(130);
	// Setting the GridPane positioning
		GridPane.setColumnIndex(inputTxt, 1);
		GridPane.setRowIndex(inputTxt, 0);
		GridPane.setColumnSpan(inputTxt, 5);
				
	// Adding nodes to pane 2
		pane5.setVgap(5);								// Set vertical gap
		pane5.setHgap(37);								// Set horizontal gap
		pane5.setPadding(new Insets(5, 10, 5, 10));		// Padding the edges of the pane
		pane5.getChildren().add(add);
		pane5.getChildren().add(inputTxt);
				
				
	
	/**
	 * Putting together the panes to set the scene and create the stage	
	 */
	// Adding normal panes to norm
		norm.setPrefWrapLength(800);					// Sets the wrap length to the length of the window
	    norm.getChildren().add(pane1);
		norm.getChildren().add(pane2);
		norm.getChildren().add(pane3);
		norm.getChildren().add(pane4);
		norm.getChildren().add(pane5);
				
		base = new GridPane();							// Creates the main pane to be used for the program
		base.getChildren().add(norm);					// Adds the normal components to the base
				
		Scene sc = new Scene(base, 750, 800);			// Initializing the scene with the base GridPane
				
		primaryStage.setScene(sc);						// Sets the scene for primaryStage
		primaryStage.show();							// Displays the window
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
	public ArrayList<MesoStation> read(String fileName) throws IOException
	{
		ArrayList<MesoStation> stationID = new ArrayList<MesoStation>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String ID = "";
		String strg = "";

		while (!((strg = br.readLine()) == null))	{
			for (int j = 0; j < 4; j++)	{
				ID += strg.charAt(j);
			}
			stationID.add(new MesoStation(ID));
			ID = "";
		}
		br.close();
		return stationID;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
