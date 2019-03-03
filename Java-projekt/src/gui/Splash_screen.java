package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Splash_screen {
	
	private Scene scena;
	private BorderPane root;
	private Stage prozor;

	public Splash_screen() {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Splash_screen.fxml"));
			root = loader.load();
			((Splash_kontroler)loader.getController()).setSplash(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		scena = new Scene(root, 600, 400);
		scena.getStylesheets().add(getClass().getResource("css/application.css").toExternalForm());
		
	}
	
	public void postavi(Stage prozor) {
		this.prozor = prozor;
		prozor.setScene(scena);
		prozor.initStyle(StageStyle.UTILITY);
		prozor.setResizable(false);
		prozor.setTitle("MPIS grupa B");
		prozor.show();
	}
	
	public void postavi_na_glavni() {
		Glavni_screen glavni = new Glavni_screen();
		glavni.postavi(prozor);
	}
	
}
