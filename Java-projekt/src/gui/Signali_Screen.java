package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projekt.MPISCore.Servis;
import projekt.MPISCore.Kompozicija.Tip_signala;

public class Signali_Screen {
	
	private Scene scena;
	private Pane root;
	private Stage prozor;
	String naslov;

	public Signali_Screen(Servis servis, Tip_signala tip) {
		
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Signali_screen.fxml"));
			root = loader.load();
			((Signali_kontroler)loader.getController()).postavi_argumente(servis, tip);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		scena = new Scene(root, 600, 400);
		scena.getStylesheets().add(getClass().getResource("css/application.css").toExternalForm());
		if (tip == Tip_signala.DV_SVE) naslov = "Dalekovodno polje, svi signali";
		else if (tip == Tip_signala.SP_SVE) naslov = "Spojno polje, svi signali";
		else if (tip == Tip_signala.DV_DESNI) naslov = "Dalekovodno polje, desni rastavljač";
		else if (tip == Tip_signala.DV_LIJEVI) naslov = "Dalekovodno polje, lijevi rastavljač";
		else if (tip == Tip_signala.SP_DESNI) naslov = "Spojno polje, desni rastavljač";
		else if (tip == Tip_signala.SP_LIJEVI) naslov = "Spojno polje, lijevi rastavljač";
		else if (tip == Tip_signala.DV_PREKIDAC) naslov = "Dalekovodno polje, prekidač";
		else if (tip == Tip_signala.DV_UZEMLJENJE) naslov = "Dalekovodno polje, linijski rastavljač";
		else if (tip == Tip_signala.SP_PREKIDAC) naslov = "Spojno polje, prekidač";
		else if (tip == Tip_signala.DV_TRENUTNO) naslov = "Dalekovodno polje, trenutni signali";
		else if (tip == Tip_signala.SP_TRENUTNO) naslov = "Spojno polje, trenutni signali";
		else if (tip == Tip_signala.DV_PRAVO_UZEMLJENJE) naslov = "Dalekovodno polje, uzemljenje";
		prozor = new Stage();
	}
	
	public void postavi() {
		prozor.setScene(scena);
		prozor.setTitle(naslov);
		prozor.initStyle(StageStyle.UTILITY);
		prozor.setResizable(false);
		prozor.show();
	}
	
}
