package KBS_ICTM2n4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteServerDialog extends JDialog implements ActionListener {
    JLabel jllabel = new JLabel("Do you want to delete this server?");
    JButton jbCancel = new JButton("Cancel");
    JButton jbConfirm = new JButton("Confirm");
    public static int welkeServer = 1;

    public DeleteServerDialog(JFrame frame){
        super(frame, true);
        setTitle("Delete Server");
        setSize(245, 105);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        add(mainPanel);
        //Componenten toevoegen
        mainPanel.add(jllabel);
        mainPanel.add(jbCancel);
        mainPanel.add(jbConfirm);
        //Componenten bounds
        jllabel.setBounds(10,0, 250, 30);
        jbCancel.setBounds(10, 30, 100, 30);
        jbConfirm.setBounds(120, 30, 100, 30);
        //Actionlistener
        jbCancel.addActionListener(this);
        jbConfirm.addActionListener(this);

        setVisible(true);
    }

    public static void setWelkeServer(int welkeServer) {
        DeleteServerDialog.welkeServer = welkeServer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbCancel){
            dispose();
        }
        if (e.getSource() == jbConfirm){
            DeleteServer();
            dispose();
        }
    }

    public void DeleteServer(){
        if (welkeServer == 1){
            //Schuif alles door
            //Server 2 naar 1
            Screen.jlServernaam1.setText(Screen.jlServernaam2.getText());
            Screen.jlStatus1.setText(Screen.jlStatus2.getText());
            Screen.jpStatuspanel1.setBackground(Screen.jpStatuspanel2.getBackground());
            Screen.jtaInfo1.setText(Screen.jtaInfo2.getText());
            //Server 3 naar 2
            Screen.jlServernaam2.setText(Screen.jlServernaam3.getText());
            Screen.jlStatus2.setText(Screen.jlStatus3.getText());
            Screen.jpStatuspanel2.setBackground(Screen.jpStatuspanel3.getBackground());
            Screen.jtaInfo2.setText(Screen.jtaInfo3.getText());
            //Server 4 naar 3
            Screen.jlServernaam3.setText(Screen.jlServernaam4.getText());
            Screen.jlStatus3.setText(Screen.jlStatus4.getText());
            Screen.jpStatuspanel3.setBackground(Screen.jpStatuspanel4.getBackground());
            Screen.jtaInfo3.setText(Screen.jtaInfo4.getText());
            //setVisible(false) welke server
            if (MonitoringDialog.serverCount == 1){
                Screen.jpServer1.setVisible(false);
            } else if (MonitoringDialog.serverCount == 2){
                Screen.jpServer2.setVisible(false);
            } else if (MonitoringDialog.serverCount == 3){
                Screen.jpServer3.setVisible(false);
            } else if (MonitoringDialog.serverCount == 4){
                Screen.jpServer4.setVisible(false);
            }
            MonitoringDialog.serverCount--;
        } else if (welkeServer == 2){
            //Schuif alles door
            //Server 3 naar 2
            Screen.jlServernaam2.setText(Screen.jlServernaam3.getText());
            Screen.jlStatus2.setText(Screen.jlStatus3.getText());
            Screen.jpStatuspanel2.setBackground(Screen.jpStatuspanel3.getBackground());
            Screen.jtaInfo2.setText(Screen.jtaInfo3.getText());
            //Server 4 naar 3
            Screen.jlServernaam3.setText(Screen.jlServernaam4.getText());
            Screen.jlStatus3.setText(Screen.jlStatus4.getText());
            Screen.jpStatuspanel3.setBackground(Screen.jpStatuspanel4.getBackground());
            Screen.jtaInfo3.setText(Screen.jtaInfo4.getText());
            //setVisible(false) welke server
            if (MonitoringDialog.serverCount == 2){
                Screen.jpServer2.setVisible(false);
            } else if (MonitoringDialog.serverCount == 3){
                Screen.jpServer3.setVisible(false);
            } else if (MonitoringDialog.serverCount == 4){
                Screen.jpServer4.setVisible(false);
            }
            MonitoringDialog.serverCount--;
        } else if (welkeServer == 3){
            //Schuif alles door
            //Server 4 naar 3
            Screen.jlServernaam3.setText(Screen.jlServernaam4.getText());
            Screen.jlStatus3.setText(Screen.jlStatus4.getText());
            Screen.jpStatuspanel3.setBackground(Screen.jpStatuspanel4.getBackground());
            Screen.jtaInfo3.setText(Screen.jtaInfo4.getText());
            //setVisible(false) welke server
            if (MonitoringDialog.serverCount == 3){
                Screen.jpServer3.setVisible(false);
            } else if (MonitoringDialog.serverCount == 4){
                Screen.jpServer4.setVisible(false);
            }
            MonitoringDialog.serverCount--;
        } else if (welkeServer == 4){
            Screen.jpServer4.setVisible(false);
            MonitoringDialog.serverCount--;
        }
    }
}
