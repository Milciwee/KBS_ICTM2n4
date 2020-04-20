package KBS_ICTM2n4;

import java.util.ArrayList;

//krijgt aantal servers mee, en geeft de kosten en het beschikbaarheidspercentage terug
public class Calculatepriceavailability {

  // Method voor beschikbaarheidspercentage voor de webservers
  public static double calculateavailabilityWebservers(ArrayList<Server> servers) {
    // tijdelijke arraylist met alle beschikbaarheden van de servers
    ArrayList<Double> a = new ArrayList<>();
    // for each loop om alle beschikbaarheden in de tijdelijke arraylist te doen
    for (Server server : servers) {
      if (server.getType().equals("webserver")) {
        a.add((1 - (server.getavailability() / 100)));
      }
    }
    // for loop om alle beschikbaarheden in de tijdelijke arraylist keer elkaar te
    // doen
    try {
      double temp = a.get(0);
      for (int i = 1; i < a.size(); i++) {
        temp = temp * a.get(i);
      }
      return 1 - temp;
    } catch (NullPointerException e) {
      System.out.println("No webservers found");
      return 0;
    }      

  }

  // Method voor beschikbaarheidspercentage voor de databaseservers
  public static double calculateAvailabilityDatabaseservers(ArrayList<Server> servers) {
    ArrayList<Double> a = new ArrayList<>();
    
    for (Server server : servers) {
      if (server.getType().equals("database")) {
        a.add((1 - (server.getavailability() / 100)));
      }
    }
    try {
      double temp = a.get(0);
      for (int i = 1; i < a.size(); i++) {
        temp = temp * a.get(i);
      }
      return 1 - temp;
    } catch (NullPointerException e) {
      System.out.println("No databaseservers found");
      return 0;
    }      
  }

  // uiteindelijke method om de volledige beschikbaarheidspercentage te berekenen
  // van volledig ontwerp
  public static double calculateavailability(ArrayList<Server> servers) {
    double webservers = calculateavailabilityWebservers(servers);
    double databaseservers = calculateAvailabilityDatabaseservers(servers);
    double availability = (webservers * databaseservers);
    return availability;

  }

  //Method voor prijs berekening voor webservers
  public static double calculatePriceWebservers(ArrayList<Server> servers) {
    double price = 0;
    for (Server server : servers) {
      if (server.getType().equals("webserver")) {
        price += server.getPrice();
      }
    }
    return price;
  }

  //Method voor prijs berekening voor databaseservers
  public static double calculatePriceDatabaseservers(ArrayList<Server> servers) {
    double price = 0;
    for (Server server : servers) {
      if (server.getType().equals("database")) {
        price += server.getPrice();
      }
    }
    return price;
  }

  //Method voor totale prijs
  public static double calculateTotalPrice(ArrayList<Server> servers) {
    double price = 0;
    for (Server server : servers) {
      price += server.getPrice();
    }

    return price;
  }

}
