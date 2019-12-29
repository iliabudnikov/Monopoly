import java.util.Scanner;
public abstract class Grundstueck extends Feld
{
    public Scanner sc = new Scanner(System.in); 
    
    // Nummer des Besitzters des Grundstücks
    private Spieler besitzer;
    // Preis des Grundstückes
    private int preis;
    // Position des Grundstücks auf dem Feld
    private int position;
    //Hat dieses Grundstück eine Hypothek?
    private boolean hypothek;
    //Wie viel Geld es kostet, eine Hypothek auf dieses Grundstück abzubezahlen
    private int hypotheksPreis;
    int[] miete;
    
	Grundstueck (int feldnummer, String feld, String feldname, int preis, int[] miete)
    {
        super(feldnummer, true, feld, feldname);
        this.besitzer = null;
        this.preis = preis;
        this.hypothek = false;
        this.hypotheksPreis = (int)((preis * 0.5) + ((preis *0.5)* 0.1));
        this.miete = miete;
    }
    
    
    public Spieler getBesitzer()
    {
        return this.besitzer;
    }
    public void setBesitzer(Spieler besitzer)
    {
        this.besitzer = besitzer;
    }
    
    public int getPreis()
    {
        return this.preis;
    }
    public void setPreis(int preis)
    {
        this.preis = preis;
    }
    
    public int getPosition()
    {
        return position;
    }
    
    public boolean getHypothek() 
    {
		return hypothek;
	}
    public int getHypothekPreis()
    {
    	return hypotheksPreis;
    }
    
    public int[] getMiete()
    {
    	return miete;
    }
    
    //Ändert den Status von hypothek auf True und gibt dem Spieler der die Hypothek aufnimmt den Geldwert der Hypothek
	public void hypothekAufnehmen()
	{
		hypothek = true;
		getBesitzer().addGeld((int)(preis*0.5));
		System.out.println("Du hast eine Hypothek auf das Grundstück " + getFeldname() + " aufgenommen und " + (preis*0.5) + " Geld bekommen.");
    }
    
    //Ändert den Status von hypothek auf false und zieht die kosten der Hypothek von dem Geld des Aktiven Spielers ab
	public void hypothekAbbezahlen()
	{
		hypothek = false;
		getBesitzer().subtractGeld(hypotheksPreis);
	}

	// ---------------- Auktionsteil ----------------
    // zählt, wie viele Spieler noch in der Auktion teilnehmen (will nicht teilnehmen oder Budget < Gebot)
    private static int zählMöglicheTeilnehmer(int [] teilnehmer, int teilnehmerSize, int Gebot)
    {
        int anzahl = 0;
        
        for (int i = 0; i < teilnehmerSize; i++) 
        {
            anzahl = ((teilnehmer[i] != -1) && (Main.alleSpieler.get(i).getGeld() > Gebot)) ? anzahl+1 : anzahl;
        }
        
        return anzahl;
    }
    
    // Auktion
    public void versteigGrundstück()
    {
        int[] teilnehmer = new int[Main.alleSpieler.size()]; // alle Teilnehmer der Auktion
        for (int i = 0; i < Main.alleSpieler.size(); i++)
            teilnehmer[i] = i;
        int gebot = 10; // aktuelles Gebot
        int dreckigesGebot; // ungeprüftes Gebot des Spielers (kann <= als das aktuelle Gebot oder > als das Budget des Spielers sein)
        int spitzenreiter = -1; // Nummer des aktuellen spitzenreiters der Auktion
        System.out.println("\nDas Anfangsgebot beträgt 10 Mark.");
        
        int i = 0;
        while (zählMöglicheTeilnehmer(teilnehmer, Main.alleSpieler.size(), gebot) > 1)
        {
            if (teilnehmer[i] != -1 && Main.alleSpieler.get(i).getGeld() >= gebot) // will noch immer teilnehmen und Budget >= Gebot
            {
                System.out.print("\nSpieler " + (i+1) + ", wie viel bieten Sie? (geben Sie eine Ganzzahl ein)"
                + "\nGeben Sie die Zahl \"-1\" (ohne Klammern), wenn Sie die Auktion verlassen möchten."
                + "\n-> ");
                dreckigesGebot = sc.nextInt();
                System.out.println();
                
                if (dreckigesGebot != -1) { // == -1? => will nicht mehr in der Auktion teilnehmen
                    while ((dreckigesGebot <= gebot) || (dreckigesGebot > Main.alleSpieler.get(i).getGeld()))
                    {
                        System.out.println("\nGeben Sie bitte ein Gebot, das größer als das Aktuelle ist, aber ihr Budget nicht überschreitet:"
                        + "\n-> ");
                        dreckigesGebot = sc.nextInt();
                        System.out.println();
                    }
                    
                    gebot = dreckigesGebot;
                    spitzenreiter = i;
                }
                else
                {
                    teilnehmer[i] = -1;
                }
            }
            else if (Main.alleSpieler.get(i).getGeld() < gebot) // vertreiben den Spieler, deren Budget < als Gebot der Auktion ist
            {
                System.out.println("\nSpieler " + (i+1) + ", leider haben Sie nicht genug Geld."
                + "Sie können in der Auktion nicht mehr teilnehmen.");
                teilnehmer[i] = -1;
            }
            i = ++i % Main.alleSpieler.size(); // läuft im Spielerkreis von 0 bis Spielerarraygröße-1
        }
            
        if (spitzenreiter != -1) // wenn irgendjemand ein Spitzenreiter war
        {
            besitzer = Main.alleSpieler.get(spitzenreiter);
            Main.alleSpieler.get(spitzenreiter).addGrundstück(position);
            Main.alleSpieler.get(spitzenreiter).subtractGeld(this.getPreis());
        }
        else
        {
            System.out.println("\nDas Grundstück bleibt unbesitzt.");
        }
    }
    // ---------------- Auktionsteil ----------------
    
    // fragen, ob der Spieler das Grungstück kaufen will
    public void askKaufentscheidung(int aktiverSpieler)
    {
        if(Main.alleSpieler.get(aktiverSpieler).getGeld() >= getPreis())
        {
            System.out.println("\nMöchten Sie dieses Grundstück kaufen? Es kostet " + getPreis() + " Geld. \nDu hast " + Main.alleSpieler.get(aktiverSpieler).getGeld() + " Geld.\n(ja - 1, nein - sonstiges)"
        + "\n-> ");
        
            String entscheidung = sc.next();

            if (entscheidung.equals("1"))
            {
                besitzer = Main.alleSpieler.get(aktiverSpieler);
                Main.alleSpieler.get(aktiverSpieler).addGrundstück(this.getPosition());
                Main.alleSpieler.get(aktiverSpieler).subtractGeld(this.getPreis());
            }
            else
            {
                this.versteigGrundstück();
            }
        }
        else
        {
            this.versteigGrundstück();
        }
    }
}