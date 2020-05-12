package KBS_ICTM2n4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MonitoringDialog extends JDialog implements ActionListener {
    static JLabel jlName = new JLabel("Name");
    static JLabel jlIP = new JLabel("Ip Address");
    static JLabel jlHostname = new JLabel("Hostname");
    static JLabel jlPassword = new JLabel("Password");
    static JTextField jtName = new JTextField();
    static JTextField jtIP = new JTextField();
    static JTextField jtHostname = new JTextField();
    static JTextField jtPassword = new JTextField();
    //ArrayList voor aantal servers
    static ArrayList<Integer> serversArray = new ArrayList<>(4);
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
        jtName.setBounds(25, 35, 191, 25);
        jtIP.setBounds(25, 100, 191, 25);
        jtHostname.setBounds(25, 165, 191, 25);
        jtPassword.setBounds(25, 230, 191, 25);

        //toevoegen aan panel
        add(mainPanel);
        mainPanel.add(jbCancel);
        mainPanel.add(jbSubmit);
        mainPanel.add(jlName);
        mainPanel.add(jtName);
        mainPanel.add(jlHostname);
        mainPanel.add(jtHostname);
        mainPanel.add(jlPassword);
        mainPanel.add(jtPassword);
        mainPanel.add(jlIP);
        mainPanel.add(jtIP);

        //ActionListener
        jbCancel.addActionListener(this);
        jbSubmit.addActionListener(this);

        setVisible(true);
    }

    public void addServer() {
        serverCount++;
        if (serverCount == 1) {
            Screen.Server1.setVisible(true);
            Screen.Servernaam1.setText(jtName.getText() + "  -  " + jtIP.getText());
        } else if (serverCount == 2) {
            Screen.Server2.setVisible(true);
            Screen.Servernaam2.setText(jtName.getText() + "  -  " + jtIP.getText());
        } else if (serverCount == 3) {
            Screen.Server3.setVisible(true);
            Screen.Servernaam3.setText(jtName.getText() + "  -  " + jtIP.getText());
        } else if (serverCount == 4) {
            Screen.Server4.setVisible(true);
            Screen.Servernaam4.setText(jtName.getText() + "  -  " + jtIP.getText());
        }
        Screen.monitorPanel.revalidate();
        jtName.setText("");
        jtIP.setText("");
        jtHostname.setText("");
        jtPassword.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbCancel) {
            dispose();
            jtName.setText("");
            jtIP.setText("");
            jtHostname.setText("");
            jtPassword.setText("");
        }
        if (e.getSource() == jbSubmit) {
            addServer();
            dispose();
        }
    }
}
