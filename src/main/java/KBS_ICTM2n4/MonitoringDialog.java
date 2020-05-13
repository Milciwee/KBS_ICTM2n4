package KBS_ICTM2n4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonitoringDialog extends JDialog implements ActionListener {
    JLabel jlName = new JLabel("Name");
    JLabel jlIP = new JLabel("Ip Address");
    JLabel jlHostname = new JLabel("Hostname");
    JLabel jlPassword = new JLabel("Password");
    private JTextField jtfName = new JTextField();
    private JTextField jtfIP = new JTextField();
    private JTextField jtfHostname = new JTextField();
    private JPasswordField jpfPassword = new JPasswordField();
    private boolean isOkPressed = false;
    //Counter voor servers
    static int serverCount = 0;
    JButton jbCancel = new JButton("Cancel");
    JButton jbSubmit = new JButton("Submit");


    public MonitoringDialog(JFrame frame) {
        super(frame, true);
        setTitle("Add New Server");
        setSize(250, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        //button bounds
        jbCancel.setBounds(25, 280, 80, 25);
        jbSubmit.setBounds(135, 280, 80, 25);
        //label bounds
        jlName.setBounds(25, 5, 100, 30);
        jlIP.setBounds(25, 70, 100, 30);
        jlHostname.setBounds(25, 135, 100, 30);
        jlPassword.setBounds(25, 200, 100, 30);
        //textfield bounds
        jtfName.setBounds(25, 35, 191, 25);
        jtfIP.setBounds(25, 100, 191, 25);
        jtfHostname.setBounds(25, 165, 191, 25);
        jpfPassword.setBounds(25, 230, 191, 25);

        //toevoegen aan panel
        add(mainPanel);
        mainPanel.add(jbCancel);
        mainPanel.add(jbSubmit);
        mainPanel.add(jlName);
        mainPanel.add(jtfName);
        mainPanel.add(jlHostname);
        mainPanel.add(jtfHostname);
        mainPanel.add(jlPassword);
        mainPanel.add(jpfPassword);
        mainPanel.add(jlIP);
        mainPanel.add(jtfIP);

        //ActionListener
        jbCancel.addActionListener(this);
        jbSubmit.addActionListener(this);

        setVisible(true);
    }

    public void addServer() {
        serverCount++;
        if (serverCount == 1) {
            Screen.jpServer1.setVisible(true);
            Screen.jlServernaam1.setText(jtfName.getText() + "  -  " + jtfIP.getText());
            Serverconnection server1 = new Serverconnection();
            if(server1.makeConnectionWithServer(getServerIP(),getServerHostname(),getServerPassword())){
                Screen.jlStatus1.setText("Online");
                Screen.jpStatuspanel1.setBackground(Color.green);
                Screen.jtaInfo1.setText(
                        "Uptime:\n" +
                        "- " + server1.serverUpTime() + "\n" +
                        "CPU usage:\n" +
                        "- " + server1.serverCpuUsed() + "\n" +
                        "Available disk space:\n" +
                        "- " + server1.serverDiskSpaceAvailable() + "\n");
            }else{
                Screen.jlStatus1.setText("Offline");
                Screen.jpStatuspanel1.setBackground(Color.red);
                Screen.jtaInfo1.setText(
                    "Uptime:\n" +
                    "- unavailable\n" +
                    "CPU usage:\n" +
                    "- unavailable\n" +
                    "Available disk space:\n" +
                    "- unavailable\n" );
            }
        } else if (serverCount == 2) {
            Screen.jpServer2.setVisible(true);
            Screen.jlServernaam2.setText(jtfName.getText() + "  -  " + jtfIP.getText());
            Serverconnection server2 = new Serverconnection();
            if(server2.makeConnectionWithServer(getServerIP(),getServerHostname(),getServerPassword())){
                Screen.jlStatus2.setText("Online");
                Screen.jpStatuspanel2.setBackground(Color.green);
                Screen.jtaInfo2.setText(
                    "Uptime:\n" +
                        "- " + server2.serverUpTime() + "\n" +
                        "CPU usage:\n" +
                        "- " + server2.serverCpuUsed() + "\n" +
                        "Available disk space:\n" +
                        "- " + server2.serverDiskSpaceAvailable() + "\n");
            }else{
                Screen.jlStatus2.setText("Offline");
                Screen.jpStatuspanel2.setBackground(Color.red);
                Screen.jtaInfo2.setText(
                    "Uptime:\n" +
                        "- unavailable\n" +
                        "CPU usage:\n" +
                        "- unavailable\n" +
                        "Available disk space:\n" +
                        "- unavailable\n" );
            }
        } else if (serverCount == 3) {
            Screen.jpServer3.setVisible(true);
            Screen.jlServernaam3.setText(jtfName.getText() + "  -  " + jtfIP.getText());
            Serverconnection server3 = new Serverconnection();
            if(server3.makeConnectionWithServer(getServerIP(),getServerHostname(),getServerPassword())){
                Screen.jlStatus3.setText("Online");
                Screen.jpStatuspanel3.setBackground(Color.green);
                Screen.jtaInfo3.setText(
                    "Uptime:\n" +
                        "- " + server3.serverUpTime() + "\n" +
                        "CPU usage:\n" +
                        "- " + server3.serverCpuUsed() + "\n" +
                        "Available disk space:\n" +
                        "- " + server3.serverDiskSpaceAvailable() + "\n");
            }else{
                Screen.jlStatus3.setText("Offline");
                Screen.jpStatuspanel3.setBackground(Color.red);
                Screen.jtaInfo3.setText(
                    "Uptime:\n" +
                        "- unavailable\n" +
                        "CPU usage:\n" +
                        "- unavailable\n" +
                        "Available disk space:\n" +
                        "- unavailable\n" );
            }
        } else if (serverCount == 4) {
            Screen.jpServer4.setVisible(true);
            Screen.jlServernaam4.setText(jtfName.getText() + "  -  " + jtfIP.getText());
            Serverconnection server4 = new Serverconnection();
            if(server4.makeConnectionWithServer(getServerIP(),getServerHostname(),getServerPassword())){
                Screen.jlStatus4.setText("Online");
                Screen.jpStatuspanel4.setBackground(Color.green);
                Screen.jtaInfo4.setText(
                    "Uptime:\n" +
                        "- " + server4.serverUpTime() + "\n" +
                        "CPU usage:\n" +
                        "- " + server4.serverCpuUsed() + "\n" +
                        "Available disk space:\n" +
                        "- " + server4.serverDiskSpaceAvailable() + "\n");
            }else{
                Screen.jlStatus4.setText("Offline");
                Screen.jpStatuspanel4.setBackground(Color.red);
                Screen.jtaInfo4.setText(
                    "Uptime:\n" +
                        "- unavailable\n" +
                        "CPU usage:\n" +
                        "- unavailable\n" +
                        "Available disk space:\n" +
                        "- unavailable\n" );
            }
        }
        Screen.monitorPanel.revalidate();
        jtfName.setText(getServerName());
        jtfIP.setText(getServerIP());
        jtfHostname.setText(getServerHostname());
        jpfPassword.setText(getServerPassword());
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbCancel) {
            dispose();
            jtfName.setText("");
            jtfIP.setText("");
            jtfHostname.setText("");
            jpfPassword.setText("");
        }
        if (e.getSource() == jbSubmit) {
            addServer();
            isOkPressed = true;
            dispose();
        }
    }

    public String getServerName() {
        return jtfName.getText();
    }

    public String getServerIP() {
        return jtfIP.getText();
    }

    public String getServerHostname() {
        return jtfHostname.getText();
    }

    public String getServerPassword() {
        return String.valueOf(jpfPassword.getPassword());
    }
}
