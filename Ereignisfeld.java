import java.util.Random;

public class Ereignisfeld extends Aktionsfelder
{
    //Alle Ereigniskarten aus diesem PDF: https://monopoly-vogtland.de/downloads/kartentexte_vogtland.pdf
    public Ereignisfeld(int Feldnummer)
    {
    	super(Feldnummer, false, "Ereignisfeld", "Ereignisfeld");
    }
    
    public void Ereignis(int aktiverSpieler, int gewürfelteZahl)
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
        		Main.alleSpieler.get(aktiverSpieler).setPosition(39);
        		//Dieser Teil des Programms ist noch nicht Implementiert.
        		((Strasse)Main.spielfeld[39]).feldBetreten(aktiverSpieler);
        		break;
        	//Machen Sie einen Ausflug zum Südbahnhof. Wenn Sie über Los kommen, ziehen Sie M 200 ein.
        	case 1:
        		System.out.println("Machen Sie einen Ausflug zum Südbahnhof. Wenn Sie über Los kommen, ziehen Sie 200 Mark ein.");
        		//Falls der Spieler über Los gehen muss, bekommt er 200 Geld
        		if(Main.alleSpieler.get(aktiverSpieler).getPosition() > 5)
        		{
        			Main.alleSpieler.get(aktiverSpieler).addGeld(200);
        		}
        		Main.alleSpieler.get(aktiverSpieler).setPosition(5);
        		//Dieser Teil des Programms ist noch nicht Implementiert.
        		((Bahnhof)Main.spielfeld[5]).feldBetreten(aktiverSpieler);
        		break;
        	//Ihr Bausparvertrag wird fällig. Sie erhalten M 200.
        	case 2:
        		System.out.println("Ihr Bausparvertrag wird fällig. Sie erhalten 200 Mark.");
        		Main.alleSpieler.get(aktiverSpieler).addGeld(200);
        		break;
        	//Rücken Sie vor bis zum Opernplatz. Wenn Sie über Los kommen, ziehen Sie M 200 ein.
        	case 3:
        		System.out.println("Rücken Sie vor bis zum Opernplatz. Wenn Sie über Los kommen, ziehen Sie 200 Mark ein.");
        		if(Main.alleSpieler.get(aktiverSpieler).getPosition() > 24)
        		{
        			Main.alleSpieler.get(aktiverSpieler).addGeld(200);
        		}
        		Main.alleSpieler.get(aktiverSpieler).setPosition(24);
        		((Strasse)Main.spielfeld[24]).feldBetreten(aktiverSpieler);
        		break;
        	//Rücken Sie vor bis zum nächsten Versorgungswerk. Werfen Sie die Würfel und zahlen dem Eigentümer den zehnfachen Betrag Ihres Wurfergebnisses. Wenn das Werk noch niemandem gehört, können Sie es von der Bank kaufen
        	case 4:
        		System.out.println("Rücken Sie vor bis zum nächsten Versorgungswerk. Werfen Sie die Würfel und zahlen dem Eigentümer den zehnfachen Betrag Ihres Wurfergebnisses. Wenn das Werk noch niemandem gehört, können Sie es von der Bank kaufen");
        		//Entscheiden, welches der Werke näher liegt
        		//Elektrizitätswerk
        		if(Main.alleSpieler.get(aktiverSpieler).getPosition() > 12)
        		{
        			Main.alleSpieler.get(aktiverSpieler).setPosition(12);
        			((Stromwerk)Main.spielfeld[12]).feldBetreten(aktiverSpieler, gewürfelteZahl);
        		}
        		//Wasserwerk
        		else
        		{
        			Main.alleSpieler.get(aktiverSpieler).setPosition(28);
        			((Wasserwerk)Main.spielfeld[28]).feldBetreten(aktiverSpieler, gewürfelteZahl);
        		}
        		break;
        	//Gehen Sie in das Gefängnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht über Los. Ziehen Sie nicht M 200 ein
        	case 5:
        		System.out.println("Gehen Sie in das Gefängnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht über Los. Ziehen Sie nicht 200 Mark ein");
        		Main.alleSpieler.get(aktiverSpieler).setImGefängnis();
        		break;
        	//Rücken Sie vor bis auf Los. (Ziehe M 200 ein).
        	case 6:
        		System.out.println("Rücken Sie vor bis auf Los. (Ziehe 200 Mark ein).");
        		Main.alleSpieler.get(aktiverSpieler).setPosition(0);
        		Main.alleSpieler.get(aktiverSpieler).setPosition(12);
    			((Start)Main.spielfeld[0]).feldBetreten(aktiverSpieler);
        		break;
        	//Die Bank zahlt Ihnen eine Dividende von M 50.
        	case 7:
        		System.out.println("Die Bank zahlt Ihnen eine Dividende von 50 Mark.");
        		Main.alleSpieler.get(aktiverSpieler).addGeld(50);
        		break;
        	//Sie lassen Ihre Häuser renovieren. Zahlen Sie: M 25 pro Haus, M 100 pro Hotel.
        	case 8:
        		System.out.println("Sie lassen Ihre Häuser renovieren. Zahlen Sie: M 25 pro Haus, 100 Mark pro Hotel.");
        		int[] grundstuecke = Main.alleSpieler.get(aktiverSpieler).getGrundstuecke();
        		for(int i = 0; i < grundstuecke.length; i++)
        		{
        			if(Main.spielfeld[grundstuecke[i]].getFeld().equalsIgnoreCase("Strasse"))
        			{
        				if(((Strasse)Main.spielfeld[grundstuecke[i]]).getHausAnzahl() >= 5)
        				{
        					kosten += (100);
        				}
        				else
        				{
        					kosten += (25 * ((Strasse)Main.spielfeld[grundstuecke[i]]).getHausAnzahl());
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
        			Main.alleSpieler.get(aktiverSpieler).subtractGeld(kosten);
        		}
        		break;
        	//Sie kommen aus dem Gefängnis frei! Behalten Sie diese Karte, bis Sie sie benötigen oder verkaufen.
        	case 9:
        		System.out.println("Sie kommen aus dem Gefängnis frei! Behalten Sie diese Karte, bis Sie sie benötigen oder verkaufen.");
        		Main.alleSpieler.get(aktiverSpieler).addGefängnisKarte();
        		break;
        	//Rücken Sie vor bis zur Seestraße. Wenn Sie über Los kommen, ziehen Sie M 200 ein
        	case 10:
        		System.out.println("Rücken Sie vor bis zur Seestraße. Wenn Sie über Los kommen, ziehen Sie M 200 ein");
        		if(Main.alleSpieler.get(aktiverSpieler).getPosition() > 11)
        		{
        			Main.alleSpieler.get(aktiverSpieler).addGeld(200);
        		}
        		Main.alleSpieler.get(aktiverSpieler).setPosition(11);
        		((Strasse)Main.spielfeld[11]).feldBetreten(aktiverSpieler);
        		break;
        	//Sie sind zum Vorstand gewählt worden. Zahlen Sie jedem Spieler M 50.
        	case 11:
        		System.out.println("Sie sind zum Vorstand gewählt worden. Zahlen Sie jedem Spieler M 50.");
        		for(int i = 0; i < Main.alleSpieler.size(); i++)
        		{
        			if(i != aktiverSpieler)
        			{
        				kosten += 50;
        				Main.alleSpieler.get(i).addGeld(50);
        			}
        		}
        		Main.alleSpieler.get(aktiverSpieler).subtractGeld(kosten);
        		break;
        	//Ihr Bausparvertrag wird fällig. Sie erhalten M 200.
        	case 12:
        		System.out.println("Ihr Bausparvertrag wird fällig. Sie erhalten M 200.");
        		Main.alleSpieler.get(aktiverSpieler).addGeld(200);
        		break;
        	//Gehen Sie 3 Felder zurück.
        	case 13:
        		System.out.println("Gehen Sie 3 Felder zurück.");
        		Main.alleSpieler.get(aktiverSpieler).setPosition(Main.alleSpieler.get(aktiverSpieler).getPosition() - 3);
        		//triggern der Feldeffekte
        		switch(Main.spielfeld[Main.alleSpieler.get(aktiverSpieler).getPosition()].getFeld())
                {
					case"Strasse":
						((Strasse)Main.spielfeld[Main.alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler);
						break;
					case"Bahnhof":
						((Bahnhof)Main.spielfeld[Main.alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler);
						break;
					case"Ereignisfeld":
						((Ereignisfeld)Main.spielfeld[Main.alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler, gewürfelteZahl);
						break;
					case"Gemeinschaftsfeld":
						((Gemeinschaftsfeld)Main.spielfeld[Main.alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler, gewürfelteZahl);
						break;
					case"Wasserwerk":
						((Wasserwerk)Main.spielfeld[Main.alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler, gewürfelteZahl);
						break;
					case"Stromwerk":
						((Stromwerk)Main.spielfeld[Main.alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler, gewürfelteZahl);
						break;
					case"Los":
						((Start)Main.spielfeld[Main.alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler);
						break;
					case"Steuern":
						((Steuern)Main.spielfeld[Main.alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler);
						break;
					case"Frei Parken":
						((FreiParken)Main.spielfeld[Main.alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler);
						break;
					case"Ins Gefängnis":
						((InsGefängnis)Main.spielfeld[Main.alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler);
						break;
                }
        		
        		break;
        	//Strafzettel! Zahlen Sie M 15.
        	case 14:
        		System.out.println("\nStrafzettel! Zahle 15 Mark.");
        		Main.alleSpieler.get(aktiverSpieler).subtractGeld(15);
        		break;
        	//Rücken Sie vor bis zum nächsten Verkehrsfeld. Der Eigentümer erhält das Doppelte der normalen Miete. Wenn das Verkehrsfeld noch niemandem gehört, können Sie es von der Bank kaufen.
        	case 15:
        		System.out.println("\nRücke vor bis zum nächsten Verkehrsfeld. Der Eigentümer erhält das Doppelte der normalen Miete. Wenn das Verkehrsfeld noch niemandem gehört, können Sie es von der Bank kaufen.");
        		//Hiermit sind Bahnhöfe gemeint
        		//Herrausfinden welcher Bahnhof am nächsten am Spieler ist
        		//Der Abstand des Spielers zu jedem Bahnhof als Array
        		int[] abstand = new int[] {Main.alleSpieler.get(aktiverSpieler).getPosition() - 5, Main.alleSpieler.get(aktiverSpieler).getPosition() - 15, Main.alleSpieler.get(aktiverSpieler).getPosition() - 25, Main.alleSpieler.get(aktiverSpieler).getPosition() - 35};
        		//Ist der Spieler schon an allen bahnhöfen vorbei? Dann muss er über los
        		if(abstand[0] < 0 && abstand[1] < 0 && abstand[2] < 0 && abstand[3] < 0)
        		{
        			Main.alleSpieler.get(aktiverSpieler).addGeld(200);
        			Main.alleSpieler.get(aktiverSpieler).setPosition(5);
        			((Bahnhof)Main.spielfeld[5]).feldBetreten(aktiverSpieler);
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
        						if(abstand[i] < abstand[kleinsterAbstand])
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
        					Main.alleSpieler.get(aktiverSpieler).setPosition(5);
        					System.out.println("\nDu rückst auf den Südbahnhof auf.");
        					((Bahnhof)Main.spielfeld[5]).feldBetreten(aktiverSpieler);
        					break;
        				case 1:
        					Main.alleSpieler.get(aktiverSpieler).setPosition(15);
        					System.out.println("\nDu rückst auf den Westbahnhof auf.");
        					((Bahnhof)Main.spielfeld[15]).feldBetreten(aktiverSpieler);
        					break;
        				case 2:
        					Main.alleSpieler.get(aktiverSpieler).setPosition(25);
        					System.out.println("\nDu rückst auf den Nordbahnhof auf.");
        					((Bahnhof)Main.spielfeld[25]).feldBetreten(aktiverSpieler);
        					break;
        				case 3:
        					Main.alleSpieler.get(aktiverSpieler).setPosition(35);
        					System.out.println("\nDu rückst auf den Hauptbahnhof auf.");
        					((Bahnhof)Main.spielfeld[35]).feldBetreten(aktiverSpieler);
        					break;
        			}
        		}
        }
    }
    
    public void feldBetreten(int aktiverSpieler, int gewürfelteZahl)
    {
        System.out.println("Du hast ein " + getFeld() + " betreten");
        Ereignis(aktiverSpieler, gewürfelteZahl);
    }
}