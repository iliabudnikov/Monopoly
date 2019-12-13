import java.util.ArrayList;
public class InsGefängnis extends Aktionsfelder
{
    public InsGefängnis(int Feldnummer)
    {
        super.feldnummer = Feldnummer;
        super.feld = "InsGefängnis";
        super.feldname = "InsGefängnis";
    }
    
    public ArrayList<Spieler> ereignis(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        System.out.println("Gegen Sie in das Gefängnis, gehen Sie nicht über Los und ziehen sie keine 200 Geld ein!");
        alleSpieler.get(aktiverSpieler).setImGefängnis();
        return alleSpieler;
    }
    
}
