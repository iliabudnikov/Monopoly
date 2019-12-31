public class Start extends Aktionsfelder
{
    
    public Start(int Feldnummer)
    {
    	//int feldnummer, boolean feldtyp, String feld, String feldname
        super(Feldnummer, false, "Los", "Los");
    	
    }
    public void Ereignis(int aktiverSpieler)
    {
        Main.alleSpieler.get(aktiverSpieler).addGeld(200);
    }
    public void feldBetreten(int aktiverSpieler)
    {
        System.out.println("\nDu hast das " + getFeld() + " Feld betreten");
        Ereignis(aktiverSpieler);
    }
}
