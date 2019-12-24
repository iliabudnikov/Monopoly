import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Gefängnis extends Aktionsfelder
{
    public Gefängnis(int Feldnummer)
    {
    	super(Feldnummer, false, "Gefängnis", "Gefängnis");
    }
    
    public ArrayList<Spieler> ereignis(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        feldBetreten(alleSpieler, aktiverSpieler, spielfeld);
        return alleSpieler;
    }
    
    public void feldBetreten(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        //Wenn der Spieler nicht im Gefängnis ist, dann wird nur ein Text ausgegeben
        if(!alleSpieler.get(aktiverSpieler).getImGefängnis())
        {
             System.out.println("Du hast das " + getFeld() + " betreten");
            System.out.println("Du bist nur zu besuch");
        }
        else 
        {
            //Im Gefängnis darf der Spieler Häuser bauen und Tauschen. Diese Funktion ist noch nicht Implementiert
            
            //Wenn der Spieler im Gefängnis ist wird erst überprüft, ob der Spieler schon zu lange im Gefängnis war, oder ob er noch Würfeln darf
            if(alleSpieler.get(aktiverSpieler).getRundenImGefängnis() == 3)
            {
                System.out.println("Du bist bereits 3 Runden im Gefängnis, du musst 50 Geld bezahlen");
                zahlen(alleSpieler, aktiverSpieler, spielfeld);
            }
            else
            {
                System.out.println("Du bist im Gefängnis.\nUm freizukommen kannst du entweder 50 Geld bezahlen, oder versuchen einen Pasch zu würfeln.\nNach 3 Runden im Gefängnis musst du Zahlen\nSchreibe Zahlen um zu Zahlen oder Würfeln um zu Würfeln.");
                System.out.println("Du bist schon " + alleSpieler.get(aktiverSpieler).getRundenImGefängnis() + " Runden im Gefängnis");
                
                Scanner sc = new Scanner(System.in);
                String input;
                
                //Hier wird die Eingabe überprüft
                boolean eingabeKorrekt = false;
                while(!eingabeKorrekt)
                {
                    input = sc.nextLine();
                    //Die eingabe die der Nutzer hier macht darf nur "Zahlen" oder "Würfel" sein. Sollte er etwas anderes Eingeben, wird die Eingabe wiederholt und eine Fehlermeldung ausgegeben
                    switch(input)
                    {
                        case"Zahlen":
                            zahlen(alleSpieler, aktiverSpieler, spielfeld);
                            //Eingabe war korrekt und muss nicht wiederholt werden
                            eingabeKorrekt = true;
                            break;
                        case"Würfeln":
                            würfeln(alleSpieler, aktiverSpieler, spielfeld);
                            //Eingabe war korrekt und muss nicht wiederholt werden
                            eingabeKorrekt = true;
                            break;
                        default:
                            System.out.println("Die Eingabe war nicht korrekt. Gebe entweder Zahlen oder Würfeln ein");
                            break;
                            
                        
                    }
                }
            }
        }
    }
    private void würfeln(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        //Genereieren von 2 Zufallszahlen zwischen 1 und 6
        int ersteZahl = rnd.nextInt(6) + 1;
        int zweiteZahl= rnd.nextInt(6) + 1;
        
        System.out.println("Du hast eine " + ersteZahl + " und eine " + zweiteZahl + " Gewürfelt");
        //Wenn der Spieler einen Pasch gewürfelt hat, kommt er aus dem Gefängnis frei
        if(ersteZahl == zweiteZahl)
        {
            System.out.println("Du hast eine " + ersteZahl + " und eine " + zweiteZahl + " Gewürfelt.\nDu kommst aus dem Gefängnis frei. Rücke " + (ersteZahl + zweiteZahl) + " Felder vor");
            alleSpieler.get(aktiverSpieler).setPosition(alleSpieler.get(aktiverSpieler).getPosition() + ersteZahl + zweiteZahl);
            alleSpieler.get(aktiverSpieler).addPasch();
            //Diese Funktion ist noch nicht Implementiert
            spielfeld[alleSpieler.get(aktiverSpieler).getPosition()].aktion oder so
        }
        else
        {
            System.out.println("Du hast keinen Pasch gewürfelt, du musst im Gefängnis bleiben");
            alleSpieler.get(aktiverSpieler).increaseRundenImGefängnis();
        }
    }
    private void zahlen(ArrayList<Spieler> alleSpieler, int aktiverSpieler, Feld[] spielfeld)
    {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        //Genereieren von 2 Zufallszahlen zwischen 1 und 6
        int ersteZahl = rnd.nextInt(6) + 1;
        int zweiteZahl= rnd.nextInt(6) + 1;
        //Abziehen des Geldes
        alleSpieler.get(aktiverSpieler).subtractGeld(50);
        
        System.out.println("Du hast 50 Geld bezahlt und eine " + ersteZahl + " und eine " + zweiteZahl + " Gewürfelt.\nRücke " + (ersteZahl + zweiteZahl) + " Felder vor");
        alleSpieler.get(aktiverSpieler).setPosition(alleSpieler.get(aktiverSpieler).getPosition() + ersteZahl + zweiteZahl);
        //Diese Funktion ist noch nicht Implementiert
        spielfeld[alleSpieler.get(aktiverSpieler).getPosition()].aktion oder so
    }
}