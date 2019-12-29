import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Gefängnis extends Aktionsfelder
{
    public Gefängnis(int Feldnummer)
    {
    	super(Feldnummer, false, "Gefängnis", "Gefängnis");
    }
    
     public ArrayList<Spieler> feldBetreten(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        //Wenn der Spieler nicht im Gefängnis ist, dann wird nur ein Text ausgegeben
    	System.out.println("Du hast das " + getFeld() + " betreten");
        System.out.println("Du bist nur zu besuch");
        return alleSpieler; 
    }
}