package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projekt.MPISCore.Kompozicija.Mjerenja;

public class Mjerenja_Screen {
	
	private Scene scena;
	private AnchorPane root;

	public Mjerenja_Screen() {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Mjerenja_screen.fxml"));
			root = loader.load();
			((Mjerenja_kontroler)loader.getController()).postavi(new Mjerenja());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		scena = new Scene(root, 340, 160);
		scena.getStylesheets().add(getClass().getResource("css/application.css").toExternalForm());
		
	}
	
	public void postavi() {
		Stage prozor = new Stage();
		prozor.setScene(scena);
		prozor.setTitle("Mjerenja");
		prozor.initStyle(StageStyle.UTILITY);
		prozor.setResizable(false);
		prozor.show();
	}
	
}
