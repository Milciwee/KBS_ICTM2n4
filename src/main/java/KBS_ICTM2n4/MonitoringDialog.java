package KBS_ICTM2n4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MonitoringDialog extends JDialog implements ActionListener {
    JLabel jlName = new JLabel("Name");
    JLabel jlIP = new JLabel("Ip Address");
    JLabel jlHostname = new JLabel("Hostname");
    JLabel jlPassword = new JLabel("Password");
    JLabel jlError = new JLabel("Can not add more than 4 servers!");
    private static JTextField jtfName = new JTextField();
    private static JTextField jtfIP = new JTextField();
    private static JTextField jtfHostname = new JTextField();
    private static JPasswordField jpfPassword = new JPasswordField();
    private static Serverconnection[] serverConnections = new Serverconnection[4];

    // Counter voor servers
    static int serverCount = 0;
    JButton jbCancel = new JButton("Cancel");
    JButton jbSubmit = new JButton("Submit");

    public MonitoringDialog(JFrame frame) {
        super(frame, true);
        setTitle("Add New Server");
        setSize(250, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        // button bounds
        jbCancel.setBounds(25, 280, 80, 25);
        jbSubmit.setBounds(135, 280, 80, 25);
        // label bounds
        jlName.setBounds(25, 5, 100, 30);
        jlIP.setBounds(25, 70, 100, 30);
        jlHostname.setBounds(25, 135, 100, 30);
        jlPassword.setBounds(25, 200, 100, 30);
        jlError.setBounds(25, 250, 250, 30);
        // textfield bounds
        jtfName.setBounds(25, 35, 191, 25);
        jtfIP.setBounds(25, 100, 191, 25);
        jtfHostname.setBounds(25, 165, 191, 25);
        jpfPassword.setBounds(25, 230, 191, 25);

        // toevoegen aan panel
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
        mainPanel.add(jlError);

        // ActionListener
        jbCancel.addActionListener(this);
        jbSubmit.addActionListener(this);

        //Error niet zichtbaar
        jlError.setVisible(false);

        setVisible(true);
    }

    public void addServer() {
        JPanel jpServer = Screen.jpServers[serverCount];
        JLabel jlServernaam = Screen.jlServernamen[serverCount];
        JPanel jpStatuspanel = Screen.jpSatuspanen[serverCount];
        JLabel jlStatus = Screen.jlSatussen[serverCount];
        JTextArea jtaInfo = Screen.jtaInfos[serverCount];
        JButton jbKruis = Screen.jbKruisen[serverCount];

        jpServer.setVisible(true);
        jpServer.setName(jtfName.getText());
        jlServernaam.setText(jtfName.getText() + "  -  " + jtfIP.getText());
        Serverconnection serverConnectionTemp = new Serverconnection();
        serverConnections[serverCount] = serverConnectionTemp;
        Serverconnection serverConnection = serverConnections[serverCount];
        if (serverConnection.makeConnectionWithServer(getServerIP(), getServerHostname(), getServerPassword())) {
            jlStatus.setText("Online");
            jpStatuspanel.setBackground(Color.green);
            jtaInfo.setText("Uptime:\n" + "- " + serverConnection.serverUpTime() + "\n" + "CPU usage:\n" + "- "
                    + serverConnection.serverCpuUsed() + "\n" + "Available disk space:\n" + "- "
                    + serverConnection.serverDiskSpaceAvailable() + "\n");
        } else {
            String label = jbKruis.getName();
            jlStatus.setText("Offline");
            jpStatuspanel.setBackground(Color.red);
            jtaInfo.setText("Uptime:\n" + "- unavailable\n" + "CPU usage:\n" + "- unavailable\n"
                    + "Available disk space:\n" + "- unavailable\n" + label);

        }
        serverCount++;
        WriteJson.saveServer(getServerName(), getServerIP(), getServerHostname(), getServerPassword());
        jtfName.setText("");
        jtfIP.setText("");
        jtfHostname.setText("");
        jpfPassword.setText("");
        /*
         *
         * if (serverCount == 1) { Screen.jpServer1.setVisible(true);
         * Screen.jlServernaam1.setText(jtfName.getText() + "  -  " + jtfIP.getText());
         * Serverconnection server1 = new Serverconnection();
         * if(server1.makeConnectionWithServer(getServerIP(),getServerHostname(),
         * getServerPassword())){ Screen.jlStatus1.setText("Online");
         * Screen.jpStatuspanel1.setBackground(Color.green); Screen.jtaInfo1.setText(
         * "Uptime:\n" + "- " + server1.serverUpTime() + "\n" + "CPU usage:\n" + "- " +
         * server1.serverCpuUsed() + "\n" + "Available disk space:\n" + "- " +
         * server1.serverDiskSpaceAvailable() + "\n"); }else{
         * Screen.jlStatus1.setText("Offline");
         * Screen.jpStatuspanel1.setBackground(Color.red); Screen.jtaInfo1.setText(
         * "Uptime:\n" + "- unavailable\n" + "CPU usage:\n" + "- unavailable\n" +
         * "Available disk space:\n" + "- unavailable\n" ); } } else if (serverCount ==
         * 2) { Screen.jpServer2.setVisible(true);
         * Screen.jlServernaam2.setText(jtfName.getText() + "  -  " + jtfIP.getText());
         * Serverconnection server2 = new Serverconnection();
         * if(server2.makeConnectionWithServer(getServerIP(),getServerHostname(),
         * getServerPassword())){ Screen.jlStatus2.setText("Online");
         * Screen.jpStatuspanel2.setBackground(Color.green); Screen.jtaInfo2.setText(
         * "Uptime:\n" + "- " + server2.serverUpTime() + "\n" + "CPU usage:\n" + "- " +
         * server2.serverCpuUsed() + "\n" + "Available disk space:\n" + "- " +
         * server2.serverDiskSpaceAvailable() + "\n"); }else{
         * Screen.jlStatus2.setText("Offline");
         * Screen.jpStatuspanel2.setBackground(Color.red); Screen.jtaInfo2.setText(
         * "Uptime:\n" + "- unavailable\n" + "CPU usage:\n" + "- unavailable\n" +
         * "Available disk space:\n" + "- unavailable\n" ); } } else if (serverCount ==
         * 3) { Screen.jpServer3.setVisible(true);
         * Screen.jlServernaam3.setText(jtfName.getText() + "  -  " + jtfIP.getText());
         * Serverconnection server3 = new Serverconnection();
         * if(server3.makeConnectionWithServer(getServerIP(),getServerHostname(),
         * getServerPassword())){ Screen.jlStatus3.setText("Online");
         * Screen.jpStatuspanel3.setBackground(Color.green); Screen.jtaInfo3.setText(
         * "Uptime:\n" + "- " + server3.serverUpTime() + "\n" + "CPU usage:\n" + "- " +
         * server3.serverCpuUsed() + "\n" + "Available disk space:\n" + "- " +
         * server3.serverDiskSpaceAvailable() + "\n"); }else{
         * Screen.jlStatus3.setText("Offline");
         * Screen.jpStatuspanel3.setBackground(Color.red); Screen.jtaInfo3.setText(
         * "Uptime:\n" + "- unavailable\n" + "CPU usage:\n" + "- unavailable\n" +
         * "Available disk space:\n" + "- unavailable\n" ); } } else if (serverCount ==
         * 4) { Screen.jpServer4.setVisible(true);
         * Screen.jlServernaam4.setText(jtfName.getText() + "  -  " + jtfIP.getText());
         * Serverconnection server4 = new Serverconnection();
         * if(server4.makeConnectionWithServer(getServerIP(),getServerHostname(),
         * getServerPassword())){ Screen.jlStatus4.setText("Online");
         * Screen.jpStatuspanel4.setBackground(Color.green); Screen.jtaInfo4.setText(
         * "Uptime:\n" + "- " + server4.serverUpTime() + "\n" + "CPU usage:\n" + "- " +
         * server4.serverCpuUsed() + "\n" + "Available disk space:\n" + "- " +
         * server4.serverDiskSpaceAvailable() + "\n"); }else{
         * Screen.jlStatus4.setText("Offline");
         * Screen.jpStatuspanel4.setBackground(Color.red); Screen.jtaInfo4.setText(
         * "Uptime:\n" + "- unavailable\n" + "CPU usage:\n" + "- unavailable\n" +
         * "Available disk space:\n" + "- unavailable\n" ); } }
         * Screen.monitorPanel.revalidate(); jtfName.setText(getServerName());
         * jtfIP.setText(getServerIP()); jtfHostname.setText(getServerHostname());
         * jpfPassword.setText(getServerPassword());
         * WriteJson.saveServer(getServerName(), getServerIP(), getServerHostname(),
         * getServerPassword());
         */
    }

    public static void addServerFromJson() {
        File[] files = new File("src/savedServers").listFiles();
        String[] array = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            array[i] = (name.replace(".json", ""));
        }
        serverCount = files.length;
        for (int i = 0; i < array.length; i++) {

            String name = ReadJson.readServer(array[i], "name");
            String ip = ReadJson.readServer(array[i], "ip");
            String hostname = ReadJson.readServer(array[i], "hostname");
            String password = ReadJson.readServer(array[i], "password");
            JPanel jpServer = Screen.jpServers[i];
            JLabel jlServernaam = Screen.jlServernamen[i];
            JPanel jpStatuspanel = Screen.jpSatuspanen[i];
            JLabel jlStatus = Screen.jlSatussen[i];
            JTextArea jtaInfo = Screen.jtaInfos[i];
            jpServer.setVisible(true);
            jpServer.setName(name);
            jlServernaam.setText(name + "  -  " + ip);
            Serverconnection serverConnectionTemp = new Serverconnection();
            serverConnections[i] = serverConnectionTemp;
            Serverconnection serverConnection = serverConnections[i];
            serverConnection.makeConnectionWithServer(ip,hostname,password);
            System.out.println(serverConnection.session);
            if (serverConnection.serverConnected(i)) {
                jlStatus.setText("Online");
                jpStatuspanel.setBackground(Color.green);
                jtaInfo.setText("Uptime:\n" + "- " + serverConnection.serverUpTime() + "\n" + "CPU usage:\n" + "- "
                        + serverConnection.serverCpuUsed() + "\n" + "Available disk space:\n" + "- "
                        + serverConnection.serverDiskSpaceAvailable() + "\n");
            } else {
                jlStatus.setText("Offline");
                jpStatuspanel.setBackground(Color.red);
                jtaInfo.setText("Uptime:\n" + "- unavailable\n" + "CPU usage:\n" + "- unavailable\n"
                        + "Available disk space:\n" + "- unavailable\n");
            }

        }


    }

    public static void refreshServers(){
        File[] files = new File("src/savedServers").listFiles();
        String[] array = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            array[i] = (name.replace(".json", ""));
        }
        for (int i = 0; i < array.length; i++) {

            String ip = ReadJson.readServer(array[i], "ip");
            String hostname = ReadJson.readServer(array[i], "hostname");
            String password = ReadJson.readServer(array[i], "password");
            JPanel jpStatuspanel = Screen.jpSatuspanen[i];
            JLabel jlStatus = Screen.jlSatussen[i];
            JTextArea jtaInfo = Screen.jtaInfos[i];
            Serverconnection serverConnection = serverConnections[i];
            if (serverConnection.serverConnected(i)) {
                jlStatus.setText("Online");
                jpStatuspanel.setBackground(Color.green);
                jtaInfo.setText("Uptime:\n" + "- " + serverConnection.serverUpTime() + "\n" + "CPU usage:\n" + "- "
                        + serverConnection.serverCpuUsed() + "\n" + "Available disk space:\n" + "- "
                        + serverConnection.serverDiskSpaceAvailable() + "\n");
            } else if(serverConnection.makeConnectionWithServer(ip,hostname,password)){
                jlStatus.setText("Online");
                jpStatuspanel.setBackground(Color.green);
                jtaInfo.setText("Uptime:\n" + "- " + serverConnection.serverUpTime() + "\n" + "CPU usage:\n" + "- "
                        + serverConnection.serverCpuUsed() + "\n" + "Available disk space:\n" + "- "
                        + serverConnection.serverDiskSpaceAvailable() + "\n");

            } else {
                jlStatus.setText("Offline");
                jpStatuspanel.setBackground(Color.red);
                jtaInfo.setText("Uptime:\n" + "- unavailable\n" + "CPU usage:\n" + "- unavailable\n"
                        + "Available disk space:\n" + "- unavailable\n");
            }
        }
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
            if (serverCount < 4){
            try{
//                System.out.println(getServerIP());
//                System.out.println(getServerHostname());
//                System.out.println(getServerPassword());
                String userInputName = getServerName();
                if (userInputName.equals("")){
                    JOptionPane.showMessageDialog(this, "Please enter a server name");
                    int throwsExeption = 0/0;
                }
                String userInputNameJson = getServerName() + ".json";
                File[] files = new File("src/savedServers").listFiles();
                for (int i = 0; i < files.length; i++) {
                    String name = files[i].getName();
                    if(name.equals(userInputNameJson)){
                        JOptionPane.showMessageDialog(this, "This server name already exists");
                        int throwsExeption = 0/0;
                    }
                }
                if (!validate(getServerIP())){
                    JOptionPane.showMessageDialog(this, "Please use a valid ip address");
                    int throwsExeption = 0/0;
                }

                addServer();
                dispose();
            }catch (Exception ex4){
                System.out.println("faulty user input, server was not added");
            }
            } else {
                jlError.setVisible(true);
            }
        }
    }

    public static String getServerName() {
        return jtfName.getText();
    }

    public static String getServerIP() {
        return jtfIP.getText();
    }

    public static String getServerHostname() {
        return jtfHostname.getText();
    }

    public static String getServerPassword() {
        return String.valueOf(jpfPassword.getPassword());
    }

    public static Serverconnection[] getServerConnections(){
        return serverConnections;
    }
    public static void setJtfName(String jtfName) {
        MonitoringDialog.jtfName.setText(jtfName);
    }

    public static void setJtfIP(String jtfIP) {
        MonitoringDialog.jtfIP.setText(jtfIP);
    }

    public static void setJtfHostname(String jtfHostname) {
        MonitoringDialog.jtfHostname.setText(jtfHostname);
    }

    public static void setJpfPassword(String jpfPassword) {
        MonitoringDialog.jpfPassword.setText(jpfPassword);
    }
    public static boolean validate(final String ip) {
        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        return ip.matches(PATTERN);
    }
}
