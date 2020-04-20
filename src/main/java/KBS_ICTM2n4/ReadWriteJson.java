package KBS_ICTM2n4;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ReadWriteJson {

  public static void main(String[] args) {

    JSONObject serverData = new JSONObject();
    serverData.put("name", "wb1");
    serverData.put("type", "webserver");
    serverData.put("availability", "98%");
    serverData.put("Price", "2500");

    JSONObject serverData2 = new JSONObject();
    serverData2.put("name", "wb2");
    serverData2.put("type", "webserver");
    serverData2.put("availability", "97%");
    serverData2.put("Price", "3000");

    JSONObject serverObject = new JSONObject();
    serverObject.put("Server", serverData);
    serverObject.put("Server", serverData2);

    JSONArray serverlist = new JSONArray();
    serverlist.add(serverObject);

    try (FileWriter file = new FileWriter("src/savedDesigns/server.json")) {
      file.write(serverlist.toJSONString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}