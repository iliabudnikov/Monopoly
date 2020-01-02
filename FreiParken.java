import java.util.ArrayList;
public class FreiParken extends Aktionsfelder
{
    public FreiParken(int Feldnummer)
    {
    	super(Feldnummer, false, "Frei Parken", "Frei Parken");
    }
    public ArrayList<Spieler> Ereignis(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        return alleSpieler;
    }
    public ArrayList<Spieler> feldBetreten(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        System.out.println("Du hast ein " + getFeld() + " Feld betreten");
        return alleSpieler;
    }
    
    public String toString()
    {
    	String ergebnis = "Das " + getFeldname() + " Feld.";
    	return ergebnis;
    }
}