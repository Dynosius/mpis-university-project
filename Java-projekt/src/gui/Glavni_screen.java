package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Glavni_screen {
	
	private Scene scena;
	private Pane root;
	private Stage prozor;

	public Glavni_screen() {
		
		try {

			root = FXMLLoader.load(getClass().getResource("fxml/Glavni_screen.fxml"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		scena = new Scene(root, 600, 400);
		scena.getStylesheets().add(getClass().getResource("css/application.css").toExternalForm());
		
		prozor = new Stage();
	}
	
	public void postavi(Stage stariProzor) {
		prozor.setScene(scena);
		prozor.setMaximized(true);
		prozor.setTitle("MPIS grupa B");
		prozor.show();
		stariProzor.hide();
	}
	
}
