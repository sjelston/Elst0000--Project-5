import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
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
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// read in the Mesonet text file
				//ArrayList<String> stations = read("Mesonet.txt");
				 
				GridPane base = null;
				GridPane pane1 = null;
				GridPane pane2 = null;
				GridPane pane3 = null;
				FlowPane norm = null;							// Pane to hold all requirements for the project
				FlowPane pane4 = null;												
				// Pane to hold extra project
				
				Label enterDist = null;     					// Label for "Enter Hamming Dist: "
				Label compare = null;     						// Label for "Compare with: "
				
				Slider slide = null;							// Slider to determine distance
			
				TextArea slideTxt = null; 						// TextArea for Slider output
				TextArea outputTxt = null;						// TextArea for Show Station output
				
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
				
				slideTxt = new TextArea();						// Creates the text area for slide output
			    slideTxt.setEditable(false); 					// Disables user-editting of the TextArea
			// Setting size fields for the text area
			    slideTxt.setMaxHeight(1);
			    slideTxt.setMaxWidth(100);
			    slideTxt.setMinWidth(30);
			// Setting the GridPane positioning
			    GridPane.setColumnIndex(slideTxt, 1);
			    GridPane.setColumnSpan(slideTxt, 3);
				GridPane.setRowIndex(slideTxt, 0);
			    
			    slide = new Slider();							// Creates the Slider and sets options
			    slide.setShowTickLabels(true);
			    slide.setShowTickMarks(true);
			    slide.setMajorTickUnit(1.0);
			    slide.setMinorTickCount(4);
			    slide.setBlockIncrement(1.0);
			    slide.setMin(1.0);
			    slide.setMax(4.0);
			 // Setting the GridPane positioning
			    GridPane.setColumnIndex(slide, 0);
			    GridPane.setRowIndex(slide, 2);
			    GridPane.setColumnSpan(slide, 10);
				
			// Adding nodes to pane 1
				pane1.setVgap(5);								// Set vertical gap
				pane1.setHgap(5);								// Set horizontal gap
				pane1.setPadding(new Insets(1, 2, 1, 2));		// Padding the edges of the pane
				pane1.getChildren().add(enterDist);
				pane1.getChildren().add(slide);
				pane1.getChildren().add(slideTxt);
				
				
			/**
			 * Creating the second pane
			 * This will contain the show station button and the text area for its output
			 */					
				pane2 = new GridPane();
				
				showStation = new Button("Show Station");		// Initializing the Show Station button
			// Setting the GridPane positioning
				GridPane.setColumnIndex(showStation, 0);
			    GridPane.setRowIndex(showStation, 0);
				
				outputTxt = new TextArea();						// Initializes the output text area for showStation
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
				pane2.setVgap(5);								// Set vertical gap
				pane2.setHgap(5);								// Set horizontal gap
				pane2.setPadding(new Insets(1, 2, 1, 2));		// Padding the edges of the pane
				pane2.getChildren().add(showStation);
				pane2.getChildren().add(outputTxt);
				
				
			/**
			 * Creating the third pane
			 * This will contain the compare with: label and the drop down box
			 */					
				pane3 = new GridPane();
				
				compare = new Label("Compare with:");			// Creating Label for "Compare with: "
				GridPane.setColumnIndex(compare, 0);
				GridPane.setRowIndex(compare, 0);
				
				dropDown = new ComboBox<MesoStation>();			// Creating the drop down box
				GridPane.setColumnIndex(dropDown, 1);
				GridPane.setColumnSpan(dropDown, 2);
				GridPane.setRowIndex(dropDown, 0);
				
			// Adding nodes to pane 3
				pane3.setVgap(5);								// Set vertical gap
				pane3.setHgap(5);								// Set horizontal gap
				pane3.setPadding(new Insets(1, 2, 1, 2));		// Padding the edges of the pane
				pane3.getChildren().add(compare);
				pane3.getChildren().add(dropDown);
				
					
				
			/**
			 * Creating the fourth pane
			 * This will contain the Calculate HD button as well as the distance labels and fields
			 */	
				pane4 = new FlowPane(Orientation.VERTICAL);
				
				
				
				
				
				
			// Adding normal panes to norm
				norm.getChildren().add(pane1);
				norm.getChildren().add(pane2);
				norm.getChildren().add(pane3);
				
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
