import java.util.ArrayList;

public class Bahnhof extends Grundstueck
{
    public Bahnhof(int Feldnummer, String Feldname)
    {
    	super(Feldnummer, "Bahnhof", Feldname, 200, null);
    }
    
    public ArrayList<Spieler> Ereignis(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
    	//Kaufentscheidung/ Miete
    	if(getBesitzer() == null)
    	{
    		askKaufentscheidung(aktiverSpieler);
    	}
    	else
    	{
    		alleSpieler = miete(alleSpieler, aktiverSpieler, spielfeld);
    	}
    	
    	return alleSpieler;
    }
    
    public ArrayList<Spieler> feldBetreten(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
    	System.out.println("Du hast den " + getFeldname() +" betreten.");
    	return Ereignis(alleSpieler, aktiverSpieler, spielfeld);
    }
    
    public ArrayList<Spieler> miete(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
    	int bahnhöfeBesitzer = 0;
    	for(int i = 0; i < spielfeld.length; i++)
    	{
    		if(spielfeld[i].getFeld().equals("Bahnhof"))
    		{
    			bahnhöfeBesitzer++;
    		}
    	}
    	
    	switch(bahnhöfeBesitzer)
    	{
    	case(1):
    		alleSpieler.get(aktiverSpieler).paySpieler(getBesitzer().getSpielernummer(), 25);
    		break;
    	case(2):
    		alleSpieler.get(aktiverSpieler).paySpieler(getBesitzer().getSpielernummer(), 50);
    		break;
    	case(3):
    		alleSpieler.get(aktiverSpieler).paySpieler(getBesitzer().getSpielernummer(), 100);
    		break;
    	case(4):
    		alleSpieler.get(aktiverSpieler).paySpieler(getBesitzer().getSpielernummer(), 200);
    		break;
    	}
    	return alleSpieler;
    }
}