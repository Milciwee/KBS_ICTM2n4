package KBS_ICTM2n4;

import java.util.ArrayList;
import java.util.Arrays;

//berekent de goedkoopste servercombinatie met het gegeven beschikbaarheidspercentage

public class Backtracking {

    //De volgende statische variabelen gebruiken we om gegevens die we verkrijgen in de optimalisatiefunctie tijdelijk op te slaan.
    private static int[] cheapestSetFound = new int[6];
    private static int cheapest = 0;
    private static boolean firstLoop = true;

    //Dit is niet de functie die in andere delen van de code zou moeten worden aangeroepen, maar vormt een groot onderdeel van de optimisation-functie
    //die later in deze class staat
    public static boolean optimisationMainLoop(int[] givenComponents, int[] addedComponents, Server[] availableComponents, double minAvailability, int level) {

        //We voegen een server toe op het huidige niveau.
        //Als dit echter de eerste keer is dat we de functie gebruiken, willen we controleren of de set "as-is" (+ al dan niet één db1 of w1)
        //een kandidaat oplevert. Dit is natuurlijk niet netjes, maar met deze uitwerking levert het andere problemen op als we proberen het +1 moment
        //later in de functie te verwerken
        if (!(firstLoop)) {
            addedComponents[level] += 1;
        }
        firstLoop = false;

        //Als de huidige set geen database- of webservers heeft, willen we daar één van toevoegen op het eerste niveau van dat servertype
        //Eerst kijken we wat het totaal aantal database- en webservers is.
        int dbAmount = 0;
        int wAmount = 0;

        for(int i = 0; i < availableComponents.length; i++) {
            if(availableComponents[i].getType().equals("database")) {
                dbAmount += (givenComponents[i] + addedComponents[i]);
            } else if (availableComponents[i].getType().equals("webserver")) {
                wAmount += (givenComponents[i] + addedComponents[i]);
            }
        }

        //Als er geen databaseservers zijn, voegen we één databaseserver van het eerste type in de lijst toe.
        boolean dbAdded = false;

        if(dbAmount == 0) {
            for(int i = 0; i < availableComponents.length; i++) {
                if((availableComponents[i].getType().equals("database")) && !(dbAdded)) {
                    addedComponents[i] += 1;
                    dbAdded = true;
                }
            }
        }

        //Idem met webservers.
        boolean wAdded = false;

        if(wAmount == 0) {
            for(int i = 0; i < availableComponents.length; i++) {
                if((availableComponents[i].getType().equals("webserver")) && !(wAdded)) {
                    addedComponents[i] += 1;
                    wAdded = true;
                }
            }
        }

        //We zullen regelmatig willen weten wat de som van de gegeven en de toegevoegde componenten is
        int[] totalComponents = new int[availableComponents.length];
        for(int i = 0; i < availableComponents.length; i++) {
            totalComponents[i] = givenComponents[i] + addedComponents[i];
        }

        //Vanaf hier stellen we de beschikbaarheid van de huidige set vast.

        double availabilityDB = 1;
        double availabilityW = 1;

        //We gaan de mogelijke servers langs. Wanneer er servers van een bepaald soort aanwezig zijn,
        //passen we de totale beschikbaarheid van de database- of webservers aan op basis van de hoeveelheid servers van die soort.
        for (int i = 0; i < availableComponents.length; i++) {
            if (totalComponents[i] > 0) {
                for (int j = 0; j < totalComponents[i]; j++) {
                    if (availableComponents[i].getType().equals("database")) {
                        availabilityDB *= (1 - availableComponents[i].getavailability());
                    } else if (availableComponents[i].getType().equals("webserver")) {
                        availabilityW *= (1 - availableComponents[i].getavailability());
                    }
                }
            }
        }

        availabilityDB = 1 - availabilityDB;
        availabilityW = 1 - availabilityW;

        //Vervolgens rekenen we de volledige beschikbaarheid uit, inclusief pfSense.

        double availability = availabilityDB * availabilityW * 0.99998;

        //We stellen de prijs van de huidige set vast.

        int price = 0;
        for (int i = 0; i < availableComponents.length; i++) {
            price += (availableComponents[i].getPrice() * totalComponents[i]);
        }
        //Inclusief prijs voor de pfSense
        price += 4000;

        //Als de huidige set (nu al) duurder is dan de goedkoopste set met de gegeven beschikbaarheid die we tot nu toe hebben gevonden,
        //kunnen we deze set onmiddelijk verwerpen, en doorgaan naar de volgende. (De toevoegingen op huidige niveau halen we weg, en deze functie
        //roepen we met het volgende niveau aan.)

        //Daarnaast is het mogelijk dat de beschikbaarheid van "de huidige set webservers" * "de pfSense" nooit de gegeven beschikbaarheid bereikt,
        //ook al is de beschikbaarheid van de databaseservers honderd procent. Als we dan momenteel op een databaseniveau servers toevoegen,
        //zouden we daar oneindig mee doorgaan. Hier controleren we ook op, en als dit het geval is gaan we ook door naar de volgende set.
        //Hetzelfde probleem kan zich niet voordoen als we webservers toevoegen,
        //want als de gevraagde beschikbaarheid niet wordt gehaald valt de functie automatisch terug naar de databaseniveau's.
        //(Dit werkt natuurlijk alleen als de databaseservers voorin de availableComponents-array zitten,
        //dus dit zou misschien flexibeler kunnen worden gemaakt. Als de availableComponents-array uit een losse functie voortkomt zou ook
        //daar deze regel kunnen worden ingesteld.)

        //Voor de zekerheid gaan we ook door naar de volgende set als we meer dan 35 componenten op het huidige niveau hebben toegevoegd (los van de opgegeven
        //componenten). Dit voorkomt bijvoorbeeld een oneindig aantal toevoegingen van het eerste type webserver als er een beschikbaarheid van 100% wordt gevraagd.

        if ((!(cheapest == 0) && (price > cheapest)) ||
            ((availableComponents[level].getType().equals("database")) && (availabilityW * 0.99998 < minAvailability)) ||
                (addedComponents[level] > 35 )) {

            //Als we tegen de bovenstaande situaties aanlopen op het laatste niveau, hebben we alle relevante sets bekeken en kunnen we stoppen.
            if (level == availableComponents.length - 1) {
                return true;
            }

            //Anders gaan we door naar de "volgende" set: we halen alle toegevoegde componenten van het huidige niveau weg
            //en roepen deze functie aan op het volgende niveau, wat daar in ieder geval één bij zal optellen.

            addedComponents[level] = 0;

            if (optimisationMainLoop(givenComponents, addedComponents, availableComponents, minAvailability, level + 1)) {
                return true;
            }
        } else {
            //Lopen we niet tegen de bovenstaande problemen aan, dan controleren we of de beschikbaarheid het minimaal gevraagde percentage heeft bereikt.
            if (availability >= minAvailability) {
                //Zo ja, is de prijs lager dan de goedkoopste die we tot nu toe hebben gevonden?
                //Of hebben we nog geen goedkoopste vastgesteld (cheapest == 0)?
                if (price < cheapest || cheapest == 0) {
                    //Dan wordt dit de goedkoopste.
                    cheapest = price;

                    //We stellen de inhoud van de goedkoopste gevonden set gelijk aan de inhoud van de huidige set.
                    for (int i = 0; i < availableComponents.length; i++) {
                        cheapestSetFound[i] = totalComponents[i];
                    }
                }

                //Als dit het laatste niveau is hebben we alle relevante sets bekeken en kunnen we stoppen.
                if (level == availableComponents.length - 1) {
                    return true;
                }

                //Anders gaan we verder - de toevoegingen op het huidige niveau worden weggehaald en we roepen deze functie aan op het volgende niveau.
                addedComponents[level] = 0;

                if (optimisationMainLoop(givenComponents, addedComponents, availableComponents, minAvailability, level + 1)) {
                    return true;
                }
            }
        }
        //Is de huidige set niet verworpen en ook niet geslaagd, dan keren we terug naar het vorige niveau (tenzij dit het eerste niveau is,
        //dan komen we terug in de while-loop die deze functie op dat niveau opnieuw aanroept).
        return false;
    }

    //Deze functie kan in andere delen van de code worden aangeroepen.
    public static ArrayList<Server> optimisation(int[] givenComponents, double minAvailability) {

        //We maken een array aan met alle beschikbare onderdelen zodat we makkelijk hiernaar kunnen verwijzen,
        //omdat de index van deze array en de amountPerComponent-array naar dezelfde componentsoort verwijst.
        //de servers die worden gebruikt voor het backtrackingproces worden uit de Screen klasse gehaald,
        //hierdoor is een aanpassing aan de prijs of naam van een of meerdere servers makkelijker
        ArrayList<Server> totalServers = Server.getServerList();
        Server db1 = totalServers.get(0);
        Server db2 = totalServers.get(1);
        Server db3 = totalServers.get(2);
        Server w1 = totalServers.get(3);
        Server w2 = totalServers.get(4);
        Server w3 = totalServers.get(5);

        Server[] availableComponents = new Server[6];

        availableComponents[0] = db1;
        availableComponents[1] = db2;
        availableComponents[2] = db3;
        availableComponents[3] = w1;
        availableComponents[4] = w2;
        availableComponents[5] = w3;

        //We maken ook een array met de componenten die toe worden gevoegd aan de gegeven set
        int[] addedComponents = new int[availableComponents.length];

        //We gebruiken kommagetallen in deze functie, maar we krijgen een percentage opgegeven. Deze passen we dus aan.
        minAvailability = minAvailability/100;

        //De optimisationMainLoop-functie voegt stapsgewijs onderdelen op een niveau toe, waar een niveau verwijst naar een specifiek soort server.
        //Wanneer hiermee de gevraagde beschikbaarheid is behaald worden die toevoegingen weggehaald
        //en wordt de functie op het volgende niveau betrokken. Anders loopt de functie weer niveaus terug. We beginnen op het eerste niveau.
        int level = 0;

        //We gebruiken een while-loop om herhaaldelijk de optimisationMainLoop-functie aan te roepen op het eerste niveau.
        //De optimisationMainLoop-functie roept zichzelf aan voor de volgende niveaus.
        //Als het laatste niveau is bereikt geeft de functie een "true" terug, wat alle onderliggende functies oplost, alsook
        //de onderstaande while-loop.

        boolean checkedAllSets = false;

        while (!(checkedAllSets)) {

            if (optimisationMainLoop(givenComponents, addedComponents, availableComponents, minAvailability, level)) {
                checkedAllSets = true;
            }
        }

        //We zetten de gevonden goedkoopste set in een ArrayList.
        ArrayList<Server> resultCheapestSet = new ArrayList<>();

        for(int i = 0; i < cheapestSetFound.length; i++) {
            for(int j = 0; j < cheapestSetFound[i]; j++) {
                resultCheapestSet.add(availableComponents[i]);
            }
        }

        //Vervolgens wissen we de gebruikte statische functies voor de volgende keer dat deze functie wordt aangeroepen.
        cheapestSetFound = new int[availableComponents.length];
        cheapest = 0;
        firstLoop = true;

        //Tot slot geven we de ArrayList met de goedkoopst gevonden set terug.
        return resultCheapestSet;
    }

//    public static void main(String[] args) {
//
//        int[] gegevenLijst = new int[6];
//        gegevenLijst[0] = 0;
//        gegevenLijst[1] = 0;
//        gegevenLijst[2] = 0;
//        gegevenLijst[3] = 0;
//        gegevenLijst[4] = 0;
//        gegevenLijst[5] = 0;
//
//        ArrayList<Server> goedkoopsteMetGegevenLijst = optimisation(gegevenLijst, 99.99);
//
//        System.out.println("");
//        System.out.println("Goedkoopste set: ");
//        for(Server server : goedkoopsteMetGegevenLijst) {
//            System.out.println(server.getName());
//        }
//    }
}
