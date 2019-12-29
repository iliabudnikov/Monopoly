import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static ArrayList<Spieler> alleSpieler;
    public static Feld [] spielfeld;
	private static int aktiverSpieler;
	private static Scanner sc = new Scanner(System.in);
	
	// überprüft die Eingabe des Benutzer (funktioniert mit ganzen Zahlen)
	// als die Grenze kann man -1 eingeben, wenn keine Grenze nötig sind; die Grenze sind weich (>=, =<)
	public static int checkCorrectNum(int grenzeLinks, int grenzeRechts)
	{
		int eingabe;
		boolean eingabeKorrekt = false;
		do {
			System.out.print("\nGib eine Ganzzahl von " + 1 + " bis " + 10 + " ein.\n\n-> ");
			while (!sc.hasNextInt()) {
				System.out.println("\nDas ist keine Zahl. Versuch noch einmal.");
				sc.next();
				System.out.print("\nGib eine Ganzzahl von " + 1 + " bis " + 10 + " ein.\n\n-> ");
			}

			eingabe = sc.nextInt();
			if (eingabe < 1 || eingabe > 10)
				System.out.println("\nDie Zahl liegt außerhalb des zugelassenen Bereichs. Versuch noch einmal.");
			else
				eingabeKorrekt = true;
		} while (!eingabeKorrekt);
		
		return eingabe;
	}

    //Das erstellen eines Main-Objektes in der main Methode, erlaubt die Nutzung von variablen und Objekten in der gesammten Main Klasse
    public Main()
    {
        alleSpieler = new ArrayList<Spieler>();
        //Begrüßung der Spieler. Frage nach der Spieleranzahl
        System.out.println("Willkommen zu Monopoly!\n\nMit wie vielen Spielern möchtest du Spielen? (2 - 4)");
		//Eingabe der Spieleranzahl
		int spielerAnzahl = checkCorrectNum(2, 4);
        
        //Erstellen der Benötigten Spieler
        alleSpieler = new ArrayList<Spieler>();
        //Arraylist die alle Figuren enthält.
        ArrayList<String> figuren = new ArrayList<String>();
        //Das erstellen einer Liste welche alle Mögliche Figuren enthält ermöglicht es, diese auf einmal zu der Arraylist hinzuzufügen
        List<String> figurNamen = Arrays.asList("Hund", "Schuh", "Auto", "Schiff", "Hut", "Fingerhut", "Schubkarre", "Bügeleisen");
        figuren.addAll(figurNamen);
        //Integer welche speichert, welche figur der Nutzer ausgewählt hat
        int welcheFigur;

        
        for(int i = 0; i < spielerAnzahl; i++)
        {
        	//Figur aussuchen
        	System.out.println("\nWelche Figur möchte Spieler " + (i+1) + "?\n");
			for(int j = 0; j < figuren.size(); j++)
            {
            		System.out.println("Für die Figur \"" + figuren.get(j) + "\" schreibe " + (j+1));
			}
			
			welcheFigur = checkCorrectNum(1, figuren.size()) - 1;
			
        	//Erstellen des Spielers mit der Figur, die gewählt wurde
        	alleSpieler.add(new Spieler(figuren.get(welcheFigur).toString(), i));
        	//Entfernen der Figur aus der Arraylist, damit kein anderer Spieler diese auswählen kann
        	figuren.remove(welcheFigur);
        }
        	
      //Erstellen des Spielfelds
        spielfeld = new Feld[]{
        	new Start(0), new Strasse(1, "Badstraße", null, 80, "Lila", 0, 50,(new int[] {2, 10, 30, 90, 160, 250})), new Gemeinschaftsfeld(2) , new Strasse(3, "Turmstraße", null,  80, "Lila", 0, 50,(new int[] {4, 20, 60, 180, 320, 450})), new Steuern(4, "Einkommenssteuer", false), new Bahnhof(5, "Südbahnhof"), new Strasse(6, "Chaussee-Straße", null, 100, "Hellblau", 0, 50, (new int[] {6, 30, 90, 270, 400, 550})), new Ereignisfeld(7),  new Strasse(8, "Elisenstraße", null, 100, "Hellblau", 0, 50, (new int[] {6, 30, 90, 270, 400, 550})), new Strasse(9, "Poststraße", null, 120, "Hellblau", 0, 50, (new int[] {8, 40, 100, 300, 450, 600})),
        	new Gefängnis(10), new Strasse(11, "Seestraße", null, 140, "Magenta", 0, 100, (new int[] {10, 50, 150, 450, 625, 750})), new Stromwerk(12), new Strasse(13, "Hafenstraße", null, 140, "Magenta", 0, 100, (new int[] {10, 50, 150, 450, 625, 750})), new Strasse(14, "Neue Straße", null, 160, "Magenta", 0, 100, (new int[] {12, 60, 180, 500, 705, 900})), new Bahnhof(15, "Westbahnhof"), new Strasse(16, "Münchner Straße", null, 180, "Orange", 0, 100, (new int[] {14, 70, 200, 550, 750, 950})), new Gemeinschaftsfeld(17), new Strasse(18, "Wiener Straße", null, 180, "Orange", 0, 100, (new int[] {14, 70, 200, 550, 750, 950})), new Strasse(19,  "Berliner Straße", null, 200, "Orange", 0, 100, (new int[] {16, 80, 220, 600, 800, 1000})), 
        	new FreiParken(20), new Strasse(21, "Theaterstraße", null, 220, "Rot", 0, 150, (new int[] {18, 90, 250, 700, 875, 1050})), new Ereignisfeld(22), new Strasse(23, "Museumsstraße", null, 220, "Rot", 0, 150, (new int[] {18, 90, 250, 700, 875, 1050})), new Strasse(24, "Opernplatz", null, 240, "Rot", 0, 150, (new int[] {20, 100, 300, 750, 925, 1100})), new Bahnhof(25, "Nordbahnhof"), new Strasse(26, "Lessingstraße", null, 260, "Gelb", 0, 150, (new int[] {22, 110, 330, 800, 975, 1150})), new Strasse(27, "SchillerStraße", null, 260, "Gelb", 0, 150, (new int[] {22, 110, 330, 800, 975, 1150})), new Wasserwerk(28), new Strasse(29, "Goethetraße", null, 280, "Gelb", 0, 150, (new int[] {24, 120, 360, 850, 1025, 1200})), 
        	new InsGefängnis(30), new Strasse(31, "Rathausplatz", null, 300, "Grün", 0, 200, (new int[] {26, 130, 390, 900, 1100, 1275})), new Strasse(32, "Hauptstraße", null, 300, "Grün", 0, 200, (new int[] {26, 130, 390, 900, 1100, 1275})), new Gemeinschaftsfeld(33), new Strasse(34, "Bahnhofstraße", null, 320, "Grün", 0, 200, (new int[] {28, 150, 450, 1000, 1200, 1400})), new Bahnhof(35, "Hauptbahnhof"), new Ereignisfeld(36), new Strasse(37, "Parkstraße", null, 350, "Blau", 0, 200, (new int[] {35, 175, 500, 1100, 1300, 1500})), new Steuern(38, "Zusatzsteuer" ,true), new Strasse(39, "Schlossallee", null, 400, "Blau", 0, 200, (new int[] {50, 200, 600, 1400, 1700, 2000}))     
        };	
    }
        
        public static void main(String[] args)
        {
            // --!-- Nur ein Spieler? Ist ein Gewinner automatisch!
            // Alle Regeln von dieser Website: https://monopoly-regeln.de/monopoly-spielregeln/
            // Spieler Erstellen
            
            // Spielfeld Erstellen (https://de.wikipedia.org/wiki/Monopoly Das Spielfeld)
        	
        	Main main = new Main();
        	// Random die Reihenfolge festlegen, in der die Spieler an der Reihe sind
        	ArrayList<Integer> reihenfolge = new ArrayList<Integer>();
        	int[] temp = reihenfolgeFestlegen();
        	for(int i = 0; i < temp.length; i++)
        	{
        		reihenfolge.add(temp[i]);
        	}
			
        	while(true)
        	{
        		//Spielbeginn
        		for(int i = 0; i < alleSpieler.size(); i++)
        		{
        			//Setzen des aktiven Spielers nach der Reihenfolge, welche vorher festgelegt wurde
        			aktiverSpieler = reihenfolge.get(i);
        			//Ausgeben welcher Spieler am zug ist
        			System.out.println("Der Spieler mit der Figur " + alleSpieler.get(aktiverSpieler).getFigur() + " ist am Zug.");
        			System.out.println("Du hast " + alleSpieler.get(aktiverSpieler).getGeld() + " Geld.");
        			if(alleSpieler.get(aktiverSpieler).getImGefängnis())
        			{
        				alleSpieler.get(aktiverSpieler).increaseRundenImGefängnis();
        			}
        			//Eine String ArrayList, welche alle möglichen Aktionen Beinhaltet, die der Spieler gerade ausführen kann. Wird in der Spieler Klasse durch eine Methode generiert.
        			//Wird nach jeder Ausgeführten Aktion neu generiert, um nicht mehr mögliche Aktionen zu entfernen.
        			
        			//Sollte diese Methode false returnen, hat der Aktive Spieler verloren und wird aus der Spieler arrayList entfernt. Da das nächste element in der ArrayList nun den Index dieses Elementes hat, muss außerdem i um 1 verringert werden
        			boolean spielerAktiv = aktionenAusführen(aktiverSpieler);
        			if(spielerAktiv)
        			{
        				alleSpieler.remove(aktiverSpieler);
        				reihenfolge.remove(i);
        				i--;
        			}
					
					System.out.println("\nSpieler " + aktiverSpieler + " hat seinen Zug beendet.");
					
					// die anderen Spieler können auch bestimmte Aktionen nicht während seines Zugs ausführen
					String eingabe;
					ArrayList<String> nachaktionen;
					int j = (aktiverSpieler + 1) % alleSpieler.size(); // um nicht überzulaufen
					
					while(j != aktiverSpieler)
					{
						nachaktionen = alleSpieler.get(j).möglicheNachaktionen();
						if (nachaktionen.size() > 1) // d.h. nicht nur "Zug beenden"
						{
							System.out.println("\nSpieler " + (j+1) + ", möchtest du jetzt etwas tun? (ja - 1, nein - sonstiges)");
							eingabe = sc.next();
							if (eingabe == "1")
								spielerAktiv = nachaktionenAusführen(j, nachaktionen);
						}

						j = ++j % alleSpieler.size(); // j läuft im Kreis, vom aktiven Spieler bin zum aktiven Spieler
					}
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
		// true, wenn Zug beenden, sonst false
        public static boolean aktionenAusführen(int aktiverSpieler)
        {
        	while(!alleSpieler.get(aktiverSpieler).getHatVerloren())
        	{
        		//Beinhaltet alle möglichen Aktionen die der Spieler ausführen kann
            	ArrayList<String> aktionen = alleSpieler.get(aktiverSpieler).möglicheAktionen();
            	System.out.println("\nWas möchtest du tun?\n");
            	//Gibt aus, welche aktionen der Spieler ausführen kann und welche Zahl er dafür eingeben soll
            	for(int i = 0; i < aktionen.size(); i++)
            	{
            		System.out.println("Zum " + aktionen.get(i) + "gebe " + (i + 1) + " ein.");
            	}
				
				int eingabe = Main.checkCorrectNum(1, aktionen.size());
            	
            	//Hier kann nun die ausgewählte Aktion ausgeführt werden
            	switch(aktionen.get(eingabe  - 1))
            	{
            		case"Würfeln":
            			Würfeln();
            			break;
            		case"Zug beenden":
            			//Setzt die boolean hatGewürfelt zurrück, damit der Spieler in der nächsten Runde würfeln kann
            			alleSpieler.get(aktiverSpieler).setHatGewürfelt(false);
            			return false;
            		case"Aus dem Gefängnis freikaufen (Dies kostet 50 Mark)":
            			alleSpieler.get(aktiverSpieler).subtractGeld(50);
            			alleSpieler.get(aktiverSpieler).ausGefängnis();
            			System.out.println("Du hast 50 Mark bezahlt und bist nun nicht mehr eingesperrt.");
            			break;
            		case"„Du kommst aus dem Gefängnis frei“-Karte verwenden":
            			alleSpieler.get(aktiverSpieler).ausGefängnis();
            			System.out.println("Du hast eine „Du kommst aus dem Gefängnis frei“-Karte verwendet und bist nun nicht mehr eingesperrt.");
            			break;
            		case"Haus bauen":
						alleSpieler.get(aktiverSpieler).HausKaufenVerfahren();
						break;
					case"Häuser verkaufen":
            			alleSpieler.get(aktiverSpieler).HausVerkaufenVerfahren();
            			break;
            		case"Handeln":
						alleSpieler.get(aktiverSpieler).HandelnVerfahren();
            			break;
            		case"Hypothen auf ein Grundstück aufnehmen":
            			alleSpieler.get(aktiverSpieler).hypothekAufnehmen();
            			break;
            		case"Hypotheken abbezahlen":
            			alleSpieler.get(aktiverSpieler).hypothekAbbezahlen();
            			break;
            			
            		//Man sollte warscheinich auch eine Art inventar hinzufügen, wo der Spieler ansehen kann was er alles so hat(Grundstücke, komm aus dem Gefängnis frei Karten, All sein Geld, ob seine Grundstücke Hypotheken haben, usw)
            	}
			}
			
        	//Der Loop kann nur verlassen werden, wenn der Spieler verloren hat. Sollte diese Funktion also false returnen, wird der Aktive Spieler entfernt
        	return true;
		}
		
		//Ermöglicht dem Spieler mägliche Aktionen NICHT während seinem Zug durchführen
		// true, wenn der "während"-Zug beendet ist, sonst false
        public static boolean nachaktionenAusführen(int welcherSpieler, ArrayList<String> nachaktionen)
        {
        	while(!alleSpieler.get(welcherSpieler).getHatVerloren())
        	{
        		System.out.println("\nWas möchtest du tun?\n");
            	//Gibt aus, welche aktionen der Spieler ausführen kann und welche Zahl er dafür eingeben soll
            	for(int i = 0; i < nachaktionen.size(); i++)
            	{
            		System.out.println("Zum " + nachaktionen.get(i) + "gebe " + (i + 1) + " ein.");
            	}
				
				int eingabe = Main.checkCorrectNum(1, nachaktionen.size());
            	
            	//Hier kann nun die ausgewählte Aktion ausgeführt werden
            	switch(nachaktionen.get(eingabe  - 1))
            	{
            		case"Würfeln":
            			Würfeln();
            			break;
            		case"Zug beenden":
            			//Setzt die boolean hatGewürfelt zurrück, damit der Spieler in der nächsten Runde würfeln kann
            			alleSpieler.get(aktiverSpieler).setHatGewürfelt(false);
            			return false;
            		case"Aus dem Gefängnis freikaufen (Dies kostet 50 Mark)":
            			alleSpieler.get(aktiverSpieler).subtractGeld(50);
            			alleSpieler.get(aktiverSpieler).ausGefängnis();
            			System.out.println("Du hast 50 Mark bezahlt und bist nun nicht mehr eingesperrt.");
            			break;
            		case"„Du kommst aus dem Gefängnis frei“-Karte verwenden":
            			alleSpieler.get(aktiverSpieler).ausGefängnis();
            			System.out.println("Du hast eine „Du kommst aus dem Gefängnis frei“-Karte verwendet und bist nun nicht mehr eingesperrt.");
            			break;
            		case"Haus bauen":
						alleSpieler.get(aktiverSpieler).HausKaufenVerfahren();
						break;
					case"Häuser verkaufen":
            			alleSpieler.get(aktiverSpieler).HausVerkaufenVerfahren();
            			break;
            		case"Handeln":
						alleSpieler.get(aktiverSpieler).HandelnVerfahren();
            			break;
            		case"Hypothen auf ein Grundstück aufnehmen":
            			alleSpieler.get(aktiverSpieler).hypothekAufnehmen();
            			break;
            		case"Hypotheken abbezahlen":
            			alleSpieler.get(aktiverSpieler).hypothekAbbezahlen();
            			break;
            	}
			}
			
        	//Der Loop kann nur verlassen werden, wenn der Spieler verloren hat. Sollte diese Funktion also false returnen, wird der Aktive Spieler entfernt
        	return true;
        }
        
        public static void Würfeln()
        {
            int paschAnzahl = 0;
            Random rnd = new Random();
            rnd.setSeed(System.currentTimeMillis());
            //Läuft wenn kein Pasch gewürfelt wurde genau ein mal. Wenn der Spieler einen Pasch würfelt, wird i um 1 verringert, wodurch der for loop nach dem ersten durchlauf nocheinmal
            for(int i = 0; i <= 0; i++)
            {
            	int rndInt1 = rnd.nextInt(5) + 1;
                int rndInt2 = rnd.nextInt(5) + 1;
                
                System.out.println("Du hast eine " + rndInt1 + " und eine " + rndInt2 + " gewürfelt.");
                
            	//ist der Spieler im Gefängnis? Wenn ja, muss er einen Pasch würfeln, um das Gefängnis verlassen zu können
                if(alleSpieler.get(aktiverSpieler).getImGefängnis())
                {
                   //Gefängnis event triggern. Alles andere passiert jetzt erstmal da.
                	if(rndInt1 == rndInt2)
                    {
                        i--;
                      //Wenn der Spieler durch Würfeln eines Pasches aus dem Gefängnis freigekommen ist, wird der Paschcounter erhöht.
                        paschAnzahl++;
                        System.out.println("Das ist ein Pasch, du kommst aus dem Gefängnis frei!");
                        alleSpieler.get(aktiverSpieler).ausGefängnis();
                    }
                }
                else
                {
                	//Sollte der Spieler nicht im Gefängnis sein, darf er würfeln. Hier wird ein If genutzt, da der Spieler aus dem Gefängnis freikommen kann und danach seinen Zug ganz normal fortsetzen kann
                    
                	if(rndInt1 == rndInt2)
                    {
                        i--;
                        paschAnzahl++;
                        System.out.println("Das ist ein Pasch!");
                    } 
				}
				
				if (paschAnzahl == 3)
				{
					i++; // Ins Gefängnis! und das war es dann für den Spieler
					System.out.println("\nDiesmal muss du aber ins Gefängnis gehen!\nDreimal in der gleichen Runde, das ist ungesetzlich!");
					
				}

                if(!alleSpieler.get(aktiverSpieler).getImGefängnis())
                {
                	alleSpieler.get(aktiverSpieler).setPosition(alleSpieler.get(aktiverSpieler).getPosition() + rndInt1 + rndInt2);
                	System.out.println(spielfeld[alleSpieler.get(aktiverSpieler).getPosition()].getFeld());
                	//Triggern der Aktion des Feldes
                	switch(spielfeld[alleSpieler.get(aktiverSpieler).getPosition()].getFeld())
                    {
                    case"Straße":
                    	((Strasse)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler);
                    	break;
                    case"Bahnhof":
                    	((Bahnhof)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler);
                    	break;
                    case"Ereignisfeld":
                    	((Ereignisfeld)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler, (rndInt1 + rndInt2));
                    	break;
                    case"Gemeinschaftsfeld":
                    	((Gemeinschaftsfeld)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler, (rndInt1 + rndInt2));
                    	break;
                    case"Wasserwerk":
                    	((Wasserwerk)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler, (rndInt1 + rndInt2));
                    	break;
                    case"Stromwerk":
                    	((Stromwerk)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler, (rndInt1 + rndInt2));
                    	break;
                    case"Los":
                    	((Start)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler);
                    	break;
                    case"Steuern":
                    	((Steuern)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler);
                    	break;
                    case"Frei Parken":
                    	((FreiParken)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler);
                    	break;
                    case"Gefängnis":
                    	((Gefängnis)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetreten(aktiverSpieler);
                    	break;
                    }
				}
				
				alleSpieler.get(aktiverSpieler).setHatGewürfelt(true);
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