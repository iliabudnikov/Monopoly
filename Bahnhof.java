public class Bahnhof extends Grundstueck
{
    public Bahnhof(int Feldnummer, String Feldname)
    {
    	super(Feldnummer, "Bahnhof", Feldname, 200, null);
    }
    
    public void Ereignis(int aktiverSpieler)
    {
    	//Kaufentscheidung/ Miete
    	if(getBesitzer() == null)
    	{
    		askKaufentscheidung(aktiverSpieler);
    	}
    	else if(!getHypothek()) // mit Hypothek = keine Miete = Frei Parken
    	{
    		miete(aktiverSpieler);
    	}
    }
    
    public void feldBetreten(int aktiverSpieler)
    {
    	System.out.println("\nDu hast den " + getFeldname() +" betreten.");
    	Ereignis(aktiverSpieler);
    }
    
    public void miete(int aktiverSpieler)
    {
    	int bahnhöfeBesitzer = 0;
    	for(int i = 0; i < Main.alleSpieler.get(aktiverSpieler).getGrundstuecke().length; i++)
    	{
    		if(Main.spielfeld[i].getFeld().equals("Bahnhof"))
    		{
    			bahnhöfeBesitzer++;
    		}
    	}
    	
    	switch(bahnhöfeBesitzer)
    	{
			case 1:
				Main.alleSpieler.get(aktiverSpieler).paySpieler(getBesitzer().getSpielernummer(), 25);
				break;
			case 2:
				Main.alleSpieler.get(aktiverSpieler).paySpieler(getBesitzer().getSpielernummer(), 50);
				break;
			case 3:
				Main.alleSpieler.get(aktiverSpieler).paySpieler(getBesitzer().getSpielernummer(), 100);
				break;
			case 4:
				Main.alleSpieler.get(aktiverSpieler).paySpieler(getBesitzer().getSpielernummer(), 200);
				break;
    	}
    }
    
    public String toString(Spieler welcherSpieler)
    {
    	String ergebnis = "-- Der " + getFeldname() + " --";
    	//Falls dieses Feld keinem Gehört, wird dies Ausgegeben
    	if(getBesitzer() == null)
    	{
    		ergebnis = ergebnis + "\nDieser " + getFeldname() + " gehört keinem.";
    	}
    	//Ausgeben vom Besitzer des Feldes
    	else
    	{
			if (welcherSpieler.equals(getBesitzer()))
				ergebnis = ergebnis + "\nDieser " + getFeldname() + " gehört dir.";
			else
				ergebnis = ergebnis + "\nDieser " + getFeldname() + " gehört dem Spieler mit der Figur: " + getBesitzer().getFigur() + ".";
	  	}
		
		if(getHypothek())
		  ergebnis += " Auf den Bahnhof wurde eine Hypothek aufgenommen.";
		
    	return ergebnis;
    }
}