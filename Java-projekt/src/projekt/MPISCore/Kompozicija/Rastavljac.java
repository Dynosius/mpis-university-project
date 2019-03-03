package projekt.MPISCore.Kompozicija;


public class Rastavljac {

    private StanjeRastavljaca stanje = StanjeRastavljaca.ISKLJUCEN; //Imao sam neki exception pa mi se cini da ovo rijesava taj problem
    private String name;

    public enum StanjeRastavljaca {
        UKLJUCEN, ISKLJUCEN, KVAR, MEDUPOLOZAJ;
    }

    public Rastavljac(String name)
    {
        this.name = name;
    }
    public String getStanje() {
        return name + ": " + stanje.toString();
    }

    public String rawStanje()
    {
        return stanje.toString();
    }


    public void setStanje(StanjeRastavljaca stanje) {
        this.stanje = stanje;
    }

    public void ukljuci(){ this.stanje = stanje.UKLJUCEN; }
    public void iskljuci(){ this.stanje = stanje.ISKLJUCEN; }
}
