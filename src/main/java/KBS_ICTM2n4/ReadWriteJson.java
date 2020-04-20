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

    JSONObject serverObject = new JSONObject();
    serverObject.put("Server", serverData);

    JSONArray serverlist = new JSONArray();
    serverlist.add(serverObject);

    try (FileWriter file = new FileWriter("server.json")) {
      file.write(serverlist.toJSONString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}