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
		do
		{
			if (grenzeLinks != grenzeRechts)
			{
				if(grenzeLinks != -1)
				{
					if(grenzeRechts != -1)
						System.out.print("\nGib eine Ganzzahl von " + grenzeLinks + " bis " + grenzeRechts + " ein.\n\n-> ");
					else
						System.out.print("\nGib eine Ganzzahl, die größer/gleich " + grenzeLinks + " ist, ein.\n\n-> ");
				}
				else
					System.out.print("\nGib eine Ganzzahl, die kleiner/gleich als " + grenzeRechts + " ist, ein.\n\n-> ");
			}
			else
				System.out.print("\n-> ");

			while (!sc.hasNextInt())
			{
				System.out.println("\nDas ist keine Zahl. Versuch noch einmal.");
				sc.next();
				if (grenzeLinks != grenzeRechts)
				{
					if(grenzeLinks != -1)
					{
						if(grenzeRechts != -1)
							System.out.print("\nGib eine Ganzzahl von " + grenzeLinks + " bis " + grenzeRechts + " ein.\n\n-> ");
						else
							System.out.print("\nGib eine Ganzzahl, die größer/gleich als " + grenzeLinks + " ist, ein.\n\n-> ");
					}
					else
						System.out.print("\nGib eine Ganzzahl, die kleiner/gleich als " + grenzeRechts + " ist, ein.\n\n-> ");
				}
				else
					System.out.print("Gib eine Ganzzahl ein.\n\n-> ");
			}

			eingabe = sc.nextInt();
			if (grenzeLinks != grenzeRechts)
			{
				if(grenzeLinks != -1)
				{
					if(grenzeRechts != -1)
					{
						if(eingabe >= grenzeLinks && eingabe <= grenzeRechts)
							eingabeKorrekt = true;
						else
							System.out.println("\nDie Zahl liegt außerhalb des zugelassenen Bereichs. Versuch noch einmal.");
					}
					else
					{
						if(eingabe >= grenzeLinks)
							eingabeKorrekt = true;
						else
							System.out.println("\nDie Zahl liegt außerhalb des zugelassenen Bereichs. Versuch noch einmal.");
					}
				}
				else
				{
					if(eingabe <= grenzeRechts)
						eingabeKorrekt = true;
					else
						System.out.println("\nDie Zahl liegt außerhalb des zugelassenen Bereichs. Versuch noch einmal.");
				}
			}
			else
			{
				if(grenzeRechts != -1) // grenzeLinks = grenzeRechts = -1 
				{
					if (eingabe != grenzeRechts)
						System.out.println("\nDu kannst nur die Zahl " + grenzeRechts + " eingeben.");
					else
						eingabeKorrekt = true;
				}
				else
					eingabeKorrekt = true;
			}
		} while (!eingabeKorrekt);
		
		return eingabe;
	}

    //Das erstellen eines Main-Objektes in der main Methode, erlaubt die Nutzung von variablen und Objekten in der gesammten Main Klasse
    public Main()
    {
        alleSpieler = new ArrayList<Spieler>();
        //Begrüßung der Spieler. Frage nach der Spieleranzahl
        System.out.println("\nWillkommen zu Monopoly!\n\nWie viele Spieler sind am Spiel beteiligt?");
		//Eingabe der Spieleranzahl
		int spielerAnzahl = checkCorrectNum(2, -1);
        
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
			System.out.println("\n-----------------------");

        	//Figur aussuchen
        	System.out.println("\nWelche Figur möchte der Spieler " + (i+1) + "?\n");
			for(int j = 0; j < figuren.size(); j++)
            {
            		System.out.println("Für die Figur „" + figuren.get(j) + "“ gebe " + (j+1) + " ein.");
			}
			
			welcheFigur = checkCorrectNum(1, figuren.size());
			
        	//Erstellen des Spielers mit der Figur, die gewählt wurde
        	alleSpieler.add(new Spieler(figuren.get(welcheFigur-1).toString(), i));
        	//Entfernen der Figur aus der Arraylist, damit kein anderer Spieler diese auswählen kann
        	figuren.remove(welcheFigur-1);
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
		
		while(alleSpieler.size() > 1)
		{
			//Spielbeginn
			for(int i = 0; i < alleSpieler.size(); i++)
			{
				//Setzen des aktiven Spielers nach der Reihenfolge, welche vorher festgelegt wurde
				aktiverSpieler = reihenfolge.get(i);
				//Ausgeben welcher Spieler am zug ist
				System.out.println("\n-----------------------");
				System.out.println("\nDer Spieler mit der Figur " + alleSpieler.get(aktiverSpieler).getFigur() + ", du bist am Zug.");
				System.out.println("\nDu hast " + alleSpieler.get(aktiverSpieler).getGeld() + " Mark.");
				if(alleSpieler.get(aktiverSpieler).getImGefängnis())
				{
					alleSpieler.get(aktiverSpieler).increaseRundenImGefängnis();
				}
				//Eine String ArrayList, welche alle möglichen Aktionen Beinhaltet, die der Spieler gerade ausführen kann. Wird in der Spieler Klasse durch eine Methode generiert.
				//Wird nach jeder Ausgeführten Aktion neu generiert, um nicht mehr mögliche Aktionen zu entfernen.
				
				//Sollte diese Methode false returnen, hat der aktive Spieler verloren und wird aus der Spieler arrayList entfernt (nach der Nachaktionenausführung).
				boolean spielerAktiv = aktionenAusführen(aktiverSpieler);
				ArrayList<Spieler> verliererImZug = new ArrayList<Spieler>(); // welche Spieler haben in diesem Zug verloren?
				if(!spielerAktiv)
					verliererImZug.add(alleSpieler.get(aktiverSpieler)); // der Aktive hat verloren
				
				// die anderen Spieler können bestimmte Aktionen nicht während ihrer Züge ausführen
				// da wir den aktiven Spieler (wenn er verloren hat) noch nicht entfernt haben, können wir diesen folgenden Code-Abschnitt in den beiden Fällen verwenden
				if(alleSpieler.size() - verliererImZug.size() > 1) // unnötig, wenn ein Gewinner schon festgelegt ist 
				{
					String eingabe;
					ArrayList<String> nachaktionen;
					int j = (aktiverSpieler + 1) % alleSpieler.size(); // um nicht überzulaufen (der nächste Spieler);

					if(spielerAktiv)
						System.out.println("\nDer Spieler mit der Figur " + alleSpieler.get(aktiverSpieler).getFigur() + " hat seinen Zug beendet.");
					else
					{
						System.out.println("\nDer Spieler mit der Figur " + alleSpieler.get(aktiverSpieler).getFigur() + " ist nicht mehr am Spiel beteiligt.");
					}

					System.out.println("\n-----------------------");
					System.out.println("\nZwischenzüge:");

					while(j != aktiverSpieler)
					{
						nachaktionen = alleSpieler.get(j).möglicheNachaktionen();
						if (nachaktionen.size() > 1) // d.h., nicht nur "Zug beenden" ist vorhanden
						{
							System.out.print("\nDer Spieler mit der Figur " + alleSpieler.get(j).getFigur() + ", möchtest du jetzt etwas tun?\n(ja - 1, nein - sonstiges)\n\n-> ");
							eingabe = sc.next();
							if (eingabe.equals("1"))
								spielerAktiv = nachaktionenAusführen(j, nachaktionen);
						}
						if(!spielerAktiv) // wenn der Spieler abgegeben hat
						{
							verliererImZug.add(alleSpieler.get(j));
							System.out.println("\nDer Spieler mit der Figur " + alleSpieler.get(j).getFigur() + " ist nicht mehr am Spiel beteiligt.");
						}

						j = ++j % alleSpieler.size(); // j läuft im Kreis, vom aktiven Spieler bin zum aktiven Spieler nicht einschließlich
					}
				}

				// entfernen die Verlierer
				for(Spieler s : verliererImZug)
				{
					Integer integer = Integer.valueOf(alleSpieler.indexOf(s)); // Krücken (remove() removt nicht int Objekt, sondern einen Index, wenn eine reine Zahl übergeben wird)
					reihenfolge.remove(integer); // in der reihenfolge liegen die Indizes der Spieler im alleSpieler-Array ლ(¯ロ¯"ლ)
					alleSpieler.remove(s); 

					i--; // da das nächste Element in der ArrayList nun den Index dieses Elementes hat, muss außerdem i um 1 verringert werden
				}

				if(alleSpieler.size() == 1)
				{
					System.out.println("\n＼(＾∀＾)メ(＾∀＾)ノ\n\nHerzlichen Glückwunsch an den Spieler mit der Figur " + alleSpieler.get(0).getFigur() + "!!!\n");
					break;
				}
			}
		}
	}
	
	//Ermöglicht dem Spieler alle möglichen eingaben während des Spieles zu machen
	// true, wenn Zug beenden, sonst false
	public static boolean aktionenAusführen(int aktiverSpieler)
	{
		boolean ersteWahl = true; // um den aktuellen Zustand des Zugs dem Nutzer deutlicher zu machen, unterscheiden wir die erste Wahl des Zugs und die Weiteren
		while(!alleSpieler.get(aktiverSpieler).getHatVerloren())
		{
			
			//Beinhaltet alle möglichen Aktionen die der Spieler ausführen kann
			ArrayList<String> aktionen = alleSpieler.get(aktiverSpieler).möglicheAktionen();

			if (ersteWahl)
				System.out.println("\nWas möchtest du tun?\n");
			else
			{
				System.out.println("\n-----------------------");
				System.out.println("\nWas möchtest du noch tun?\n");
			}
			//Gibt aus, welche aktionen der Spieler ausführen kann und welche Zahl er dafür eingeben soll
			for(int i = 0; i < aktionen.size(); i++)
			{
				System.out.println("Zum " + aktionen.get(i) + " gebe " + (i + 1) + " ein.");
			}
			
			int eingabe = Main.checkCorrectNum(1, aktionen.size());
			
			if(!aktionen.get(eingabe-1).equals("Zug beenden"))
				System.out.println("\n-----------------------");

			//Hier kann nun die ausgewählte Aktion ausgeführt werden
			switch(aktionen.get(eingabe-1))
			{
				case"Würfeln":
					Würfeln();
					break;
				case"Zug beenden":
					//Setzt die boolean hatGewürfelt zurrück, damit der Spieler in der nächsten Runde würfeln kann
					alleSpieler.get(aktiverSpieler).setHatGewürfelt(false);
					return true;
				case"Geld anzeigen":
					System.out.println("\nDu hast " + alleSpieler.get(aktiverSpieler).getGeld() + " Mark.");
					break;
				case"Grundstücke anzeigen":
					alleSpieler.get(aktiverSpieler).printBesitzt();
					break;
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
				case"Hypotheken auf ein Grundstück aufnehmen":
					alleSpieler.get(aktiverSpieler).hypothekAufnehmen();
					break;
				case"Hypotheken abbezahlen":
					alleSpieler.get(aktiverSpieler).hypothekAbbezahlen();
					break;
				case"aufgenommene Hypotheken anzeigen":
					alleSpieler.get(aktiverSpieler).hypothekenAnzeigen();
					break;
				case"„Du kommst aus dem Gefängnis frei“-Kartenanzahl anzeigen":
					if(alleSpieler.get(aktiverSpieler).getGefängnisKarte() > 1)
						System.out.println("\nDu hast " + alleSpieler.get(aktiverSpieler).getGefängnisKarte() + " Karten.");
					else
						System.out.println("\nDu hast eine Karte.");
					break;
				case"Spielfeld überblicken":
					feldAusgeben(alleSpieler.get(aktiverSpieler));
					break;
				case"Aufgeben":
					System.out.print("\nBist du sicher?\n(ja - 1, nein - sonstiges)\n\n-> ");
					String eingabeAufgeben = sc.next();
					if (eingabeAufgeben.equals("1"))
						return false; // d.h., der Spieler ist nicht mehr aktiv;
					break;
					
				
				//Man sollte warscheinich auch eine Art inventar hinzufügen, wo der Spieler ansehen kann was er alles so hat(Grundstücke, komm aus dem Gefängnis frei Karten, All sein Geld, ob seine Grundstücke Hypotheken haben, usw)
			}

			ersteWahl = false;
		}
		
		//Der Loop kann nur verlassen werden, wenn der Spieler verloren hat. Sollte diese Funktion also false returnen, wird der Aktive Spieler entfernt
		return false;
	}
	
	//Ermöglicht dem Spieler mägliche Aktionen NICHT während seinem Zug durchführen
	//false - der Spieler nimmt am Spiel nicht mehr teil
	public static boolean nachaktionenAusführen(int welcherSpieler, ArrayList<String> nachaktionen)
	{
		boolean ersteWahl = true; // um den aktuellen Zustand des Zugs dem Nutzer deutlicher zu machen, unterscheiden wir die erste Wahl des Zugs und die Weiteren

		while(true)
		{
			if (ersteWahl)
				System.out.println("\nWas möchtest du tun?\n");
			else
			{
				System.out.println("\n-----------------------");
				System.out.println("\nWas möchtest du noch tun?\n");
			}
			for(int i = 0; i < nachaktionen.size(); i++)
			{
				System.out.println("Zum " + nachaktionen.get(i) + " gebe " + (i + 1) + " ein.");
			}
			
			int eingabe = Main.checkCorrectNum(1, nachaktionen.size());
			
			if (!nachaktionen.get(eingabe-1).equals("Zwischenzug beenden"))
				System.out.println("\n-----------------------");

			switch(nachaktionen.get(eingabe-1))
			{
				case"Zwischenzug beenden":
					return true;
				case"Geld anzeigen":
					System.out.println("\nDu hast " + alleSpieler.get(welcherSpieler).getGeld() + " Mark.");
					break;
				case"Grundstücke anzeigen":
					alleSpieler.get(welcherSpieler).printBesitzt();
					break;	
				case"Haus bauen":
					alleSpieler.get(welcherSpieler).HausKaufenVerfahren();
					break;
				case"Häuser verkaufen":
					alleSpieler.get(welcherSpieler).HausVerkaufenVerfahren();
					break;
				case"Handeln":
					alleSpieler.get(welcherSpieler).HandelnVerfahren();
					break;
				case"Hypotheken auf ein Grundstück aufnehmen":
					alleSpieler.get(welcherSpieler).hypothekAufnehmen();
					break;
				case"Hypotheken abbezahlen":
					alleSpieler.get(welcherSpieler).hypothekAbbezahlen();
					break;
				case"aufgenommene Hypotheken anzeigen":
					alleSpieler.get(welcherSpieler).hypothekenAnzeigen();
					break;
				case"„Du kommst aus dem Gefängnis frei“-Kartenanzahl anzeigen":
					if(alleSpieler.get(welcherSpieler).getGefängnisKarte() > 1)
						System.out.println("\nDu hast " + alleSpieler.get(welcherSpieler).getGefängnisKarte() + " Karten.");
					else
						System.out.println("\nDu hast eine Karte.");
					break;
				case"Spielfeld überblicken":
					feldAusgeben(alleSpieler.get(welcherSpieler));
					break;
				case"Aufgeben":
					System.out.print("\nBist du sicher?\n(ja - 1, nein - sonstiges)\n\n-> ");
					String eingabeAufgeben = sc.next();
					if (eingabeAufgeben.equals("1"))
						return false; // d.h., der Spieler ist nicht mehr aktiv;
					break;
				
			}

			ersteWahl = false;
		}
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
			
			if (paschAnzahl > 0)
			{
				System.out.println("\n-----------------------");
				System.out.println("\nDa du einen Pasch gewürfelt hast, würfelst du noch einmal.");
			}
			System.out.println("\nDu hast eine " + rndInt1 + " und eine " + rndInt2 + " gewürfelt.");

			//ist der Spieler im Gefängnis? Wenn ja, muss er einen Pasch würfeln, um das Gefängnis verlassen zu können
			if(alleSpieler.get(aktiverSpieler).getImGefängnis())
			{
				//Gefängnis event triggern. Alles andere passiert jetzt erstmal da.
				if(rndInt1 == rndInt2)
				{
					i--;
					//Wenn der Spieler durch Würfeln eines Pasches aus dem Gefängnis freigekommen ist, wird der Paschcounter erhöht.
					paschAnzahl++;
					System.out.println("\nDas ist ein Pasch, du kommst aus dem Gefängnis frei!");
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
					System.out.println("\nDas ist ein Pasch!");
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
				case"Stadtwerk":
					((Stadtwerk)spielfeld[alleSpieler.get(aktiverSpieler).getPosition()]).feldBetretenSW(aktiverSpieler, (rndInt1 + rndInt2));
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

		System.out.println("\n-----------------------");
		System.out.println("\nDie Reihenfolge wurde festgelegt.\n");
		for(int i =  0; i < alleSpieler.size(); i++)
		{
			System.out.println("Der Spieler " + (i+1) + " ist der Spieler mit der Figur " + alleSpieler.get(reihenfolge[i]).getFigur() + ".");
		}
		
		return reihenfolge;
	}
	
	public static void feldAusgeben(Spieler welcherSpieler)
	{

		System.out.println("\nDu befindest dich auf dem Feld " + spielfeld[welcherSpieler.getPosition()].getFeldname() + ".\n");

		System.out.println("\nDas Spielfeld:\n");

		//Erstellen eines Arrays der Feldnummern die durch ein mal Würfeln ohne Pasch von der jetzigen Position des Aktiven Spielers erreicht werden können:
		ArrayList<Integer> einMalWürfelnFelder = new ArrayList<Integer>();
		for(int i = 1; i < 13; i ++)
		{
			if((welcherSpieler.getPosition() + i) > 39)
			{
				einMalWürfelnFelder.add((welcherSpieler.getPosition() + i) - 39);
			}
			else
			{
				einMalWürfelnFelder.add(welcherSpieler.getPosition() + i);
			}
		}
		//Ausgeben der Felder die durch einmaliges Würfeln erreicht werden können
		for(int i = 0; i < spielfeld.length; i++)
		{
			for(int j = 0; j < einMalWürfelnFelder.size(); j++)
			{
				//Ist die Feldnummer bei einem Mal würfeln erreichbar?
				if(i == einMalWürfelnFelder.get(j)+1)
				{
					//Bestimmen der Zahl, welche gewürfelt werden muss, um dieses Feld zu erreichen
					int zahl = 0; 
					if(welcherSpieler.getPosition() < einMalWürfelnFelder.get(j))
					{
						zahl =  einMalWürfelnFelder.get(j) - welcherSpieler.getPosition();
					}
					else
					{
						zahl = (welcherSpieler.getPosition() - 39) + einMalWürfelnFelder.get(j);
					}
					System.out.println("Um dieses Feld zu erreichen musst du eine " + (zahl) + " Würfeln.");
				}
			}

			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
			switch(spielfeld[i].getFeld())
			{
			case"Straße":
				System.out.println(((Strasse)spielfeld[i]).toString(welcherSpieler));
				break;
			case"Bahnhof":
				System.out.println(((Bahnhof)spielfeld[i]).toString(welcherSpieler));
				break;
			case"Ereignisfeld":
				System.out.println(((Ereignisfeld)spielfeld[i]).toString());
				break;
			case"Gemeinschaftsfeld":
				System.out.println(((Gemeinschaftsfeld)spielfeld[i]).toString());
				break;
			case"Stadtwerk":
				System.out.println(((Stadtwerk)spielfeld[i]).toStringSW(welcherSpieler));
				break;
			case"Los":
				System.out.println(((Start)spielfeld[i]).toString());
				break;
			case"Steuern":
				System.out.println(((Steuern)spielfeld[i]).toString());
				break;
			case"Frei Parken":
				System.out.println(((FreiParken)spielfeld[i]).toString());
				break;
			case"Gefängnis":
				System.out.println(((Gefängnis)spielfeld[i]).toString());
				break;
			case"Ins Gefängnis":
				System.out.println(((InsGefängnis)spielfeld[i]).toString());
				break;
			}

			boolean nichtAllein = false; // true, wenn auf dem entsprechenden Feld mehr als ein Spieler steht
			for(int j = 0; j <  alleSpieler.size(); j++)
			{
				if(alleSpieler.get(j).getPosition() == i && j != alleSpieler.indexOf(welcherSpieler))
				{
					if(!nichtAllein)
					{
						System.out.print("Auf diesem Feld befinden sich die Spieler mit den Figuren: " + alleSpieler.get(j).getFigur());
						nichtAllein = true;
					}
					else
					{
						System.out.print(", " + alleSpieler.get(j).getFigur());
					}
				}
			}
			if(nichtAllein) // wenn mindestens ein Spieler steht
				System.out.println();
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
	}
}