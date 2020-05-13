package KBS_ICTM2n4;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// Structure van de JSON file is als volgt: 1 Array, met daarin 6 Objects (voor elke server 1 object).
// In elke serverObject zitten twee waardes, 'Amount' en 'Name'. Amount is aantal van die server,
// name is de naam van die server. De volgorde van de servers is altijd gelijk DB1-DB2-DB3-WB1-WB2-WB3.

public class WriteJson {

  // method om ingevulde waardes in een JSON file op te slaan
  public static void saveDesign(ArrayList<Server> servers, String designName, ArrayList<Integer> serverAmounts) {
    int counter = 0;
    // De array aanmaken
    JSONArray serverList = new JSONArray();
    for (Server server : servers) {
      // JSONObject voor de data in de Server Object, dus de naam en aantal
      JSONObject serverData = new JSONObject();
      // JSONObject voor de 6 server objects in de array
      JSONObject serverObject = new JSONObject();
      serverData.put("name", server.getName());
      serverData.put("amount", serverAmounts.get(counter));
      serverObject.put("server", serverData);
      // Voegt data aan de Array toe
      serverList.add(serverObject);
      counter++;

    }

    // Maak een file aan met de designName en write de Array van hiervoor in de file
    try (FileWriter file = new FileWriter("src/savedDesigns/" + designName + ".json")) {
      file.write(serverList.toJSONString());
      file.flush();
    } catch (IOException e) {
      System.out.println("Invalid permissions to write a file or not enough disk space");
    }

  }

}