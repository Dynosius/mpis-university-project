package projekt.MPISCore.Kompozicija;
/*
    Treba vratiti neka bezvezna random mjerenja, ovisno o informacijama koje su zadane u zadatku
 */
public class Mjerenja {
    private double jalovaE = 1.5;
    private double struja = 20;
    private double jalovaS = 2;

    public String getJalovaE() {
        return jalovaE + " MVArh";
    }

    public String getJalovaS() {
        return jalovaS + " MVAr";
    }

    public void setJalovaS(double jalovaS) {
        this.jalovaS = jalovaS;
    }

    public String getStruja() {

        return struja + " A";
    }

    public void setStruja(double struja) {
        this.struja = struja;
    }

    public void setJalovaE(double jalovaE) {
        this.jalovaE = jalovaE;
    }
}
