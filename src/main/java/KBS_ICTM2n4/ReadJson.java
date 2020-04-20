package KBS_ICTM2n4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {

  public static void main(String[] args) {

    // JSON parser object om file te parsen
    JSONParser jsonParser = new JSONParser();

    try (FileReader reader = new FileReader("src/savedDesigns/server.json")) {

      // JSON file readen
      Object obj = jsonParser.parse(reader);

      JSONArray serverList = (JSONArray) obj;
      System.out.println(serverList);

      serverList.forEach(server -> parseServerObject((JSONObject) server));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

  }

  private static void parseServerObject(JSONObject server) {
    JSONObject serverObject = (JSONObject) server.get("Server");

    String type = (String) serverObject.get("type");
    System.out.println(type);

    String availability = (String) serverObject.get("availability");
    System.out.println(availability);

    String price = (String) serverObject.get("price");
    System.out.println(price);

    String name = (String) serverObject.get("name");
    System.out.println(name);
  }

}