package KBS_ICTM2n4;
//dit maakt de verbinding tussen de client en de servers

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Serverconnection {

    //145.44.233.16

    public static boolean canConnectWithServer(String DestinationIP) {

        String host = DestinationIP;
        String username = "root"; //root
        String password = "Teamsvmware01!"; //Teamsvmware01!
        int port = 3389; //3389

        try {

            JSch jsch = new JSch();
            Session session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();

            if(session.isConnected()) {
                System.out.println("Verbinding is geslaagd.");
                session.disconnect();
                return true;
            } else {
                System.out.println("Het is niet gelukt om een verbinding te maken.");
                session.disconnect();
                return false;
            }

        } catch(Exception e) {

            System.out.println("Onbekende error.");
            return false;

        }
    }

    public static void main(String[] args) {
        if(canConnectWithServer("145.44.233.16")) {
            System.out.println("Dat lukte, blijkbaar.");
        }
    }
}
