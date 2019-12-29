public class Stromwerk extends Stadtwerk
{
    public Stromwerk(int feldnummer)
    {
    	super(feldnummer, "Elektrizitätswerk", "Elektrizitätswerk", 150);
    }
    
    //Bei Stadtwerken, wird die Miete anders berechnet als bei anderen Feldern. 
    //Wenn der Besitzer dieses Stadtwerkes das andere nicht besitzt, ist die Miete = (Die gewürfelte Zahl des Aktiven Spielers * 4)
    //Wenn der Besitzer das andere Stadtwerk auch besitzt, ist die Miete = (Die gewürfelte Zahl des Aktiven Spielers * 4)
    public void miete(int aktiverSpieler, int gewürfelteZahl)
    {
    	//Ist der Besitzer des Stromwerks auch der Besitzer des Wasserwerks?
    	if(getBesitzer() == ((Stadtwerk)(Main.spielfeld[28])).getBesitzer())
    	{
    		Main.alleSpieler.get(aktiverSpieler).paySpieler(getBesitzer().getSpielernummer(), (gewürfelteZahl * 10));
    	}
    	else
    	{
    		Main.alleSpieler.get(aktiverSpieler).paySpieler(getBesitzer().getSpielernummer(), (gewürfelteZahl * 4));
    	}
    }
    
    public void feldBetreten(int aktiverSpieler, int gewürfelteZahl)
    {
    	//Gehört dieses Stromwerk schon jemandem? Falls nein, kann es gekauft werden, falls ja, muss miete bezahlt werden
    	if(getBesitzer() == null)
    	{
    		askKaufentscheidung(aktiverSpieler);
    	}
    	else if(!getHypothek()) // mit Hypothek = keine Miete = Frei Parken
    	{
    		miete(aktiverSpieler, gewürfelteZahl);
    	}
    }
}
