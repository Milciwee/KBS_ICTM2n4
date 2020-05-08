package KBS_ICTM2n4;
//dit maakt de verbinding tussen de client en de servers

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Serverconnection {

    public static Session session;

    public static boolean makeConnectionWithServer(String destinationIP) {

        String host = destinationIP;
        String username = "root"; //root
        String password = "Teamsvmware01!"; //Teamsvmware01!
        int port = 3389; //3389

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

            return output;

        } catch (Exception e) {

            System.out.println("Er is iets misgegaan bij het achterhalen van de uptime");
            return "";

        }
    }

    public static void closeConnectionWithServer() {
        session.disconnect();
        session = null;
    }

    public static void main(String[] args) {

        String upTime = null;

        //145.44.233.16

        if(makeConnectionWithServer("145.44.233.16")) {
            upTime = serverUpTime();
            closeConnectionWithServer();
        }

        System.out.println("---");
        System.out.println(upTime);

    }
}
