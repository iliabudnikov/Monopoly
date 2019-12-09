import java.util.ArrayList;
public class Spieler
{
    //Das Geld, dass der Spieler hat
    private int geld;
    //Die Positon des Spielers auf dem Spielfeld
    private int position;
    //Die GrundstÃ¼cke die der Spieler gekauft hat. Hier wird die Position des GrundstÃ¼ckes gespeichert. 
    private ArrayList<Integer> grundstücke;
    //Jeder Spieler braucht eine Spielernummer, damit man die Spieler unterscheiden kann
    private int spielernummer;
    
    
    public Spieler()
    {
        grundstücke = new ArrayList<Integer>();
    }
    
    //Getter und Setter für alle Instanzvariablen
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
    public int[] getGrundstücke()
    {
        int[] ausgabe = new int[grundstücke.size()];
        for(int i = 0; i < ausgabe.length; i++)
        {
        	ausgabe[i] = grundstücke.get(i);
        }
        return ausgabe;
    }
    public void addGrundstück(int Position)
    {
        grundstücke.add(Position);
    }
    public void removeGrundstück(int Position)
    {
        grundstücke.remove(Position);
    }
    public int getSpielernummer()
    {
        return spielernummer;
    }
}
