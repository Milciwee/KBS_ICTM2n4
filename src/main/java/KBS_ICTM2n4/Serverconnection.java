package KBS_ICTM2n4;
//dit maakt de verbinding tussen de client en de servers

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class Serverconnection {

    public static Session session;

    public static boolean makeConnectionWithServer(String destinationIP) {

        String host = destinationIP;
        String username = "root"; //root
        String password = "Teamsvmware01!"; //Teamsvmware01!
        int port = 3389; //3389?

        try {

            JSch jsch = new JSch();
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            if(session.isConnected()) {
                System.out.println("Verbinding is geslaagd.");
                return true;
            } else {
                System.out.println("Het is niet gelukt om een verbinding te maken.");
                return false;
            }

        } catch(Exception e) {

            System.out.println("Er is iets misgegaan bij het maken van een verbinding met de server.");
            return false;

        }
    }

    public static String serverDiskSpaceAvailable() {

        String command = "df -h\nexit\n";

        try {

            Channel channel = session.openChannel("shell");

            channel.setInputStream(new ByteArrayInputStream(command.getBytes(StandardCharsets.UTF_8)));
            InputStream in = channel.getInputStream();
            StringBuilder outBuff = new StringBuilder();

            channel.connect();

            while (true) {
                for (int c; ((c = in.read()) >= 0); ) {
                    outBuff.append((char) c);
                }

                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    break;
                }
            }

            channel.disconnect();

            String[] lines = outBuff.toString().split("\\r?\\n");

            String relevantLine = null;

            for (String line : lines) {
                if (line.startsWith("/dev/mapper/cl-root")) {
                    relevantLine = line;
                }
            }

            String[] splitLine = relevantLine.split(" ");

            ArrayList<String> lineContent = new ArrayList<>();

            for (int i = 0; i < splitLine.length; i++) {
                if(!(splitLine[i].equals(""))) {
                    lineContent.add(splitLine[i]);
                }
            }

            int percentageUsed = Integer.parseInt(lineContent.get(4).replace("%", ""));

            int percentageAvailable = 100 - percentageUsed;

            String output = lineContent.get(3) + " (" + percentageAvailable + "% of " + lineContent.get(1) + ")";

            return output;

        } catch (Exception e) {

            System.out.println("Er is iets misgegaan bij het achterhalen van de beschikbare diskruimte.");
            return "";

        }
    }

    public static String serverUpTime() {

        //uptime, uptime -s en uptime -p werken
        String command = "uptime -p\nexit\n";

        try {

            Channel channel = session.openChannel("shell");

            channel.setInputStream(new ByteArrayInputStream(command.getBytes(StandardCharsets.UTF_8)));
            InputStream in = channel.getInputStream();
            StringBuilder outBuff = new StringBuilder();

            channel.connect();

            while (true) {
                for (int c; ((c = in.read()) >= 0); ) {
                    outBuff.append((char) c);
                }

                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    break;
                }
            }

            channel.disconnect();

            String[] lines = outBuff.toString().split("\\r?\\n");

            String output = null;

            for (String line : lines) {
                if (line.startsWith("up ")) {
                    output = line;
                }
            }

            output = output.replace("up ", "");

            return output;

        } catch (Exception e) {

            System.out.println("Er is iets misgegaan bij het achterhalen van de uptime.");
            return "";

        }
    }

    public static void closeConnectionWithServer() {
        session.disconnect();
        session = null;
    }

    public static void main(String[] args) {

        String upTime = null;
        String diskSpace = null;

        if(makeConnectionWithServer("192.168.1.2")) {
            upTime = serverUpTime();
            diskSpace = serverDiskSpaceAvailable();
            closeConnectionWithServer();
        }

        System.out.println("---");
        System.out.println("Uptime: " + upTime);
        System.out.println("Diskruimte beschikbaar: " + diskSpace);

    }
}
