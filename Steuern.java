import java.util.ArrayList;
public class Steuern extends Aktionsfelder
{
    //Diese Boolean unterscheidet zwischen dem "Zusatzsteuer"(true) und dem "Einkommenssteuer"(false) Feld.
    private boolean welchesFeld;
    public Steuern(int Feldnummer, String Feld, boolean WelchesFeld) //Die Boolean "welchesFeld" ist dazu da um zwischen den beiden Steuerfeldern zu unterscheiden
    {
        super(Feldnummer, true, "Steuern", Feld);
        welchesFeld = WelchesFeld;
    }
    
    public ArrayList<Spieler> Ereignis(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        //Zusatzsteuer
        if(welchesFeld)
        {
            System.out.println("Du musst die Zusatzsteuer von 100 Geld bezahlen!");
            alleSpieler.get(aktiverSpieler).subtractGeld(100, alleSpieler, spielfeld, aktiverSpieler);
        }
        //Einkommenssteuer
        else
        {
            System.out.println("Du musst die Einkommenssteuer von 200 Geld bezahlen!");
            alleSpieler.get(aktiverSpieler).subtractGeld(200, alleSpieler, spielfeld, aktiverSpieler);
        }
        return alleSpieler;
    }
    
    public ArrayList<Spieler> feldBetreten(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        System.out.println("Du hast ein " + getFeld() + " betreten");
        return Ereignis(alleSpieler, aktiverSpieler, spielfeld);
    }
}