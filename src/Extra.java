import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Extra {
	
	public Extra()	{
		
	}
	
	public void setPane()	{
	GridPane g = new GridPane();
	
	Button A = new Button("Which");
	A.setOnAction(new EventHandler<ActionEvent>() {
		MesoStation current = new MesoStation(inputTxt.getText());

		@Override
		public void handle(ActionEvent e) {
			if (!(dropDown.getItems().contains(current))) {
				dropDown.getItems().add(current);
				dropDown.getItems().sort(current);
			}
		}
	});
	Button B = new Button("Will");
	Button C = new Button("You");
	Button D = new Button("Choose?");
	
	
	
	}
	
	
}
