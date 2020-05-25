package KBS_ICTM2n4;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Timer;
import java.util.logging.Handler;

import org.apache.commons.lang3.ArrayUtils;

//nog niks mee doen
public final class Main {

    static Timer timer;//new Timer(); Timer aanmaken zodat deze gebruikt kan worden door de functies.

    public static void main(String[] args) {
        //kijkt of de bronmappen van de designs bestaan, zoniet, dan maakt hij ze aan.
        //er wordt dan ook gelijk 1 ontwerp toegevoegd waarvan alle waarden 1 zijn.
        File serverFolder = new File("src/savedServers");
        if (!serverFolder.exists() && !serverFolder.isDirectory()) {
            System.out.println("Server directory is missing");
            new File("src/savedServers").mkdirs();
        }
        File designFolder = new File("src/savedDesigns");
        if (!designFolder.exists() && !designFolder.isDirectory()) {
            System.out.println("Design directory is missing");
            new File("src/savedDesigns").mkdirs();
            ArrayList<Server> servers = Server.getServerList();
            String name = "new Design";
            ArrayList<Integer> serverAmount = new ArrayList<>();
            for(int i = 0; i < 6; i++){
                serverAmount.add(1);
            }
            WriteJson.saveDesign(servers, name, serverAmount);
        }
        //hierin wordt de Screen klasse aangemaakt
        Screen test = new Screen();
    }

    public static void startTimer(){
        //functie om de monitorfunctie te laten starten, hierdoor vraagt de applicatie elke 3 seconden informatie van de servers.
        timer = new Timer();
        timer.schedule(new RefreshLoop(), 0, 3000);
        System.out.println("---Start Monitoring---");
    }
    public static void stopTimer(){
        //functie om de monitorfunctie te laten stoppen
        timer.purge();
        timer.cancel();
        System.out.println("---Stop Monitoring---");
    }
}
