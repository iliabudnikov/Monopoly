public class Strasse extends Grundstueck
{
    private String farbe;
    private int hausAnzahl; // 5 Häuse => Hotel
    
    public Strasse (int feldnummer, String feld, String feldname, int besitzer, int preis, String farbe, int hausAnzahl)
    {
        super(feldnummer, "Straße", feldname, besitzer, ???);
        this.farbe = farbe;
        this.hausAnzahl = hausAnzahl;
    }
    
    
    public int getFarbe()
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
    
    // --!-- In MAIN: if Straße : wenn Spieler besitzt alle Felder der Farbe => fragen nach dem neuen Huus
    // bauen ein Haus?
    public void bauHaus()
    {
        
    }
    
    // zeigt Straßeninfos mit (True) oder ohne (False) Immobilieninfos
    public void toString(boolean mitImmobilien)
    {
        System.out.println("\nDie " + feldname + " Straße in der Farbe " + farbe + "kostet " + preis + " Mark.");
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
}