import java.util.ArrayList;

public class Bahnhof extends Grundstueck
{
    public Bahnhof(int Feldnummer, String Feldname)
    {
    	super(Feldnummer, "Bahnhof", Feldname, 200);
    }
    
    public ArrayList<Spieler> Ereignis(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
    	//Kaufentscheidung/ Miete
    	
    	
    	return alleSpieler;
    }
    
    public ArrayList<Spieler> feldBetreten(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
    	System.out.println("Du hast den " + getFeldname() +" betreten.");
    	return Ereignis(alleSpieler, aktiverSpieler, spielfeld);
    }
    
    public void toString()
    {
        
    }
}