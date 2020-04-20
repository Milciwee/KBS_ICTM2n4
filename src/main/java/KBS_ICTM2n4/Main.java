package KBS_ICTM2n4;

import java.util.ArrayList;

//nog niks mee doen
/**
 * Hello world!
 */
public final class Main {
    private Main() {
    }

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        //hier worden de designs toegevoegd aan de dropdown,deze moeten later vanaf een file in de root folder komen
        Screen.dropdownitemsedit.add("test1");
        Screen.dropdownitemsedit.add("test2");

        Screen test = new Screen();

        //server beschikbaarheids test (verwachte percentage uitkomst: ~ 99,9910%)
        Server server1 = new Server("database", "db1", 98, 2500);
        Server server2 = new Server("database", "db2", 98, 2500);
        Server server3 = new Server("database", "db3", 95, 2500);
        Server server4 = new Server("webserver", "web1", 80, 2500);
        Server server5 = new Server("webserver", "web2", 90, 2500);
        Server server6 = new Server("webserver", "web3", 95, 2500);
        Server server7 = new Server("webserver", "web3", 95, 2500);

        ArrayList<Server> array = new ArrayList<>();
        array.add(server1);
        array.add(server2);
        array.add(server3);
        array.add(server4);
        array.add(server5);
        array.add(server6);
        array.add(server7);

        double a = Calculatepriceavailabiltiy.calculateAvailabilty(array);
        System.out.println(100 * (0.99998 * a));
        // test voor berekenen prijs servers
        double b  = Calculatepriceavailabiltiy.calculateTotalPrice(array);
        System.out.println(b);


    }
    // test
}
