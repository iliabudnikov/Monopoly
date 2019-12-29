import java.util.ArrayList;

public class Wasserwerk extends Stadtwerk
{
	public Wasserwerk(int feldnummer)
    {
    	super(feldnummer, "Wasserwerk", "Wasserwerk", 150);
    }
    
	//Bei Stadtwerken, wird die Miete anders berechnet als bei anderen Feldern. 
    //Wenn der Besitzer dieses Stadtwerkes das andere nicht besitzt, ist die Miete = (Die gewürfelte Zahl des Aktiven Spielers * 4)
    //Wenn der Besitzer das andere Stadtwerk auch besitzt, ist die Miete = (Die gewürfelte Zahl des Aktiven Spielers * 4)
    public ArrayList<Spieler> miete(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld, int gewürfelteZahl)
    {
    	//Ist der Besitzer des Wasserrwerks auch der Besitzer des Stromwerks?
    	if(getBesitzer() ==  ((Stadtwerk)(spielfeld[12])).getBesitzer())
    	{
    		alleSpieler.get(aktiverSpieler).paySpieler(getBesitzer().getSpielernummer(), (gewürfelteZahl * 10));
    	}
    	else
    	{
    		alleSpieler.get(aktiverSpieler).paySpieler(getBesitzer().getSpielernummer(), (gewürfelteZahl * 4));
    	}
    	return alleSpieler;
    }
    
    public ArrayList<Spieler> feldBetreten(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld, int gewürfelteZahl)
    {
    	//Gehört dieses Wasserwerk schon jemandem? Falls nein, kann es gekauft werden, falls ja, muss miete bezahlt werden
    	if(getBesitzer() == null)
    	{
    		askKaufentscheidung(aktiverSpieler);
    	}
    	else
    	{
    		alleSpieler = miete(alleSpieler, aktiverSpieler, spielfeld, gewürfelteZahl);
    	}
    	
    	return alleSpieler;
    }
}
