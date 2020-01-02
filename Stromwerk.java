import java.util.ArrayList;

public class Stromwerk extends Stadtwerk
{
    public Stromwerk(int feldnummer)
    {
    	super(feldnummer, "Elektrizitätswerk", "Elektrizitätswerk", 150);
    }
    
    //Bei Stadtwerken, wird die Miete anders berechnet als bei anderen Feldern. 
    //Wenn der Besitzer dieses Stadtwerkes das andere nicht besitzt, ist die Miete = (Die gewürfelte Zahl des Aktiven Spielers * 4)
    //Wenn der Besitzer das andere Stadtwerk auch besitzt, ist die Miete = (Die gewürfelte Zahl des Aktiven Spielers * 4)
    public ArrayList<Spieler> miete(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld, int gewürfelteZahl)
    {
    	//Ist der Besitzer des Stromwerks auch der Besitzer des Wasserwerks?
    	if(getBesitzer() ==  ((Stadtwerk)(spielfeld[28])).getBesitzer())
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
    	//Gehört dieses Stromwerk schon jemandem? Falls nein, kann es gekauft werden, falls ja, muss miete bezahlt werden
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
    
    public String toString()
    {
    	String ergebnis = "Das " + getFeldname();
    	//Falls dieses Feld keinem Gehört, wird dies Ausgegeben
    	if(getBesitzer() == null)
    	{
    		ergebnis = ergebnis + "\nDieses " + getFeldname() + " gehört keinem.";
    	}
    	//Ausgeben vom Besitzer des Feldes
    	else
    	{
    		ergebnis = ergebnis + "\nDieses " + getFeldname() + " gehört dem Spieler mit der Figur :" + getBesitzer().getFigur() + ".";
    	}
    	
    	return ergebnis;
    }
}
