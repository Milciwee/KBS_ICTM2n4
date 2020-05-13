package KBS_ICTM2n4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Structure van de JSON file is als volgt: 1 Array, met daarin 6 Objects (voor elke server 1 object).
// In elke serverObject zitten twee waardes, 'Amount' en 'Name'. Amount is aantal van die server,
// name is de naam van die server. De volgorde van de servers is altijd gelijk DB1-DB2-DB3-WB1-WB2-WB3.

public class ReadJson {

  // method voor het ophalen van alle file names, heeft een designName nodig dat
  // overeenkomt met een file naam.
  public static int[] readDesign(String designName) {
    JSONParser jsonParser = new JSONParser();
    int[] returnArray = new int[6];

    try (FileReader reader = new FileReader("src/savedDesigns/" + designName + ".json")) {

      int counter = 0;
      // De file word geparsed naar een object en vervolgens naar een JSONArray
      // gemaakt.
      Object obj = jsonParser.parse(reader);
      JSONArray serverList = (JSONArray) obj;

      // For loop om door alle objects in de JSON file te gaan
      for (Object object : serverList) {
        // Verwijst naar de method die onderaan staat.
        int amount = parseServerObject((JSONObject) object);
        returnArray[counter] = amount;
        counter++;
      }
      return returnArray;
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    } catch (IOException e) {
      System.out.println("Invalid permissions");
    } catch (ParseException e) {
      System.out.println("Parsing went wrong");
    }
    return null;

  }

  // method voor het ophalen van alle file names, heeft een designName nodig dat
  // overeenkomt met een file naam.
  public static String[] readDesignNames(String designName) {
    JSONParser jsonParser = new JSONParser();
    String[] returnArray = new String[6];

    try (FileReader reader = new FileReader("src/savedDesigns/" + designName + ".json")) {

      Object obj = jsonParser.parse(reader);
      int counter = 0;
      JSONArray serverList = (JSONArray) obj;

      for (Object object : serverList) {
        String name = parseServerObject2((JSONObject) object);
        returnArray[counter] = name;
        counter++;
      }
      return returnArray;
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    } catch (IOException e) {
      System.out.println("Invalid permissions");
    } catch (ParseException e) {
      System.out.println("Parsing went wrong");
    }
    return null;

  }

  public static String readServer(String servername, String data) {
    JSONParser jsonParser = new JSONParser();

    try (FileReader reader = new FileReader("src/savedServers/" + servername + ".json")) {
      Object obj = jsonParser.parse(reader);
      if(data.equals("name")){
        String name = parseServerName((JSONObject) obj);
        return name;
      } else if(data.equals("ip")){
        String ip = parseServerIp((JSONObject) obj);
        return ip;
      } else if(data.equals("password")){
        String password = parseServerPassword((JSONObject) obj);
        return password;
      } else if(data.equals("hostname")){
        String hostname = parseServerHostname((JSONObject) obj);
        return hostname;
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    } catch (IOException e) {
      System.out.println("Invalid permissions");
    } catch (ParseException e) {
      System.out.println("Parsing went wrong");
    }
    return null;

  }

  // Method om amount op te halen van Server Object in de file.
  private static int parseServerObject(JSONObject server) {
    JSONObject serverObject = (JSONObject) server.get("server");
    int amount = ((Number) serverObject.get("amount")).intValue();
    return amount;
  }

  // Method om name op te halen van Server Object in de file.
  private static String parseServerObject2(JSONObject server) {
    JSONObject serverObject = (JSONObject) server.get("server");
    int amount = ((Number) serverObject.get("amount")).intValue();
    if (amount != 0) {
      String name = (String) serverObject.get("name");
      return name;
    }
    return null;

  }

  private static String parseServerName(JSONObject server) {
    JSONObject serverObject = (JSONObject) server.get("server");
    String name = (String) serverObject.get("name");
    return name;

  }

  private static String parseServerIp(JSONObject server) {
    JSONObject serverObject = (JSONObject) server.get("server");
    String name = (String) serverObject.get("ip");
    return name;

  }

  private static String parseServerPassword(JSONObject server) {
    JSONObject serverObject = (JSONObject) server.get("server");
    String name = (String) serverObject.get("password");
    return name;

  }

  private static String parseServerHostname(JSONObject server) {
    JSONObject serverObject = (JSONObject) server.get("server");
    String name = (String) serverObject.get("hostname");
    return name;

  }

  public static void main(String[] args) {
    String array = readServer("test", "name");
    System.out.println(array);


  }
}
