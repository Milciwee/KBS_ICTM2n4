package KBS_ICTM2n4;

import java.util.ArrayList;
import java.util.Timer;
import java.util.logging.Handler;

import org.apache.commons.lang3.ArrayUtils;

//nog niks mee doen
public final class Main {
    static Timer timer;//new Timer(); Timer aanmaken zodat deze gebruikt kan worden door de functies.
    private Main() {
    }

    /*
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        Screen test = new Screen();
        //Timer timer = new Timer();
        // timer.schedule(new RefreshLoop(), 0, 3000);


    }
    public static void startTimer(){
        timer = new Timer();
        timer.schedule(new RefreshLoop(), 0, 3000);
        System.out.println("---Start Monitoring---");

    }
    public static void stopTimer(){
        //Timer timer = new Timer();
        timer.purge();
        timer.cancel();
        System.out.println("---Stop Monitoring---");

    }



}
