import java.util.ArrayList;

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
    
    public int getMietpreis()
    {
    	return miete[hausAnzahl];
    }
    public ArrayList<Spieler> miete(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
    	System.out.println("Zahle Miete von " + getMietpreis() +" Geld an den Spieler mit der Figur " + getBesitzer().getFigur());
    	alleSpieler.get(aktiverSpieler).paySpieler(getBesitzer().getSpielernummer(), getMietpreis());
    	return alleSpieler;
    }
    
    public ArrayList<Spieler> feldBetreten(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld) 
    {
    	System.out.println("Du hast die Straße " + getFeldname() + " betreten.\nSie hat die Farbe " + getFarbe());
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
    
    // zeigt Straßeninfos mit (True) oder ohne (False) Immobilieninfos
    public String toString(boolean mitImmobilien)
    {
        String ergebnis = "Die " + getFeldname() + " Straße in der Farbe " + farbe;
        
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
    	
    	
        if (mitImmobilien)
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