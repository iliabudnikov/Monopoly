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
    public void subtractGeld(int Geld, ArrayList<Spieler> alleSpieler, Feld[] spielfeld, int aktiverSpieler)
    {
    	//Hat der Spieler genug Geld, um den Gewünschten wert abzubezahlen?
    	if(geld >= Geld)
    	{
    		geld -= Geld;
    	}
    	else
    	{
    		//Wenn der Spieler nicht genug geld hat, ist er vorrübergehend pleite. Er kann Hypotheken aufnehmen und Häuser verkaufen, um an genug Geld zu kommen. Wenn er danach genug geld hat, kann er bezahlen, sonst ist er raus.
    		int neuGeld = pleite(alleSpieler, spielfeld, aktiverSpieler, Geld);
    		//Wenn der Spieler nun genug Geld hat, kann er den gewünschten wert abbezahlen
    		if(neuGeld >= Geld)
    		{
    			geld -= Geld;
    		}
    		//Wenn der Spieler aufgegeben hat, hat er verloren. In diesem Fall wird sein gesamter Besitz versteigert
    		else
    		{
    			
    		}
    	}
    }
    
    //Wenn einer der Spieler einem anderen Geld geben muss
    public ArrayList<Spieler> paySpieler(ArrayList<Spieler> alleSpieler, int welcherSpieler, int schulden, Feld[] spielfeld, int aktiverSpieler)
    {
    	//Hat der Spieler genug geld, um seine Schulden bei dem Anderen Spieler abzubezahlen?
    	if(geld >= schulden)
    	{
    		subtractGeld(schulden, alleSpieler, spielfeld, aktiverSpieler);
    		alleSpieler.get(welcherSpieler).addGeld(schulden);
    	}
    	//Wenn der Spieler nicht genug geld hat, ist er vorrübergehend pleite. Er kann Hypotheken aufnehmen und Häuser verkaufen, um an genug Geld zu kommen. Wenn er danach genug geld hat, kann er bezahlen, sonst ist er raus.
    	else
    	{
    		int neuGeld = pleite(alleSpieler, spielfeld, aktiverSpieler, schulden);
    		//Wenn der Spieler seine Schulden begleichen kann, werden ihm seine schulden abgezogen und dem anderen Spieler gegeben
    		if(neuGeld >= schulden)
    		{
    			subtractGeld(schulden, alleSpieler, spielfeld, aktiverSpieler);
    			alleSpieler.get(welcherSpieler).addGeld(schulden);
    		}
    		//Wenn der Spieler aufgegeben hat, bekommt der andere Spieler den gesamten Besitz des Schuldners
    		else
    		{
    			//Der Aktive Spieler hat verloren
    			hatVerloren = true;
    			//Übergeben der Grundstücke
    			for(int i = 0; i < grundstuecke.size(); i++)
    			{
    				alleSpieler.get(welcherSpieler).addGrundstück((Grundstueck)spielfeld[grundstuecke.get(i)]);
    			}
    			//Übergeben des restlichen geldes
    			alleSpieler.get(welcherSpieler).addGeld(geld);
    			//Übergeben der KommeAusDemGefängisFrei karten
    			for(int i = 0; i < kommAusDemGefängnisFreiKarten; i++)
    			{
    				alleSpieler.get(welcherSpieler).addGefängnisKarte();
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
    public void addGrundstück(Grundstueck grundstueck)
    {
        grundstuecke.add(grundstueck.getPosition());
    }
    public void removeGrundstück(int position)
    {
        int i = 0;
        while (i < grundstuecke.size() && grundstuecke.get(i).getPosition() != position)
            i++;
        
        grundstuecke.remove(i);
    }
    
        // sagt, ob der Spieler alle StraЯe in der Farbe hat
        public boolean sayIfAlleFarben(String farbe)
        {
            int strassenAnzahl = 1; // die Anzahl von StraЯen mit gleichen Farben
            for(int i = 0; i < grundstuecke.size(); i++)
            {
                if (grundstuecke.get(i).getFeld() == "Strasse" && ((Strasse)grundstuecke.get(i)).getFarbe() == farbe)
                    strassenAnzahl += 1; // wenn eine StraЯe gefunden ist und ihre Farbe passend ist
            }
            
            if ((farbe == "Lila" || farbe == "Blau") && strassenAnzahl == 2)
                return true;
            else if (strassenAnzahl == 3)
                return true;
    
            return false;
        }
        
        // bauen ein Haus?
        public boolean sayIfCanHaus()
        {
            ArrayList<String> farben = new ArrayList<String>(); // um dieselben Farben mehrmals nicht zu prьfen
            for(int i = 0; i < grundstuecke.size()-1; i++) // grundstuecke.size()-1, da das letzte Grundstьck schon sinnlos zu prьfen ist
            {
                if (grundstuecke.get(i).getFeld() == "Strasse" && !farben.contains(((Strasse)grundstuecke.get(i)).getFarbe()))
                // wenn das Grundstьck eine StraЯe ist und die Farbe wir noch nicht geprьft haben
                {
                    if (sayIfAlleFarben(((Strasse)grundstuecke.get(i)).getFarbe()))
                        return true;
                    
                    farben.add(((Strasse)grundstuecke.get(i)).getFarbe());
                }
            }
            
            return false;
        } 
        
        
        
        // IN MAIN PRЬFEN, HOTEL ODER HAUS
        
        
        // gibt alle StraЯen, wo ein Haus gebaut werden kann
        public ArrayList<Integer> sayWhereCanHaus()
        {
            ArrayList<Integer> strassen = new ArrayList<Integer>();
            String [] farben = {"Lila", "Tьrkis", "Violett", "Orange", "Rot", "Geld", "Grьn", "Blau"};
            boolean koennenWeiter = false; // sagt, ob fьr die Farbe alle StraЯe schon gefunden ist und wir weiter gehen kцnnen
            int hausanzahl; // wie viele Hдuser auf jeder StraЯe der Farbe stehen
            
            for(String i : farben)
            {
                if(sayIfAlleFarben(i)) { // wenn ein Haus auf den StraЯen dieser Farbe ьberhaupt mцglich ist
                    koennenWeiter = false;
                    hausanzahl = 1; // wenn weniger als 1 Haus, dann kцnnen bauen usw fьr 2 Hдuser...
                    while(!koennenWeiter && hausanzahl < 6) // < 5? => ein Hotel gebaut werden kann!
                    {
                        for(int j = 0; j < grundstuecke.size(); j++)
                        {
                            if(grundstuecke.get(j).getFeld() == "Strasse" && ((Strasse)grundstuecke.get(j)).getFarbe() == i)
                            {
                                if(((Strasse)grundstuecke.get(j)).getHausAnzahl() < hausanzahl)
                                {
                                    koennenWeiter = true; // kцnnen hier ein Haus bauen
                                    strassen.add(grundstuecke.get(j).getFeldnummer());
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
        
        // gibt alle StraЯen, die getascht werden kцnnen
        public ArrayList<Integer> sagWasKannTauschen() {
            ArrayList<Integer> strassen = new ArrayList<Integer>();
            for(int j = 0; j < grundstuecke.size(); j++)
                if(grundstuecke.get(j).getFeld() == "Strasse" && ((Strasse)grundstuecke.get(j)).getHausAnzahl() == 0)
                    strassen.add(grundstuecke.get(j).getFeldnummer());
            return strassen;
        }
    }
    
    //Kann der Spieler auf ein Gundstück eine Hypothek aufnehmen?
    public boolean sayIfCanHypothek(Feld[] spielfeld)
    {
    	for(int i = 0; i < grundstuecke.size(); i++)
    	{
    		//Hat dieses Grundstück schon eine Hypothek aufgenommen? Falls ja, kann keine weitere aufgenommen und das nächste Element wird betrachtet
    		if(!((Grundstueck) spielfeld[grundstuecke.get(i)]).getHypothek())
    		{
    			//Für Strassen können nur Hypotheken aufgenommen werden, wenn kein Haus auf ihnen steht
        		if(spielfeld[grundstuecke.get(i)].getFeld() == "Strasse")
        		{
        			if(((Strasse) spielfeld[grundstuecke.get(i)]).getHausAnzahl() == 0)
            		{
            			return true;
            		}
        		}
        		//Falls das Grundstück keine Strasse ist, muss die Anzahl der Häuser nicht betrachtet werden. Solange nicht bereits eine Hypothek besteht, kann eine Hypothek aufgenommen werden
        		else
        		{
        			return true;
        		}
        		
    		}
    	}
    	return false;
    }
    //Kann der Spieler mindestens eine seiner Hypotheken abbezahlen? Falls ja, true, falls nein, false
    public boolean sayIfCanHypothekAbbezahlen(Feld[] spielfeld)
    {
    	for(int i = 0; i < grundstuecke.size(); i++)
    	{
    		if(((Grundstueck) spielfeld[grundstuecke.get(i)]).getHypothek())
    		{
    			if(this.geld >= ((Grundstueck)spielfeld[grundstuecke.get(i)]).getHypothekPreis())
    			{
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    //Hat der Spieler mindestens ein Haus, welches er verkaufen kann?
    public boolean sayIfCanHausVerkaufen(Feld[] spielfeld)
    {
    	for(int i = 0; i < grundstuecke.size(); i++)
    	{
    		//Nur Strassen können häuser haben, weswegen hier nur die Elemente von grundstuecke betrachtet werden, die Strassen sind
    		if(spielfeld[grundstuecke.get(i)].getFeld() == "Strasse")
    		{
    			//Ist auf einer Strasse des Spielers mindestens ein Haus?
    			if(((Strasse) spielfeld[grundstuecke.get(i)]).getHausAnzahl() > 0)
        		{
        			return true;
        		}
    		}
    	}
    	return false;
    }
    
    
    
    //Gibt eine ArrayList mit allen möglichen Eingaben, die der Spieler gerade machen kann aus
    public ArrayList<String> möglicheAktionen(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
    	ArrayList<String> ausgabe = new ArrayList<String>();
    	
    	//Wenn der Spieler noch nicht gewürfelt hat, kann er würfeln
    	if(!hatGewürfelt)
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
    		ausgabe.add("Aus dem Gefängnis freikaufen (Dies kostet 50 Geld)");
    		//Wenn der Spieler im Gefängnis ist und mindestens eine "Komme aus dem Gefängnis frei" karte besitzt, wird diese zu benutzen als Aktion hinzugefügt.
    		if(getGefängnisKarte() > 0)
    		{
    			ausgabe.add("'Komme aus dem Gefängnis Frei' Karte verwenden");
    		}
    	}
    	
    	//Der Spieler kann wenn er am Zug ist jederzeit Häuser bauen. Er kann jedoch nur Häuser bauen, wenn er alle Straßen einer Farbe besitzt, und genug Geld hat.
    	if(sayIfCanHaus())
    	{
    		ausgabe.add("Haus bauen");
    	}
    	
    	//Hat der Spieler mindestens ein Haus, welches er verkaufen kann?
    	if(sayIfCanHausVerkaufen(spielfeld))
    	{
    		ausgabe.add("Häuser verkaufen");
    	}
    	//Der Spieler kann wenn er am Zug ist jederzeit handeln. Hier gibt es keine Beschränkung, weswegen dies Immmer in der Liste ist.
    	ausgabe.add("Handeln");
    	
    	//Hat der Spieler mindestens ein Grundstück, auf das er eine Hypothek aufnehmen kann?
    	if(sayIfCanHypothek(spielfeld))
    	{
    		ausgabe.add("Hypothen auf ein Grundstück aufnehmen");
    	}
    	
    	//Hat der Spieler mindestens eine Hypothek und genug Geld um diese Abzubezahlen?
    	if(sayIfCanHypothek(spielfeld)&& sayIfCanHypothekAbbezahlen(spielfeld))
    	{
    		ausgabe.add("Hypotheken abbezahlen");
    	}
    	
    	return ausgabe;
    }
    
    //Wenn der Spieler kein Geld mehr hat, kann er Hypotheken aufnehmen und Häuser verkaufen um Geld zu machen
    public int pleite(ArrayList<Spieler> alleSpieler,Feld[] spielfeld, int aktiverSpieler, int schulden)
    {
    	System.out.println("Du hast Schulden, die du nicht abbezahlen kannst. Du kannst Hypotheken aufnehmen oder Häuser verkaufen um Geld zu machen.");
    	//Solange der Spieler seine Schulden nicht abbezahlen kann, wird dies Wiederholt
    	while(schulden - geld > 0)
    	{
    		System.out.println("Du hast " + geld + " Geld, und du musst " + " Geld bezahlen. \nDir Fehlen also noch " + (schulden - geld) + " Geld.");
        	ArrayList<String> ausgabe = new ArrayList<String>();
        	if(sayIfCanHausVerkaufen(spielfeld))
        	{
        		ausgabe.add("Haus verkaufen");
        	}
        	if(sayIfCanHypothek(spielfeld))
        	{
        		ausgabe.add("Hypothek aufnehmen");
        	}
        	ausgabe.add("Aufgeben");
        	
        	for(int i = 0; i < ausgabe.size(); i++)
        	{
        		System.out.println("Zum " + ausgabe.get(i) + " gebe " + (i+1) + " ein.");
        	}
        	
            Scanner sc = new Scanner(System.in);
            boolean eingabeKorrekt = false;
            int eingabe = 0;
            while(!eingabeKorrekt)
            {
            	if(sc.hasNextInt())
            	{
            		eingabe = sc.nextInt();
            		if(eingabe > 0 && eingabe <= ausgabe.size())
            		{
            			eingabeKorrekt = true;
            		}
            		else
            		{
            			System.out.println("Du kannst nur Zahlen von 1 bis " + ausgabe.size() + " eingeben.");
            			for(int i = 0; i < ausgabe.size(); i++)
                    	{
                    		System.out.println("Zum " + ausgabe.get(i) + " gebe " + (i+1) + " ein.");
                    	}
            		}
            	}
            	else
            	{
            		System.out.println("Du darfst nur Zahlen eingeben.");
            		for(int i = 0; i < ausgabe.size(); i++)
                	{
                		System.out.println("Zum " + ausgabe.get(i) + " gebe " + (i+1) + " ein.");
                	}
            	}
            }
            
            switch(ausgabe.get(eingabe))
            {
            	case"Haus verkaufen":
            		alleSpieler = hausVerkaufen(alleSpieler, spielfeld, aktiverSpieler);
            		break;
            	case"Hypothek aufnehmen": 
            		alleSpieler = hypothekAufnehmen(alleSpieler, aktiverSpieler);
            		break;
            	case"Aufgeben":
            		return geld;
            		break;
            }
            
    	}
    	return schulden;
    }
    
    public ArrayList<Spieler> hypothekAbbezahlen(ArrayList<Spieler> alleSpieler, Feld[] spielfeld, int aktiverSpieler)
    { 
    	//ArrayList, welche den Index aller Grundstücke enthält, bei welchen hypothek = true ist 
    	ArrayList<Integer> alleHypotheken = new ArrayList<Integer>();
    	int[] grundstuecke = alleSpieler.get(aktiverSpieler).getGrundstuecke();
    	for(int i = 0; i < grundstuecke.length; i++)
    	{
    		//Wenn eines der Grundstücke des Arrays mit den Grundstücken des Spielers eine Hypothek hat, wird der Index dieses zu alleHypotheken hinzugefügt
    		if(((Grundstueck)spielfeld[grundstuecke[i]]).getHypothek())
    		{
    			alleHypotheken.add(grundstuecke[i]);
    		}
    	}
    	
    	System.out.println("Welche deiner Hypotheken willst du abbezahlen?\nWenn du dir das Abbezahlen einer Hypothek nicht leisten kannst, steht sie nicht zur Auswahl.\nDu hast gerade " + alleSpieler.get(aktiverSpieler).getGeld() + " Geld");
    	
    	for(int i = 0; i < alleHypotheken.size(); i++)
    	{
    		//Entfernen von Indexen, deren Grundstücke einen Höheren Hypothekspreis haben, als der Spieler bezahlen könnte
    		if(((Grundstueck)(spielfeld[alleHypotheken.get(i)])).getHypothekPreis() > alleSpieler.get(aktiverSpieler).getGeld())
    		{
    			//Ausgeben vom Hypothekspreis und dem dazugehörigen Grundstücksnamen
    			System.out.println("Für das Grundstück" + spielfeld[alleHypotheken.get(i)].getFeldname() + " kannst du dir die Hypothek von " + ((Grundstueck)(spielfeld[alleHypotheken.get(i)])).getHypothekPreis() + " Geld nicht leisten");
    			alleHypotheken.remove(i);
    			//Da dieses Element nun entfernt wurde, ist alleHypotheken.get(i) ein anderes element als vorher. Daher wird der Loop für diesen Wert von i erneut ausgeführt
    			i--;
    		}
    		System.out.println("Für das Grundstück" + spielfeld[alleHypotheken.get(i)].getFeldname() + " mit einer Hypothek von " + ((Grundstueck)(spielfeld[alleHypotheken.get(i)])).getHypothekPreis() + " Geld, gebe " + (i+1) + " ein.");
    	}
    	
    	
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
    			//Wenn die eingegebene Zahl innerhalb des Wertebereichs 1 - alleHypotheken.size() liegt, ist die Eingabe korrekt
    			for(int i = 0; i < alleHypotheken.size(); i++)
    			{
    				if(eingabe -1 == i)
    				{
    					eingabeKorrekt = true;
    				}
    			}
    			//Sollte eingabeKorrekt immer noch false sein, war die Eingabe nicht korrekt und muss wiederholt werden
    			if(!eingabeKorrekt)
        		{
        			System.out.println("Die Eingabe war nicht korrekt, versuch es noch einmal. Die möglichen Eingaben sind: ");
        			for(int i = 0; i < alleHypotheken.size(); i++)
                	{
                		System.out.println("Für das Grundstück" + spielfeld[alleHypotheken.get(i)].getFeldname() + " mit einer Hypothek von " + ((Grundstueck)(spielfeld[alleHypotheken.get(i)])).getHypothekPreis() + " Geld, gebe " + (i+1) + " ein.");
                	}
        		}
    		}
    		else
    		{
    			System.out.println("Die Eingabe war nicht korrekt, versuch es noch einmal. Die möglichen Eingaben sind: ");
    			for(int i = 0; i < alleHypotheken.size(); i++)
            	{
            		System.out.println("Für das Grundstück" + spielfeld[alleHypotheken.get(i)].getFeldname() + " mit einer Hypothek von " + ((Grundstueck)(spielfeld[alleHypotheken.get(i)])).getHypothekPreis() + " Geld, gebe " + (i+1) + " ein.");
            	}
    		}
    	}
    	
    	//Abbezahlen der Hypothek, die der Spieler ausgewählt hat
    	alleSpieler = ((Grundstueck)(spielfeld[alleHypotheken.get(eingabe)])).hypothekAbbezahlen(alleSpieler,spielfeld, aktiverSpieler);
    	return alleSpieler;
    }
    
    public ArrayList<Spieler> hypothekAufnehmen(ArrayList<Spieler> alleSpieler,Feld[] spielfeld, int aktiverSpieler)
    {
    	int[] grundstuecke = alleSpieler.get(aktiverSpieler).getGrundstuecke();
    	ArrayList<Integer> erlaubteGrundstücke = new ArrayList<Integer>();
    	for(int i = 0; i < grundstuecke.length; i++)
    	{
    		//Hat dieses Grundstück schon eine Hypothek aufgenommen? Falls ja, kann keine weitere aufgenommen und das nächste Element wird betrachtet
    		if(!((Grundstueck) spielfeld[grundstuecke[i]]).getHypothek())
    		{
    			//Für Strassen können nur Hypotheken aufgenommen werden, wenn kein Haus auf ihnen steht
        		if(spielfeld[grundstuecke[i]].getFeld() == "Strasse")
        		{
        			if(((Strasse) spielfeld[grundstuecke[i]]).getHausAnzahl() == 0)
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
    	System.out.println("Für welches Grundstück möchtest du eine Hypothek aufnehmen?\nDu hast gerade " + alleSpieler.get(aktiverSpieler).getGeld() + " Geld");
    	for(int i = 0; i < erlaubteGrundstücke.size(); i++)
    	{
    		System.out.println("Für " + ((Grundstueck) (spielfeld[erlaubteGrundstücke.get(i)])).getFeldname() + " gebe " + (i + 1) + " ein");
    		System.out.println("Für dieses Grundstück bekommst du " + ((Grundstueck) (spielfeld[erlaubteGrundstücke.get(i)])).getPreis()*0.5 + " Geld");
    	}
    	
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
    			//Wenn die eingegebene Zahl innerhalb des Wertebereichs 1 - erlaubteGrundstücke.size() liegt, ist die Eingabe korrekt
    			for(int i = 0; i < erlaubteGrundstücke.size(); i++)
    			{
    				if(eingabe -1 == i)
    				{
    					eingabeKorrekt = true;
    				}
    			}
    			//Sollte eingabeKorrekt immer noch false sein, war die Eingabe nicht korrekt und muss wiederholt werden
    			if(!eingabeKorrekt)
        		{
        			System.out.println("Die Eingabe war nicht korrekt, versuch es noch einmal. Die möglichen Eingaben sind: ");
        			for(int i = 0; i < erlaubteGrundstücke.size(); i++)
                	{
                		System.out.println("Für " + ((Grundstueck) (spielfeld[erlaubteGrundstücke.get(i)])).getFeldname() + " gebe " + (i + 1) + " ein");
                		System.out.println("Für dieses Grundstück bekommst du " + ((Grundstueck) (spielfeld[erlaubteGrundstücke.get(i)])).getPreis()*0.5 + " Geld");
                	}
        		}
    		}
    		else
    		{
    			System.out.println("Die Eingabe war nicht korrekt, versuch es noch einmal. Die möglichen Eingaben sind: ");
    			for(int i = 0; i < erlaubteGrundstücke.size(); i++)
            	{
            		System.out.println("Für " + ((Grundstueck) (spielfeld[erlaubteGrundstücke.get(i)])).getFeldname() + " gebe " + (i + 1) + " ein");
            		System.out.println("Für dieses Grundstück bekommst du " + ((Grundstueck) (spielfeld[erlaubteGrundstücke.get(i)])).getPreis()*0.5 + " Geld");
            	}
    		}
    	}
    	
    	alleSpieler = ((Grundstueck)spielfeld[erlaubteGrundstücke.get(eingabe)]).hypothekAufnehmen(alleSpieler, aktiverSpieler);
    	return alleSpieler;
    }
    
    public ArrayList<Spieler> hausVerkaufen(ArrayList<Spieler> alleSpieler, Feld[] spielfeld, int aktiverSpieler)
    {
    	ArrayList<Integer> grundstückeMitHaus = new ArrayList<Integer>();
    	//Hier werden die Grundstücke, welche Häuser haben zu der ArrayList hinzugefügt.
    	for(int i = 0; i < grundstuecke.size(); i++)
    	{
    		//Wenn das Grundstück ein Haus hat, wird es zur grundstückMitHaus ArrayList hinzugefügt
    		if(((Strasse)(spielfeld[grundstuecke.get(i)])).hasHaus())
    		{
    			grundstückeMitHaus.add(grundstuecke.get(i));
    		}
    	}
    	
    	//Häuser dürfen nur gleichmäßig verkauft werden. z.B. : Spieler Besitzt alle Gelben Grundstücke, Hat auf Lessingstaße und Schillerstraße ein Haus und auf der Goethestraße zwei Häuser. Er müsste erst das Haus auf der Goethestraße verkaufen, bevor er die von den anderen Straßen verkaufen kann.
    	for(int i = 0; i < grundstückeMitHaus.size(); i++)
    	{
    		for(int j = 0; j < grundstückeMitHaus.size(); j++)
    		{
    			//Hat der Spieler mehrere Grundstücke mit der selben Farbe und mindestens einem Haus? und: Ist die Anzahl der Häuser unterschiedlich?
    			if(((Strasse)(spielfeld[grundstückeMitHaus.get(i)])).getFarbe() == ((Strasse)(spielfeld[grundstückeMitHaus.get(j)])).getFarbe() && ((Strasse)(spielfeld[grundstückeMitHaus.get(i)])).getHausAnzahl() != ((Strasse)(spielfeld[grundstückeMitHaus.get(j)])).getHausAnzahl())
    			{
    				//Das Grundstück mit weniger Häusern wird aus der liste entfernt
    				if(((Strasse)(spielfeld[grundstückeMitHaus.get(i)])).getHausAnzahl() > ((Strasse)(spielfeld[grundstückeMitHaus.get(j)])).getHausAnzahl())
    				{
    					grundstückeMitHaus.remove(j);
    				}
    				if(((Strasse)(spielfeld[grundstückeMitHaus.get(i)])).getHausAnzahl() < ((Strasse)(spielfeld[grundstückeMitHaus.get(j)])).getHausAnzahl())
    				{
    					grundstückeMitHaus.remove(i);
    				}
    			}
    		}
    	}
    	
    	
    	System.out.println("Du hast " + geld + " Geld.");
    	//Ausgeben von allen Grundstücken die mindestens ein Haus haben, deren Farbe, deren Hausanzahl und wie viel Geld der Spieler für das verkaufen eines Hauses bekommt
    	for(int i = 0; i < grundstückeMitHaus.size(); i++)
    	{
    		System.out.println("Für die Straße " + spielfeld[grundstückeMitHaus.get(i)].getFeldname() + " mit der Farbe " + ((Strasse)(spielfeld[grundstückeMitHaus.get(i)])).getFarbe() + " und " + ((Strasse)(spielfeld[grundstückeMitHaus.get(i)])).getHausAnzahl() + " Häusern mit dem Verkauspreis von " + ((Strasse)(spielfeld[grundstückeMitHaus.get(i)])).getHausPreis() + " Geld, gebe " + (i+1) + " ein." );
    	}
    	
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
    			//Wenn die eingegebene Zahl innerhalb des Wertebereichs 1 - erlaubteGrundstücke.size() liegt, ist die Eingabe korrekt
    			for(int i = 0; i < grundstückeMitHaus.size(); i++)
    			{
    				if(eingabe -1 == i)
    				{
    					eingabeKorrekt = true;
    				}
    			}
    			//Sollte eingabeKorrekt immer noch false sein, war die Eingabe nicht korrekt und muss wiederholt werden
    			if(!eingabeKorrekt)
        		{
        			System.out.println("Die Eingabe war nicht korrekt, versuch es noch einmal. Die möglichen Eingaben sind: ");
        			for(int i = 0; i < grundstückeMitHaus.size(); i++)
        	    	{
        	    		System.out.println("Für die Straße " + spielfeld[grundstückeMitHaus.get(i)].getFeldname() + " mit der Farbe " + ((Strasse)(spielfeld[grundstückeMitHaus.get(i)])).getFarbe() + " und " + ((Strasse)(spielfeld[grundstückeMitHaus.get(i)])).getHausAnzahl() + " Häusern mit dem Verkauspreis von " + ((Strasse)(spielfeld[grundstückeMitHaus.get(i)])).getHausPreis() + " Geld, gebe " + (i+1) + " ein." );
        	    	}
        		}
    		}
    		else
    		{
    			System.out.println("Die Eingabe war nicht korrekt, versuch es noch einmal. Die möglichen Eingaben sind: ");
    			for(int i = 0; i < grundstückeMitHaus.size(); i++)
    	    	{
    	    		System.out.println("Für die Straße " + spielfeld[grundstückeMitHaus.get(i)].getFeldname() + " mit der Farbe " + ((Strasse)(spielfeld[grundstückeMitHaus.get(i)])).getFarbe() + " und " + ((Strasse)(spielfeld[grundstückeMitHaus.get(i)])).getHausAnzahl() + " Häusern mit dem Verkauspreis von " + ((Strasse)(spielfeld[grundstückeMitHaus.get(i)])).getHausPreis() + " Geld, gebe " + (i+1) + " ein." );
    	    	}
    		}
    	}
    	
    	//Tatsächliches Verkaufen von dem ausgewählten Haus
    	((Strasse)(spielfeld[grundstückeMitHaus.get(eingabe)])).setHausAnzahl(((Strasse)(spielfeld[grundstückeMitHaus.get(eingabe)])).getHausAnzahl() - 1);
    	addGeld(((Strasse)(spielfeld[grundstückeMitHaus.get(eingabe)])).getHausPreis());
    	System.out.println("Du hast ein Haus auf " + spielfeld[grundstückeMitHaus.get(eingabe)].getFeldname() + " für " + ((Strasse)(spielfeld[grundstückeMitHaus.get(eingabe)])).getHausPreis() + " Geld verkauft.\nDu hast nun " + geld + " Geld");
    	
    	return alleSpieler;
    }
    
}