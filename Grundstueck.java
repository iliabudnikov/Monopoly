import java.util.Scanner;
public abstract class Grundstueck extends Feld
{
    public Scanner sc = new Scanner(System.in); 
    
    // Nummer des Besitzters des Grundstücks
    private Spieler besitzer;
    // Preis des Grundstückes
    private int preis;
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
		System.out.println("\nDu hast eine Hypothek auf das Grundstück " + getFeldname() + " aufgenommen und " + (int)(preis*0.5) + " Mark bekommen.");
    }
    
    //Ändert den Status von hypothek auf false und zieht die kosten der Hypothek von dem Geld des Aktiven Spielers ab
	public void hypothekAbbezahlen()
	{
		hypothek = false;
        getBesitzer().subtractGeld(hypotheksPreis);
        
        System.out.println("\nDie Hypothek wurde erfolgreich abbezahlt!");
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
    public void versteigGrundstück(int aktiverSpieler)
    {
        int[] teilnehmer = new int[Main.alleSpieler.size()]; // alle Teilnehmer der Auktion
        for (int i = 0; i < Main.alleSpieler.size(); i++)
            teilnehmer[i] = i;
        int gebot = 10; // aktuelles Gebot
        int dreckigesGebot; // ungeprüftes Gebot des Spielers (kann <= als das aktuelle Gebot oder > als das Budget des Spielers sein)
        int spitzenreiter = -1; // Nummer des aktuellen spitzenreiters der Auktion
        System.out.println("\n-----------------------");
        System.out.println("\nDas Grundstück wird versteigt.");
        System.out.println("\nDas Anfangsgebot beträgt 10 Mark.");
        
        int i = (aktiverSpieler + 1) % Main.alleSpieler.size(); // fangen mit dem nächsten Spieler an
        while (zählMöglicheTeilnehmer(teilnehmer, Main.alleSpieler.size(), gebot) > 1)
        {
            if (teilnehmer[i] != -1 && Main.alleSpieler.get(i).getGeld() > gebot) // will noch immer teilnehmen und Budget >= Gebot
            {
                System.out.print("\nSpieler mit der Figur " + Main.alleSpieler.get(i).getFigur() + ", wie viel bietest du? (gib eine Ganzzahl ein)"
                + "\nGib irgendeinen Buchstaben, wenn du die Auktion verlassen möchtest.\n\n-> ");
                
                if (sc.hasNextInt()) { // wenn kein Buchstabe
                    dreckigesGebot = sc.nextInt();
                    if(dreckigesGebot <= gebot || dreckigesGebot > Main.alleSpieler.get(i).getGeld())
                    {
                    System.out.println("\nGib bitte ein Gebot, das größer als das Aktuelle ist, aber dein Budget nicht überschreitet:");
                    dreckigesGebot = Main.checkCorrectNum(gebot+1, Main.alleSpieler.get(i).getGeld());
                    }
                    
                    gebot = dreckigesGebot;
                    spitzenreiter = i;
                }
                else
                {
                    teilnehmer[i] = -1;
                    sc.next();
                }
            }
            else if (Main.alleSpieler.get(i).getGeld() <= gebot) // vertreiben den Spieler, deren Budget < als Gebot der Auktion ist
            {
                System.out.println("\nSpieler mit der Figur " + Main.alleSpieler.get(i).getFigur() + ", leider hast du nicht genug Geld, um weiter in der Auktion teilzunehmen.");
                teilnehmer[i] = -1;
            }
            i = ++i % Main.alleSpieler.size(); // läuft im Spielerkreis von 0 bis Spielerarraygröße-1
        }
            
        if (spitzenreiter != -1) // wenn irgendjemand ein Spitzenreiter war (hat ein Gebot gemacht)
        {
            besitzer = Main.alleSpieler.get(spitzenreiter);
            Main.alleSpieler.get(spitzenreiter).addGrundstück(getFeldnummer());
            Main.alleSpieler.get(spitzenreiter).subtractGeld(getPreis());

            System.out.println("\nNun gehört das Feld " + getFeldname() + " dem Spieler mit der Figur " + Main.alleSpieler.get(spitzenreiter).getFigur() + ".");
            return;     // um tiefer in die Methode nicht zu gehen
        }
        
        for(int j : teilnehmer)
        {
            if(j != -1)
                spitzenreiter = j; // der einzige gebliebene ist nun der Spitzenreiter (doofe aber mögliche Situation...)
        }

        if(spitzenreiter != -1) // es gibt keinen Spitzenreiter und nur einen Spieler... Der Spieler bekommst das Feld für 10 Mark!
        {
            if(Main.alleSpieler.get(spitzenreiter).getGeld() >= 10)
            {
                System.out.print("\nDer Spieler mit der Figur " + Main.alleSpieler.get(spitzenreiter).getFigur() + ", willst du dieses Grundstück für 10 Mark kaufen?\n(ja - 1, nein - sonstiges)\n\n-> ");
        
                String entscheidung = sc.next();
    
                if (entscheidung.equals("1"))
                {
                    besitzer = Main.alleSpieler.get(spitzenreiter);
                    Main.alleSpieler.get(spitzenreiter).addGrundstück(getFeldnummer());
                    Main.alleSpieler.get(spitzenreiter).subtractGeld(getPreis());
        
                    System.out.println("\nNun gehört das Feld " + getFeldname() + " dem Spieler mit der Figur " + Main.alleSpieler.get(spitzenreiter).getFigur() + ".");        
                }
                else
                {
                    System.out.println("\nDas Grundstück hat weiterhin keinen Besitzer.");
                }
            } 
        }
        else
        {
            System.out.println("\nDas Grundstück hat weiterhin keinen Besitzer.");
        }

        System.out.println("\nZurück zum Spieler mit der Figur " + Main.alleSpieler.get(aktiverSpieler).getFigur() + ".");	
    }
    // ---------------- Auktionsteil ----------------
    
    // fragen, ob der Spieler das Grungstück kaufen will
    public void askKaufentscheidung(int aktiverSpieler)
    {
        if(Main.alleSpieler.get(aktiverSpieler).getGeld() >= getPreis())
        {
            System.out.print("\nMöchtest du dieses Grundstück kaufen? Es kostet " + getPreis() + " Mark.\n(ja - 1, nein - sonstiges)\n\n-> ");
        
            String entscheidung = sc.next();

            if (entscheidung.equals("1"))
            {
                besitzer = Main.alleSpieler.get(aktiverSpieler);
                Main.alleSpieler.get(aktiverSpieler).addGrundstück(getFeldnummer());
                Main.alleSpieler.get(aktiverSpieler).subtractGeld(getPreis());

                System.out.println("\nGut! Nun gehört das Feld " + getFeldname() + " dir!");
            }
            else
            {
                versteigGrundstück(aktiverSpieler);
            }
        }
        else
        {
            versteigGrundstück(aktiverSpieler);
        }
    }
}