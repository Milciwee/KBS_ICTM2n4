package KBS_ICTM2n4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {

  public static void readDesign() {
    JSONParser jsonParser = new JSONParser();

    try (FileReader reader = new FileReader("src/savedDesigns/server.json")) {

      // JSON file readen
      Object obj = jsonParser.parse(reader);

      JSONArray serverList = new JSONArray();
      serverList = (JSONArray) obj;

      serverList.forEach(server -> parseServerObject((JSONObject) server));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private static void parseServerObject(JSONObject server) {
    JSONObject serverObject = (JSONObject) server.get("Design1");

    String name = (String) serverObject.get("name");
    System.out.println(name);
  }

  public static void main(String[] args) {
    readDesign();
  }
}