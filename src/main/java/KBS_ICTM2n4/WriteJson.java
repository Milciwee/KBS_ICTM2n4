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

  public static void saveDesign(ArrayList<Server> servers, String designName) {
    JSONArray serverList = new JSONArray();
    for (Server server : servers) {
      JSONObject serverData = new JSONObject();
      JSONObject serverObject = new JSONObject();
      serverData.put("name", server.getName());
      serverObject.put("server", serverData);
      serverList.add(serverObject);

    }

    try (FileWriter file = new FileWriter("src/savedDesigns/" + designName + ".json")) {
      file.write(serverList.toJSONString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    Server server1 = new Server("database", "db1", 98, 2500);
    Server server2 = new Server("database", "db2", 98, 2500);
    Server server3 = new Server("database", "db3", 95, 2500);
    Server server4 = new Server("webserver", "web1", 80, 2500);
    Server server5 = new Server("webserver", "web2", 90, 2500);
    Server server6 = new Server("webserver", "web3", 95, 2500);
    Server server7 = new Server("webserver", "web3", 95, 2500);

    ArrayList<Server> array = new ArrayList<>();
    array.add(server1);
    array.add(server2);
    array.add(server3);
    array.add(server4);
    array.add(server5);
    array.add(server6);
    array.add(server7);

    WriteJson.saveDesign(array, "Design1");

    

  }

}