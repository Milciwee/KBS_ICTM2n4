package KBS_ICTM2n4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WriteJson {

  public static void main(String[] args) {

    // JSON object aanmaken voor de eerste server
    JSONObject serverData = new JSONObject();
    serverData.put("name", "wb1");
    serverData.put("type", "webserver");
    serverData.put("availability", "98%");
    serverData.put("price", "2500");

    JSONObject serverObject = new JSONObject();
    serverObject.put("Server", serverData);

    // JSOn object aanmaken voor de tweede server
    JSONObject serverData2 = new JSONObject();
    serverData2.put("name", "wb2");
    serverData2.put("type", "webserver");
    serverData2.put("availability", "97%");
    serverData2.put("price", "3000");

    JSONObject serverObject2 = new JSONObject();
    serverObject2.put("Server", serverData2);

    // Servers aan list toevoegen
    JSONArray serverlist = new JSONArray();
    serverlist.add(serverObject);
    serverlist.add(serverObject2);

    // JSON file writen met de
    try (FileWriter file = new FileWriter("src/savedDesigns/server.json")) {
      file.write(serverlist.toJSONString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  // json file deleten
  // File f = new File("src/savedDesigns/server.json");
  // f.delete();

}