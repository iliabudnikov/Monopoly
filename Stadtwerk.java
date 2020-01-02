public abstract class Stadtwerk extends Grundstueck
{
	public Stadtwerk(int feldnummer, String feld, String feldname, int preis)
    {
    	super(feldnummer, feld, feldname, preis, null);
    }
	
	public void feldBetreten(int aktiverSpieler, int gewürfelteZahl)
	{
		if(getFeld().equals("Wasserwerk"))
		{
			((Wasserwerk)Main.spielfeld[28]).feldBetreten(aktiverSpieler, gewürfelteZahl);
		}
		else
		{
			((Stromwerk)Main.spielfeld[18]).feldBetreten(aktiverSpieler, gewürfelteZahl);
		}
	}
}