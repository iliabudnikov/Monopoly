import java.util.ArrayList;
public class Start extends Aktionsfelder
{
    
    public Start(int Feldnummer)
    {
    	//int feldnummer, boolean feldtyp, String feld, String feldname
        super(Feldnummer, false, "Los", "Los");
    	
    }
    public ArrayList<Spieler> Ereignis(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        return alleSpieler;
    }
    public ArrayList<Spieler> feldBetreten(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        System.out.println("Du hast das " + getFeld() + " Feld betreten");
        return alleSpieler;
    }
}
