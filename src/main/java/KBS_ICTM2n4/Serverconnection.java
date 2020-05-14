package KBS_ICTM2n4;
//dit maakt de verbinding tussen de client en de servers

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Serverconnection {

    // Wanneer de makeConnectionWithServer-functie wordt aangeroepen, wordt de
    // sessie die daarin tot stand komt in dit Session-object opgeslagen
    // Dit object wordt weer op null gezet in de closeConnectionWithServer-functie.
    public Session session;
    private Serverconnection[] serverConnections = MonitoringDialog.getServerConnections();

    // Deze functie poogt een session met het opgegeven IP-adres op te slaan in het
    // bovenstaande static Session-object "session",
    // en geeft true terug als dit lukt, en false als dit niet lukt.
    public boolean makeConnectionWithServer(String destinationIP, String username, String password) {

        // Dit poortnummer wordt gebruikt om met SSH in te loggen.
        int port = 3389;

        try {

            // We maken een nieuw Java Secure Channel-object aan (JSch), en starten hier vervolgens een sessie mee.
            JSch jsch = new JSch();
            session = jsch.getSession(username, destinationIP, port);
            session.setPassword(password);
            try {
                session.setTimeout(1000);
            } catch (JSchException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            // (Momenteel controleren we de sleutel van de host niet. Als we een veiligere verbinding willen maken, kunnen StrictHostKeyChecking
            // wel activeren, maar dan moeten we de host en sleutel eerst bekendmaken aan het systeem.)
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            // Als "session" verbonden is, geven we true terug. Als er een error heeft plaatsgevonden of er geen verbinding is, geven we false terug.
            if(session.isConnected()) {
                System.out.println("Verbinding is geslaagd.");
                return true;
            } else {
                System.out.println("Het is niet gelukt om een verbinding te maken.");
                return false;
            }

        } catch(Exception e) {
            System.out.println("Er is iets misgegaan bij verbinden met de server.");
            System.out.println(e);
            return false;
        }
    }

    // Deze functie controleert of de sessie een verbinding met de server heeft.
    public boolean serverConnected(int i) {
        Serverconnection[] serverConnections =  MonitoringDialog.getServerConnections();
        Serverconnection serverConnection = serverConnections[i];
        if(serverConnection.session.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    // Deze functie geeft een String terug waarin de uptime wordt weergegeven van de server waarmee.
    // een SSH-connectie is gemaakt in het static Session-object "session" (in het format "X week(s), X day(s), X hour(s), X minute(s)").
    // Als de session niet verbonden is geeft het een lege String terug.
    public String serverUpTime() {

        // Dit SSH-command zal de uptime weergeven.
        String command = "uptime -p\nexit\n";

        try {

            // We maken een Channel-object aan van het type "shell",
            // welke we kunnen gebruiken om het command op te geven, en de resulterende output te lezen.
            Channel channel = session.openChannel("shell");

            // We maken een inputStream aan met de gegevens die we van het command krijgen.
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(command.getBytes());
            channel.setInputStream(byteArrayInputStream);
            InputStream inputStream = channel.getInputStream();

            // Er wordt verbonden met het shell-kanaal.
            channel.connect();

            // Met de volgende code wordt de inhoud van de inputStream overgeschreven naar een outputStream.

            // Een gedeelte van de inputstream wordt overgezet naar de buffer, en de lengte van deze overschrijving
            // wordt vastgesteld als "length". Zolang length groter dan 0 is wordt de buffer overgeschreven naar de outputstream.

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            // Het kanaal wordt weer gesloten.
            channel.disconnect();

            // De inhoudt van outputStream wordt in losse strings geknipt,
            // waarbij elke nieuwe regel (na een enter: regex "\\r?\\n") een nieuwe string is in de array genaamd lines.
            String[] lines = outputStream.toString("UTF-8").split("\\r?\\n");

            String output = null;

            // De inhoud die we willen teruggeven staat in de regel die begint met "up ".
            for (String line : lines) {
                if (line.startsWith("up ")) {
                    output = line;
                }
            }

            // We willen slechts de uptime in woorden teruggeven.
            output = output.replace("up ", "");

            return output;

            // Als er iets mis is gegaan, geven we een lege String terug.
        } catch (Exception e) {
            System.out.println("Er is iets misgegaan bij het achterhalen van de uptime.");
            return "";
        }
    }

    // Deze functie geeft een String terug, dat het percentage van het CPU dat in gebruik is weergeeft.
    public String serverCpuUsed() {

        // Dit SSH-command zal informatie over het CPU-gebruik weergeven.
        String command = "iostat\nexit";

        try {

            // We maken een Channel-object aan van het type "shell",
            // welke we kunnen gebruiken om het command op te geven, en de resulterende output te lezen.
            Channel channel = session.openChannel("shell");

            // We maken een inputStream aan met de gegevens die we van het command krijgen.
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(command.getBytes());
            channel.setInputStream(byteArrayInputStream);
            InputStream inputStream = channel.getInputStream();

            // Er wordt verbonden met het shell-kanaal.
            channel.connect();

            // Met de volgende code wordt de inhoud van de inputStream overgeschreven naar een outputStream.

            // Aangezien het "top"-command blijft updaten, kunnen we niet bytes blijven lezen tot het einde.
            // Als we de String "KiB Mem" lezen, hebben we de informatie die we nodig hebben.
            // (Voor het geval dat kunnen we ook stoppen als de totale output een bepaalde lengte heeft bereikt, maar die controle
            // levert misschien vertraging op.)
            // (Kan waarschijnlijk efficiënter en netter).

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;

            while (true) {
                length = inputStream.read(buffer);
                outputStream.write(buffer, 0, length);
                if(outputStream.toString().contains("Device")) {
                    break;
                }
            }

            // Het kanaal wordt weer gesloten.
            channel.disconnect();

            // De inhoudt van outputStream wordt in losse strings geknipt,
            // waarbij elke nieuwe regel (na een enter: regex "\\r?\\n") een nieuwe string is in de array genaamd lines.
            String[] lines = outputStream.toString().split("\\r?\\n");

            // De regel die begint met "%Cpu(s)" bevat de informatie die we nodig hebben.
            boolean lastLineAvgCpu = false;
            String relevantLine = null;

            for (String line : lines) {
                if (lastLineAvgCpu) {
                    relevantLine = line;
                    lastLineAvgCpu = false;
                }
                if (line.startsWith("avg-cpu:")) {
                    lastLineAvgCpu = true;
                }
            }

            // Deze regel splitsen we weer op bij elke spatie.
            String[] splitLine = relevantLine.split(" ");

            ArrayList<String> lineContent = new ArrayList<>();

            // Omdat er meerdere spaties na elkaar voorkomen, zitten er nu Strings in de lineContent-array die geen inhoud hebben.
            // Daarom maken we een ArrayList met alle andere Strings.
            for (int i = 0; i < splitLine.length; i++) {
                if(!(splitLine[i].equals(""))) {
                    lineContent.add(splitLine[i]);
                }
            }

            // De double met het percentage ongebruikte CPU staat op de zesde positie in de nieuwe array de twee spaties in dit stukje tekst.
            double idlePercentage = Double.parseDouble(lineContent.get(5));

            // Vervolgens achterhalen we hiermee het percentage van het CPU dat wel in gebruik is, en ronden we deze double af op één getal achter de komma.
            double usedPercentage = 100 - idlePercentage;
            usedPercentage = Math.round(usedPercentage * 10) / 10.0;

            // Hier plakken we nog een procentteken aan vast
            String output = usedPercentage + "%";

            return output;

            // Als er iets mis is gegaan, geven we een lege String terug.
        } catch (Exception e) {
            System.out.println("Er is iets misgegaan bij het achterhalen van het CPU-gebruik.");
            return "";
        }
    }

    // Deze functie geeft een String terug die de hoeveelheid diskruimte die beschikbaar is in bytes aangeeft,
    // alsook het percentage dat dit is van de gehele diskruimte in bytes.
    public String serverDiskSpaceAvailable() {

        // Dit SSH-command zal informatie over de diskruimte weergeven.
        String command = "df -h\nexit\n";

        try {

            // We maken een Channel-object aan van het type "shell",
            // welke we kunnen gebruiken om het command op te geven, en de resulterende output te lezen.
            Channel channel = session.openChannel("shell");

            // We maken een inputStream aan met de gegevens die we van het command krijgen.
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(command.getBytes());
            channel.setInputStream(byteArrayInputStream);
            InputStream inputStream = channel.getInputStream();

            // Er wordt verbonden met het shell-kanaal.
            channel.connect();

            // Met de volgende code wordt de inhoud van de inputStream overgeschreven naar een outputStream.

            // Een gedeelte van de inputstream wordt overgezet naar de buffer, en de lengte van deze overschrijving
            // wordt vastgesteld als "length". Zolang length groter dan 0 is wordt de buffer overgeschreven naar de outputstream.

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            // Het kanaal wordt weer gesloten.
            channel.disconnect();

            // De inhoudt van outputStream wordt in losse strings geknipt,
            // waarbij elke nieuwe regel (na een enter: regex "\\r?\\n") een nieuwe string is in de array genaamd lines.
            String[] lines = outputStream.toString("UTF-8").split("\\r?\\n");

            // De informatie die we nodig hebben staat in de regel die begint met "/dev/mapper/cl-root".
            // (Dit kan dynamischer, maar het lijkt in ieder geval te werken voor de servers die we gebruiken in Skylab.
            // Als het niet blijkt te werken moeten we de eerste regel na de regel die begint met "Filesystem" vinden.)

            String relevantLine = null;

            for (String line : lines) {
                if (line.startsWith("/dev/mapper/cl-root")) {
                    relevantLine = line;
                }
            }

            // Deze regel splitsen we weer op bij elke spatie.
            String[] splitLine = relevantLine.split(" ");

            ArrayList<String> lineContent = new ArrayList<>();

            // Omdat er meerdere spaties na elkaar voorkomen, zitten er nu Strings in de lineContent-array die geen inhoud hebben.
            // Daarom maken we een ArrayList met alle andere Strings.
            for (int i = 0; i < splitLine.length; i++) {
                if(!(splitLine[i].equals(""))) {
                    lineContent.add(splitLine[i]);
                }
            }

            // De vierde van deze Strings bevat het percentage van de diskruimte dat gebruikt wordt,
            // én een procentteken dat we weg moeten werken om de inhoud als integer te gebruiken
            int percentageUsed = Integer.parseInt(lineContent.get(4).replace("%", ""));

            // We gebruiken dit om de beschikbare ruimte te achterhalen.
            int percentageAvailable = 100 - percentageUsed;

            // In de vierde String staat het aantal bytes dat beschikbaar is,
            // en in de vierde String staat het totale aantal bytes aan diskruimte in /dev/mapper/cl-root.
            // We reigen deze gegevens aan elkaar in de vorm die willen teruggeven.
            String output = lineContent.get(3) + " (" + percentageAvailable + "% of " + lineContent.get(1) + ")";

            return output;

            // Als er iets mis is gegaan, geven we een lege String terug.
        } catch (Exception e) {
            System.out.println("Er is iets misgegaan bij het achterhalen van de beschikbare diskruimte.");
            return "";
        }
    }

    // Deze functie sluit de connectie van het Sessie-object, en maakt dit object ook leeg voor de volgende keer dat er een verbinding wordt gemaakt.
    public void closeConnectionWithServer() {
        session.disconnect();
        session = null;
    }

//    public static void main(String[] args) {
//
//        String upTime = null;
//        String diskSpace = null;
//        String cpuUsed = null;
//
//        if(makeConnectionWithServer("192.168.1.113", "root", "Teamsvmware01!")) {
//            upTime = serverUpTime();
//            diskSpace = serverDiskSpaceAvailable();
//            cpuUsed = serverCpuUsed();
//
//            closeConnectionWithServer();
//        }
//
//        System.out.println("---");
//        System.out.println("Uptime: " + upTime);
//        System.out.println("Diskruimte beschikbaar: " + diskSpace);
//        System.out.println("Percentage van CPU in gebruik: " + cpuUsed);
//    }
}
