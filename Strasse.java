public class Strasse extends Grundstueck
{
    private String farbe;
    private int hausAnzahl; // 5 Häuse => Hotel
    private int hausPreis;
    
    public Strasse (int feldnummer, String feldname, Spieler besitzer, int preis, String farbe, int hausAnzahl, int hausPreis)
    {
        super(feldnummer, "Straße", feldname, preis);
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
    // --!-- In MAIN: if Straße : wenn Spieler besitzt alle Felder der Farbe => fragen nach dem neuen Huus
    
    public void bauHaus()
    {
        setHausAnzahl(getHausAnzahl()+1);
        String hausOderhotel = (getHausAnzahl() < 4) ? "Haus" : "Hotel";
    
        if(hausOderhotel == "Haus")
        {
            getBesitzer().subtractGeld(getHausPreis());
        }
    }
    
    // zeigt Straßeninfos mit (True) oder ohne (False) Immobilieninfos
    public String toString(boolean mitImmobilien)
    {
        String ergebnis = "Die " + getFeldname() + " Straße in der Farbe " + farbe;

        if (mitImmobilien)
        {
            switch (hausAnzahl)
            {
                case 0:
                    ergebnis += ". Auf der Straße gibt es keine Immobilien.";
                    break;
                case 1:
                    ergebnis += ". Auf der Straße gibt es ein Haus.";
                    break;
                case 5:
                    ergebnis += ". Auf der Straße ist ein Hotel";
                    break;
                default:
                    ergebnis += ". Auf der Straße gibt es " + hausAnzahl + " Häuse.";
            }
        }

        return ergebnis;
    }
}