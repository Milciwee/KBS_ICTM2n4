package KBS_ICTM2n4;

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


  
  

}