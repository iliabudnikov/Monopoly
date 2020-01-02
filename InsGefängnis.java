public class InsGefängnis extends Aktionsfelder
{
    public InsGefängnis(int Feldnummer)
    {
        super(Feldnummer, false, "Ins Gefängnis", "Ins Gefängnis");
    }
    
    public void Ereignis(int aktiverSpieler)
    {
        Main.alleSpieler.get(aktiverSpieler).setImGefängnis();
    }
    
    public void feldBetreten(int aktiverSpieler)
    {
    	System.out.println("\nDu hast das „Gehen sie in das Gefängnis“ Feld betreten.");
    	Ereignis(aktiverSpieler);
    }
    
    public String toString()
    {
    	String ergebnis = "Das Gehe ins Gefängnis Feld";
    	return ergebnis;
    }
}
