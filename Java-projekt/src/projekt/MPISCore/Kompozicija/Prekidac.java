package projekt.MPISCore.Kompozicija;

public class Prekidac {

    private StanjePrekidaca stanje;
    private String name;
    public enum StanjePrekidaca
    {
        UKLJUCEN, ISKLJUCEN, MEDUPOLOZAJ, KVAR;
    }
    public Prekidac(String name)
    {
        this.name = name;
    }

    public void ukljuci(){ this.stanje = stanje.UKLJUCEN; }
    public void iskljuci(){ this.stanje = stanje.ISKLJUCEN; }

    public String getStanje() {
        return name + ": " + stanje.toString();
    }
    public String rawStanje()
    {
        return stanje.toString();
    }

    public void setStanje(StanjePrekidaca stanje) {
        this.stanje = stanje;
    }
}
