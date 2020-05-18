package KBS_ICTM2n4;

import java.util.TimerTask;

public class RefreshLoop extends TimerTask{

  @Override
  public void run() {
    MonitoringDialog.refreshServers();

  }


}
