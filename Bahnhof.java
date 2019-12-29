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
    		if(Main.spielfeld[i].getFeld() == "Bahnhof")
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
}