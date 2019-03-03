package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import projekt.MPISCore.Kompozicija.Mjerenja;

public class Mjerenja_kontroler {


	    @FXML
	    private Label jenergija;

	    @FXML
	    private Label struja;

	    @FXML
	    private Label jsnaga;
	    
	    private Mjerenja mjerenja;

	    void postavi(Mjerenja m) {
	    	this.mjerenja = m;
	    	jenergija.setText(mjerenja.getJalovaE());
			jsnaga.setText(mjerenja.getJalovaS());
			struja.setText(mjerenja.getStruja());
	    }


}
