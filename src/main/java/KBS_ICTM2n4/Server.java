package KBS_ICTM2n4;

import java.util.ArrayList;

// Deze class is voor het aanmaken van een server, met alle attributen.
public class Server {

  private int id;
  private String type;
  private String name;
  private double availability;
  private double price;

  public Server(int id, String type, String name, double availability, double price) {
    this.id = id;
    this.type = type;
    this.name = name;
    this.availability = availability;
    this.price = price;
  }

  public Server(String type, String name, double availability, double price) {
    this.type = type;
    this.name = name;
    this.availability = availability;
    this.price = price;
  }

  public int getId(){
    return this.id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getavailability() {
    return this.availability;
  }

  public void setavailability(double availability) {
    this.availability = availability;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  // een getter voor de serverlijst die word gebruikt voor de POC met alle attributen
  public static ArrayList<Server> getServerList() {
    ArrayList<Server> servers = new ArrayList<>();
    Server db1 = new Server(0, "database", "HAL9001DB", 0.90, 5100);
    Server db2 = new Server(1, "database", "HAL9002DB", 0.95, 7700);
    Server db3 = new Server(2, "database", "HAL9003DB", 0.98, 12200);
    Server ws1 = new Server(3, "webserver", "HAL9001W", 0.80, 2200);
    Server ws2 = new Server(4, "webserver", "HAL9002W", 0.90, 3200);
    Server ws3 = new Server(5, "webserver", "HAL9003W", 0.95, 5100);
    servers.add(db1);
    servers.add(db2);
    servers.add(db3);
    servers.add(ws1);
    servers.add(ws2);
    servers.add(ws3);

    return servers;
  }

}
