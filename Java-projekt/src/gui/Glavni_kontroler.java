package gui;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import projekt.MPISCore.Servis;
import projekt.MPISCore.Kompozicija.Prekidac;
import projekt.MPISCore.Kompozicija.Rastavljac;
import projekt.MPISCore.Kompozicija.Signal;
import projekt.MPISCore.Kompozicija.Tip_signala;
import projekt.MPISCore.Kompozicija.Upravljanje;


public class Glavni_kontroler implements Initializable {

    @FXML
    private BorderPane root;

 	@FXML
    private Button isk_sp_p;

    @FXML
    private Button isk_sp_l_r;

    @FXML
    private Circle dv_rl_k;

    @FXML
    private Button isk_dv_p;

    @FXML
    private Button isk_dv_l_r;

    @FXML
    private Line dp;
    
    @FXML
    private Button uklop_botun_s1;

    @FXML
    private Button uklop_botun_s2;

    @FXML
    private Line linra_linija;

    @FXML
    private Circle linra_lampica;

    @FXML
    private Button isk_sp_d_r;

    @FXML
    private Button uk_dv_do_r;

    @FXML
    private Circle dv_rd_k;

    @FXML
    private MenuItem ras_p_dv;

    @FXML
    private Button isk_dv_d_r;

    @FXML
    private Button uk_sp_d_r;

    @FXML
    private Line drd;

    @FXML
    private Button uk_dv_d_r;

    @FXML
    private Button uk_sp_p;

    @FXML
    private Circle dv_rdo_k;

    @FXML
    private Line sp;

    @FXML
    private Line drl;

    @FXML
    private Button uk_dv_l_r;

    @FXML
    private MenuItem ras_d_dv;

    @FXML
    private Line srd;

    @FXML
    private MenuItem svi_dv;

    @FXML
    private MenuItem ras_l_sp;

    @FXML
    private Button isk_dv_do_r;

    @FXML
    private Line srl;

    @FXML
    private MenuItem ras_l_dv;
    
    @FXML
    private MenuItem trenutni_dv;
    
    @FXML
    private MenuItem trenutni_sp;

    @FXML
    private Circle sp_p_k;

    @FXML
    private Button uk_sp_l_r;

    @FXML
    private Circle sp_rl_k;

    @FXML
    private Button uk_dv_p;

    @FXML
    private Line drdo;

    @FXML
    private MenuItem p_sp;

    @FXML
    private MenuItem ras_d_sp;
    
    @FXML
    private MenuItem mjerenja;

    @FXML
    private Circle sp_rd_k;

    @FXML
    private MenuItem svi_sp;

    @FXML
    private Circle dv_p_k;
    
    @FXML
    private MenuItem ras_do_dv;
    
    @FXML
    private Button uklop_botun;
    
    @FXML
    private Button isklop_botun;
    
    @FXML
    private Label labela_greske;
    
    @FXML
    private RadioButton r_daljinsko;
    
    @FXML
    private RadioButton r_lokalno;
    
    @FXML
    private Button obrisi_botun;
    
    private boolean dv_ras_l = true;
    private boolean dv_ras_d = true;
    private boolean dv_ras_do = true;
    private boolean dv_p = true;
    private boolean sp_ras_l = true;
    private boolean sp_ras_d = true;
    private boolean sp_p = true;
    private boolean pravo_uzemljenje = true;
    private Servis servis;
    
    @FXML
    void uklop_botun_s1_klik(ActionEvent event) {
    	//Uklop na S1
        servis.uklopS1();
    }
    
    @FXML
    void uklop_botun_s2_klik(ActionEvent event) {
    	//Uklop na S2
        servis.uklopS2();
    }
    
    //Za signale kod uzemljenja koristiti servis.getSignali().add(new Signal("DV-uzemljenje",...
    
    @FXML
    void linra_uk_klik(ActionEvent event) {
    	//Pravo uzemljenje uklop
    	System.out.println("Linra uk");
        if(servis.isTestUpravljanje()) {
            if (!servis.getZastita().ispitaj(servis.getDVPolje())) //ispituje jesu li ukljuceni prekidac i rastavljaci, ako je TRUE, ne smijemo upaliti uzemljenje
            {
                if (!pravo_uzemljenje) {
                    pomakni_uzemljenje(linra_linija, false, linra_lampica);
                    servis.getDvUzemljenje().ukljuci();
                    servis.getSignali().add(new Signal("DV-Uzemljenje", "UKLJUCEN"));
                }
                pravo_uzemljenje = true;
            } else greska("Prekidač i rastavljači su uključeni, \nne smijete upaliti uzemljenje");
        }
    }

    @FXML
    void linra_isk_klik(ActionEvent event) {
    	if (pravo_uzemljenje)
    	{
    	    pomakni_uzemljenje(linra_linija, true, linra_lampica);
    	    servis.getDvUzemljenje().iskljuci();
    	    servis.getSignali().add(new Signal("DV-Uzemljenje", "ISKLJUCEN"));
        }
    	pravo_uzemljenje = false;
    }
    
    @FXML
    void linijski_ras_klik(ActionEvent event) {
    	//Uzemljenje, klik na meni za prikaz signala
    	Signali_Screen ss = new Signali_Screen(servis, Tip_signala.DV_PRAVO_UZEMLJENJE);
    	ss.postavi();
    }

    public void rasDVlinijski (boolean x)
    {
        if(x) uk_dv_do_r_klik(null);
        else isk_dv_do_r_klik(null);
    }
    public void rasDVlijevi(boolean x)
	{
        if (x) uk_dv_l_r_klik(null);
        else isk_dv_l_r_klik(null);
    }
    public void rasDVdesni(boolean x)
    {
        if(x) uk_dv_d_r_klik(null);
        else isk_dv_d_r_klik(null);
    }
    public void dvPrek(boolean x)
    {
        if(x) uk_dv_p_klik(null);
        else isk_dv_p_klik(null);
    }
    public void dvUzem(boolean x)
    {
        if (x) linra_uk_klik(null);
        else linra_isk_klik(null);
    }
    public void rasSPlijevi(boolean x)
    {
        if(x) uk_sp_l_r_klik(null);
        else isk_sp_l_r_klik(null);
    }
    public void rasSPdesni(boolean x)
    {
        if(x) uk_sp_d_r_klik(null);
        else isk_sp_d_r_klik(null);
    }
    public void spPrek(boolean x)
    {
        if(x) uk_sp_p_klik(null);
        else isk_sp_p_klik(null);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        servis = new Servis(this);
        if(servis.getDvUzemljenje().rawStanje().equals("ISKLJUCEN"))
        {
            dvUzem(false);
        }
        if(servis.getDvLijevi().rawStanje().equals("ISKLJUCEN"))
		{
			rasDVlijevi(false);
		}
		if(servis.getDvDesni().rawStanje().equals("ISKLJUCEN"))
		{
			rasDVdesni(false);
		}
		if(servis.getSpLijevi().rawStanje().equals("ISKLJUCEN"))
		{
			rasSPlijevi(false);
		}
		if(servis.getSpDesni().rawStanje().equals("ISKLJUCEN"))
		{
			rasSPdesni(false);
		}
		
		ToggleGroup grupa = new ToggleGroup();
		r_daljinsko.setToggleGroup(grupa);
		r_lokalno.setToggleGroup(grupa);
		r_daljinsko.setSelected(true);
		
    }

    //Botuni
    
    @FXML
    public void uk_dv_l_r_klik(ActionEvent event) {
        if(servis.isTestUpravljanje()) {
            if (servis.getDvUzemljenje().rawStanje().equals("ISKLJUCEN") && servis.getDvDesni().rawStanje().equals("ISKLJUCEN")) //Ako je uzemljenje ugaseno mozemo slobodno paliti/gasiti rastavljace
            {
                if (!dv_ras_l) pomakni_rastavljac_lijevi(drl, false, dv_rl_k);
                dv_ras_l = true;
                servis.getDvLijevi().setStanje(Rastavljac.StanjeRastavljaca.UKLJUCEN);
                servis.getSignali().add(new Signal("DV-Lijevi", "UKLJUCEN"));
            } else {
                if(servis.getDvUzemljenje().rawStanje().equals("UKLJUCEN")) {
                    greska("UPOZORENJE: Uzemljenje je upaljeno!");//System.err.println("UPOZORENJE: Uzemljenje je upaljeno");
                }
                else
                    greska("UPOZORENJE: Drugi rastavljač je upaljen!");
            }
        }
        else greska("Upravljanje je lokalno");//System.err.println("Upravljanje je lokalno");

    }

    @FXML
    void isk_dv_l_r_klik(ActionEvent event) {

        if(servis.isTestUpravljanje()) {
            if (dv_ras_l) pomakni_rastavljac_lijevi(drl, true, dv_rl_k);
            dv_ras_l = false;
            servis.getDvLijevi().setStanje(Rastavljac.StanjeRastavljaca.ISKLJUCEN);
            servis.getSignali().add(new Signal("DV-Lijevi", "ISKLJUCEN"));
        }
        else greska("Upravljanje je lokalno");//System.err.println("Upravljanje je lokalno");

    }

    @FXML
    void isk_dv_do_r_klik(ActionEvent event) {
        if(servis.isTestUpravljanje()) {
            if (dv_ras_do) pomakni_rastavljac_donji(drdo, true, dv_rdo_k);
            dv_ras_do = false;
            servis.getDvLinijski().setStanje(Rastavljac.StanjeRastavljaca.ISKLJUCEN);
            servis.getSignali().add(new Signal("DV-Linijski", "ISKLJUCEN"));
        }
        else greska("Upravljanje je lokalno");//System.err.println("Upravljanje je lokalno");

    }
    
    @FXML
    void uk_dv_do_r_klik(ActionEvent event) {
        if(servis.isTestUpravljanje())
        {
            if (!dv_ras_do) pomakni_rastavljac_donji(drdo, false, dv_rdo_k);
            dv_ras_do = true;
            servis.getDvLinijski().setStanje(Rastavljac.StanjeRastavljaca.UKLJUCEN);
            servis.getSignali().add(new Signal("DV-Linijski", "UKLJUCEN"));
        }
        else greska("Upravljanje je lokalno");//System.err.println("Upravljanje je lokalno");


    }

    @FXML
    void isk_dv_p_klik(ActionEvent event) {
        if(servis.isTestUpravljanje()) {
            if (dv_p) pomakni_prekidac_dalekovodno(dp, true, dv_p_k);
            dv_p = false;
            servis.getDvPrekidac().setStanje(Prekidac.StanjePrekidaca.ISKLJUCEN);
            servis.getSignali().add(new Signal("DV-Prekidac", "ISKLJUCEN"));
        }
        else greska("Upravljanje je lokalno");//System.err.println("Upravljanje je lokalno");
    }

    @FXML
    void uk_dv_p_klik(ActionEvent event) {
        if(servis.isTestUpravljanje()) {
            if(servis.getDvUzemljenje().rawStanje().equals("ISKLJUCEN")) //Ako je uzemljenje ugaseno mozemo slobodno paliti/gasiti rastavljace
            {
                if (!dv_p) pomakni_prekidac_dalekovodno(dp, false, dv_p_k);
                dv_p = true;
                servis.getDvPrekidac().setStanje(Prekidac.StanjePrekidaca.UKLJUCEN);
                servis.getSignali().add(new Signal("DV-Prekidac", "UKLJUCEN"));
            }
            else
            {
            	greska("UPOZORENJE: Uzemljenje je upaljeno");//System.err.println("UPOZORENJE: Uzemljenje je upaljeno");
            }
        }
        else greska("Upravljanje je lokalno");//System.err.println("Upravljanje je lokalno");
    }

    @FXML
    void isk_dv_d_r_klik(ActionEvent event) {
        if(servis.isTestUpravljanje()) {
            if (dv_ras_d) pomakni_rastavljac_desni(drd, true, dv_rd_k);
            dv_ras_d = false;
            servis.getDvDesni().setStanje(Rastavljac.StanjeRastavljaca.ISKLJUCEN);
            servis.getSignali().add(new Signal("DV-Desni", "ISKLJUCEN"));
        }
        else greska("Upravljanje je lokalno");//System.err.println("Upravljanje je lokalno");
    }

    @FXML
    void uk_dv_d_r_klik(ActionEvent event) {
        if(servis.isTestUpravljanje()) {
            if(servis.getDvUzemljenje().rawStanje().equals("ISKLJUCEN") && servis.getDvLijevi().rawStanje().equals("ISKLJUCEN")) //Ako je uzemljenje ugaseno mozemo slobodno paliti/gasiti rastavljace
            {
                if (!dv_ras_d) pomakni_rastavljac_desni(drd, false, dv_rd_k);
                dv_ras_d = true;
                servis.getDvDesni().setStanje(Rastavljac.StanjeRastavljaca.UKLJUCEN);
                servis.getSignali().add(new Signal("DV-Desni", "UKLJUCEN"));
            }
            else
            {
                if(servis.getDvUzemljenje().rawStanje().equals("UKLJUCEN")) {
                    greska("UPOZORENJE: Uzemljenje je upaljeno!");//System.err.println("UPOZORENJE: Uzemljenje je upaljeno");
                }
                else
                    greska("UPOZORENJE: Drugi rastavljač je upaljen!");
            }
        }
        else greska("Upravljanje je lokalno");//System.err.println("Upravljanje je lokalno");
    }

    @FXML
    void isk_sp_l_r_klik(ActionEvent event) {
        if(servis.isTestUpravljanje()) {
            if (sp_ras_l) pomakni_rastavljac_lijevi(srl, true, sp_rl_k);
            sp_ras_l = false;
            servis.getSpLijevi().setStanje(Rastavljac.StanjeRastavljaca.ISKLJUCEN);
            servis.getSignali().add(new Signal("SP-Lijevi", "ISKLJUCEN"));
        }
        else greska("Upravljanje je lokalno");//System.err.println("Upravljanje je lokalno");
    }

    @FXML
    void uk_sp_l_r_klik(ActionEvent event) {
        if(servis.isTestUpravljanje()) {
            if (!sp_ras_l) pomakni_rastavljac_lijevi(srl, false, sp_rl_k);
            sp_ras_l = true;
            servis.getSpLijevi().setStanje(Rastavljac.StanjeRastavljaca.UKLJUCEN);
            servis.getSignali().add(new Signal("SP-Lijevi", "UKLJUCEN"));
        }
        else greska("Upravljanje je lokalno");//System.err.println("Upravljanje je lokalno");
    }


    @FXML
    void uk_sp_p_klik(ActionEvent event) {
        if(servis.isTestUpravljanje()) {
            if (!sp_p) pomakni_prekidac_spojno(sp, false, sp_p_k);
            sp_p = true;
            servis.getSpPrekidac().setStanje(Prekidac.StanjePrekidaca.UKLJUCEN);
            servis.getSignali().add(new Signal("SP-Prekidac", "UKLJUCEN"));
        }
        else greska("Upravljanje je lokalno");//System.err.println("Upravljanje je lokalno");
    }

    @FXML
    void isk_sp_p_klik(ActionEvent event) {
        if(servis.isTestUpravljanje()) {
            if (sp_p) pomakni_prekidac_spojno(sp, true, sp_p_k);
            sp_p = false;
            servis.getSpPrekidac().setStanje(Prekidac.StanjePrekidaca.ISKLJUCEN);
            servis.getSignali().add(new Signal("SP-Prekidac", "ISKLJUCEN"));
        }
        else greska("Upravljanje je lokalno");//System.err.println("Upravljanje je lokalno");
    }

    @FXML
    void uk_sp_d_r_klik(ActionEvent event)
    {
        if(servis.isTestUpravljanje())
        {
            if (!sp_ras_d) pomakni_rastavljac_desni(srd, false, sp_rd_k);
            sp_ras_d = true;
            servis.getSpDesni().setStanje(Rastavljac.StanjeRastavljaca.UKLJUCEN);
            servis.getSignali().add(new Signal("SP-Desni", "UKLJUCEN"));
        }
        else greska("Upravljanje je lokalno");//System.err.println("Upravljanje je lokalno");
    }

    @FXML
    void isk_sp_d_r_klik(ActionEvent event) {
        if(servis.isTestUpravljanje()) {
            if (sp_ras_d) pomakni_rastavljac_desni(srd, true, sp_rd_k);
            sp_ras_d = false;
            servis.getSpDesni().setStanje(Rastavljac.StanjeRastavljaca.ISKLJUCEN);
            servis.getSignali().add(new Signal("SP-Desni", "ISKLJUCEN"));
        }
        else greska("Upravljanje je lokalno");//System.err.println("Upravljanje je lokalno");
    }

    @FXML
    void ras_l_dv_klik(ActionEvent event) {
    	Signali_Screen ss = new Signali_Screen(servis, Tip_signala.DV_LIJEVI);
    	ss.postavi();
    }

    @FXML
    void ras_d_dv_klik(ActionEvent event) {
    	Signali_Screen ss = new Signali_Screen(servis, Tip_signala.DV_DESNI);
    	ss.postavi();
    }

    @FXML
    void ras_p_dv_klik(ActionEvent event) {
    	Signali_Screen ss = new Signali_Screen(servis, Tip_signala.DV_PREKIDAC);
    	ss.postavi();
    }

    @FXML
    void ras_l_sp_klik(ActionEvent event) {
    	Signali_Screen ss = new Signali_Screen(servis, Tip_signala.SP_LIJEVI);
    	ss.postavi();
    }

    @FXML
    void ras_d_sp_klik(ActionEvent event) {
    	Signali_Screen ss = new Signali_Screen(servis, Tip_signala.SP_DESNI);
    	ss.postavi();
    }

    @FXML
    void p_sp_klik(ActionEvent event) {
    	Signali_Screen ss = new Signali_Screen(servis, Tip_signala.SP_PREKIDAC);
    	ss.postavi();
    }
    
    @FXML
    void ras_do_dv_klik(ActionEvent event) {
    	Signali_Screen ss = new Signali_Screen(servis, Tip_signala.DV_UZEMLJENJE);
    	ss.postavi();
    }
    @FXML
    void svi_sp_klik(ActionEvent event) {
    	Signali_Screen ss = new Signali_Screen(servis, Tip_signala.SP_SVE);
    	ss.postavi();
    }
    
    @FXML
    void svi_dv_klik(ActionEvent event) {
    	Signali_Screen ss = new Signali_Screen(servis, Tip_signala.DV_SVE);
    	ss.postavi();
    }
    
    @FXML
    void trenutni_sp_klik(ActionEvent event) {
    	Signali_Screen ss = new Signali_Screen(servis, Tip_signala.SP_TRENUTNO);
    	ss.postavi();
    }
    
    @FXML
    void trenutni_dv_klik(ActionEvent event) {
    	Signali_Screen ss = new Signali_Screen(servis, Tip_signala.DV_TRENUTNO);
    	ss.postavi();
    }
    
    //Funkcije
    
    private void pomakni_rastavljac_lijevi(Line linija, boolean otvori, Circle status) {
    	Rotate rotation = new Rotate();
        rotation.pivotXProperty().bind(linija.endXProperty());
        rotation.pivotYProperty().bind(linija.endYProperty());
    	
    	linija.getTransforms().add(rotation);
    	
    	double angle = 0;
    	
    	Animation animacija;
    	
    	if (otvori) {
    		angle = 30;
    		
    		animacija = new Transition() {
            	
            	{
            		setCycleDuration(Duration.millis(400));
            	}
    			
    			@Override
    			protected void interpolate(double frac) {
    				status.setFill(new Color((0.361+frac>=1)?1:0.361+frac, (0.765-frac<=0)?0:0.765-frac, 0.016, 1));
    			}
    		};
    		
    	} else {
    		angle = -30;
    		
    		animacija = new Transition() {
            	
            	{
            		setCycleDuration(Duration.millis(400));
            	}
    			
    			@Override
    			protected void interpolate(double frac) {
    				status.setFill(new Color((1-frac<=0.361)?0.361:1-frac, (frac>=0.765)?0.765:frac, 0.016, 1));
    			}
    		};
    		
    	}

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(400), new KeyValue(rotation.angleProperty(), angle)));
        
        timeline.play();
		animacija.play();
    }
    
  private void pomakni_rastavljac_desni(Line linija, boolean otvori, Circle status) {
    	Rotate rotation = new Rotate();
        rotation.pivotXProperty().bind(linija.endXProperty());
        rotation.pivotYProperty().bind(linija.endYProperty());
    	
    	linija.getTransforms().add(rotation);
    	
    	double angle = 0;
    	
    	Animation animacija;
    	
    	if (otvori) {
    		angle = -30;
    		
    		animacija = new Transition() {
            	
            	{
            		setCycleDuration(Duration.millis(400));
            	}
    			
    			@Override
    			protected void interpolate(double frac) {
    				status.setFill(new Color((0.361+frac>=1)?1:0.361+frac, (0.765-frac<=0)?0:0.765-frac, 0.016, 1));
    			}
    		};
    		
    	} else {
    		angle = 30;
    		
    		animacija = new Transition() {
            	
            	{
            		setCycleDuration(Duration.millis(400));
            	}
    			
    			@Override
    			protected void interpolate(double frac) {
    				status.setFill(new Color((1-frac<=0.361)?0.361:1-frac, (frac>=0.765)?0.765:frac, 0.016, 1));
    			}
    		};
    		
    	}

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(400), new KeyValue(rotation.angleProperty(), angle)));
        
        timeline.play();
        animacija.play();
    }
  
  private void pomakni_uzemljenje(Line linija, boolean otvori, Circle status) {
	  	Rotate rotation = new Rotate();
	      rotation.pivotXProperty().bind(linija.endXProperty());
	      rotation.pivotYProperty().bind(linija.endYProperty());
	  	
	  	linija.getTransforms().add(rotation);
	  	
	  	double angle = 0;
	  	
	  	Animation animacija;
	  	
	  	if (otvori) {
	  		angle = 30;
	  		
	  		animacija = new Transition() {
	        	
	        	{
	        		setCycleDuration(Duration.millis(400));
	        	}
				
				@Override
				protected void interpolate(double frac) {
					status.setFill(new Color((0.361+frac>=1)?1:0.361+frac, (0.765-frac<=0)?0:0.765-frac, 0.016, 1));
				}
			};
	  		
	  	} else {
	  		angle = -30;
	  		
	  		animacija = new Transition() {
	        	
	        	{
	        		setCycleDuration(Duration.millis(400));
	        	}
				
				@Override
				protected void interpolate(double frac) {
					status.setFill(new Color((1-frac<=0.361)?0.361:1-frac, (frac>=0.765)?0.765:frac, 0.016, 1));
				}
			};
	  		
	  	}

	      Timeline timeline = new Timeline(
	              new KeyFrame(Duration.millis(400), new KeyValue(rotation.angleProperty(), angle)));
	      
	      timeline.play();
	      animacija.play();
	  }
  
  private void pomakni_rastavljac_donji(Line linija, boolean otvori, Circle status) {
  	Rotate rotation = new Rotate();
      rotation.pivotXProperty().bind(linija.endXProperty());
      rotation.pivotYProperty().bind(linija.endYProperty());
  	
  	linija.getTransforms().add(rotation);
  	
  	double angle = 0;
  	
  	Animation animacija;
  	
  	if (otvori) {
  		angle = 30;
  		
  		animacija = new Transition() {
        	
        	{
        		setCycleDuration(Duration.millis(400));
        	}
			
			@Override
			protected void interpolate(double frac) {
				status.setFill(new Color((0.361+frac>=1)?1:0.361+frac, (0.765-frac<=0)?0:0.765-frac, 0.016, 1));
			}
		};
  		
  	} else {
  		angle = -30;
  		
  		animacija = new Transition() {
        	
        	{
        		setCycleDuration(Duration.millis(400));
        	}
			
			@Override
			protected void interpolate(double frac) {
				status.setFill(new Color((1-frac<=0.361)?0.361:1-frac, (frac>=0.765)?0.765:frac, 0.016, 1));
			}
		};
  		
  	}

      Timeline timeline = new Timeline(
              new KeyFrame(Duration.millis(400), new KeyValue(rotation.angleProperty(), angle)));
      
      timeline.play();
      animacija.play();
  }
  
  private void pomakni_prekidac_spojno(Line linija, boolean otvori, Circle status) {
	  RotateTransition rotacija = new RotateTransition(Duration.millis(400));
	  rotacija.setNode(linija);
  	
  	double angle = 0;
  	
  	Animation animacija;
  	
  	if (otvori) {
  		angle = 90;
  		
  		animacija = new Transition() {
        	
        	{
        		setCycleDuration(Duration.millis(400));
        	}
			
			@Override
			protected void interpolate(double frac) {
				status.setFill(new Color((0.361+frac>=1)?1:0.361+frac, (0.765-frac<=0)?0:0.765-frac, 0.016, 1));
			}
		};
  		
  	} else {
  		angle = 0;
  		
  		animacija = new Transition() {
        	
        	{
        		setCycleDuration(Duration.millis(400));
        	}
			
			@Override
			protected void interpolate(double frac) {
				status.setFill(new Color((1-frac<=0.361)?0.361:1-frac, (frac>=0.765)?0.765:frac, 0.016, 1));
			}
		};
  		
  	}

     rotacija.setToAngle(angle);;
     rotacija.play();
     animacija.play();
  }
  
  private void pomakni_prekidac_dalekovodno(Line linija, boolean otvori, Circle status) {
	  RotateTransition rotacija = new RotateTransition(Duration.millis(400));
	  rotacija.setNode(linija);
  	
  	double angle = 0;
  	
  	Animation animacija;
  	
  	if (otvori) {
  		angle = 0;
  		
  		animacija = new Transition() {
        	
        	{
        		setCycleDuration(Duration.millis(400));
        	}
			
			@Override
			protected void interpolate(double frac) {
				status.setFill(new Color((0.361+frac>=1)?1:0.361+frac, (0.765-frac<=0)?0:0.765-frac, 0.016, 1));
			}
		};
  		
  	} else {
  		angle = 90;
  		
  		animacija = new Transition() {
        	
        	{
        		setCycleDuration(Duration.millis(400));
        	}
			
			@Override
			protected void interpolate(double frac) {
				status.setFill(new Color((1-frac<=0.361)?0.361:1-frac, (frac>=0.765)?0.765:frac, 0.016, 1));
			}
		};
  		
  	}

     rotacija.setToAngle(angle);;
     rotacija.play();
     animacija.play();
  }

  @FXML
  void isklop_botun_klik(ActionEvent event) {
	  servis.isklop();
  }
  
  @FXML
  void daljinsko_klik(ActionEvent event) {
        servis.setUpravlj(Upravljanje.DALJINSKO);
  }

  @FXML
  void lokalno_klik(ActionEvent event) {
	  servis.setUpravlj(Upravljanje.LOKALNO);
  }
  
  @FXML
  void obrisi_botun_klik(ActionEvent event) {
	  labela_greske.setText("");
  }
  
  @FXML
  void mjerenja_klik(ActionEvent event) {
  	Mjerenja_Screen m = new Mjerenja_Screen();
  	m.postavi();
  }
  
  void greska(String err) {
	  
	  int god = Calendar.getInstance().get(Calendar.YEAR);
      int mjes = Calendar.getInstance().get(Calendar.MONTH) + 1;
      int dan = Calendar.getInstance().get(Calendar.DATE);
      int sat = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
      int min = Calendar.getInstance().get(Calendar.MINUTE);
      int sec = Calendar.getInstance().get(Calendar.SECOND);
      
      String sat_str, min_str, sec_str;
      
      if (sat < 10) sat_str = "0" + sat;
      else sat_str = String.valueOf(sat);
      
      if (min < 10) min_str = "0" + min;
      else min_str = String.valueOf(min);
      
      if (sec < 10) sec_str = "0" + sec;
      else sec_str = String.valueOf(sec);
      
      String vrijeme = sat_str+":"+min_str+":"+sec_str+" "+dan+"/"+mjes+"/"+god;
      
	  String do_sada = labela_greske.getText();
	  do_sada = vrijeme + " " + err + "\n" + do_sada;
	  labela_greske.setText(do_sada);
	  
  }
	  

}


