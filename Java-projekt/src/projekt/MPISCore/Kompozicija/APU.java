package projekt.MPISCore.Kompozicija;

public class APU {

    public enum StanjeAPU
    {
        UKLJUCEN, ISKLJUCEN;
    }
    StanjeAPU stanje;

    public APU()
    {
        setStanje(StanjeAPU.UKLJUCEN);
    }
    public String getStanje() {
        return "APU: " + stanje.toString();
    }

    public void setStanje(StanjeAPU stanje) {
        this.stanje = stanje;
    }
}
