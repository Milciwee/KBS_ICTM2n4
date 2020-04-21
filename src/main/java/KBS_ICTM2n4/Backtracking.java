package KBS_ICTM2n4;

import java.util.ArrayList;
import java.util.Arrays;

//berekent de goedkoopste servercombinatie met het gegeven beschikbaarheidspercentage

//Incompleet, en moet nog van commentaar worden voorzien
//Op dit moment houdt het geen rekening met de componentenset die wordt opgegeven,
//en de code kan een stuk efficïenter op meerdere plaatsen

public class Backtracking {

    public static boolean optimisationMainLoop(int[] amountPerComponent, Server[] availableComponents, double minAvailability, int level, int cheapest) {

        amountPerComponent[level] += 1;

        if ((amountPerComponent[0] == 0) && (amountPerComponent[1] == 0) && (amountPerComponent[2] == 0)) {
                amountPerComponent[0] += 1;
        }
        if ((amountPerComponent[3] == 0) && (amountPerComponent[4] == 0) && (amountPerComponent[5] == 0)) {
            amountPerComponent[3] += 1;
        }

        double availabilityDB = 1;

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

        double availability = availabilityDB * availabilityW * 0.99998;

        int price = 0;

        for (int i = 0; i < amountPerComponent.length; i++) {
            price += (availableComponents[i].getPrice() * amountPerComponent[i]);
        }

        price += 4000;

        if ((!(cheapest == 0) && (price > cheapest)) || amountPerComponent[level] > 6) {

            if (level == amountPerComponent.length - 1) {
                return true;
            }

            amountPerComponent[level] = 0;

            if (optimisationMainLoop(amountPerComponent, availableComponents, minAvailability, level + 1, cheapest)) {
                return true;
            }

        } else {
            if (availability >= minAvailability) {
                if (price < cheapest || cheapest == 0) {
                    cheapest = price;
                    System.out.println(Arrays.toString(amountPerComponent));
                    System.out.println(price);
                }

                if (level == amountPerComponent.length - 1) {
                    return true;
                }

                amountPerComponent[level] = 0;

                if (optimisationMainLoop(amountPerComponent, availableComponents, minAvailability, level + 1, cheapest)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean optimisation(int[] amountPerComponent, double minAvailability) {

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

        int level = 0;
        int cheapest = 0;
        boolean checkedAllSets = false;

        while (!(checkedAllSets)) {

            amountPerComponent[level] += 1;

            if ((amountPerComponent[0] == 0) && (amountPerComponent[1] == 0) && (amountPerComponent[2] == 0)) {
                amountPerComponent[0] += 1;
            }
            if ((amountPerComponent[3] == 0) && (amountPerComponent[4] == 0) && (amountPerComponent[5] == 0)) {
                amountPerComponent[3] += 1;
            }

            double availabilityDB = 1;

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

            double availability = availabilityDB * availabilityW * 0.99998;

            int price = 0;

            for (int i = 0; i < amountPerComponent.length; i++) {
                price += (availableComponents[i].getPrice() * amountPerComponent[i]);
            }

            price += 4000;

            if ((!(cheapest == 0) && (price > cheapest)) || amountPerComponent[level] > 6) {

                amountPerComponent[level] = 0;

                if (optimisationMainLoop(amountPerComponent, availableComponents, minAvailability, level + 1, cheapest)) {
                    checkedAllSets = true;
                }

            } else {
                if (availability >= minAvailability) {
                    if (price < cheapest || cheapest == 0) {
                        cheapest = price;
                        System.out.println(Arrays.toString(amountPerComponent));
                        System.out.println(price);
                    }

                    if (level == amountPerComponent.length - 1) {
                        checkedAllSets = true;
                    }

                    amountPerComponent[level] = 0;

                    if (optimisationMainLoop(amountPerComponent, availableComponents, minAvailability, level + 1, cheapest)) {
                        checkedAllSets = true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int[] legeLijst = new int[6];

        optimisation(legeLijst, 0.9999);
    }
}
