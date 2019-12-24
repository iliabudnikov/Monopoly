import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static ArrayList<Spieler> alleSpieler;
    public static Feld[] spielfeld;
    static int aktiverSpieler;
    
    //Das erstellen eines Main objektes in der main Methode, erlaubt die Nutzung von variablen und Objekten in der gesammten Main Klasse
    public Main()
    {
        alleSpieler = new ArrayList<Spieler>();
        //Begrüßung der Spieler. Frage nach der Spieleranzahl
        System.out.println("Willkommen zu Monopoly\nMit wie vielen Spielern möchtest du Spielen? (2 - 4)");
        //Eingabe der Spieleranzahl
        Scanner sc = new Scanner(System.in);
        //Boolean für den Loop zur überprüfung der Eingabe
        boolean eingabeKorrekt = false;
        String eingabe;
        int spielerAnzahl = 0;
        while(!eingabeKorrekt)
        {
            eingabe= sc.nextLine();
            switch(eingabe)
            {
                case"2":
                    spielerAnzahl = 2;
                    eingabeKorrekt = true;
                    break;
                case"3":
                    spielerAnzahl = 3;
                    eingabeKorrekt = true;
                    break;
                case"4":
                    spielerAnzahl = 4;
                    eingabeKorrekt = true;
                    break;
                default:
                    System.out.println("Es können nur 2 - 4 Spieler spielen.\n Sie können nur 2,3 oder 4 eingeben, versuchen sie es nochmal");
            }
        }
        
        //Erstellen der Benötigten Spieler
        alleSpieler = new ArrayList<Spieler>();
        //Arraylist die alle Figuren enthält.
        ArrayList figuren = new ArrayList<String>();
        //Das erstellen einer Liste welche alle Mögliche Figuren enthält ermöglicht es, diese auf einmal zu der Arraylist hinzuzufügen
        List<String> figurNamen = Arrays.asList("Hund", "Schuh", "Auto", "Schiff", "Hut", "Fingerhut", "Schubkarre", "Bügeleisen");
        figuren.addAll(figurNamen);
        //Integer welche speichert, welche figur der Nutzer ausgewählt hat
        int welcheFigur = -1;

        
        for(int i = 0; i < spielerAnzahl; i++)
        {
        	welcheFigur = -1;
        	//Figur aussuchen
        	System.out.println("Welche Figur möchte Spieler " + (i+1) + "?");
        	//Dieser Loop wird solange ausgeführt, bis der Spieler eine Zahl zwischen  1 und der Anzahl der Elemente in Figuren eingibt
        	eingabeKorrekt = false;
        	while(!eingabeKorrekt)
        	{
        		//Ausgabe der Figuren, die der Spieler auswählen kann
        		for(int j =0; j < figuren.size(); j++)
            	{
            		System.out.println("Für die Figur" + figuren.get(j) + " schreibe " + (j +1));
            	}
        		
        		welcheFigur = sc.nextInt();
        		//Hat die eingegebene Zahl ein Korrespondierendes Element in Figuren?
        		if(welcheFigur < 0 && welcheFigur >= figuren.size())
        		{
        			eingabeKorrekt = true;
        		}
        		else
        		{
        			System.out.println("Die Eingabe war nicht korrekt, versuch es nocheinmal.");
        		}
        	}
        	//Erstellen des Spielers mit der Figur, die gewählt wurde
        	alleSpieler.add(new Spieler(figuren.get(welcheFigur).toString(), i));
        	//Entfernen der Figur aus der Arraylist, damit kein anderer Spieler diese auswählen kann
        	figuren.remove(welcheFigur);
        }
        	
      //Erstellen des Spielfelds
        spielfeld = new Feld[]{
        	new Start(0), new Strasse(1, "Badstraße", 0, 80, "Lila", 0, 50), new Gemeinschaftsfeld(2) , new Strasse(3, "Turmstraße", 0,  80, "Lila", 0, 50), new Steuern(4, "Einkommenssteuer", false), new Bahnhof(5, "Südbahnhof"), new Strasse(6, "Chaussee-Straße", 0, 100, "Hellblau", 0, 50), new Ereignisfeld(7),  new Strasse(8, "Elisenstraße", 0, 100, "Hellblau", 0, 50), new Strasse(9, "Poststraße", 0, 120, "Hellblau", 0, 50),
        	new Gefängnis(10), new Strasse(11, "Seestraße", 0, 140, "Magenta", 0, 100), new Stromwerk(12), new Strasse(13, "Hafenstraße", 0, 140, "Magenta", 0, 100), new Strasse(14, "Neue Straße", 0, 160, "Magenta", 0, 100), new Bahnhof(15, "Westbahnhof"), new Strasse(16, "Münchner Straße", 0, 180, "Orange", 0, 100), new Gemeinschaftsfeld(17), new Strasse(18, "Wiener Straße", 0, 180, "Orange", 0, 100), new Strasse(19,  "Berliner Straße", 0, 200, "Orange", 0, 100), 
        	new FreiParken(20), new Strasse(21, "Theaterstraße", 0, 220, "Rot", 0, 150), new Ereignisfeld(22), new Strasse(23, "Museumsstraße", 0, 220, "Rot", 0, 150), new Strasse(24, "Opernplatz", 0, 240, "Rot", 0, 150), new Strasse(24, "Opernplatz", 0, 240, "Rot", 0, 150), new Bahnhof(25, "Nordbahnhof"), new Strasse(26, "Lessingstraße", 0, 260, "Gelb", 0, 150), new Strasse(27, "SchillerStraße", 0, 260, "Gelb", 0, 150), new Wasserwerk(28), new Strasse(29, "Goethetraße", 0, 280, "Gelb", 0, 150), 
        	new InsGefängnis(30), new Strasse(31, "Rathausplatz", 0, 300, "Grün", 0, 200), new Strasse(32, "Hauptstraße", 0, 300, "Grün", 0, 200), new Gemeinschaftsfeld(33), new Strasse(34, "Bahnhofstraße", 0, 320, "Grün", 0, 200), new Bahnhof(35, "Hauptbahnhof"), new Ereignisfeld(36), new Strasse(37, "Parkstraße", 0, 350, "Blau", 0, 200), new Steuern(38, "Zusatzsteuer" ,true), new Strasse(39, "Schlossallee", 0, 400, "Blau", 0, 200)     
        };	
    }
        
        
        
  
        public static void main(String[] args)
        {
            // --!-- Nur ein Spieler? Ist ein Gewinner automatisch!
            // Alle Regeln von dieser Website: https://monopoly-regeln.de/monopoly-spielregeln/
            // Spieler Erstellen
            
            //       SEITE EXISTIERT NICHT        Spielfeld Erstellen (https://de.wikipedia.org/wiki/Monopoly Das Spielfeld)
           
        	while(true)
        	{
        		Main main = new Main();
        		// Random die Reihenfolge festlegen, in der die Spieler an der Reihe sind
        		int[] reihenfolge = reihenfolgeFestlegen();
        		
        		//Spielbeginn
        		for(int i = 0; i < alleSpieler.size(); i++)
        		{
        			//Setzen des aktiven Spielers nach der Reihenfolge, welche vorher festgelegt wurde
        			aktiverSpieler = reihenfolge[i];
        			
        			//Eine String ArrayList, welche alle möglichen Aktionen Beinhaltet, die der Spieler gerade ausführen kann. Wird in der Spieler Klasse durch eine Methode generiert.
        			//Wird nach jeder Ausgeführten Aktion neu generiert, um nicht mehr mögliche Aktionen zu entfernen.
        			ArrayList<String> möglicheAktionen;
        			
        		}
        		
        		
        	}
        	
            
            
            
            
            
            
            // Würfeln mit 2 Würfeln. Addieren der gewürfelten Zahlen und so viele Felder vorrücken. Zwei gleiche Zahlen = noch einmal würfeln.
            // Wenn er sich über Start/Los geht, bekommt ein Spieler 200 Geld
            
            // Auf welchem Feld steht ein Spieler? Feldspeziefische Funktionen: Kaufen/Miete, Aktionen
            // Wenn ein Spieler ein Grundstück nicht kaufen möchte, wird es per Auktion an die Spieler verkauft
            
            // Jetzt hat ein Spieler die Möglichkeit Häuser zu bauen, falls er alle Straßen einer Farbe besitzt. Es müssen einheitlich viele Häuser auf allen Straßen einer Farbe stehen. Die kosten für ein Haus variieren je nach Straße. Das 5. Haus auf einer Straße ist ein Hotel. Diese haben einen anderen Preis als Häuser. Man kann nur ein Hotel pro Straße bauen (Nicht pro Farbe, Pro Straße; mit dem Hotel kein Gebäude mehr auf der Straßemöglich)
            
            // Der Spieler kann während seines Zuges jederzeit mit anderen Spielern handeln. Er kann unbebaute Grundstücke, Geld und "Komme aus dem Gefängnis Frei" Karten frei mit anderen Spielern Tauschen.
            // Wenn der Spieler nicht genug Geld hat/ mehr Geld haben will, kann er Häuser zum halben Preis verkaufen. Es müssen weiterhin einheitlich viele Häuser auf allen Straßen einer Farbe stehen.
            // Der Spieler kann eine Hypothek auf ein unbebautes Grundstück aufnehmen. Wie viel Geld der Spieler für eine Hypothek bekommt variiert nach Grundstück.
            // Wenn auf ein Grundstück eine Hypothek angemeldet ist, bekommt der Besitzer keine Miete, wenn ein anderer Spieler es betritt. Es kann weiterhin gehandelt werden, die Hypothek bleibt bestehen
            // Um eine Hypothek aufzulösen, muss der Besitzer das Geld, was er durch die Hypothek bekommen hat +10% zurrückbezahlen.       

        }
        
        //Ermöglicht dem Spieler alle möglichen eingaben während des Spieles zu machen
        public void aktionenAusführen()
        {
        	//Beinhaltet alle möglichen Aktionen die der Spieler ausführen kann
        	ArrayList<String> aktionen = alleSpieler.get(aktiverSpieler).möglicheAktionen(alleSpieler, aktiverSpieler, spielfeld);
        	System.out.println("Was möchtest du tun?");
        	//Gibt aus, welche aktionen der Spieler ausführen kann und welche Zahl er dafür eingeben soll
        	for(int i = 0; i < aktionen.size(); i++)
        	{
        		System.out.println("Zum " + aktionen.get(i) + "gebe " + (i + 1) + " ein.");
        	}
        	
        	//Eingabe der Zahl durch den Nutzer
        	Scanner sc = new Scanner(System.in);
        	
        	boolean eingabeKorrekt = false;
        	int eingabe = -1;
        	//Wenn die Eingabe nicht korrekt ist wird dieser Loop wiederholt. Er läuft immer mindestens einmal
        	while(!eingabeKorrekt)
        	{
        		//Hat der Nutzer eine Zahl eingegeben? Falls ja, weiter, falls nein war die Eingabe fehlerhaft und muss wiederholt werden.
        		if(sc.hasNextInt())
        		{
        			eingabe = sc.nextInt();
        			//Wenn die Eingabe innerhalb von 1 - aktionen.size() liegt, ist die Eingabe korrekt. Wenn dies nicht der Fall ist, wird die Eingabe wiederholt
        			if(eingabe > 0 && eingabe <= aktionen.size())
        			{
        				eingabeKorrekt = true;
        			}
        			else
            		{
            			System.out.println("Die Eingabe war nicht korrekt, versuch es noch einmal. Die möglichen Eingaben sind: ");
            			for(int i = 0; i < aktionen.size(); i++)
                    	{
                    		System.out.println("Zum " + aktionen.get(i) + "gebe " + (i + 1) + " ein.");
                    	}
            		}
        		}
        		else
        		{
        			System.out.println("Die Eingabe war nicht korrekt, versuch es noch einmal. Die möglichen Eingaben sind: ");
        			for(int i = 0; i < aktionen.size(); i++)
                	{
                		System.out.println("Zum " + aktionen.get(i) + "gebe " + (i + 1) + " ein.");
                	}
        		}
        	}
        	
        	//Hier kann nun die ausgewählte Aktion ausgeführt werden
        	switch(aktionen.get(eingabe  - 1))
        	{
        		case"Würfeln":
        			Würfeln();
        			break;
        		case"Aus dem Gefängnis freikaufen (Dies kostet 50 Geld)":
        			alleSpieler.get(aktiverSpieler).subtractGeld(50, alleSpieler,  spielfeld, aktiverSpieler);
        			alleSpieler.get(aktiverSpieler).ausGefängnis();
        			System.out.println("Du hast 50 Geld bezahlt und bist nun nicht mehr eingesperrt.");
        			break;
        		case"'Komme aus dem Gefängnis Frei' Karte verwenden":
        			alleSpieler.get(aktiverSpieler).ausGefängnis();
        			System.out.println("Du hast eine 'Komme aus dem Gefängnis Frei' verwendet und bist nun nicht mehr eingesperrt.");
        			break;
        		case"Haus bauen":
        			
        			break;
        		case"Häuser verkaufen":
        			alleSpieler = alleSpieler.get(aktiverSpieler).hausVerkaufen(alleSpieler, spielfeld, aktiverSpieler);
        			break;
        		case"Handeln":
        			
        			break;
        		case"Hypothen auf ein Grundstück aufnehmen":
        			alleSpieler.get(aktiverSpieler).hypothekAufnehmen(alleSpieler, spielfeld, aktiverSpieler);
        			break;
        		case"Hypotheken abbezahlen":
        			alleSpieler.get(aktiverSpieler).hypothekAbbezahlen(alleSpieler, spielfeld, aktiverSpieler);
        			break;
        			
        		//Man sollte warscheinich auch eine Art inventar hinzufügen, wo der Spieler ansehen kann was er alles so hat(Grundstücke, komm aus dem Gefängnis frei Karten, All sein Geld, ob seine Grundstücke Hypotheken haben, usw)
        	}
        	
        }
        
        public void Würfeln()
        {
            int paschAnzahl = 0;
            Random rnd = new Random();
            rnd.setSeed(System.currentTimeMillis());
            //Läuft wenn kein Pasch gewürfelt wurde genau ein mal. Wenn der Spieler einen Pasch würfelt, wird i um 1 verringert, wodurch der for loop nach dem ersten durchlauf nocheinmal
            for(int i = 0; i <= 0; i++)
            {
                
                if(alleSpieler.get(aktiverSpieler).getImGefängnis())
                {
                   //Gefängnis event aus dem Feld Array triggern. Alles andere Passiert jetzt ersmal da.
                   
                }
                //Sollte der Spieler nicht im Gefängnis sein, darf er würfeln. Hier wird ein If genutzt, da der Spieler aus dem Gefängnis freikommen kann und danach seinen Zug ganz normal fortsetzen kann
                //Wenn der Spieler durch würfeln eines Pasches aus dem Gefängnis freigekommen ist, wird der Paschcounter erhöht.
                
                int rndInt1 = rnd.nextInt(5) + 1;
                int rndInt2 = rnd.nextInt(5) + 1;
                
                System.out.println("Du hast eine " + rndInt1 + " und eine " + rndInt2 + " gewürfelt.");
                
                if(rndInt1 == rndInt2)
                {
                    i--;
                    paschAnzahl++;
                    System.out.println("Du hast einen Pasch gewürfelt.");
                }
                
                alleSpieler.get(aktiverSpieler).setPosition(alleSpieler.get(aktiverSpieler).getPosition() + rndInt1 + rndInt2);
                //Triggern der Aktion des Feldes
                spielfeld[alleSpieler.get(aktiverSpieler).getPosition()].aktion oder so; 
                
            }
        }
        
        public static int[] reihenfolgeFestlegen()
        {
        	int[] reihenfolge = new int[alleSpieler.size()];
        	Random rnd = new Random();
        	rnd.setSeed(System.currentTimeMillis());
        	
        	for(int i = 0; i < alleSpieler.size(); i++)
        	{
        		//Generieren einer zahl zwischen 0 und alleSpieler.size-1
        		reihenfolge[i] = rnd.nextInt(alleSpieler.size());
        		if(i != 0)
        		{
        			for(int j = 0; j < i; j++)
        			{
        				//Falls bereits eine vorherige Zahl diesen Wert hatte, wird der erste for loop erneut für diesen i wert ausgeführt
        				if(reihenfolge[i] == reihenfolge[j])
        				{
        					i--;
        					break;
        				}
        			}
        		}
        	}
        	
        	System.out.println("Die Reihenfolge wurde festgelegt.");
        	for(int i = 0; i < alleSpieler.size(); i++)
        	{
        		System.out.println("Spieler " + (i+1) + " ist der Spieler mit der Figur: " + alleSpieler.get(reihenfolge[i]).getFigur());
        	}
        	
        	return reihenfolge;
        }
}