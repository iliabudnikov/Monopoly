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
    //Ist dieser Spieler gerade im Gefängnis?
    private boolean imGefängnis = false;
    private int rundenImGefängnis = 0;
    //Die anzahl von "Komm aus dem Gefängnis Frei" Karten, die der Spieler besitzt
    private int kommAusDemGefängnisFreiKarten = 0;
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
        if(Position < 0)
        {
        	Position = 39 + Position;
        }
    	position = Position;
    }
    public boolean getImGefängnis()
    {
    	return imGefängnis;
    }
    public void setImGefängnis()
    {
    	imGefängnis = true;
    	position = 10;
    	rundenImGefängnis = 0;
    }
    public int getRundenImGefängnis()
    {
    	return rundenImGefängnis;
    }
    public void increaseRundenImGefängnis()
    {
    	rundenImGefängnis++;
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
    public int getGefängnisKarte()
    {
    	return kommAusDemGefängnisFreiKarten;
    }
    public void addGefängnisKarte()
    {
    	kommAusDemGefängnisFreiKarten++;
    }
    public void subtractGefängnisKarte()
    {
    	kommAusDemGefängnisFreiKarten--;
    }
    
}