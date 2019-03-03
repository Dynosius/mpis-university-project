package projekt.MPISCore.Kompozicija;

import java.util.Calendar;


public class Signal
{
    private String name;
    private String stanje;
    private int god;
    private int mjes;
    private int dan;
    private int sat;
    private int min;
    private int sec;

    public Signal (String name, String stanje)
    {
        god = Calendar.getInstance().get(Calendar.YEAR);
        mjes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        dan = Calendar.getInstance().get(Calendar.DATE);
        sat = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        min = Calendar.getInstance().get(Calendar.MINUTE);
        sec = Calendar.getInstance().get(Calendar.SECOND);
        this.name = name;
        this.stanje = stanje;
    }

    public String getName() {
        return name;
    }

    public String getStanje() {
        return stanje;
    }

    public int getGod() {
        return god;
    }

    public int getMjes() {
        return mjes;
    }

    public int getDan() {
        return dan;
    }

    public int getSat() {
        return sat;
    }

    public int getMin() {
        return min;
    }

    public int getSec() {
        return sec;
    }
    
    public String getVrijeme() {
        
        String sat_str, min_str, sec_str;
        
        if (sat < 10) sat_str = "0" + sat;
        else sat_str = String.valueOf(sat);
        
        if (min < 10) min_str = "0" + min;
        else min_str = String.valueOf(min);
        
        if (sec < 10) sec_str = "0" + sec;
        else sec_str = String.valueOf(sec);
        
        return sat_str+":"+min_str+":"+sec_str+" "+dan+"/"+mjes+"/"+god;

    }
}


















