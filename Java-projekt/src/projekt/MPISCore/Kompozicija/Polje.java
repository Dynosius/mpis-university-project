package projekt.MPISCore.Kompozicija;

import java.util.ArrayList;
import java.util.List;

public class Polje {

    public enum StanjaPolja { UKLJUCENO, ISKLJUCENO }

    private int naponskaRazina;
    private String tModul;
    private String postrojenje;
    private StanjaPolja stanje;
    private List<Prekidac> prekidaci = new ArrayList<>();
    private List<Rastavljac> rastavljaci = new ArrayList<>();

    private Polje(){}
    public Polje(StanjaPolja stanjeP, String tModul, String postrojenje, int naponskaRazina)    //inicijalno postavljanje polja. Treba staviti na "uključeno"
    {
        this.naponskaRazina = naponskaRazina;
        this.postrojenje = postrojenje;
        this.tModul = tModul;
        this.stanje = stanjeP;
    }

    public String vratiStanje()
    {
       return stanje.toString();
    }

    //Getteri i setteri
    public int vratiNapon() { return this.naponskaRazina; }
    public String vratiPostrojenje() { return this.postrojenje; }
    public String vratiModul() { return this.tModul; }
    public List<Prekidac> getPrekidaci() { return prekidaci; }
    public List<Rastavljac> getRastavljaci() { return rastavljaci; }
}

