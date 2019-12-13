import java.util.ArrayList;
import java.util.Random;

public class Ereignisfeld extends Aktionsfelder
{
    //Alle Ereigniskarten aus diesem PDF: https://monopoly-vogtland.de/downloads/kartentexte_vogtland.pdf
    public Ereignisfeld(int Feldnummer)
    {
        super.feldnummer = Feldnummer;
        super.feld = "Ereignisfeld";
        super.feldname = "Ereignisfeld";
    }
    
    public ArrayList<Spieler> Ereignis(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        feldBetreten();
        //Erstellen eines Random objektes, um ein Ereignis auszwählen
        Random rnd = new Random();
        //Setzt den Seed abhängig von der Systemzeit, damit bei jedem Spieldurchlauf die Reihenfolge der Ereigniskarten variiert
        rnd.setSeed(System.currentTimeMillis());
        
        //Generiert eine Zahl zwischen 0 und 15, um eine der 16 Ereigniskarten auszuwählen
        switch(rnd.nextInt(16))
        {
        	//Rücken Sie vor bis zur Schlossallee
        	case 0:
        		System.out.println("Rücken Sie vor bis zur Schlossallee.");
        		alleSpieler.get(aktiverSpieler).setPosition(39);
        		//Dieser Teil des Programms ist noch nicht Implementiert.
        		spielfeld[39].aktion oder so
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
        		spielfeld[39].aktion oder so
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
        		//Dieser Teil des Programms ist noch nicht Implementiert.
        		spielfeld[39].aktion oder so
        		break;
        	//Rücken Sie vor bis zum nächsten Versorgungswerk. Werfen Sie die Würfel und zahlen dem Eigentümer den zehnfachen Betrag Ihres Wurfergebnisses. Wenn das Werk noch niemandem gehört, können Sie es von der Bank kaufen
        	case 4:
        		System.out.println("Rücken Sie vor bis zum nächsten Versorgungswerk. Werfen Sie die Würfel und zahlen dem Eigentümer den zehnfachen Betrag Ihres Wurfergebnisses. Wenn das Werk noch niemandem gehört, können Sie es von der Bank kaufen");
        		//Entscheiden, welches der Werke näher liegt
        		//Elektrizitätswerk
        		if(alleSpieler.get(aktiverSpieler).getPosition() > 12)
        		{
        			//Funktion noch nicht Implementiert
        		}
        		//Wasserwerk
        		else
        		{
        			//Funktion noch nicht Implementiert
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
        		break;
        	//Die Bank zahlt Ihnen eine Dividende von M 50.
        	case 7:
        		System.out.println("Die Bank zahlt Ihnen eine Dividende von M 50.");
        		alleSpieler.get(aktiverSpieler).addGeld(50);
        		break;
        	//Sie lassen Ihre Häuser renovieren. Zahlen Sie: M 25 pro Haus, M 100 pro Hotel.
        	case 8:
        		System.out.println("Sie lassen Ihre Häuser renovieren. Zahlen Sie: M 25 pro Haus, M 100 pro Hotel.");
        		//Funktion noch nicht implementiert
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
        		break;
        	//Sie sind zum Vorstand gewählt worden. Zahlen Sie jedem Spieler M 50.
        	case 11:
        		System.out.println("Sie sind zum Vorstand gewählt worden. Zahlen Sie jedem Spieler M 50.");
        		int kosten = 0;
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
        		//Diese Funktion ist noch nicht Implementiert
        		spielfeld[alleSpieler.get(aktiverSpieler).getPosition()].aktion oder so
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
        			//Diese Funktion ist noch nicht implementiert
        			spielfeld[5],aktion oder so
        		}
        		else
        		{
        			//Das element von abstand das > 0 ist und am kleiner als alle anderen die > 0 sind
        			int kleinsterAbstand;
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
        					//Diese Funktion ist noch nicht Implementiert
        					spielfeld[5].aktion oder so
        					break;
        				case 1:
        					alleSpieler.get(aktiverSpieler).setPosition(15);
        					System.out.println("Du rückst auf den Bahnhof auf");
        					//Diese Funktion ist noch nicht Implementiert
        					spielfeld[15].aktion oder so
        					break;
        				case 2:
        					alleSpieler.get(aktiverSpieler).setPosition(25);
        					System.out.println("Du rückst auf den Bahnhof auf");
        					//Diese Funktion ist noch nicht Implementiert
        					spielfeld[25].aktion oder so
        					break;
        				case 3:
        					alleSpieler.get(aktiverSpieler).setPosition(35);
        					System.out.println("Du rückst auf den Bahnhof auf");
        					//Diese Funktion ist noch nicht Implementiert
        					spielfeld[35].aktion oder so
        					break;
        			}
        		}
        }
        
        return alleSpieler;
    }
    
    public void feldBetreten()
    {
        System.out.println("Du hast ein " + feld + " betreten");
    }
}