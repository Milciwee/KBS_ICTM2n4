package KBS_ICTM2n4;

public class Server {

  private String type;
  private String name;
  private double availabilty;
  private double price;


  public Server(String type, String name, double availabilty, double price) {
    this.type = type;
    this.name = name;
    this.availabilty = availabilty;
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

  public double getAvailabilty() {
    return this.availabilty;
  }

  public void setAvailabilty(double availabilty) {
    this.availabilty = availabilty;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  
  

}