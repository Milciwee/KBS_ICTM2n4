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
            Screen.Servernaam1.setText(Screen.Servernaam2.getText());
            Screen.Status1.setText(Screen.Status2.getText());
            Screen.Statuspanel1.setBackground(Screen.Statuspanel2.getBackground());
            Screen.Info1.setText(Screen.Info2.getText());
            //Server 3 naar 2
            Screen.Servernaam2.setText(Screen.Servernaam3.getText());
            Screen.Status2.setText(Screen.Status3.getText());
            Screen.Statuspanel2.setBackground(Screen.Statuspanel3.getBackground());
            Screen.Info2.setText(Screen.Info3.getText());
            //Server 4 naar 3
            Screen.Servernaam3.setText(Screen.Servernaam4.getText());
            Screen.Status3.setText(Screen.Status4.getText());
            Screen.Statuspanel3.setBackground(Screen.Statuspanel4.getBackground());
            Screen.Info3.setText(Screen.Info4.getText());
            //setVisible(false) welke server
            if (MonitoringDialog.serverCount == 1){
                Screen.Server1.setVisible(false);
            } else if (MonitoringDialog.serverCount == 2){
                Screen.Server2.setVisible(false);
            } else if (MonitoringDialog.serverCount == 3){
                Screen.Server3.setVisible(false);
            } else if (MonitoringDialog.serverCount == 4){
                Screen.Server4.setVisible(false);
            }
            MonitoringDialog.serverCount--;
        } else if (welkeServer == 2){
            //Schuif alles door
            //Server 3 naar 2
            Screen.Servernaam2.setText(Screen.Servernaam3.getText());
            Screen.Status2.setText(Screen.Status3.getText());
            Screen.Statuspanel2.setBackground(Screen.Statuspanel3.getBackground());
            Screen.Info2.setText(Screen.Info3.getText());
            //Server 4 naar 3
            Screen.Servernaam3.setText(Screen.Servernaam4.getText());
            Screen.Status3.setText(Screen.Status4.getText());
            Screen.Statuspanel3.setBackground(Screen.Statuspanel4.getBackground());
            Screen.Info3.setText(Screen.Info4.getText());
            //setVisible(false) welke server
            if (MonitoringDialog.serverCount == 2){
                Screen.Server2.setVisible(false);
            } else if (MonitoringDialog.serverCount == 3){
                Screen.Server3.setVisible(false);
            } else if (MonitoringDialog.serverCount == 4){
                Screen.Server4.setVisible(false);
            }
            MonitoringDialog.serverCount--;
        } else if (welkeServer == 3){
            //Schuif alles door
            //Server 4 naar 3
            Screen.Servernaam3.setText(Screen.Servernaam4.getText());
            Screen.Status3.setText(Screen.Status4.getText());
            Screen.Statuspanel3.setBackground(Screen.Statuspanel4.getBackground());
            Screen.Info3.setText(Screen.Info4.getText());
            //setVisible(false) welke server
            if (MonitoringDialog.serverCount == 3){
                Screen.Server3.setVisible(false);
            } else if (MonitoringDialog.serverCount == 4){
                Screen.Server4.setVisible(false);
            }
            MonitoringDialog.serverCount--;
        } else if (welkeServer == 4){
            Screen.Server4.setVisible(false);
            MonitoringDialog.serverCount--;
        }
    }
}
