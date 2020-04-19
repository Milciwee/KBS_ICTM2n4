package KBS_ICTM2n4;

import java.util.ArrayList;

//krijgt aantal servers mee, en geeft de kosten en het beschikbaarheidspercentage terug
public class Calculatepriceavailabiltiy {

  public static double calculateAvailabiltyWebservers(ArrayList<Server> servers) {
    ArrayList<Double> a = new ArrayList<>();
    for (Server server : servers) {
      if (server.getType().equals("webserver")) {
        a.add((1 - (server.getAvailabilty() / 100)));
      }
    }
    double temp = a.get(0);
    for (int i = 1; i < a.size(); i++) {
      temp = temp * a.get(i);
    }
    return 1 - temp;
  }

  public static double calculateAvailabiltyDatabaseservers(ArrayList<Server> servers) {
    ArrayList<Double> a = new ArrayList<>();
    for (Server server : servers) {
      if (server.getType().equals("database")) {
        a.add((1 - (server.getAvailabilty() / 100)));
      }
    }
    double temp = a.get(0);
    for (int i = 1; i < a.size(); i++) {
      temp = temp * a.get(i);
    }
    return 1 - temp;
  }

  public static double calculateAvailabilty(ArrayList<Server> servers) {
    double webservers = calculateAvailabiltyWebservers(servers);
    double databaseservers = calculateAvailabiltyDatabaseservers(servers);
    double availability = (webservers * databaseservers);
    return availability;

  }

}
