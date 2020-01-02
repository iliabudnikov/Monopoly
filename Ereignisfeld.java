import java.util.ArrayList;
import java.util.Random;

public class Ereignisfeld extends Aktionsfelder
{
    //Alle Ereigniskarten aus diesem PDF: https://monopoly-vogtland.de/downloads/kartentexte_vogtland.pdf
    public Ereignisfeld(int Feldnummer)
    {
    	super(Feldnummer, false, "Ereignisfeld", "Ereignisfeld");
    }
    
    public ArrayList<Spieler> Ereignis(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld, int gewürfelteZahl)
    {
        //Erstellen eines Random objektes, um ein Ereignis auszwählen
        Random rnd = new Random();
        //Setzt den Seed abhängig von der Systemzeit, damit bei jedem Spieldurchlauf die Reihenfolge der Ereigniskarten variiert
        rnd.setSeed(System.currentTimeMillis());
        int kosten = 0;
        //Generiert eine Zahl zwischen 0 und 15, um eine der 16 Ereigniskarten auszuwählen
        switch(rnd.nextInt(16))
        {
        	//Rücken Sie vor bis zur Schlossallee
        	case 0:
        		System.out.println("Rücken Sie vor bis zur Schlossallee.");
        		alleSpieler.get(aktiverSpieler).setPosition(39);
        		//Dieser Teil des Programms ist noch nicht Implementiert.
        		alleSpieler = ((Strasse)spielfeld[39]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
        		break;
        	//Machen Sie einen Ausflug zum Südbahnhof. Wenn Sie über Los kommen, ziehen Sie M 200 ein.
        	case 1:
        		System.out.println("Machen Sie einen Ausflug zum Südbahnhof. Wenn Sie über Los kommen, ziehen Sie M 200 ein.");
        		//Falls der Spieler über Los gehen muss, bekommt er 200 Geld
        		if(alleSpieler.get(aktiverSpieler).getPosition() > 5)
        		{
        			alleSpieler.get(aktiverSpieler).addGeld(200);
        		}
        		alleSpieler.get(aktiverSpieler).setPosition(5);
        		//Dieser Teil des Programms ist noch nicht Implementiert.
        		alleSpieler = ((Bahnhof)spielfeld[5]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
        		break;
        	//Ihr Bausparvertrag wird fällig. Sie erhalten M 200.
        	case 2:
        		System.out.println("Ihr Bausparvertrag wird fällig. Sie erhalten M 200.");
        		alleSpieler.get(aktiverSpieler).addGeld(200);
        		break;
        	//Rücken Sie vor bis zum Opernplatz. Wenn Sie über Los kommen, ziehen Sie M 200 ein.
        	case 3:
        		System.out.println("Rücken Sie vor bis zum Opernplatz. Wenn Sie über Los kommen, ziehen Sie M 200 ein.");
        		if(alleSpieler.get(aktiverSpieler).getPosition() > 24)
        		{
        			alleSpieler.get(aktiverSpieler).addGeld(200);
        		}
        		alleSpieler.get(aktiverSpieler).setPosition(24);
        		alleSpieler = ((Strasse)spielfeld[24]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
        		break;
        	//Rücken Sie vor bis zum nächsten Versorgungswerk. Werfen Sie die Würfel und zahlen dem Eigentümer den zehnfachen Betrag Ihres Wurfergebnisses. Wenn das Werk noch niemandem gehört, können Sie es von der Bank kaufen
        	case 4:
        		System.out.println("Rücken Sie vor bis zum nächsten Versorgungswerk. Werfen Sie die Würfel und zahlen dem Eigentümer den zehnfachen Betrag Ihres Wurfergebnisses. Wenn das Werk noch niemandem gehört, können Sie es von der Bank kaufen");
        		//Entscheiden, welches der Werke näher liegt
        		//Elektrizitätswerk
        		if(alleSpieler.get(aktiverSpieler).getPosition() > 12)
        		{
        			alleSpieler.get(aktiverSpieler).setPosition(12);
        			alleSpieler = ((Stromwerk)spielfeld[12]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld, gewürfelteZahl);
        		}
        		//Wasserwerk
        		else
        		{
        			alleSpieler.get(aktiverSpieler).setPosition(28);
        			alleSpieler = ((Wasserwerk)spielfeld[28]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld, gewürfelteZahl);
        		}
        		break;
        	//Gehen Sie in das Gefängnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht über Los. Ziehen Sie nicht M 200 ein
        	case 5:
        		System.out.println("Gehen Sie in das Gefängnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht über Los. Ziehen Sie nicht M 200 ein");
        		alleSpieler.get(aktiverSpieler).setImGefängnis();
        		break;
        	//Rücken Sie vor bis auf Los. (Ziehe M 200 ein).
        	case 6:
        		System.out.println("Rücken Sie vor bis auf Los. (Ziehe M 200 ein).");
        		alleSpieler.get(aktiverSpieler).setPosition(0);
        		alleSpieler.get(aktiverSpieler).addGeld(200);
        		alleSpieler.get(aktiverSpieler).setPosition(12);
    			alleSpieler = ((Start)spielfeld[0]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
        		break;
        	//Die Bank zahlt Ihnen eine Dividende von M 50.
        	case 7:
        		System.out.println("Die Bank zahlt Ihnen eine Dividende von M 50.");
        		alleSpieler.get(aktiverSpieler).addGeld(50);
        		break;
        	//Sie lassen Ihre Häuser renovieren. Zahlen Sie: M 25 pro Haus, M 100 pro Hotel.
        	case 8:
        		System.out.println("Sie lassen Ihre Häuser renovieren. Zahlen Sie: M 25 pro Haus, M 100 pro Hotel.");
        		int[] grundstuecke = alleSpieler.get(aktiverSpieler).getGrundstuecke();
        		for(int i = 0; i < grundstuecke.length; i++)
        		{
        			if(spielfeld[grundstuecke[i]].getFeld().equalsIgnoreCase("Strasse"))
        			{
        				if(((Strasse)spielfeld[grundstuecke[i]]).getHausAnzahl() >= 5)
        				{
        					kosten += (100);
        				}
        				else
        				{
        					kosten += (25 * ((Strasse)spielfeld[grundstuecke[i]]).getHausAnzahl());
        				}
        			}
        		}
        		
        		if(kosten == 0)
        		{
        			System.out.println("Da du keine Grundstücke hast, musst du nichts bezahlen.");
        		}
        		else
        		{
        			System.out.println("Du musst insgesammt " + kosten + " Geld bezahlen.");
        			alleSpieler.get(aktiverSpieler).subtractGeld(kosten);
        		}
        		break;
        	//Sie kommen aus dem Gefängnis frei! Behalten Sie diese Karte, bis Sie sie benötigen oder verkaufen.
        	case 9:
        		System.out.println("Sie kommen aus dem Gefängnis frei! Behalten Sie diese Karte, bis Sie sie benötigen oder verkaufen.");
        		alleSpieler.get(aktiverSpieler).addGefängnisKarte();
        		break;
        	//Rücken Sie vor bis zur Seestraße. Wenn Sie über Los kommen, ziehen Sie M 200 ein
        	case 10:
        		System.out.println("Rücken Sie vor bis zur Seestraße. Wenn Sie über Los kommen, ziehen Sie M 200 ein");
        		if(alleSpieler.get(aktiverSpieler).getPosition() > 11)
        		{
        			alleSpieler.get(aktiverSpieler).addGeld(200);
        		}
        		alleSpieler.get(aktiverSpieler).setPosition(11);
        		alleSpieler = ((Strasse)spielfeld[11]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
        		break;
        	//Sie sind zum Vorstand gewählt worden. Zahlen Sie jedem Spieler M 50.
        	case 11:
        		System.out.println("Sie sind zum Vorstand gewählt worden. Zahlen Sie jedem Spieler M 50.");
        		for(int i = 0; i < alleSpieler.size(); i++)
        		{
        			if(i != aktiverSpieler)
        			{
        				kosten += 50;
        				alleSpieler.get(i).addGeld(50);
        			}
        		}
        		alleSpieler.get(aktiverSpieler).subtractGeld(kosten);
        		break;
        	//Ihr Bausparvertrag wird fällig. Sie erhalten M 200.
        	case 12:
        		System.out.println("Ihr Bausparvertrag wird fällig. Sie erhalten M 200.");
        		alleSpieler.get(aktiverSpieler).addGeld(200);
        		break;
        	//Gehen Sie 3 Felder zurück.
        	case 13:
        		System.out.println("Gehen Sie 3 Felder zurück.");
        		alleSpieler.get(aktiverSpieler).setPosition(alleSpieler.get(aktiverSpieler).getPosition() - 3);
        		//triggern der Feldeffekte
        		switch(spielfeld[alleSpieler.get(aktiverSpieler).getPosition()].getFeld())
                {
                case"Strasse":
                	alleSpieler = ((Strasse)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
                	break;
                case"Bahnhof":
                	alleSpieler = ((Bahnhof)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
                	break;
                case"Ereignisfeld":
                	alleSpieler = ((Ereignisfeld)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld, gewürfelteZahl);
                	break;
                case"Gemeinschaftsfeld":
                	alleSpieler = ((Gemeinschaftsfeld)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld, gewürfelteZahl);
                	break;
                case"Wasserwerk":
                	alleSpieler = ((Wasserwerk)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld, gewürfelteZahl);
                	break;
                case"Stromwerk":
                	alleSpieler = ((Stromwerk)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld, gewürfelteZahl);
                	break;
                case"Los":
                	alleSpieler = ((Start)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
                	break;
                case"Steuern":
                	alleSpieler = ((Steuern)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
                	break;
                case"Frei Parken":
                	alleSpieler = ((FreiParken)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
                	break;
                case"Ins Gefängnis":
                	alleSpieler = ((InsGefängnis)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
                	break;
                }
        		
        		break;
        	//Strafzettel! Zahlen Sie M 15.
        	case 14:
        		System.out.println("Strafzettel! Zahlen Sie M 15.");
        		alleSpieler.get(aktiverSpieler).subtractGeld(15);
        		break;
        	//Rücken Sie vor bis zum nächsten Verkehrsfeld. Der Eigentümer erhält das Doppelte der normalen Miete. Wenn das Verkehrsfeld noch niemandem gehört, können Sie es von der Bank kaufen.
        	case 15:
        		System.out.println("Rücken Sie vor bis zum nächsten Verkehrsfeld. Der Eigentümer erhält das Doppelte der normalen Miete. Wenn das Verkehrsfeld noch niemandem gehört, können Sie es von der Bank kaufen.");
        		//Hiermit sind Bahnhöfe gemeint
        		//Herrausfinden welcher Bahnhof am nächsten am Spieler ist
        		//Der Abstand des Spielers zu jedem Bahnhof als Array
        		int[] abstand = new int[] {alleSpieler.get(aktiverSpieler).getPosition() - 5, alleSpieler.get(aktiverSpieler).getPosition() - 15, alleSpieler.get(aktiverSpieler).getPosition() - 25, alleSpieler.get(aktiverSpieler).getPosition() - 35};
        		//Ist der Spieler schon an allen bahnhöfen vorbei? Dann muss er über los
        		if(abstand[0] < 0 && abstand[1] < 0 && abstand[2] < 0 && abstand[3] < 0)
        		{
        			alleSpieler.get(aktiverSpieler).addGeld(200);
        			alleSpieler.get(aktiverSpieler).setPosition(5);
        			alleSpieler = ((Bahnhof)spielfeld[5]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
        		}
        		else
        		{
        			//Das element von abstand das > 0 ist und am kleiner als alle anderen die > 0 sind
        			int kleinsterAbstand = 0;
        			//Ob in dem For loop schon ein abstand gefunden wurde, der > 0 ist
        			boolean wertGrößer0 = false;
        			for(int i = 0; i < 4; i++)
        			{
        				//ist der Wert > 0
        				if(abstand[i] > 0)
        				{
        					//Gab es schonmal einen Wert der > 0 war?
        					if(wertGrößer0)
        					{
        						//Ist der Wert kleiner als der kleinste der bis jetzt vorkam?
        						if(abstand[i] > 0 && abstand[i] < abstand[kleinsterAbstand])
        						{
        							kleinsterAbstand = i;
        						}
        					}
        					//Falls nicht, ist das hier der kleinste Abstand
        					else
        					{
        						kleinsterAbstand = i;
        						//Es gab bereits einen Wert > 0
        						wertGrößer0 = true;
        					}
        				}
        			}
        			switch(kleinsterAbstand)
        			{
        				case 0:
        					alleSpieler.get(aktiverSpieler).setPosition(5);
        					System.out.println("Du rückst auf den Bahnhof auf");
        					alleSpieler = ((Bahnhof)spielfeld[5]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
        					break;
        				case 1:
        					alleSpieler.get(aktiverSpieler).setPosition(15);
        					System.out.println("Du rückst auf den Bahnhof auf");
        					alleSpieler = ((Bahnhof)spielfeld[15]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
        					break;
        				case 2:
        					alleSpieler.get(aktiverSpieler).setPosition(25);
        					System.out.println("Du rückst auf den Bahnhof auf");
        					alleSpieler = ((Bahnhof)spielfeld[25]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
        					break;
        				case 3:
        					alleSpieler.get(aktiverSpieler).setPosition(35);
        					System.out.println("Du rückst auf den Bahnhof auf");
        					alleSpieler = ((Bahnhof)spielfeld[35]).feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
        					break;
        			}
        		}
        }
        
        return alleSpieler;
    }
    
    public ArrayList<Spieler> feldBetreten(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld, int gewürfelteZahl)
    {
        System.out.println("Du hast ein " + getFeld() + " betreten");
        return Ereignis(alleSpieler, aktiverSpieler, spielfeld, gewürfelteZahl);
    }
    
    public String toString()
    {
    	String ergebnis = "Ein " + getFeldname() + ".";
    	return ergebnis;
    } 
}