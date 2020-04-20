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

      JSONArray serverList = new JSONArray();
      serverList.add(obj);
      System.out.println(serverList);

      serverList.forEach(emp -> parseServerObject((JSONObject) emp));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

  }

}