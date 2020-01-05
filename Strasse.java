public class Strasse extends Grundstueck
{
    private String farbe;
    private int hausAnzahl; // 5 Häuse => Hotel
    private int hausPreis;
    
    public Strasse (int feldnummer, String feldname, Spieler besitzer, int preis, String farbe, int hausAnzahl, int hausPreis, int[] miete)
    {
        super(feldnummer, "Straße", feldname, preis, miete);
        this.farbe = farbe;
        this.hausAnzahl = hausAnzahl;
        this.hausPreis = hausPreis;
    }
    
    
    public int getHausPreis() {
		return hausPreis;
	}


	public String getFarbe()
    {
        return this.farbe;
    }
    
    public void setFarbe(String farbe)
    {
        this.farbe = farbe;
    }
    
    public int getHausAnzahl()
    {
        return this.hausAnzahl;
    }
    
    public void setHausAnzahl(int hausAnzahl)
    {
        this.hausAnzahl = hausAnzahl;
    }
    
    public boolean hasHaus()
    {
    	if(hausAnzahl > 0)
    	{
    		return true;
    	}
    	return false;
    }
    
    public int getMietpreis(int aktiverSpieler)
    {
        if(Main.alleSpieler.get(aktiverSpieler).sayIfAlleFarben(getFarbe()) && hausAnzahl == 0)
            return miete[0]*2; // wenn gleiche Farben vorhanden sind, aber die aktuelle Straße keine Häuser besitzt,
                              // wird die Miete zweimal höher
        return miete[hausAnzahl];
    }
    public void miete(int aktiverSpieler)
    {
    	System.out.println("\nZahle Miete von " + getMietpreis(aktiverSpieler) +" Mark an den Spieler mit der Figur " + getBesitzer().getFigur() + ".");
    	Main.alleSpieler.get(aktiverSpieler).paySpieler(getBesitzer().getSpielernummer(), getMietpreis(aktiverSpieler));
    }
    
    public void feldBetreten(int aktiverSpieler) 
    {
    	System.out.println("\nDu hast die " + getFeldname() + " betreten. Sie hat die Farbe " + getFarbe() + ".");
    	if(getBesitzer() == null)
    	{
    		askKaufentscheidung(aktiverSpieler);
        }
        else if(!getHypothek()) // mit Hypothek = keine Miete = Frei Parken
        {
            miete(aktiverSpieler);
        }
    }
    
    // welcherSpieler wird für die Unterscheidung der aktiven Spieler (für den das gezeigt wird) von anderen
    // nur für die Straße toString(): welcherSpieler == null => die Methode wird nicht für die feldAusgeben verwendet
    public String toString(Spieler welcherSpieler)
    {
        String ergebnis;
        if (welcherSpieler != null)
             ergebnis = "-- Die " + getFeldname() + " Straße in der Farbe " + farbe + " --";
        else
            ergebnis = "Die " + getFeldname() + " Straße in der Farbe " + farbe + ".";


        //Falls dieses Feld keinem Gehört, wird dies Ausgegeben
    	if(getBesitzer() == null)
    	{
    		ergebnis = ergebnis + "\nDiese Straße gehört keinem.";
    	}
    	//Ausgeben vom Besitzer des Feldes
    	else
    	{
            if (welcherSpieler.equals(getBesitzer()))
                ergebnis = ergebnis + "\nDiese Straße gehört dir.";
            else
                ergebnis = ergebnis + "\nDiese Straße gehört dem Spieler mit der Figur: " + getBesitzer().getFigur() + ".";
        }
        
        if(getHypothek())
            ergebnis += " Auf die Straße wurde eine Hypothek aufgenommen.";
        else
        {
            switch (hausAnzahl)
            {
                case 0:
                    ergebnis += " Auf der Straße gibt es keine Immobilien.";
                    break;
                case 1:
                    ergebnis += " Auf der Straße gibt es ein Haus.";
                    break;
                case 5:
                    ergebnis += " Auf der Straße ist ein Hotel";
                    break;
                default:
                    ergebnis += " Auf der Straße gibt es " + hausAnzahl + " Häuse.";
            }
        }
        return ergebnis;
    }
}