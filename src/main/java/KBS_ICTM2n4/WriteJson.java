package KBS_ICTM2n4;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WriteJson {

  // method om ingevulde waardes in een JSON file op te slaan
  public static void saveDesign(ArrayList<Server> servers, String designName, ArrayList<Integer> serverAmounts) {
    int counter = 0;
    JSONArray serverList = new JSONArray();
    for (Server server : servers) {
      JSONObject serverData = new JSONObject();
      JSONObject serverObject = new JSONObject();
      serverData.put("name", server.getName());
      serverData.put("amount", serverAmounts.get(counter));
      serverObject.put("server", serverData);
      serverList.add(serverObject);
      counter++;

    }

    try (FileWriter file = new FileWriter("src/savedDesigns/" + designName + ".json")) {
      file.write(serverList.toJSONString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}