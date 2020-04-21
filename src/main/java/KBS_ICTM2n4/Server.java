package KBS_ICTM2n4;

import java.util.ArrayList;

public class Server {

  private String type;
  private String name;
  private double availability;
  private double price;

  public Server(String type, String name, double availability, double price) {
    this.type = type;
    this.name = name;
    this.availability = availability;
    this.price = price;
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

  public static ArrayList<Server> getServerList() {
    ArrayList<Server> servers = new ArrayList<>();
    Server db1 = new Server("database", "Database server 1", 0.90, 5100);
    Server db2 = new Server("database", "Database server 2", 0.95, 7700);
    Server db3 = new Server("database", "Database server 3", 0.98, 12200);
    Server ws1 = new Server("webserver", "Webserver 1", 0.80, 2200);
    Server ws2 = new Server("webserver", "Webserver 2", 0.90, 3200);
    Server ws3 = new Server("webserver", "Webserver 3", 0.95, 5100);
    servers.add(db1);
    servers.add(db2);
    servers.add(db3);
    servers.add(ws1);
    servers.add(ws2);
    servers.add(ws3);


    return servers;
  }

}