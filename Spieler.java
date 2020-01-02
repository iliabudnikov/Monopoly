import java.util.ArrayList;
import java.util.Scanner;
 
public class Spieler
{
    // Der Name der Figur, mit der der Spieler spielt
    private String figur;
    
    // Das Geld, dass der Spieler hat (in Mark)
    private int geld;
    
    // Jeder Spieler braucht eine Spielernummer, damit man die Spieler unterscheiden kann
    private int spielernummer;
    
    // Die Positon des Spielers auf dem Feld
    private int position;
    
    // Die grundstuecke die der Spieler gekauft hat. Hier wird die Position des Grundstükes gespeichert. 
    private ArrayList<Integer> grundstuecke;
    
    // Ist dieser Spieler gerade im Gefängnis?
    private boolean imGefängnis = false;
    private int rundenImGefängnis = 0;
    // Die anzahl von "Komm aus dem Gefängnis Frei" Karten, die der Spieler besitzt
    private int kommAusDemGefängnisFreiKarten = 0;
    //Wie Oft der Spieler in einer Runde die Gleiche Zahl gewürfelt hat
    private int paschAnzahl = 0;
    
    //Hat dieser Spieler diese Runde bereits gewürfelt?
    private boolean hatGewürfelt = false;
    
    //Boolean die True wird, wenn der Spieler verloren hat. Er wird daraufhin in Main von alleSpieler entfernt
    private boolean hatVerloren = false;
	
	private static Scanner sc = new Scanner(System.in);

	// Ein neuer Spieler
    public Spieler(String Figur, int Spielernummer)
    {
        grundstuecke = new ArrayList<Integer>();
        spielernummer = Spielernummer;
        this.figur = Figur;
        geld = 1500; // Startgeld
        position = 0; // Los
    }

    // Getter und Setter für alle Instanzvariablen
    public int getSpielernummer()
    {
        return spielernummer;
    }
    
    public int getGeld()
    {
        return geld;
    }
    public void setGeld(int Geld)
    {
        geld = Geld;
    }
    
    public void addGeld(int Geld)
    {
        geld += Geld;
	}
	
    public void subtractGeld(int geld)
    {
    	//Hat der Spieler genug Geld, um den Gewünschten wert abzubezahlen?
    	if(this.geld >= geld)
    	{
    		this.geld -= geld;
    	}
    	else
    	{
    		//Wenn der Spieler nicht genug geld hat, ist er vorrübergehend pleite. Er kann Hypotheken aufnehmen und Häuser verkaufen, um an genug Geld zu kommen. Wenn er danach genug geld hat, kann er bezahlen, sonst ist er raus.
    		int neuGeld = pleite(geld);
    		//Wenn der Spieler nun genug Geld hat, kann er den gewünschten wert abbezahlen
    		if(neuGeld >= geld)
    		{
    			this.geld -= geld;
    		}
    		//Wenn der Spieler aufgegeben hat, hat er verloren. In diesem Fall wird sein gesamter Besitz versteigert
    		else
    		{
    			
    		}
    	}
    }
    
    //Wenn einer der Spieler einem anderen Geld geben muss
    public void paySpieler(int welcherSpieler, int schulden)
    {
    	//Hat der Spieler genug geld, um seine Schuld\en bei dem Anderen Spieler abzubezahlen?
    	if(geld >= schulden)
    	{
    		subtractGeld(schulden);
    		Main.alleSpieler.get(welcherSpieler).addGeld(schulden);
    	}
    	//Wenn der Spieler nicht genug geld hat, ist er vorrübergehend pleite. Er kann Hypotheken aufnehmen und Häuser verkaufen, um an genug Geld zu kommen. Wenn er danach genug geld hat, kann er bezahlen, sonst ist er raus.
    	else
    	{
    		int neuGeld = pleite(schulden);
    		//Wenn der Spieler seine Schulden begleichen kann, werden ihm seine schulden abgezogen und dem anderen Spieler gegeben
    		if(neuGeld >= schulden)
    		{
    			subtractGeld(schulden);
    			Main.alleSpieler.get(welcherSpieler).addGeld(schulden);
    		}
    		//Wenn der Spieler aufgegeben hat, bekommt der andere Spieler den gesamten Besitz des Schuldners
    		else
    		{
    			//Der Aktive Spieler hat verloren
    			hatVerloren = true;
    			//Übergeben der Grundstücke
    			for(int i = 0; i < grundstuecke.size(); i++)
    			{
    				Main.alleSpieler.get(welcherSpieler).addGrundstück(i);
    			}
    			//Übergeben des restlichen geldes
    			Main.alleSpieler.get(welcherSpieler).addGeld(geld);
    			//Übergeben der KommeAusDemGefängisFrei karten
    			for(int i = 0; i < kommAusDemGefängnisFreiKarten; i++)
    			{
    				Main.alleSpieler.get(welcherSpieler).addGefängnisKarte();
    			}
    		}
    	}
    }
    
    public boolean getHatVerloren() {
		return hatVerloren;
	}


	public void setHatVerloren(boolean hatVerloren) {
		this.hatVerloren = hatVerloren;
	}


	public int getPosition()
    {
        return position;
    }
    public void setPosition(int position)
    {
        if(position < 0)
        {
            position += 39;
        }
        if(position > 39)
        {
            position -= 39;
        }
        
        this.position = position;
    }
    
    public boolean getImGefängnis()
    {
        return imGefängnis;
    }
    public void setImGefängnis()
    {
        imGefängnis = true;
        position = 10;
        rundenImGefängnis = 0;
    }
    public void ausGefängnis()
    {
    	imGefängnis = false;
    	rundenImGefängnis = 0;
    }
    
    public int getRundenImGefängnis()
    {
        return rundenImGefängnis;
    }
    public void increaseRundenImGefängnis()
    {
        rundenImGefängnis++;
    }
        public int getGefängnisKarte()
    {
        return kommAusDemGefängnisFreiKarten;
    }
    public void addGefängnisKarte()
    {
        kommAusDemGefängnisFreiKarten++;
    }
    public void subtractGefängnisKarte()
    {
        kommAusDemGefängnisFreiKarten--;
    }
    public int getPasch()
    {
    	return paschAnzahl;
    }
    public void addPasch()
    {
    	paschAnzahl++;
    }
    public void setPasch(int wert)
    {
    	paschAnzahl = wert;
    }
    public String getFigur()
    {
    	return figur;
    }
    
    public boolean getHatGewürfelt() 
    {
		return hatGewürfelt;
	}


	public void setHatGewürfelt(boolean hatGewürfelt) 
	{
		this.hatGewürfelt = hatGewürfelt;
	}
    
    public int[] getGrundstuecke()
    {
        int[] ausgabe = new int[grundstuecke.size()];
        for(int i = 0; i < ausgabe.length; i++)
        {
            ausgabe[i] = grundstuecke.get(i);
        }
        return ausgabe;
    }
    public ArrayList<Integer> getGrundstücke()
    {
    	return grundstuecke;
    }
    public void addGrundstück(int position)
    {
        grundstuecke.add(position);
    }
    public void removeGrundstück(int position)
    {
        int i = 0;
        while (i < grundstuecke.size() && grundstuecke.get(i) != position)
            i++;
        
        grundstuecke.remove(i);
    }

    //Gibt alles aus, was der Spieler besitzt
    public void printBesitzt()
    {
		
    	System.out.println("\nDu besitzt folgende Grundstücke:\n");
    	for(int i = 0; i < grundstuecke.size(); i++)
    	{
    		System.out.println(i + ". " + Main.spielfeld[grundstuecke.get(i)].getFeldname() + ". ");
    		if(Main.spielfeld[grundstuecke.get(i)].getFeld().equals("Straße"))
    		{
    			if(!(((Strasse)Main.spielfeld[grundstuecke.get(i)]).getHypothek()))
    			{
					if(((Strasse)Main.spielfeld[grundstuecke.get(i)]).getHausAnzahl() == 0)
						System.out.println("Diese Straße hat die Farbe " + ((Strasse)Main.spielfeld[grundstuecke.get(i)]).getFarbe() + " und keine Häuser.");
					else if(((Strasse)Main.spielfeld[grundstuecke.get(i)]).getHausAnzahl() != 4)
    					System.out.println("Diese Straße hat die Farbe " + ((Strasse)Main.spielfeld[grundstuecke.get(i)]).getFarbe() + " und " + ((Strasse)Main.spielfeld[grundstuecke.get(i)]).getHausAnzahl() + " Häusern.");
					else
						System.out.println("Diese Straße hat die Farbe " + ((Strasse)Main.spielfeld[grundstuecke.get(i)]).getFarbe() + " und ein Hotel.");
				}
    			else
    			{
    				System.out.println("Diese Straße hat die Farbe " + ((Strasse)Main.spielfeld[grundstuecke.get(i)]).getFarbe() + " und auf sie ist eine Hypothek angemeldet.");
    			}
			}
		}
    }

	// -- Verfahren mit Häusern --
	// sagt, ob der Spieler alle Straße in der Farbe hat
	public boolean sayIfAlleFarben(String farbe)
	{
		int strassenAnzahl = 1; // die Anzahl von Straßen mit gleichen Farben
		for(int i : grundstuecke)
		{
			if (Main.spielfeld[i].getFeld() == "Strasse" && ((Strasse)((Grundstueck)Main.spielfeld[i])).getFarbe() == farbe && !((Grundstueck)Main.spielfeld[i]).getHypothek())
				strassenAnzahl += 1; // wenn eine Straße gefunden ist und ihre Farbe passend ist
		}
		
		if ((farbe == "Lila" || farbe == "Blau") && strassenAnzahl == 2)
			return true;
		else if (strassenAnzahl == 3)
			return true;

		return false;
	}

	// überprufen, ob der Spieler genug Geld hat, mindestens ein Haus zu bauen
	public boolean sayIfHatGenugGeld(int strassennummer) {
		int minHausanzahl = 5;
		for (int i : grundstuecke) // genug Geld für die Hausbauung (bauen dort, wo min Häuse stehen) 
		{
			if (((Strasse)Main.spielfeld[i]).getFarbe() == ((Strasse)Main.spielfeld[strassennummer]).getFarbe() && ((Strasse)Main.spielfeld[i]).getHausAnzahl() < minHausanzahl)
				minHausanzahl = ((Strasse)Main.spielfeld[i]).getHausAnzahl();
		}

		if (minHausanzahl < 4) // genug Geld für ein Haus
			if (((Strasse)Main.spielfeld[strassennummer]).getHausPreis() <= getGeld())
				return true;
		else // genug Geld für ein Hotel
			if (((Strasse)Main.spielfeld[strassennummer]).getHausPreis() * 5 <= getGeld())
				return true;
		
		return false;
	}

	// bauen ein Haus?
	public boolean sayIfCanHaus()
	{
		ArrayList<String> farben = new ArrayList<String>(); // um dieselben Farben mehrmals nicht zu prüfen
		for(int i : grundstuecke) // grundstuecke.size()-1, da das letzte Grundstück schon sinnlos zu prüfen ist
		{
			if (Main.spielfeld[i].getFeld() == "Strasse" && !farben.contains(((Strasse)Main.spielfeld[i]).getFarbe()))
			// wenn das Grundstück eine Straße ist und die Farbe wir noch nicht geprüft haben
			{
				if (sayIfAlleFarben(((Strasse)Main.spielfeld[i]).getFarbe()))
					if (sayIfHatGenugGeld(i)) // untersuchen auf dieser Weise alle Farben (Straßen, wo man Häuser bauen kann)
						return true;
				
				farben.add(((Strasse)Main.spielfeld[i]).getFarbe());
			}
		}
		
		return false;
	}

	// gibt alle Straßen, wo ein Haus gebaut werden kann
	public ArrayList<Integer> sayWhereCanHaus()
	{
		ArrayList<Integer> strassen = new ArrayList<Integer>();
		String [] farben = {"Lila", "Türkis", "Violett", "Orange", "Rot", "Geld", "Grün", "Blau"};
		boolean koennenWeiter = false; // sagt, ob für die Farbe alle Straße schon gefunden ist und wir weiter gehen können
		int hausanzahl; // wie viele Häuser auf jeder Straße der Farbe stehen
		int hauspreis; // Preis eines Hauses, das gebaut werden könnte
		
		for(String i : farben)
		{
			if(sayIfAlleFarben(i)) { // wenn ein Haus auf den Straßen dieser Farbe überhaupt möglich ist
				koennenWeiter = false;
				hausanzahl = 1; // wenn weniger als 1 Haus, dann können bauen usw für 2 Hдuser...
				while(!koennenWeiter && hausanzahl < 6) // < 5? => ein Hotel gebaut werden kann!
				{
					for(int j : grundstuecke)
					{
						if(Main.spielfeld[j].getFeld() == "Strasse" && ((Strasse)Main.spielfeld[j]).getFarbe() == i)
						{
							if(((Strasse)Main.spielfeld[j]).getHausAnzahl() < hausanzahl)
							{
								koennenWeiter = true; // können hier ein Haus bauen

								// aber hat der Spieler genug Geld dafür?
								if(((Strasse)Main.spielfeld[j]).getHausAnzahl() == 4)
									hauspreis = ((Strasse)Main.spielfeld[j]).getHausPreis() * 5;
								else
									hauspreis = ((Strasse)Main.spielfeld[j]).getHausPreis() * 4;
									
								if(hauspreis <= getGeld());
									strassen.add(j);
							}
						}
					}
					
					if (!koennenWeiter)
						hausanzahl++;
				}
			}
		}
		
		return strassen;
	}

	//Hat der Spieler mindestens ein Haus, welches er verkaufen kann?
	public boolean sayIfCanHausVerkaufen()
	{
		for(int i : grundstuecke)
		{
			//Nur Strassen können häuser haben, weswegen hier nur die Elemente von grundstuecke betrachtet werden, die Strassen sind
			if(Main.spielfeld[i].getFeld() == "Strasse")
			{
				//Ist auf einer Strasse des Spielers mindestens ein Haus?
				if(((Strasse)Main.spielfeld[i]).getHausAnzahl() > 0)
				{
					return true;
				}
			}
		}
		return false;
	}

	public void hausKaufen(int strassennummer)
	{
		((Strasse)Main.spielfeld[strassennummer]).setHausAnzahl(((Strasse)Main.spielfeld[strassennummer]).getHausAnzahl()+1);

		if (((Strasse)Main.spielfeld[strassennummer]).getHausAnzahl() == 5)
		{
			subtractGeld(((Strasse)Main.spielfeld[strassennummer]).getHausPreis() * 5); // Ein Hotel kostet 4 Häuser + 1 Haus
			System.out.println("\nDu hast jetzt ein Hotel auf der " + ((Strasse)Main.spielfeld[strassennummer]).getFeldname() + "!");
		}
		else
		{
			System.out.println("\nDu hast jetzt " + ((Strasse)Main.spielfeld[strassennummer]).getHausAnzahl() + " Häuser auf der " + ((Strasse)Main.spielfeld[strassennummer]).getFeldname() + ".");	
		}
	}

	public void hausVerkaufen(int strassennummer)
	{
		if (((Strasse)Main.spielfeld[strassennummer]).getHausAnzahl() == 5)
		{
			setGeld(getGeld() + (((Strasse)Main.spielfeld[strassennummer]).getHausPreis() * 5 / 2)); // Ein Hotel kostet 4 Häuser + 1 Haus
			System.out.println("\nDu hast ein Hotel auf " + Main.spielfeld[strassennummer].getFeldname() + " für " + ((Strasse)(Main.spielfeld[strassennummer])).getHausPreis() + " Mark verkauft.\nDu hast nun " + geld + " Mark");
		}
		else
		{
			setGeld(getGeld() + ((Strasse)Main.spielfeld[strassennummer]).getHausPreis() / 2);
			System.out.println("\nDu hast ein Haus auf " + Main.spielfeld[strassennummer].getFeldname() + " für " + ((Strasse)(Main.spielfeld[strassennummer])).getHausPreis() + " Mark verkauft.\nDu hast nun " + geld + " Mark");
		}
	}

	public void HausKaufenVerfahren()
	{
		System.out.println("\nDie möglichen Straßen für die Bebauung:");
		int [] strassen = new int[sayWhereCanHaus().size()];
		for(int i = 0; i < strassen.length; i++)
		{
			strassen[i] = sayWhereCanHaus().get(i);
		}

		for (int i = 0; i <  strassen.length; i++)
		{	// Zählen alle erlaubten Straßen auf
			System.out.println((i+1) + ". ");
			((Strasse)Main.spielfeld[strassen[i]]).toString(true);
		}
						
		System.out.println("\nWähle eine Straße aus der Liste.");
		int eingabe = Main.checkCorrectNum(1, strassen.length); // zähler erhöht sich noch mal vor dem Ausgang

		hausKaufen(strassen[eingabe-1]);
	}

	public void HausVerkaufenVerfahren()
    {
    	ArrayList<Integer> grundstückeMitHaus = new ArrayList<Integer>();
    	//Hier werden die Grundstücke, welche Häuser haben zu der ArrayList hinzugefügt.
    	for(int i : grundstuecke)
    	{
    		//Wenn das Grundstück ein Haus hat, wird es zur grundstückMitHaus ArrayList hinzugefügt
    		if(((Strasse)Main.spielfeld[i]).hasHaus())
    		{
    			grundstückeMitHaus.add(i);
    		}
    	}
		
		//Jetzt lassen wir nur diejenigen Straßen, wo die Häuser verkauft werden können

    	//Häuser dürfen nur gleichmäßig verkauft werden. z.B. : Spieler Besitzt alle gelben Grundstücke, Hat auf Lessingstraße und Schillerstraße ein Haus und auf der Goethestraße zwei Häuser. Er müsste erst das Haus auf der Goethestraße verkaufen, bevor er die von den anderen Straßen verkaufen kann.
    	for(int i = 0; i < grundstückeMitHaus.size(); i++)
    	{
    		for(int j = i; j < grundstückeMitHaus.size(); j++)
    		{
    			//Hat der Spieler mehrere Grundstücke mit der selben Farbe und mindestens einem Haus? und: Ist die Anzahl der Häuser unterschiedlich?
    			if(((Strasse)(Main.spielfeld[grundstückeMitHaus.get(i)])).getFarbe() == ((Strasse)(Main.spielfeld[grundstückeMitHaus.get(j)])).getFarbe() && ((Strasse)(Main.spielfeld[grundstückeMitHaus.get(i)])).getHausAnzahl() != ((Strasse)(Main.spielfeld[grundstückeMitHaus.get(j)])).getHausAnzahl())
    			{
    				//Das Grundstück mit weniger Häusern wird aus der liste entfernt
    				if(((Strasse)(Main.spielfeld[grundstückeMitHaus.get(i)])).getHausAnzahl() > ((Strasse)(Main.spielfeld[grundstückeMitHaus.get(j)])).getHausAnzahl())
    				{
    					grundstückeMitHaus.remove(j);
    				}
    				else if(((Strasse)(Main.spielfeld[grundstückeMitHaus.get(i)])).getHausAnzahl() < ((Strasse)(Main.spielfeld[grundstückeMitHaus.get(j)])).getHausAnzahl())
    				{
    					grundstückeMitHaus.remove(i);
    				}
    			}
    		}
    	}
    	
    	
    	System.out.println("\nDu hast " + geld + " Mark.");
    	//Ausgeben von allen Grundstücken die mindestens ein Haus haben, deren Farbe, deren Hausanzahl und wie viel Geld der Spieler für das verkaufen eines Hauses bekommt
    	for(int i = 0; i < grundstückeMitHaus.size(); i++)
    	{
    		System.out.println("\nFür die Straße " + Main.spielfeld[grundstückeMitHaus.get(i)].getFeldname() + " mit der Farbe " + ((Strasse)(Main.spielfeld[grundstückeMitHaus.get(i)])).getFarbe() + " und " + ((Strasse)(Main.spielfeld[grundstückeMitHaus.get(i)])).getHausAnzahl() + " Häusern mit dem Verkauspreis von " + ((Strasse)(Main.spielfeld[grundstückeMitHaus.get(i)])).getHausPreis() + " Mark, gebe " + (i+1) + " ein." );
    	}
    	
    	int eingabe = Main.checkCorrectNum(1, grundstückeMitHaus.size());
    	
    	//Tatsächliches Verkaufen von dem ausgewählten Haus
    	((Strasse)(Main.spielfeld[grundstückeMitHaus.get(eingabe-1)])).setHausAnzahl(((Strasse)(Main.spielfeld[grundstückeMitHaus.get(eingabe-1)])).getHausAnzahl() - 1);
		hausVerkaufen(grundstückeMitHaus.get(eingabe-1));
	}
	// -- Verfahren mit Häusern --
	
	// -- Hypotheke --
    //Kann der Spieler auf ein Gundstück eine Hypothek aufnehmen?
    public boolean sayIfCanHypothek()
    {
    	for(int i : grundstuecke)
    	{
    		if(i != 0)
    		{
        		//Hat dieses Grundstück schon eine Hypothek aufgenommen? Falls ja, kann keine weitere aufgenommen und das nächste Element wird betrachtet
        		if(!((Grundstueck)Main.spielfeld[i]).getHypothek())
        		{
        			//Für Strassen können nur Hypotheken aufgenommen werden, wenn kein Haus auf ihnen steht
            		if(Main.spielfeld[i].getFeld() == "Strasse")
            		{
            			if(((Strasse)Main.spielfeld[i]).getHausAnzahl() == 0)
                			return true;
            		}
            		//Falls das Grundstück keine Strasse ist, muss die Anzahl der Häuser nicht betrachtet werden. Solange nicht bereits eine Hypothek besteht, kann eine Hypothek aufgenommen werden
            		else
            			return true;
        		}
    		}
		}
		
    	return false;
	}
	

	//Hat der Spieler ein Grundstück mit einer aufgenommenen Hypothek?
	public boolean sayIfHatHypothek()
	{
		for(int i : grundstuecke)
    	{
    		if(((Grundstueck)Main.spielfeld[i]).getHypothek())
    		{
				return true;
			}
		}

		return false;
	}

    //Kann der Spieler mindestens eine seiner Hypotheken abbezahlen? Falls ja, true, falls nein, false
    public boolean sayIfCanHypothekAbbezahlen()
    {
    	for(int i : grundstuecke)
    	{
    		if(((Grundstueck)Main.spielfeld[i]).getHypothek())
    		{
    			if(geld >= ((Grundstueck)Main.spielfeld[i]).getHypothekPreis())
    				return true;
    		}
		}
		
    	return false;
	}
	
	public void hypothekAbbezahlen()
    { 
    	//ArrayList, welche den Index aller Grundstücke enthält, bei welchen hypothek = true ist 
    	ArrayList<Integer> alleHypotheken = new ArrayList<Integer>();
    	for(int i : grundstuecke)
    	{
    		//Wenn eines der Grundstücke des Arrays mit den Grundstücken des Spielers eine Hypothek hat, wird der Index dieses zu alleHypotheken hinzugefügt
    		if(((Grundstueck)Main.spielfeld[i]).getHypothek())
    		{
    			alleHypotheken.add(i);
    		}
    	}
    	
    	System.out.println("\nWelche deiner Hypotheken willst du abbezahlen?\nWenn du dir das Abbezahlen einer Hypothek nicht leisten kannst, steht sie nicht zur Auswahl.\nDu hast gerade " + getGeld() + " Mark");
    	
    	for(int i = 0; i < alleHypotheken.size(); i++)
    	{
    		//Entfernen von Indexen, deren Grundstücke einen Höheren Hypothekspreis haben, als der Spieler bezahlen könnte
    		if(((Grundstueck)Main.spielfeld[alleHypotheken.get(i)]).getHypothekPreis() > getGeld())
    		{
    			//Ausgeben vom Hypothekspreis und dem dazugehörigen Grundstücksnamen
    			System.out.println("\nFür das Grundstück" + Main.spielfeld[alleHypotheken.get(i)].getFeldname() + " kannst du dir die Hypothek von " + ((Grundstueck)Main.spielfeld[alleHypotheken.get(i)]).getHypothekPreis() + " Mark nicht leisten");
    			alleHypotheken.remove(i);
    			//Da dieses Element nun entfernt wurde, ist alleHypotheken.get(i) ein anderes element als vorher. Daher wird der Loop für diesen Wert von i erneut ausgeführt
    			i--;
    		}
    		System.out.println("\nFür das Grundstück" + Main.spielfeld[alleHypotheken.get(i)].getFeldname() + " mit einer Hypothek von " + ((Grundstueck)Main.spielfeld[alleHypotheken.get(i)]).getHypothekPreis() + " Mark, gebe " + (i+1) + " ein.");
    	}
    	
    	int eingabe = Main.checkCorrectNum(1, alleHypotheken.size());
    	
    	//Abbezahlen der Hypothek, die der Spieler ausgewählt hat
    	((Grundstueck)Main.spielfeld[alleHypotheken.get(eingabe-1)]).hypothekAbbezahlen();
    }
    
    public void hypothekAufnehmen()
    {
    	ArrayList<Integer> erlaubteGrundstücke = new ArrayList<Integer>();
    	for(int i : grundstuecke)
    	{
    		//Hat dieses Grundstück schon eine Hypothek aufgenommen? Falls ja, kann keine weitere aufgenommen und das nächste Element wird betrachtet
    		if(!((Grundstueck)Main.spielfeld[i]).getHypothek())
    		{
    			//Für Strassen können nur Hypotheken aufgenommen werden, wenn kein Haus auf ihnen steht
        		if(Main.spielfeld[i].getFeld() == "Strasse")
        		{
        			if(((Strasse)Main.spielfeld[i]).getHausAnzahl() == 0)
            		{
        				erlaubteGrundstücke.add(i);
            		}
        		}
        		//Falls das Grundstück keine Strasse ist, muss die Anzahl der Häuser nicht betrachtet werden. Solange nicht bereits eine Hypothek besteht, kann eine Hypothek aufgenommen werden
        		else
        		{
        			erlaubteGrundstücke.add(i);
        		}
    		}
		}
    	
    	//Gibt die Grundstücke aus, auf die der Spieler eine Hypothek aufnehmen kann
    	System.out.println("\nFür welches Grundstück möchtest du eine Hypothek aufnehmen?\nDu hast gerade " + getGeld() + " Mark");
    	for(int i = 0; i < erlaubteGrundstücke.size(); i++)
    	{
    		System.out.println("\nFür " + ((Grundstueck)Main.spielfeld[erlaubteGrundstücke.get(i)]).getFeldname() + " gebe " + (i + 1) + " ein");
    		System.out.println("\nFür dieses Grundstück bekommst du " + ((Grundstueck)Main.spielfeld[erlaubteGrundstücke.get(i)]).getPreis()*0.5 + " Mark");
    	}
		
		int eingabe = Main.checkCorrectNum(1, erlaubteGrundstücke.size());
    	
    	((Grundstueck)Main.spielfeld[erlaubteGrundstücke.get(eingabe-1)]).hypothekAufnehmen();
	}

	public void hypothekenAnzeigen()
	{
		//ArrayList, welche den Index aller Grundstücke enthält, bei welchen hypothek = true ist 
		ArrayList<Integer> alleHypotheken = new ArrayList<Integer>();
		for(int i : grundstuecke)
		{
			//Wenn eines der Grundstücke des Arrays mit den Grundstücken des Spielers eine Hypothek hat, wird der Index dieses zu alleHypotheken hinzugefügt
			if(((Grundstueck)Main.spielfeld[i]).getHypothek())
			{
				alleHypotheken.add(i);
			}
		}

		System.out.println("\nInsgesamt hast du jetzt " + alleHypotheken.size() + " unabberzahlene Hypotheken.");

		System.out.println("\nHier sind die Grundstücke mit den Hypotheken:\n");
		for(int i = 0; i < alleHypotheken.size(); i++)
		{
			System.out.println(i + ". " + Main.spielfeld[grundstuecke.get(i)].getFeldname());
		}
	}
	// -- Hypotheke --
    
    //Gibt eine ArrayList mit allen möglichen Eingaben, die der Spieler gerade machen kann aus
    public ArrayList<String> möglicheAktionen()
    {
    	ArrayList<String> ausgabe = new ArrayList<String>();

    	//Wenn der Spieler noch nicht gewürfelt hat, kann er würfeln
    	if(!hatGewürfelt && rundenImGefängnis < 3)
    	{
    		ausgabe.add("Würfeln");
		}
		
    	//Wenn der Spieler gewürftelt hat, kann er seinen Zug beenden
    	if(hatGewürfelt)
    	{
    		ausgabe.add("Zug beenden");
		}
		
    	//Wenn der Spieler im Gefängis ist, kann der Spieler sich aus dem Gefängis freikaufen
    	if(imGefängnis)
    	{
    		if(!hatGewürfelt)
    		{
    			if(geld >= 50)
    			{
    				if(rundenImGefängnis == 3)
    				{
    					System.out.println("\nDu bist bereits 3 Runden im Gefängnis, du musst 50 Geld bezahlen");
    				}
    				ausgabe.add("Aus dem Gefängnis freikaufen (Dies kostet 50 Mark)");
    			}
        		//Wenn der Spieler im Gefängnis ist und mindestens eine "Komme aus dem Gefängnis frei" karte besitzt, wird diese zu benutzen als Aktion hinzugefügt.
        		if(getGefängnisKarte() > 0 && !(rundenImGefängnis == 3))
        		{
        			ausgabe.add("„Du kommst aus dem Gefängnis frei“-Karte verwenden");
        		}
    		}
    	}
		
		ausgabe.add("Geld anzeigen");

		//Hat der Spieler schon Grundstücke?
		if(!grundstuecke.isEmpty())
		{
			ausgabe.add("Grundstücke anzeigen");
		}

    	//Der Spieler kann wenn er am Zug ist jederzeit Häuser bauen. Er kann jedoch nur Häuser bauen, wenn er alle Straßen einer Farbe besitzt, und genug Geld hat.
    	if(sayIfCanHaus())
    	{
    		ausgabe.add("Haus bauen");
    	}
    	
    	//Hat der Spieler mindestens ein Haus, welches er verkaufen kann?
    	if(sayIfCanHausVerkaufen())
    	{
    		ausgabe.add("Häuser verkaufen");
    	}
    	//Der Spieler kann wenn er am Zug ist jederzeit handeln. Hier gibt es keine Beschränkung, weswegen dies Immmer in der Liste ist.
    	if(sayIfCanHausVerkaufen())
    	{
    		ausgabe.add("Häuser verkaufen");
		}

		// Hat der Speiler eine Hypothek?
    	if(sayIfHatHypothek())
		{
			ausgabe.add("aufgenommene Hypotheken anzeigen");
		}

    	//Hat der Spieler mindestens ein Grundstück, auf das er eine Hypothek aufnehmen kann?
    	if(sayIfCanHypothek())
    	{
    		ausgabe.add("Hypotheken auf ein Grundstück aufnehmen");
    	}
    	
    	//Hat der Spieler mindestens eine Hypothek und genug Geld um diese Abzubezahlen?
    	if(sayIfCanHypothekAbbezahlen())
    	{
    		ausgabe.add("Hypotheken abbezahlen");
		}

		if(getGefängnisKarte() > 0)
    	{
    		ausgabe.add("„Du kommst aus dem Gefängnis frei“-Kartenanzahl anzeigen");
    	}
		
    	ausgabe.add("Spielfeld überblicken");
		ausgabe.add("Abgeben");

    	return ausgabe;
	}
	
	// ein Spieler darf während der Züge der Anderen (nach dem Zug seines Mitspielers) einige Aktionen ausführen
	// die Methode gibt also eine ArrayList mit allen möglichen Eingaben, die der Spieler NICHT während seines Zugs machen kann aus
    public ArrayList<String> möglicheNachaktionen()
    {
    	ArrayList<String> ausgabe = new ArrayList<String>();
    	
		ausgabe.add("Zwischenzug beenden");
		
		ausgabe.add("Geld anzeigen");

		//Hat der Spieler schon Grundstücke?
		if(!grundstuecke.isEmpty())
		{
			ausgabe.add("Grundstücke anzeigen");
		}

		//Der Spieler kann, wenn er nicht an seinem Zug ist, Häuser bauen.
		if(sayIfCanHaus())
    	{
    		ausgabe.add("Haus bauen");
    	}
    	ausgabe.add("Besitz anzeigen");
    	//Hat der Spieler mindestens ein Haus, welches er verkaufen kann?
    	if(sayIfCanHausVerkaufen())
    	{
    		ausgabe.add("Häuser verkaufen");
		}
		
		if (sayIfCanHandeln())
		{
			ausgabe.add("Handeln");
		}

		// Hat der Speiler eine Hypothek?
    	if(sayIfHatHypothek())
		{
			ausgabe.add("aufgenommene Hypotheken anzeigen");
		}

		if(sayIfCanHypothek())
    	{
    		ausgabe.add("Hypotheken auf ein Grundstück aufnehmen");
    	}
    	
    	//Hat der Spieler mindestens eine Hypothek und genug Geld um diese Abzubezahlen?
    	if(sayIfCanHypothek() && sayIfCanHypothekAbbezahlen())
    	{
    		ausgabe.add("Hypotheken abbezahlen");
    	}
		
		if(getGefängnisKarte() > 0)
    	{
    		ausgabe.add("„Du kommst aus dem Gefängnis frei“-Kartenanzahl anzeigen");
    	}
		
    	ausgabe.add("Spielfeld überblicken");
		ausgabe.add("Abgeben");

    	return ausgabe;
    }
    
    //Wenn der Spieler kein Geld mehr hat, kann er Hypotheken aufnehmen und Häuser verkaufen um Geld zu machen
    public int pleite(int schulden)
    {
    	System.out.println("\nDu hast Schulden, die du nicht abbezahlen kannst. Du kannst Hypotheken aufnehmen oder Häuser verkaufen um Geld zu machen.");
    	//Solange der Spieler seine Schulden nicht abbezahlen kann, wird dies Wiederholt
    	while(schulden - geld > 0)
    	{
    		System.out.println("\nDu hast " + geld + " Mark, und du musst " + schulden + " Mark bezahlen. \nDir Fehlen also noch " + (schulden - geld) + " Mark.");
        	ArrayList<String> ausgabe = new ArrayList<String>();
        	if(sayIfCanHausVerkaufen())
        	{
        		ausgabe.add("Haus verkaufen");
        	}
        	if(sayIfCanHypothek())
        	{
        		ausgabe.add("Hypothek aufnehmen");
        	}
        	ausgabe.add("Aufgeben");
        	
        	for(int i = 0; i < ausgabe.size(); i++)
        	{
        		System.out.println("Zum " + ausgabe.get(i) + " gebe " + (i+1) + " ein.");
        	}
        	
			int eingabe = Main.checkCorrectNum(1, ausgabe.size());
            switch(ausgabe.get(eingabe-1))
            {
            	case"Haus verkaufen":
            		HausVerkaufenVerfahren();
            		break;
            	case"Hypothek aufnehmen": 
            		hypothekAufnehmen();
            		break;
            	case"Aufgeben":
            		return geld;
            }  
		}
		
    	return schulden;
    }

	// gibt alle Straßen, die getauscht werden können
	public ArrayList<Integer> sayWhatCanTauschen()
	{
		ArrayList<Integer> strassen = new ArrayList<Integer>();
		for(int i : grundstuecke)
			if(Main.spielfeld[i].getFeld() == "Strasse" && ((Strasse)Main.spielfeld[i]).getHausAnzahl() == 0)
				strassen.add(i);
		return strassen;
	}

	// -- Handeln --
	public boolean sayIfCanHandeln()
	{
		for(int i : grundstuecke) // wenn der Spieler unbebaute Grundstücke ohne Hypothek hat
		{
			if(!((Grundstueck)Main.spielfeld[i]).getHypothek())
			{   // wenn das Grundstück eine Straße ist, dann muss Sie unbebaut sein
				if (Main.spielfeld[i].getFeld() == "Strasse")
				{	
					if (((Strasse)((Grundstueck)Main.spielfeld[i])).getHausAnzahl() == 0)
						return true;
				}
				else // ansonsten kann das Grundstück nicht bebaut werden
					return true;
			}
		}

		if(kommAusDemGefängnisFreiKarten > 0) // wenn der Spieler mind eine „Du kommst aus dem Gefängnis frei“-Karte hat
				return true;
			
		return false;
	}



	public void gefaengnisFreiVerkaufen(Spieler welcherSpieler)
	{
		// !!!
	}

	public void grundstueckVerkaufen(Spieler welcherSpieler)
	{
		// !!!
	}



	public void HandelnVerfahren()
	{
		System.out.println("\nMit wem möchtest du handeln?");
		ArrayList<Integer> möglicheHandelsPatner = new ArrayList<Integer>();
		for(int i = 0; i < Main.alleSpieler.size(); i++)
		{
			if(!(i == spielernummer))
			{
				System.out.println("Für den Spieler mit der Figur " + Main.alleSpieler.get(i).getFigur() + " gebe " + (möglicheHandelsPatner.size() + 1) + " ein.");
				möglicheHandelsPatner.add(i);
			}
		}
		System.out.println("\nGib eine Nummer des Spielers ein (1 - " + Main.alleSpieler.size() + ").");
		int spielernummer = Main.checkCorrectNum(1, möglicheHandelsPatner.size());
		Handeln(möglicheHandelsPatner.get(spielernummer - 1));
	}
	
	public void Handeln(int welcherSpieler)
	{
		//Grundstücke, die der AktiveSpieler dem Anderen Anbietet
		ArrayList<Integer> angeboteneGrundstücke = new ArrayList<Integer>();
		//Grundstücke, die der AktiveSpieler dem Anderen nach dem Handeln gibt
		ArrayList<Integer> erhaltendeGrundstücke = new ArrayList<Integer>();
		//Geld, das der AktiveSpieler dem Anderen nach dem Handel gibt
		int angebotenesGeld = 0;
		//Geld, das der AktiveSpieler von dem Anderen nach dem Handeln erhält
		int erhaltenesGeld = 0;
		//KommeAusDemGefängnisFreiKarten, die der AktiveSpieler dem Anderen nach dem Handel gibt
		int angeboteneKarten = 0;
		//KommeAusDemGefängnisFreiKarten, die der AktiveSpieler dem Anderen nach dem Handeln erhält
		int erhalteneKarten = 0;
		
		ArrayList<String> möglicheAngebote = new ArrayList<String>();
		ArrayList<String> möglicheGegenangebote = new ArrayList<String>();
		
		
		boolean handelAktiv = true;
		while(handelAktiv)
		{
			//In diesem Loop macht der Spieler sein Angebot
			boolean angebotFertig = false;
			while(!angebotFertig)
			{
				möglicheAngebote.clear();
				//Erstellen von möglicheAngebote
				if(getGeld() > 0)
				{
					möglicheAngebote.add("Geld");
				}
				if(getGefängnisKarte() > 0)
				{
					möglicheAngebote.add("Komme aus dem Gefängnis frei Karten");
				}
				if(!grundstuecke.isEmpty() && angeboteneGrundstücke.size() < grundstuecke.size())
				{
					möglicheAngebote.add("Grundstücken");
				}
				if(!möglicheAngebote.isEmpty())
				{
					möglicheAngebote.add("Angebot abschließen");
				}
				möglicheAngebote.add("Handeln abbrechen");
				
				
				System.out.println("Was möchtest du dem Spieler anbieten?");
				//Ausgabe der Möglichen angebote
				for(int i = 0; i < möglicheAngebote.size(); i++)
				{
					System.out.println("Für " + möglicheAngebote.get(i) + " gebe " + (i+1) + " ein." );
				}
				int eingabe = Main.checkCorrectNum(1, möglicheAngebote.size());
				switch(möglicheAngebote.get(eingabe -1))
				{
					case"Geld":
						System.out.println("Du hast " + getGeld() + " Geld.\n Wie viel möchtest du zum Handel anbieten?");
						angebotenesGeld = Main.checkCorrectNum(1, getGeld() - 1);
						break;
					case"Komme aus dem Gefängnis frei Karten":
						System.out.println("Du hast " + getGefängnisKarte() + " Komme aus dem Gefängnis frei Karten.\n Wie viele möchtest du zum Handel anbieten?");
						angeboteneKarten = Main.checkCorrectNum(1, getGefängnisKarte());
						break;
					case"Grundstücken":
						System.out.println("Du hast die folgenden Grundstücke:");
						//Elemente von der ArrayList grundstuecke, welche nicht bereits teil von angeboteneGrundstücke sind
						ArrayList<Integer> auswählbareGrundstücke = new ArrayList<Integer>();
						//Hinzufügen der Grundstücke zu auswählbareGrundstücke
						for(int i = 0; i < grundstuecke.size(); i++)
						{
							if(angeboteneGrundstücke.isEmpty())
							{
								auswählbareGrundstücke.add(grundstuecke.get(i));
							}
							else
							{
								for(int j = 0; j < angeboteneGrundstücke.size(); j++)
								{
									if(!(grundstuecke.get(i) == angeboteneGrundstücke.get(j)))
									{
										auswählbareGrundstücke.add(grundstuecke.get(i));
									}
								}
							}
						}
						
						for(int j = 0; j < auswählbareGrundstücke.size(); j++)
						{
							System.out.println(Main.spielfeld[auswählbareGrundstücke.get(j)].getFeldname() + " (" + (j + 1) + ")");
						}
						System.out.println("Welches der Grundstücke möchtest du zum Handel anbieten?");
						eingabe = Main.checkCorrectNum(1, auswählbareGrundstücke.size());
						angeboteneGrundstücke.add(auswählbareGrundstücke.get(eingabe - 1));
						break;
					case"Angebot abschließen":
						angebotFertig = true;
						break;
					case"Handeln abbrechen":
						return;
				}
				
				//Ausgeben von allem was Angeboten wird.
				if(angebotenesGeld != 0)
				{
					System.out.println("Du bietest " + angebotenesGeld + " Mark an.");
				}
				if(angeboteneKarten != 0)
				{
					System.out.println("Du bietest " + angeboteneKarten + " Komme aus dem Gefängnis frei Karten an.");
				}
				if(!angeboteneGrundstücke.isEmpty())
				{
					for(int i = 0; i < angeboteneGrundstücke.size(); i++)
					{
						System.out.println("Du bietest folgende Grundstücke an:");
						System.out.println(Main.spielfeld[angeboteneGrundstücke.get(i)].getFeldname());
					}
				}
			}
			
			
			//Das Gegenangebot des Anderen Spielers
			angebotFertig = false;
			while(!angebotFertig)
			{
				möglicheGegenangebote.clear();
				//Erstellen von möglicheGegenangebote
				if(Main.alleSpieler.get(welcherSpieler).getGeld() > 0)
				{
					möglicheGegenangebote.add("Geld");
				}
				if(Main.alleSpieler.get(welcherSpieler).getGefängnisKarte() > 0)
				{
					möglicheGegenangebote.add("Komme aus dem Gefängnis frei Karten");
				}
				if(!(Main.alleSpieler.get(welcherSpieler).getGrundstücke().isEmpty()) && erhaltendeGrundstücke.size() < Main.alleSpieler.get(welcherSpieler).getGrundstücke().size())
				{
					möglicheGegenangebote.add("Grundstücken");
				}
				if(!möglicheGegenangebote.isEmpty())
				{
					möglicheGegenangebote.add("Gegenangebot abschließen");
				}
				möglicheGegenangebote.add("Handeln abbrechen");
				
				
				System.out.println("Was möchtest du vom Spieler erhalten?");
				//Ausgabe der Möglichen angebote
				for(int i = 0; i < möglicheGegenangebote.size(); i++)
				{
					System.out.println("Für " + möglicheGegenangebote.get(i) + " gebe " + (i+1) + " ein." );
				}
				int eingabe = Main.checkCorrectNum(1, möglicheGegenangebote.size());
				switch(möglicheGegenangebote.get(eingabe -1))
				{
					case"Geld":
						System.out.println("Du hast " + Main.alleSpieler.get(welcherSpieler).getGeld() + " Geld.\n Wie viel möchtest du zum Handel anbieten?");
						erhaltenesGeld = Main.checkCorrectNum(1, Main.alleSpieler.get(welcherSpieler).getGeld() - 1);
						break;
					case"Komme aus dem Gefängnis frei Karten":
						System.out.println("Du hast " + Main.alleSpieler.get(welcherSpieler).getGefängnisKarte() + " Komme aus dem Gefängnis frei Karten.\n Wie viele möchtest du zum Handel anbieten?");
						erhalteneKarten= Main.checkCorrectNum(1, Main.alleSpieler.get(welcherSpieler).getGefängnisKarte());
						break;
					case"Grundstücken":
						System.out.println("Du hast die folgenden Grundstücke:");
						//Elemente von der ArrayList grundstuecke, welche nicht bereits teil von angeboteneGrundstücke sind
						ArrayList<Integer> auswählbareGrundstücke = new ArrayList<Integer>();
						//Hinzufügen der Grundstücke zu auswählbareGrundstücke
						for(int i = 0; i < Main.alleSpieler.get(welcherSpieler).getGrundstücke().size(); i++)
						{
							if(erhaltendeGrundstücke.isEmpty())
							{
								auswählbareGrundstücke.add(Main.alleSpieler.get(welcherSpieler).getGrundstücke().get(i));
							}
							else
							{
								for(int j = 0; j < erhaltendeGrundstücke.size(); j++)
								{
									if(!(Main.alleSpieler.get(welcherSpieler).getGrundstücke().get(i) == erhaltendeGrundstücke.get(j)))
									{
										auswählbareGrundstücke.add(Main.alleSpieler.get(welcherSpieler).getGrundstuecke()[i]);
									}
								}
							}
						}
						
						for(int j = 0; j < auswählbareGrundstücke.size(); j++)
						{
							System.out.println(Main.spielfeld[auswählbareGrundstücke.get(j)].getFeldname() + " (" + (j + 1) + ")");
						}
						System.out.println("Welches der Grundstücke möchtest du zum Handel anbieten?");
						eingabe = Main.checkCorrectNum(1, auswählbareGrundstücke.size());
						erhaltendeGrundstücke.add(auswählbareGrundstücke.get(eingabe -1));
						break;
					case"Gegenangebot abschließen":
						angebotFertig = true;
						break;
					case"Handeln abbrechen":
						return;
				}
				
				//Ausgeben von allem was Gefordert wird.
				if(erhaltenesGeld != 0)
				{
					System.out.println("Du bietest " + erhaltenesGeld + " Mark an.");
				}
				if(erhalteneKarten != 0)
				{
					System.out.println("Du bietest " + erhalteneKarten + " Komme aus dem Gefängnis frei Karten an.");
				}
				if(!erhaltendeGrundstücke.isEmpty())
				{
					for(int i = 0; i < erhaltendeGrundstücke.size(); i++)
					{
						System.out.println("Du bietest folgende Grundstücke an:");
						System.out.println(Main.spielfeld[erhaltendeGrundstücke.get(i)].getFeldname());
					}
				}
				//Ausgeben was erhalten wird
				if(angebotenesGeld != 0)
				{
					System.out.println("Du bietest " + angebotenesGeld + " Mark an.");
				}
				if(angeboteneKarten != 0)
				{
					System.out.println("Du bietest " + angeboteneKarten + " Komme aus dem Gefängnis frei Karten an.");
				}
				if(!angeboteneGrundstücke.isEmpty())
				{
					for(int i = 0; i < angeboteneGrundstücke.size(); i++)
					{
						System.out.println("Du bietest folgende Grundstücke an:");
						System.out.println(Main.spielfeld[angeboteneGrundstücke.get(i)].getFeldname());
					}
				}
			}
			
			System.out.println("Sind beide Spieler mit diesem Handel zufrieden?\nGeben sie für Ja 1 und für nein 2 ein.");
			int eingabe = Main.checkCorrectNum(1, 2);
			if(eingabe == 1)
			{
				handelAktiv = false;
			}
			else
			{
				System.out.println("Das angebot wurde zurrückgesetzt. Beide Spieler können nun ein anderes Angebot eingeben.");
				//Zurrücksetzen der Angebote
				angeboteneGrundstücke = new ArrayList<Integer>();
				erhaltendeGrundstücke = new ArrayList<Integer>();
				angebotenesGeld = 0;
				erhaltenesGeld = 0;
				angeboteneKarten = 0;
				erhalteneKarten = 0;
			}
			
		}
		
		//Entferen von allem Angebotenen und Hinzufügen von allem Erhaltenen
		//Geld
		subtractGeld(angebotenesGeld);
		Main.alleSpieler.get(welcherSpieler).addGeld(angebotenesGeld);
		addGeld(erhaltenesGeld);
		Main.alleSpieler.get(welcherSpieler).subtractGeld(erhaltenesGeld);
		//Karten
		for(int i = 0; i < angeboteneKarten; i++)
		{
			subtractGefängnisKarte();
			Main.alleSpieler.get(welcherSpieler).addGefängnisKarte();
		}
		for(int i = 0; i < erhalteneKarten; i++)
		{
			addGefängnisKarte();
			Main.alleSpieler.get(welcherSpieler).subtractGefängnisKarte();
		}
		//Grundstücke
		for(int i = 0; i < angeboteneGrundstücke.size(); i++)
		{
			grundstuecke.remove(angeboteneGrundstücke.get(i));
			Main.alleSpieler.get(welcherSpieler).addGrundstück(angeboteneGrundstücke.get(i));
		}
		for(int i = 0; i < erhaltendeGrundstücke.size(); i++)
		{
			grundstuecke.add(erhaltendeGrundstücke.get(i));
			Main.alleSpieler.get(welcherSpieler).removeGrundstück(erhaltendeGrundstücke.get(i));
		}
		
		
		
	}
}