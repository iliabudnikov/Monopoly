import java.util.ArrayList;
 
public class Spieler
{
    // Der Name der Figur, mit der der Spieler spielt
    private String figur;
    
    // Das Geld, dass der Spieler hat (in Mark)
    private int geld;
    
    // Jeder Spieler braucht eine Spielernummer, damit man die Spieler unterscheiden kann
    private int spielernummer;
    
    // Die Positon des Spielers auf dem Feld
    private int position;
    
    // Die grundstuecke die der Spieler gekauft hat. Hier wird die Position des Grundstükes gespeichert. 
    private ArrayList<Grundstueck> grundstuecke;
    
    // Ist dieser Spieler gerade im Gefängnis?
    private boolean imGefängnis = false;
    private int rundenImGefängnis = 0;
    // Die anzahl von "Komm aus dem Gefängnis Frei" Karten, die der Spieler besitzt
    private int kommAusDemGefängnisFreiKarten = 0;
    
    // Ein neuer Spieler
    public Spieler(String figur, int spielernummer)
    {
        this.figur = figur;
        geld = 1500; // Startgeld
        position = 0; // Los
        this.spielernummer = spielernummer;
        grundstuecke = new ArrayList<Grundstueck>();
    }

    
    // Getter und Setter für alle Instanzvariablen
    public int getSpielernummer()
    {
        return spielernummer;
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
    public void setPosition(int position)
    {
        if(position < 0)
        {
            position += 39;
        }
        if(position > 39)
        {
            position -= 39;
        }
        
        this.position = position;
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
    
    public Grundstueck[] getGrundstuecke()
    {
        Grundstueck[] ausgabe = new Grundstueck[grundstuecke.size()];
        for(int i = 0; i < ausgabe.length; i++)
        {
            ausgabe[i] = grundstuecke.get(i);
        }
        return ausgabe;
    }
    public void addGrundstück(Grundstueck grundstueck)
    {
        grundstuecke.add(grundstueck);
    }
    public void removeGrundstück(int position)
    {
        int i = 0;
        while (i < grundstuecke.size() && grundstuecke.get(i).getPosition() != position)
            i++;
        
        grundstuecke.remove(i);
    }
    
    // bauen ein Haus?
    public boolean sayIfCanHaus()
    {
        int strassenAnzahl; // die Anzahl von Straßen mit gleichen Farben
        for(int i = 0; i < grundstuecke.size()-1; i++)
        {
            if (grundstuecke.get(i).getFeld() == "Straße") // wenn das Grundstück eine Straße ist 
            {
                strassenAnzahl = 1;
                for(int j = i+1; j < grundstuecke.size(); j++)
                {
                    if (grundstuecke.get(i).getFeld() == "Straße")
                    {
                        if (grundstuecke.get(i).getFarbe() == grundstuecke.get(j).getFarbe()) // wenn noch eine Straße gefunden ist und ihre Farbe passend ist
                            strassenAnzahl += 1;
                    }
                }
                
                if (strassenAnzahl == 4)
                    return true;
            }
        }
        
        return false;
    } 
}
