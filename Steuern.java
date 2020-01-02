public class Steuern extends Aktionsfelder
{
    //Diese Boolean unterscheidet zwischen dem "Zusatzsteuer"(true) und dem "Einkommenssteuer"(false) Feld.
    private boolean welchesFeld;
    public Steuern(int Feldnummer, String Feld, boolean WelchesFeld) //Die Boolean "welchesFeld" ist dazu da um zwischen den beiden Steuerfeldern zu unterscheiden
    {
        super(Feldnummer, true, "Steuern", Feld);
        welchesFeld = WelchesFeld;
    }
    
    public void Ereignis(int aktiverSpieler)
    {
        //Zusatzsteuer
        if(welchesFeld)
        {
            System.out.println("\nDu musst die Zusatzsteuer von 100 Mark bezahlen!");
            Main.alleSpieler.get(aktiverSpieler).subtractGeld(100);
        }
        //Einkommenssteuer
        else
        {
            System.out.println("\nDu musst die Einkommenssteuer von 200 Mark bezahlen!");
            Main.alleSpieler.get(aktiverSpieler).subtractGeld(200);
        }
    }
    
    public void feldBetreten(int aktiverSpieler)
    {
        System.out.println("\nDu hast ein " + getFeld() + " betreten.");
        Ereignis(aktiverSpieler);
    }
    
    public String toString()
    {
    	String ergebnis = "Das " + getFeldname() + "feld :";
    	//Zusatzsteuer
    	if(welchesFeld)
    	{
    		ergebnis += "Zusatzsteuer(betreten kostet 100 Geld)";
    	}
    	//Einkommenssteuer
    	else
    	{
    		ergebnis += "Einkommenssteuer(betreten kostet 200 Geld)";
    	}
    	
    	return ergebnis;
    }
}