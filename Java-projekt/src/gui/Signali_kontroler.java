package gui;

import java.util.Collections;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import projekt.MPISCore.Servis;
import projekt.MPISCore.Kompozicija.Signal;
import projekt.MPISCore.Kompozicija.Tip_signala;
public class Signali_kontroler {

    Servis servis;
    String uredjaj;
    Tip_signala tip;
    
    @FXML
    private Label tekst;

	
	void postavi_argumente(Servis s, Tip_signala tip) {
		servis = s;
		this.tip = tip;
		
		//Prikaz signala
		sortiraj();
		
		String linija;
		System.out.println(servis);
		if (tip == Tip_signala.DV_SVE) {
			
			for (int i = 0; i < servis.getSignali().size(); i++) {
				
				if (!servis.getSignali().get(i).getName().contains("DV")) continue;
				
				linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  DV-B  "+servis.getSignali().get(i).getName()+
						"  "+servis.getSignali().get(i).getStanje();
				tekst.setText(tekst.getText()+"\n"+linija);
				
			}
		} else if (tip == Tip_signala.SP_SVE) {
			
			for (int i = 0; i < servis.getSignali().size(); i++) {
				
				if (!servis.getSignali().get(i).getName().contains("SP")) continue;
				
				linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  SP-B  "+servis.getSignali().get(i).getName()+
						"  "+servis.getSignali().get(i).getStanje();
				tekst.setText(tekst.getText()+"\n"+linija);
				
			}
		} else if (tip == Tip_signala.DV_LIJEVI) {
			
			for (int i = 0; i < servis.getSignali().size(); i++) {
				
				if (!servis.getSignali().get(i).getName().contains("DV-Lijevi")) continue;
				
				linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  DV-B  "+servis.getSignali().get(i).getName()+
						"  "+servis.getSignali().get(i).getStanje();
				tekst.setText(tekst.getText()+"\n"+linija);
				
			}
		} else if (tip == Tip_signala.DV_DESNI) {
			
			for (int i = 0; i < servis.getSignali().size(); i++) {
				
				if (!servis.getSignali().get(i).getName().contains("DV-Desni")) continue;
				
				linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  DV-B  "+servis.getSignali().get(i).getName()+
						"  "+servis.getSignali().get(i).getStanje();
				tekst.setText(tekst.getText()+"\n"+linija);
				
			}
		} else if (tip == Tip_signala.SP_LIJEVI) {
			
			for (int i = 0; i < servis.getSignali().size(); i++) {
				
				if (!servis.getSignali().get(i).getName().contains("SP-Lijevi")) continue;
				
				linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  SP-B  "+servis.getSignali().get(i).getName()+
						"  "+servis.getSignali().get(i).getStanje();
				tekst.setText(tekst.getText()+"\n"+linija);
				
			}
		} else if (tip == Tip_signala.SP_DESNI) {
			
			for (int i = 0; i < servis.getSignali().size(); i++) {
				
				if (!servis.getSignali().get(i).getName().contains("SP-Desni")) continue;
				
				linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  SP-B  "+servis.getSignali().get(i).getName()+
						"  "+servis.getSignali().get(i).getStanje();
				tekst.setText(tekst.getText()+"\n"+linija);
				
			}
		} else if (tip == Tip_signala.SP_PREKIDAC) {
			
			for (int i = 0; i < servis.getSignali().size(); i++) {
				
				if (!servis.getSignali().get(i).getName().contains("SP-Prekid")) continue;
				
				linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  SP-B  "+servis.getSignali().get(i).getName()+
						"  "+servis.getSignali().get(i).getStanje();
				tekst.setText(tekst.getText()+"\n"+linija);
				
			}
		} else if (tip == Tip_signala.DV_PREKIDAC) {
			
			for (int i = 0; i < servis.getSignali().size(); i++) {
				
				if (!servis.getSignali().get(i).getName().contains("DV-Prekid")) continue;
				
				linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  DV-B  "+servis.getSignali().get(i).getName()+
						"  "+servis.getSignali().get(i).getStanje();
				tekst.setText(tekst.getText()+"\n"+linija);
				
			}
		} else if (tip == Tip_signala.DV_UZEMLJENJE) {
			
			for (int i = 0; i < servis.getSignali().size(); i++) {
				
				if (!servis.getSignali().get(i).getName().contains("DV-Linijski")) continue;
				
				linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  DV-B  "+servis.getSignali().get(i).getName()+
						"  "+servis.getSignali().get(i).getStanje();
				tekst.setText(tekst.getText()+"\n"+linija);
				
			}
		} else if (tip == Tip_signala.DV_TRENUTNO) {
			
			for (int i = 0; i < servis.getSignali().size(); i++) 
				if (servis.getSignali().get(i).getName().contains("DV-Lijevi")) {
					linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  DV-B  "+servis.getSignali().get(i).getName()+
							"  "+servis.getSignali().get(i).getStanje();
					tekst.setText(tekst.getText()+"\n"+linija);
					break;
				}
			
			for (int i = 0; i < servis.getSignali().size(); i++) 
				if (servis.getSignali().get(i).getName().contains("DV-Desni")) {
					linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  DV-B  "+servis.getSignali().get(i).getName()+
							"  "+servis.getSignali().get(i).getStanje();
					tekst.setText(tekst.getText()+"\n"+linija);
					break;
				}
			
			for (int i = 0; i < servis.getSignali().size(); i++) 
				if (servis.getSignali().get(i).getName().contains("DV-Prekid")) {
					linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  DV-B  "+servis.getSignali().get(i).getName()+
							"  "+servis.getSignali().get(i).getStanje();
					tekst.setText(tekst.getText()+"\n"+linija);
					break;
				}
			
			for (int i = 0; i < servis.getSignali().size(); i++) 
				if (servis.getSignali().get(i).getName().contains("DV-Uzemljenje")) {
					linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  DV-B  "+servis.getSignali().get(i).getName()+
							"  "+servis.getSignali().get(i).getStanje();
					tekst.setText(tekst.getText()+"\n"+linija);
					break;
				}
			
			for (int i = 0; i < servis.getSignali().size(); i++) 
				if (servis.getSignali().get(i).getName().contains("DV-Linijski")) {
					linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  DV-B  "+servis.getSignali().get(i).getName()+
							"  "+servis.getSignali().get(i).getStanje();
					tekst.setText(tekst.getText()+"\n"+linija);
					break;
				}
		} 
		
		else if (tip == Tip_signala.SP_TRENUTNO) {
			
			for (int i = 0; i < servis.getSignali().size(); i++) 
				if (servis.getSignali().get(i).getName().contains("SP-Lijevi")) {
					linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  SP-B  "+servis.getSignali().get(i).getName()+
							"  "+servis.getSignali().get(i).getStanje();
					tekst.setText(tekst.getText()+"\n"+linija);
					break;
				}
			
			for (int i = 0; i < servis.getSignali().size(); i++) 
				if (servis.getSignali().get(i).getName().contains("SP-Desni")) {
					linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  SP-B  "+servis.getSignali().get(i).getName()+
							"  "+servis.getSignali().get(i).getStanje();
					tekst.setText(tekst.getText()+"\n"+linija);
					break;
				}
			
			for (int i = 0; i < servis.getSignali().size(); i++) 
				if (servis.getSignali().get(i).getName().contains("SP-Prekid")) {
					linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  SP-B  "+servis.getSignali().get(i).getName()+
							"  "+servis.getSignali().get(i).getStanje();
					tekst.setText(tekst.getText()+"\n"+linija);
					break;
				}
			
		} else if (tip == Tip_signala.DV_PRAVO_UZEMLJENJE) {
			
			for (int i = 0; i < servis.getSignali().size(); i++) {
				
				if (!servis.getSignali().get(i).getName().contains("DV-Uzemljenje")) continue;
				
				linija = servis.getSignali().get(i).getVrijeme()+"  TS-B  DV-B  "+servis.getSignali().get(i).getName()+
						"  "+servis.getSignali().get(i).getStanje();
				tekst.setText(tekst.getText()+"\n"+linija);
				
			}
		}
		
	}
	
	private void sortiraj() {
		
		Collections.sort(servis.getSignali(), Collections.reverseOrder(new Comparator<Signal>() {

			@Override
			public int compare(Signal o1, Signal o2) {
				
				int godk = Integer.valueOf(o1.getGod()).compareTo(Integer.valueOf(o2.getGod()));
				int mjek = Integer.valueOf(o1.getMjes()).compareTo(Integer.valueOf(o2.getMjes()));
				int dank = Integer.valueOf(o1.getDan()).compareTo(Integer.valueOf(o2.getDan()));
				int satk = Integer.valueOf(o1.getSat()).compareTo(Integer.valueOf(o2.getSat()));
				int mink = Integer.valueOf(o1.getMin()).compareTo(Integer.valueOf(o2.getMin()));
				int seck = Integer.valueOf(o1.getSec()).compareTo(Integer.valueOf(o2.getSec()));
				
				if (godk != 0) return godk;
				else {
					if (mjek != 0) return mjek;
					else {
						if (dank != 0) return dank;
						else {
							if (satk != 0) return satk;
							else {
								if (mink != 0) return mink;
								else return seck;
							}
						}
					}
				}
			}
			
		}));
		
	}
	  
}















