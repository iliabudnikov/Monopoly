import java.util.ArrayList;

public abstract class Feld
{
    //Nummer des Feldes in der Reihenfolge des Gesammten Spielfeldes
    private int feldnummer;
    //Boolean zur unterscheidung von Aktionsfeldern(False) und Spielfeldern(True)
    private boolean feldtyp;
    //Was für ein Feld ist es genau? Straße? Bahnhof? Ereignisfeld? Als String
    private String feld;
    //z.B. Südbahnhof, Schlossallee
    private String feldname;
    
    public Feld (int feldnummer, boolean feldtyp, String feld, String feldname)
    {
        this.feldnummer = feldnummer;
        this.feldtyp = feldtyp;
        this. feld = feld;
        this.feldname = feldname;
    }
    
    public int getFeldnummer()
    {
        return feldnummer;
    }
    
    public boolean getfeldtyp()
    {
        return feldtyp;
    }
    
    public String getFeld()
    {
        return feld;
    }
    
    public String getFeldname()
    {
        return feldname;
    }
}