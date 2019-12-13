public abstract class Feld
{
    //Nummer des Feldes in der Reihenfolge des Gesammten Spielfeldes
    public int feldnummer;
    //Boolean zur unterscheidung von Aktionsfeldern(False) und Spielfeldern(True)
    public boolean feldtyp;
    //Was für ein Feld ist es genau? Straße? Bahnhof? Ereignisfeld? Als String
    public String feld; 
    //z.B. Südbahnhof, Schlossallee
    public String feldname;
}
