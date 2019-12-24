import java.util.ArrayList;
import java.util.Random;

public class Gemeinschaftsfeld extends Aktionsfelder
{
    //Alle Ereigniskarten aus diesem PDF: https://monopoly-vogtland.de/downloads/kartentexte_vogtland.pdf
    public Gemeinschaftsfeld(int Feldnummer)
    {
    	super(Feldnummer, false, "Gemeinschaftsfeld", "Gemeinschaftsfeld");
    }
    
    public ArrayList<Spieler> ereignis(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
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
        		System.out.println("Sie kommen aus dem Gefängnis frei! Behalten Sie diese Karte, bis Sie sie benötigen oder verkaufen");
        		alleSpieler.get(aktiverSpieler).addGefängnisKarte();
        		break;
        	//Schulgeld. Zahlen Sie M 50.
        	case 1:
        		System.out.println("Schulgeld. Zahlen Sie M 50.");
        		alleSpieler.get(aktiverSpieler).subtractGeld(50, alleSpieler, spielfeld, aktiverSpieler);
        		break;
        	//Urlaubsgeld! Sie erhalten M 100.
        	case 2:
        		System.out.println("Urlaubsgeld! Sie erhalten M 100.");
        		alleSpieler.get(aktiverSpieler).addGeld(100);
        		break;
        	//Ihre Lebensversicherung wird fällig. Sie erhalten M 100
        	case 3:
        		System.out.println("Ihre Lebensversicherung wird fällig. Sie erhalten M 100");
        		alleSpieler.get(aktiverSpieler).addGeld(100);
        		break;
        	//Arzt-Kosten. Zahlen Sie M 50.
        	case 4:
        		System.out.println("Arzt-Kosten. Zahlen Sie M 50.");
        		alleSpieler.get(aktiverSpieler).subtractGeld(50, alleSpieler, spielfeld, aktiverSpieler);
        		break;
        	//Einkommenssteuerrückerstattung. Sie erhalten M 20.
        	case 5:
        		System.out.println("Einkommenssteuerrückerstattung. Sie erhalten M 20.");
        		alleSpieler.get(aktiverSpieler).addGeld(20);
        		break;
        	//Krankenhausgebühren. Zahlen Sie M 100.
        	case 6:
        		alleSpieler.get(aktiverSpieler).subtractGeld(100, alleSpieler, spielfeld, aktiverSpieler);
        		System.out.println("Krankenhausgebühren. Zahlen Sie M 100.");
        		break;
        	//Gehen Sie in das Gefängnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht über Los. Ziehen Sie nicht M 200 ein.
        	case 7:
        		System.out.println("Gehen Sie in das Gefängnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht über Los. Ziehen Sie nicht M 200 ein.");
        		alleSpieler.get(aktiverSpieler).setImGefängnis();
        		break;
        	//Sie erhalten auf Vorzugs-Aktien 7% Dividende: M 25
        	case 8:
        		System.out.println("Sie erhalten auf Vorzugs-Aktien 7% Dividende: M 25");
        		alleSpieler.get(aktiverSpieler).addGeld(25);
        		break;
        	//Sie haben Geburtstag. Jeder Spieler schenkt Ihnen M 10.
        	case 9:
        		System.out.println("Sie haben Geburtstag. Jeder Spieler schenkt Ihnen M 10.");
        		int geschenkGeld = 0;
        		//Zieht jedem Spieler der nicht der aktive Spieler ist, 10 Geld ab und gibt dem aktiven Spieler die Summe des aktiven Spielers
        		for(int i = 0; i < alleSpieler.size(); i++)
        		{
        			if(i != aktiverSpieler)
        			{
        				alleSpieler.get(i).subtractGeld(10, alleSpieler, spielfeld, aktiverSpieler);
        				geschenkGeld += 10;
        			}
        		}
        		alleSpieler.get(aktiverSpieler).addGeld(geschenkGeld);
        		break;
        	//Sie erben M 100.
        	case 10:
        		System.out.println("Sie erben M 100.");
        		alleSpieler.get(aktiverSpieler).addGeld(100);
        		break;
        	//Aus Lagerverkäufen erhalten Sie M 50.
        	case 11:
        		System.out.println("Aus Lagerverkäufen erhalten Sie M 50.");
        		alleSpieler.get(aktiverSpieler).addGeld(50);
        		break;
        	//Zweiter Preis im Schönheitswettbewerb. Sie erhalten M 10.
        	case 12:
        		System.out.println("Zweiter Preis im Schönheitswettbewerb. Sie erhalten M 10.");
        		alleSpieler.get(aktiverSpieler).addGeld(10);
        		break;
        	//Sie werden zu Straßenausbesserungsarbeiten herangezogen. Zahlen Sie M 40 je Haus und M 115 je Hotel an die Bank.
        	//Noch nicht Implementiert
        	case 13:
        		System.out.println("Sie werden zu Straßenausbesserungsarbeiten herangezogen. Zahlen Sie M 40 je Haus und M 115 je Hotel an die Bank.");
        		break;
        	//Rücken Sie vor bis auf Los. (Ziehe M 200 ein).
        	case 14:
        		System.out.println("Rücken Sie vor bis auf Los. (Ziehe M 200 ein).");
        		alleSpieler.get(aktiverSpieler).setPosition(0);
        		alleSpieler.get(aktiverSpieler).addGeld(200);
        		break;
        	//Bank-Irrtum zu Ihren Gunsten. Ziehen Sie M 200 ein.
        	case 15:
        		System.out.println("Bank-Irrtum zu Ihren Gunsten. Ziehen Sie M 200 ein.");
        		alleSpieler.get(aktiverSpieler).addGeld(200);
        		break;
        }
        
        return alleSpieler;
    }
    
    public void feldBetreten()
    {
        System.out.println("Du hast ein " + getFeld() + " betreten");
    }
}