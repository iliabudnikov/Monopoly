import java.util.ArrayList;

public abstract class Stadtwerk extends Grundstueck
{
	public Stadtwerk(int feldnummer, String feld, String feldname, int preis)
    {
    	super(feldnummer, feld, feldname, preis, null);
    }
	
	public ArrayList<Spieler> feldBetreten(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld, int gewürfelteZahl)
	{
		if(this.getFeld().equals("Wasserwerk"))
		{
			return ((Wasserwerk)spielfeld[28]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld, gewürfelteZahl);
		}
		else
		{
			return ((Stromwerk)spielfeld[18]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld, gewürfelteZahl);
		}
	}
    
}