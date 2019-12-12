import java.util.ArrayList;
public class Start extends Aktionsfelder
{
    
    public Start(int Feldnummer)
    {
        super.feldnummer = Feldnummer;
        super.feld = "Los";
        super.feldtyp = false;
    }
    public ArrayList<Spieler> ereignis(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        feldBetreten();
        return alleSpieler;
    }
    public void feldBetreten()
    {
        System.out.println("Du hast das " + feld + " Feld betreten");
    }
}
