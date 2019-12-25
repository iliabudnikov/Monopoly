public class Strasse extends Grundstueck
{
    private String farbe;
    private int hausAnzahl; // 5 Häuse => Hotel
    private int hausPreis;
    
    public Strasse (int feldnummer, String feldname, int besitzer, int preis, String farbe, int hausAnzahl, int hausPreis)
    {
        super(feldnummer, "Straße", feldname, besitzer, ???);
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
        
    }
    
    // zeigt Straßeninfos mit (True) oder ohne (False) Immobilieninfos
    public void toString(boolean mitImmobilien)
    {
        System.out.println("\nDie " + getFeldname() + " Straße in der Farbe " + farbe + "kostet " + getPreis() + " Mark.");
        if (mitImmobilien)
        {
            switch (hausAnzahl)
            {
                case 0:
                    System.out.println("\nAuf der Straße gibt es keine Immobilien.");
                    break;
                case 1:
                    System.out.println("\nAuf der Straße gibt es ein Haus.");
                    break;
                case 5:
                    System.out.println("\nAuf der Straße ist ein Hotel");
                    break;
                default:
                    System.out.println("\nAuf der Straße gibt es " + hausAnzahl + " Häuse.");
            }
        }
    }


	@Override
	public void toString() {
		// TODO Auto-generated method stub
		
	}
}