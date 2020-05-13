package KBS_ICTM2n4;

import java.util.TimerTask;

public class Test extends TimerTask{

  @Override
  public void run() {
    MonitoringDialog.refreshServers();

  }

  
}