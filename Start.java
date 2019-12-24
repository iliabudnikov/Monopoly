import java.util.ArrayList;
public class Start extends Aktionsfelder
{
    
    public Start(int Feldnummer)
    {
    	//int feldnummer, boolean feldtyp, String feld, String feldname
        super(Feldnummer, false, "Los", "Los");
    	
    }
    public ArrayList<Spieler> ereignis(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        feldBetreten();
        return alleSpieler;
    }
    public void feldBetreten()
    {
        System.out.println("Du hast das " + getFeld() + " Feld betreten");
    }
}
