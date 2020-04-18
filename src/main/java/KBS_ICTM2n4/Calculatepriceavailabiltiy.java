package KBS_ICTM2n4;

import java.util.ArrayList;

//krijgt aantal servers mee, en geeft de kosten en het beschikbaarheidspercentage terug
public class Calculatepriceavailabiltiy {

  public ArrayList<Double> calculateAvailabiltyWebservers(ArrayList<Server> servers) {
    ArrayList<Double> a = new ArrayList<>();
    for (Server server : servers) {
      a.add((1 - (server.getAvailabilty() / 100)));
    }
    return a;
  }

  public ArrayList<Double> calculateAvailabiltyDatabaseservers(ArrayList<Server> servers) {
    ArrayList<Double> a = new ArrayList<>();
    for (Server server : servers) {
      a.add((1 - (server.getAvailabilty() / 100)));
    }
    return a;
  }

}
