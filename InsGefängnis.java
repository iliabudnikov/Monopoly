import java.util.ArrayList;
public class InsGefängnis extends Aktionsfelder
{
    public InsGefängnis(int Feldnummer)
    {
        super(Feldnummer, false, "Ins Gefängnis", "Ins Gefängnis");
    }
    
    public ArrayList<Spieler> Ereignis(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        System.out.println("Gegen Sie in das Gefängnis, gehen Sie nicht über Los und ziehen sie keine 200 Geld ein!");
        alleSpieler.get(aktiverSpieler).setImGefängnis();
        return alleSpieler;
    }
    
    public ArrayList<Spieler> feldBetreten(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
    	System.out.println("Du hast das 'Gehen sie in das Gefängnis' Feld betreten.");
    	return Ereignis(alleSpieler, aktiverSpieler, spielfeld);
    }
    
    public String toString()
    {
    	String ergebnis = "Das Gehe ins Gefängnis Feld";
    	return ergebnis;
    }
}
