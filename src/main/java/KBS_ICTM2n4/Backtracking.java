package KBS_ICTM2n4;

import java.util.ArrayList;
import java.util.Arrays;

//berekent de goedkoopste servercombinatie met het gegeven beschikbaarheidspercentage

//Op dit moment houdt het geen rekening met de componentenset die wordt opgegeven,
//er missen nog wat eisen,
//en de code kan een stuk efficïenter op meerdere plaatsen

public class Backtracking {

    private static int[] cheapestSetFound = new int[6];
    private static int cheapest = 0;
    private static boolean firstLoop = true;

    public static boolean optimisationMainLoop(int[] amountPerComponent, Server[] availableComponents, double minAvailability, int level) {

        //We voegen een server toe op het huidige niveau.
        //Als dit echter de eerste keer is dat we de functie gebruiken, willen we controleren of de set "as-is" (+ al dan niet één db1 of w1)
        //een kandidaat oplevert. Dit is natuurlijk niet netjes, maar met deze uitwerking levert het andere problemen op als we proberen het +1 moment
        //later in de functie te verwerken
        if (!(firstLoop)) {
            amountPerComponent[level] += 1;
        }

        firstLoop = false;

        //Als de huidige set geen enkele databaseserver heeft, voegen we één databaseserver op de eerste positie (db1) toe.
        //Dit is niet elegant, en kan later wat dynamischer worden gemaakt.
        if ((amountPerComponent[0] == 0) && (amountPerComponent[1] == 0) && (amountPerComponent[2] == 0)) {
                amountPerComponent[0] += 1;
        }
        //Idem met webservers.
        if ((amountPerComponent[3] == 0) && (amountPerComponent[4] == 0) && (amountPerComponent[5] == 0)) {
            amountPerComponent[3] += 1;
        }

        //Vanaf hier stellen we de beschikbaarheid van de huidige set vast.
        //We bepalen eerst de beschikbaarheid van de databaseserver(s).

        double availabilityDB = 1;

        //We gaan (wederom, niet op een mooie manier) door de mogelijke databaseservers. Als er geen van het betreffende type aanwezig is,
        //wordt de beschikbaarheid niet veranderd (*1).
        //Anders passen we de beschikbaarheid aan op basis van de hoeveelheid servers van het betreffende type.
        for (int i = 0; i < 3; i++) {
            if (amountPerComponent[i] == 0) {
                availabilityDB *= 1;
            } else {
                for (int j = 0; j < amountPerComponent[i]; j++) {
                    availabilityDB *= (1 - availableComponents[i].getavailability());
                }
            }
        }

        availabilityDB = 1 - availabilityDB;

        double availabilityW = 1;

        //We stellen op de bovenstaande manier de beschikbaarheid van de webserver(s) vast.

        for (int i = 3; i < 6; i++) {
            if (amountPerComponent[i] == 0) {
                availabilityW *= 1;
            } else {
                for (int j = 0; j < amountPerComponent[i]; j++) {
                    availabilityW *= (1 - availableComponents[i].getavailability());
                }
            }
        }

        availabilityW = 1 - availabilityW;

        //Vervolgens rekenen we de volledige beschikbaarheid uit, inclusief pfSense.

        double availability = availabilityDB * availabilityW * 0.99998;

        //We stellen de prijs van de huidige set vast.

        int price = 0;
        for (int i = 0; i < amountPerComponent.length; i++) {
            price += (availableComponents[i].getPrice() * amountPerComponent[i]);
        }
        price += 4000;

        //Als de huidige set (nu al) duurder is dan de goedkoopste set met de gegeven beschikbaarheid die we tot nu toe hebben gevonden,
        //kunnen we deze set onmiddelijk verwerpen.

        //Daarnaast is het mogelijk dat de beschikbaarheid van de huidige set webservers * de pfSense nooit de gegeven beschikbaarheid bereikt,
        //ook al is de beschikbaarheid van de databaseservers honderd procent. Als we dan momenteel op een databaseniveau servers toevoegen,
        //zouden we daar oneindig mee doorgaan. Hier zal een slimme oplossing voor zijn die rekening houdt met de beschikbaarheid van de componenten
        //die toegevoegd worden etc. (if (availablecomponents[i].getAvailability * availabilityW * 0.99998 < minAvailability) of iets dergelijks),
        //maar die kan ik (nog) niet uitvogelen. Voor nu stoppen we als er meer dan 20 componenten van het huidige type zijn toegevoegd.
        if ((!(cheapest == 0) && (price > cheapest)) || amountPerComponent[level] > 20) {

            //Als we tegen de bovenstaande situaties aanlopen op het laatste niveau, hebben we alle relevante sets bekeken en kunnen we stoppen.
            if (level == amountPerComponent.length - 1) {
                return true;
            }

            //Anders gaan we door naar de "volgende" set: we halen alle componenten van het huidige niveau weg en roepen deze functie aan op het volgende
            //niveau, wat daar in ieder geval één bij zal optellen.

            amountPerComponent[level] = 0;

            if (optimisationMainLoop(amountPerComponent, availableComponents, minAvailability, level + 1)) {
                return true;
            }
        } else {
            //Lopen we niet tegen de bovenstaande problemen aan, dan controleren we of de beschikbaarheid het minimaal gevraagde percentage heeft bereikt.
            if (availability >= minAvailability) {
                //Zo ja, is de prijs lager dan de goedkoopste die we tot nu toe hadden gevonden,
                //of hebben we nog geen goedkoopste vastgesteld (cheapest == 0)?
                if (price < cheapest || cheapest == 0) {
                    //Dan wordt dit de goedkoopste.
                    cheapest = price;
                    System.out.println(cheapest);

                    //We stellen de inhoud van de goedkoopste set gelijk aan de inhoud van de huidige set.
                    for (int i = 0; i < availableComponents.length; i++) {
                        cheapestSetFound[i] = amountPerComponent[i];
                    }

                    System.out.println(Arrays.toString(cheapestSetFound));
                }

                //Als dit het laatste niveau is hebben we alle relevante sets bekeken en kunnen we stoppen.
                if (level == amountPerComponent.length - 1) {
                    return true;
                }

                //Anders gaan we door - het huidige niveau wordt leeggemaakt en we roepen deze functie aan op het volgende niveau.
                amountPerComponent[level] = 0;

                if (optimisationMainLoop(amountPerComponent, availableComponents, minAvailability, level + 1)) {
                    return true;
                }
            }
        }
        //Is de huidige set niet verworpen en ook niet geslaagd, dan keren we terug naar het vorige niveau (tenzij dit het eerste niveau is,
        //dan komen we terug in de while-loop die deze functie op dat niveau opnieuw aanroept.

        return false;
    }

    public static ArrayList<Server> optimisation(int[] amountPerComponent, double minAvailability) {

        //We maken een array aan met alle beschikbare onderdelen zodat we makkelijk hiernaar kunnen verwijzen,
        //omdat de index van deze array en de amountPerComponent-array naar dezelfde componentsoort verwijst
        //(zou misschien een losse functie kunnen zijn, en sowieso wat dynamischer)
        Server db1 = new Server("database", "db1", 0.90, 5100);
        Server db2 = new Server("database", "db2", 0.95, 7700);
        Server db3 = new Server("database", "db3", 0.98, 12200);
        Server w1 = new Server("webserver", "w1", 0.80, 2200);
        Server w2 = new Server("webserver", "w2", 0.90, 3200);
        Server w3 = new Server("webserver", "w3", 0.95, 5100);

        Server[] availableComponents = new Server[6];

        availableComponents[0] = db1;
        availableComponents[1] = db2;
        availableComponents[2] = db3;
        availableComponents[3] = w1;
        availableComponents[4] = w2;
        availableComponents[5] = w3;

        //Het niveau is in de eerste plaats 0.
        int level = 0;

        //We gebruiken een while-loop om componenten op te tellen op het eerste niveau. De functie die hier gebruikt wordt gebruikt zichzelf voor
        //de volgende niveaus. Als het laatste niveau is bereikt geeft de functie een "true" terug, wat alle onderliggende functies oplost, alsook
        //de onderstaande while-loop.

        boolean checkedAllSets = false;

        while (!(checkedAllSets)) {

            if (optimisationMainLoop(amountPerComponent, availableComponents, minAvailability, level)) {
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
        cheapestSetFound = new int[6];
        cheapest = 0;
        firstLoop = true;

        //Tot slot geven we de ArrayList met de goedkoopst gevonden set terug.
        System.out.println("Klaar!");
        return resultCheapestSet;
    }

    public static void main(String[] args) {

        int[] gegevenLijst = new int[6];
        gegevenLijst[1] = 0;
        gegevenLijst[3] = 0;

        ArrayList<Server> goedkoopsteMetGegevenLijst = optimisation(gegevenLijst, 0.9999);

        System.out.println("");
        System.out.println("Goedkoopste set: ");
        for(Server server : goedkoopsteMetGegevenLijst) {
            System.out.println(server);
        }
    }
}
