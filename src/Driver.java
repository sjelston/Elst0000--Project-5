import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
				 
				GridPane pane1 = new GridPane();
				
				Label enterDist = null;     					// Label for "Enter Hamming Dist: "
				
				Slider slide = null;							// Slider to determine distance
			
				TextArea slideTxt = null; 						// TextArea for Slider output
				
				
				enterDist = new Label("Enter Hamming Dist: ");
			 // Setting the GridPane positioning
				GridPane.setColumnIndex(enterDist, 0);
				GridPane.setRowIndex(enterDist, 0);
				
				slideTxt = new TextArea();						// Creates the text area for slide output
			    slideTxt.setEditable(false); 					// Disables user-editting of the TextArea
			 // Setting size fields for the text area
			    slideTxt.setMaxHeight(1);
			    slideTxt.setMaxWidth(100);
			    slideTxt.setMinWidth(20);
			 // Setting the GridPane positioning
			    GridPane.setColumnIndex(slideTxt, 1);
			    GridPane.setColumnSpan(slideTxt, 3);
				GridPane.setRowIndex(slideTxt, 0);
			    
			    slide = new Slider();							// Creates the Slider
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
			    GridPane.setColumnSpan(slide, 5);
				
			 // Adding nodes to pane 1
				pane1.setVgap(5);								// Set vertical gap
				pane1.setHgap(5);								// Set horizontal gap
				pane1.setPadding(new Insets(1, 2, 1, 2));		// Padding the edges of the pane
				pane1.getChildren().add(enterDist);
				pane1.getChildren().add(slide);
				pane1.getChildren().add(slideTxt);
				
				
				
				
				
				
				
				Scene sc = new Scene(pane1, 750, 800);
				
				primaryStage.setScene(sc);;
				primaryStage.show();
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
