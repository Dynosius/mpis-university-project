package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Splash_kontroler {
	
	@FXML
	private Button pokreni_gumb;
	private Splash_screen splash;
	
	@FXML
	private void pokreni_klik(ActionEvent e) {
		splash.postavi_na_glavni();
	}

	public void setSplash(Splash_screen splash) {
		this.splash = splash;
	}

}
