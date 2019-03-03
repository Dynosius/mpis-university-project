package projekt.MPISCore.Kompozicija;
//Treba samo vratiti neke signale, i imati varijablu koja kaze je li on/off;
public class Zastita {

    public enum Stanje
    {
        UKLJUCENA, ISKLJUCENA
    }
    private Stanje stanje;
    private Stanje NPC;
    private Stanje VPC;

    public String getVPC() {
        return VPC.toString();
    }

    public void setVPC(Stanje VPC) {
        this.VPC = VPC;
    }

    public String getNPC() {
        return NPC.toString();
    }

    public void setNPC(Stanje NPC) {
        this.NPC = NPC;
    }

    public void setStanje(Stanje state)
    {
        this.stanje = state;
    }
    public String vratiStanje()
    {
        return stanje.toString();
    }

    public boolean ispitaj(Polje polje)
    {
        if((polje.getRastavljaci().get(0).rawStanje().equals("UKLJUCEN") || polje.getRastavljaci().get(1).rawStanje().equals("UKLJUCEN")) &&
                polje.getPrekidaci().get(0).rawStanje().equals("UKLJUCEN")) return true;
        else return false;
    }
}
