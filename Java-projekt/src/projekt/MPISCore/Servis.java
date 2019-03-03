package projekt.MPISCore;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gui.Glavni_kontroler;
import projekt.MPISCore.Kompozicija.APU;
import projekt.MPISCore.Kompozicija.Mjerenja;
import projekt.MPISCore.Kompozicija.Polje;
import projekt.MPISCore.Kompozicija.Prekidac;
import projekt.MPISCore.Kompozicija.Rastavljac;
import projekt.MPISCore.Kompozicija.Signal;
import projekt.MPISCore.Kompozicija.Upravljanje;
import projekt.MPISCore.Kompozicija.Zastita;


/**
    Ova klasa predstavlja runtime. Dakle cijelo međudjelovanje objekata se odvija ovdje. Svaki objekt će imati određeno ponašanje i atribute, koji će
    se ovdje odvijati/mijenjati, itd.
 */

public class Servis{

    private Glavni_kontroler glavni_kontroler;

    private Upravljanje upravlj;
    private Polje DVPolje;
    private Polje SPolje;
    private Prekidac dvPrekidac;
    private Rastavljac dvLijevi;
    private Rastavljac dvDesni;
    private Rastavljac dvUzemljenje;
    private Rastavljac dvLinijski;
    private Prekidac spPrekidac;
    private Rastavljac spLijevi;
    private Rastavljac spDesni;
    private APU apu;
    private Mjerenja mjer;
    private Zastita zastita = new Zastita();
    private final static long delay = 700;
    private ArrayList<Signal> signali = new ArrayList<>();
    private boolean testUpravljanje = true;

    //Boolean za test upravljanja
    public boolean isTestUpravljanje() {
        return testUpravljanje;
    }

    public ArrayList<Signal> getSignali() {

        //Vraca listu objekata, moras pristupiti sa .getElement();
        return signali;
    }

    public Upravljanje getUpravlj() {
        return upravlj;
    }
    public Prekidac getDvPrekidac() {
        return dvPrekidac;
    }

    public Rastavljac getDvLijevi() {
        return dvLijevi;
    }

    public Rastavljac getDvDesni() {
        return dvDesni;
    }

    public Rastavljac getDvUzemljenje() {
        return dvUzemljenje;
    }
    public Rastavljac getDvLinijski() { return dvLinijski; }

    public Prekidac getSpPrekidac() {
        return spPrekidac;
    }

    public Rastavljac getSpLijevi() {
        return spLijevi;
    }

    public Rastavljac getSpDesni() {
        return spDesni;
    }
    public void setUpravlj(Upravljanje upravlj) {
        this.upravlj = upravlj;
        if(upravlj.toString().equals("DALJINSKO"))
        {
            this.testUpravljanje = true;
        }
        else this.testUpravljanje = false;

    }

    public Zastita getZastita()
    {
        return zastita;
    }

    public Polje getDVPolje() {
        return DVPolje;
    }

    public Polje getSPolje() {
        return SPolje;
    }

    public Servis(Glavni_kontroler glavni_kontroler)    //javaception, instanca klase u kojoj je instancirana klasa servis...
    {
        super();
        this.glavni_kontroler = glavni_kontroler;
        DVPolje = new Polje(Polje.StanjaPolja.UKLJUCENO, "DV-B", "TS-B", 220);
        SPolje = new Polje (Polje.StanjaPolja.UKLJUCENO, "DV-B", "TS-B", 220);
        apu = new APU();
        mjer = new Mjerenja();
        setUpravlj(Upravljanje.DALJINSKO);

        dvPrekidac = new Prekidac("DV-Prekidač");
        dvLijevi = new Rastavljac("DV-Lijevi Rastavljač");
        dvDesni = new Rastavljac("DV-Desni Rastavljač");
        dvUzemljenje = new Rastavljac("DV-Uzemljenje");
        spPrekidac = new Prekidac("SP-Prekidač");
        spLijevi = new Rastavljac("SP-Lijevi Rastavljač");
        spDesni = new Rastavljac("SP-Desni Rastavljač");
        dvLinijski = new Rastavljac ("DV-Linijski");


        DVPolje.getPrekidaci().add(dvPrekidac);
        DVPolje.getRastavljaci().add(dvLijevi);
        DVPolje.getRastavljaci().add(dvDesni);
        DVPolje.getRastavljaci().add(dvUzemljenje);
        DVPolje.getRastavljaci().add(dvLinijski);
        SPolje.getRastavljaci().add(spLijevi);
        SPolje.getRastavljaci().add(spDesni);
        SPolje.getPrekidaci().add(spPrekidac);

    }

    public void uklopS2() //automatski se pobrine da su sve zastite i apu upaljene, te pali sve potrebne komponente
    {
        if(!testUpravljanje) System.err.println("Upravljanje je postavljeno na 'Lokalno'");
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.submit (() -> {
            zastita.setStanje(Zastita.Stanje.UKLJUCENA);
            apu.setStanje(APU.StanjeAPU.UKLJUCEN);
            dvUzemljenje.iskljuci();
            glavni_kontroler.dvUzem(false);

            //Provjera je li uzemljenje uključeno, ako nije, upali prekidače
            if(dvUzemljenje.rawStanje().equals("ISKLJUCEN"))
            {
                sleep(delay);
                dvPrekidac.ukljuci();
                glavni_kontroler.dvPrek(true);
                sleep(delay);
            }
            else
            {
                dvUzemljenje.iskljuci();
                glavni_kontroler.dvUzem(false);
                sleep(delay);
                dvLinijski.ukljuci();
                glavni_kontroler.rasDVlinijski(true);
                dvPrekidac.ukljuci();
                sleep(delay);
                glavni_kontroler.dvPrek(true);
                sleep(delay);
            }
            //Ako su prekidači upaljeni, upali S2 rastavljač
            if(dvPrekidac.rawStanje().equals("UKLJUCEN"))
            {
                if(getDvDesni().rawStanje().equals("UKLJUCEN"))
                {
                    dvDesni.iskljuci();
                    glavni_kontroler.rasDVdesni(false);
                }
                dvLinijski.ukljuci();
                glavni_kontroler.rasDVlinijski(true);
                sleep(delay/2);
                dvLijevi.ukljuci();
                glavni_kontroler.rasDVlijevi(true);


            }
            else
            {
                dvLijevi.iskljuci();
                glavni_kontroler.rasDVlijevi(false);
                dvDesni.iskljuci();
                glavni_kontroler.rasDVdesni(false);
                dvLinijski.iskljuci();
                glavni_kontroler.rasDVlinijski(false);
                dvUzemljenje.ukljuci();
                sleep(delay);
                glavni_kontroler.dvUzem(true);
            }
        });
        exec.shutdown();
    }

    public void uklopS1()
    {
        if(!testUpravljanje) System.err.println("Upravljanje je postavljeno na 'Lokalno'");
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.submit (() -> {
            zastita.setStanje(Zastita.Stanje.UKLJUCENA);
            apu.setStanje(APU.StanjeAPU.UKLJUCEN);
            dvUzemljenje.iskljuci();
            glavni_kontroler.dvUzem(false);

            //Provjera je li uzemljenje uključeno, ako nije, upali prekidače
            if(dvUzemljenje.rawStanje().equals("ISKLJUCEN"))
            {
                sleep(delay);
                dvPrekidac.ukljuci();
                glavni_kontroler.dvPrek(true);
                sleep(delay);
            }
            else
            {
                dvUzemljenje.iskljuci();
                glavni_kontroler.dvUzem(false);
                dvPrekidac.ukljuci();
                sleep(delay);
                glavni_kontroler.dvPrek(true);
                spPrekidac.ukljuci();
                sleep(delay);
                glavni_kontroler.spPrek(true);

            }
            //Ako su prekidači upaljeni, počni paliti rastavljače
            if(dvPrekidac.rawStanje().equals("UKLJUCEN"))
            {
                if(getDvLijevi().rawStanje().equals("UKLJUCEN"))
                {
                    dvLijevi.iskljuci();
                    glavni_kontroler.rasDVlijevi(false);
                }
                dvLinijski.ukljuci();
                glavni_kontroler.rasDVlinijski(true);
                sleep(delay/2);
                dvDesni.ukljuci();
                glavni_kontroler.rasDVdesni(true);
            }
            else
            {
                dvDesni.iskljuci();
                glavni_kontroler.rasDVlijevi(false);
                dvDesni.iskljuci();
                glavni_kontroler.rasDVdesni(false);
                dvLinijski.iskljuci();
                glavni_kontroler.rasDVlinijski(false);
                dvUzemljenje.ukljuci();
                sleep(delay);
                glavni_kontroler.dvUzem(true);

            }
        });
        exec.shutdown();
    }


    public void isklop(){ //    Isto pazi na sve komponente, gasi APU (mozda jos dodati provjeru za upravljanje)
        if(!testUpravljanje) System.err.println("Upravljanje je postavljeno na 'Lokalno'");
        ExecutorService exec = Executors.newSingleThreadExecutor(); //Ovo sluzi da se ne zablokira JavaFX thread kada zelim dodati delay

        exec.submit(() -> {
            apu.setStanje(APU.StanjeAPU.ISKLJUCEN);
            //Rastavljaci
            dvLijevi.iskljuci();
            sleep(delay);
            glavni_kontroler.rasDVlijevi(false);
            dvDesni.iskljuci();
            glavni_kontroler.rasDVdesni(false);
            sleep(delay);
            dvLinijski.iskljuci();
            glavni_kontroler.rasDVlinijski(false);
            //Uzemljenje
            dvUzemljenje.ukljuci();
            sleep(delay);
            glavni_kontroler.dvUzem(true);
            //Prekidac DV
            dvPrekidac.iskljuci();
            sleep(delay);
            glavni_kontroler.dvPrek(false);
        });
        exec.shutdown();

    }
    private void sleep(long millisec)
    {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }
    
    
}
