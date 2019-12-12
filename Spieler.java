 import java.util.ArrayList;
public class Spieler
{
    //Das Geld, dass der Spieler hat
    private int geld;
    //Die Positon des Spielers auf dem Spielfeld
    private int position;
    //Die Grundstücke die der Spieler gekauft hat. Hier wird die Position des Grundstückes gespeichert. 
    private ArrayList<Integer> grundstücke;
    //Jeder Spieler braucht eine Spielernummer, damit man die Spieler unterscheiden kann
    private int spielernummer;
    
    
    public Spieler()
    {
        grundstücke = new ArrayList<Integer>();
    }
    public int getGeld()
    {
        return geld;
    }
    public void setGeld(int Geld)
    {
        geld = Geld;
    }
    public void addGeld(int Geld)
    {
        geld += Geld;
    }
    public void subtractGeld(int Geld)
    {
        geld -= Geld;
    }
    public int getPosition()
    {
        return position;
    }
    public void setPosition(int Position)
    {
        position = Position;
    }
    /*public Spielfeld[] getGrundstücke()
    {
        return Spielfeld.toArray();
    }*/
    public void addGrundstück(int Position)
    {
        
    }
    public void removeGrundstück()
    {
        
    }
    /*public int getSpielernummer()
    {
        
    }*/
}
