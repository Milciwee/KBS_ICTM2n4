package KBS_ICTM2n4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {

  // method om de gegevens van het design op te halen, heeft een designName dat
  // overeenkomt met een file naam nodig
  public static int[] readDesign(String designName) {
    JSONParser jsonParser = new JSONParser();
    int[] returnArray = new int[6];

    try (FileReader reader = new FileReader("src/savedDesigns/" + designName + ".json")) {

      Object obj = jsonParser.parse(reader);
      int counter = 0;
      JSONArray serverList = (JSONArray) obj;

      for (Object object : serverList) {
        int test = parseServerObject((JSONObject) object);
        returnArray[counter] = test;
        counter++;
      }
      return returnArray;
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;

  }

  // method voor het ophalen van alle file names, heeft een designName dat
  // overeenkomt met een file naam nodig
  public static String[] readDesignNames(String designName) {
    JSONParser jsonParser = new JSONParser();
    String[] returnArray = new String[6];

    try (FileReader reader = new FileReader("src/savedDesigns/" + designName + ".json")) {

      Object obj = jsonParser.parse(reader);
      int counter = 0;
      JSONArray serverList = (JSONArray) obj;

      for (Object object : serverList) {
        String test = parseServerObject2((JSONObject) object);
        returnArray[counter] = test;
        counter++;
      }
      return returnArray;
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;

  }

  private static int parseServerObject(JSONObject server) {
    JSONObject serverObject = (JSONObject) server.get("server");
    int amount = ((Number) serverObject.get("amount")).intValue();
    String name = (String) serverObject.get("name");
    return amount;
  }

  private static String parseServerObject2(JSONObject server) {
    JSONObject serverObject = (JSONObject) server.get("server");
    int amount = ((Number) serverObject.get("amount")).intValue();
    if (amount != 0) {
      String name = (String) serverObject.get("name");
      return name;
    }
    return null;

  }

}
