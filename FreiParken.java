import java.util.ArrayList;
public class FreiParken extends Aktionsfelder
{
    public FreiParken(int Feldnummer)
    {
        super.feldnummer = Feldnummer;
        super.feld = "FreiParken";
        super.feldtyp = false;
    }
    public ArrayList<Spieler> ereignis(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        feldBetreten();
        return alleSpieler;
    }
    public void feldBetreten()
    {
        System.out.println("Du hast ein " + feld + " Feld betreten");
    }
}
