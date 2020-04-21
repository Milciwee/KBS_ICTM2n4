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

  public static void saveDesign(ArrayList<Server> servers, String designName, ArrayList<Integer> serverAmounts) {
    int counter = 0;
    JSONArray serverList = new JSONArray();
    try {
      for (Server server : servers) {
        JSONObject serverData = new JSONObject();
        JSONObject serverObject = new JSONObject();
        serverData.put("name", server.getName());
        serverData.put("amount", serverAmounts.get(counter));
        serverObject.put("server", serverData);
        serverList.add(serverObject);
        counter++;

      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    try (FileWriter file = new FileWriter("src/savedDesigns/" + designName + ".json")) {
      file.write(serverList.toJSONString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    ArrayList<Server> array = Server.getServerList();

    ArrayList<Integer> array2 = new ArrayList<>();
    int test = 1;
    int test2 = 2;
    array2.add(test);
    array2.add(test2);
    array2.add(test);
    array2.add(test2);
    array2.add(test);
    array2.add(test2);

    WriteJson.saveDesign(array, "Design1", array2);

  }

}