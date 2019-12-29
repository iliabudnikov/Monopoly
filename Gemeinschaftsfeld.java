import java.util.Random;

public class Gemeinschaftsfeld extends Aktionsfelder
{
    //Alle Ereigniskarten aus diesem PDF: https://monopoly-vogtland.de/downloads/kartentexte_vogtland.pdf
    public Gemeinschaftsfeld(int Feldnummer)
    {
    	super(Feldnummer, false, "Gemeinschaftsfeld", "Gemeinschaftsfeld");
    }
	
	
	// -------- Wenn der Spieler nicht genud Geld hat? ----------


    public void Ereignis(int aktiverSpieler, int gewürfelteZahl)
    {
        //Erstellen eines Random objektes, um ein Ereignis auszwählen
        Random rnd = new Random();
        //Setzt den Seed abhängig von der Systemzeit, damit bei jedem Spieldurchlauf die Reihenfolge der Ereigniskarten variiert
        rnd.setSeed(System.currentTimeMillis());
        
        //Generiert eine Zahl zwischen 0 und 15, um eine der 16 Ereigniskarten auszuwählen
        switch(rnd.nextInt(16))
        {
        	//Sie kommen aus dem Gefängnis frei! Behalten Sie diese Karte, bis Sie sie benötigen oder verkaufen
        	case 0:
        		System.out.println("\n„Du kommst aus dem Gefängnis frei“-Karte!");
        		Main.alleSpieler.get(aktiverSpieler).addGefängnisKarte();
        		break;
        	//Schulgeld. Zahlen Sie M 50.
        	case 1:
        		System.out.println("\nSchulgeld. Zahle 50 Mark.");
        		Main.alleSpieler.get(aktiverSpieler).subtractGeld(50);
        		break;
        	//Urlaubsgeld! Sie erhalten M 100.
        	case 2:
        		System.out.println("\nUrlaubsgeld! Du erhaltest 100 Mark.");
        		Main.alleSpieler.get(aktiverSpieler).addGeld(100);
        		break;
        	//Ihre Lebensversicherung wird fällig. Sie erhalten M 100
        	case 3:
        		System.out.println("\nIhre Lebensversicherung wird fällig. Du erhaltest 100 Mark");
        		Main.alleSpieler.get(aktiverSpieler).addGeld(100);
        		break;
        	//Arzt-Kosten. Zahlen Sie M 50.
        	case 4:
        		System.out.println("\nArzt-Kosten. Zahle 50 Mark.");
        		Main.alleSpieler.get(aktiverSpieler).subtractGeld(50);
        		break;
        	//Einkommenssteuerrückerstattung. Sie erhalten M 20.
        	case 5:
        		System.out.println("\nEinkommenssteuerrückerstattung. Du erhaltest 20 Mark.");
        		Main.alleSpieler.get(aktiverSpieler).addGeld(20);
        		break;
        	//Krankenhausgebühren. Zahlen Sie M 100.
        	case 6:
        		Main.alleSpieler.get(aktiverSpieler).subtractGeld(100);
        		System.out.println("\nKrankenhausgebühren. Zahle 100 Mark.");
        		break;
        	//Gehen Sie in das Gefängnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht über Los. Ziehen Sie nicht M 200 ein.
        	case 7:
        		System.out.println("\nGehe ins Gefängnis! Begebe dich direkt dorthin.");
        		Main.alleSpieler.get(aktiverSpieler).setImGefängnis();
        		break;
        	//Sie erhalten auf Vorzugs-Aktien 7% Dividende: M 25
        	case 8:
        		System.out.println("\nDu erhaltest auf Vorzugs-Aktien 7% Dividende: 25 Mark.");
        		Main.alleSpieler.get(aktiverSpieler).addGeld(25);
        		break;
        	//Sie haben Geburtstag. Jeder Spieler schenkt Ihnen M 10.
        	case 9:
        		System.out.println("\nDu hast Geburtstag! Jeder Spieler schenkt Ihnen 10 Mark.");
        		//Zieht jedem Spieler der nicht der aktive Spieler ist, 10 Geld ab und gibt dem aktiven Spieler das Abgezogene geld
        		for(int i = 0; i < Main.alleSpieler.size(); i++)
        		{
        			if(i != aktiverSpieler)
        			{
        				Main.alleSpieler.get(i).paySpieler(aktiverSpieler, 10);
        			}
        		}
        		break;
        	//Sie erben M 100.
        	case 10:
        		System.out.println("\nDu erbst 100 Mark.");
        		Main.alleSpieler.get(aktiverSpieler).addGeld(100);
        		break;
        	//Aus Lagerverkäufen erhalten Sie M 50.
        	case 11:
        		System.out.println("\nAus Lagerverkäufen erhaltest du 50 Mark.");
        		Main.alleSpieler.get(aktiverSpieler).addGeld(50);
        		break;
        	//Zweiter Preis im Schönheitswettbewerb. Sie erhalten M 10.
        	case 12:
        		System.out.println("\nZweiter Preis im Schönheitswettbewerb. Du erhaltest 10 Mark.");
        		Main.alleSpieler.get(aktiverSpieler).addGeld(10);
        		break;
        	//Sie werden zu Straßenausbesserungsarbeiten herangezogen. Zahlen Sie M 40 je Haus und M 115 je Hotel an die Bank.
        	case 13:
        		System.out.println("\nDu wirst zu Straßenausbesserungsarbeiten herangezogen. Zahle 40 Mark je Haus und 115 Mark je Hotel an die Bank.");
        		int kosten = 0;
        		int[] grundstuecke = Main.alleSpieler.get(aktiverSpieler).getGrundstuecke();
        		for(int i = 0; i < grundstuecke.length; i++)
        		{
        			if(Main.spielfeld[grundstuecke[i]].getFeld().equalsIgnoreCase("Strasse"))
        			{
        				if(((Strasse)Main.spielfeld[grundstuecke[i]]).getHausAnzahl() >= 5)
        				{
        					kosten += 115;
        				}
        				else
        				{
        					kosten += 40 * ((Strasse)Main.spielfeld[grundstuecke[i]]).getHausAnzahl();
        				}
        			}
        		}
        		
        		if(kosten == 0)
        		{
        			System.out.println("\nDa du keine Grundstücke hast, musst du nichts bezahlen.");
        		}
        		else
        		{
        			System.out.println("\nDu musst insgesammt " + kosten + " Geld bezahlen.");
        			Main.alleSpieler.get(aktiverSpieler).subtractGeld(kosten);
        		}
        		break;
        	//Rücken Sie vor bis auf Los. (Ziehe M 200 ein).
        	case 14:
        		System.out.println("\nRücke vor bis auf Los und ziehe 200 Mark ein!");
        		Main.alleSpieler.get(aktiverSpieler).setPosition(0);
        		Main.alleSpieler.get(aktiverSpieler).addGeld(200);
        		break;
        	//Bank-Irrtum zu Ihren Gunsten. Ziehen Sie M 200 ein.
        	case 15:
        		System.out.println("Bank-Irrtum zu Ihren Gunsten! Ziehe 200 Mark ein.");
        		Main.alleSpieler.get(aktiverSpieler).addGeld(200);
        		break;
        }
	}
    
	public void feldBetreten(int aktiverSpieler, int gewürfelteZahl)
    {
        System.out.println("\nDu hast ein " + getFeld() + " betreten.");
        Ereignis(aktiverSpieler, gewürfelteZahl);
    }
}