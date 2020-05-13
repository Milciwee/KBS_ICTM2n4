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
    private JTextField jtfPassword = new JTextField();
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
        jtfPassword.setBounds(25, 230, 191, 25);

        //toevoegen aan panel
        add(mainPanel);
        mainPanel.add(jbCancel);
        mainPanel.add(jbSubmit);
        mainPanel.add(jlName);
        mainPanel.add(jtfName);
        mainPanel.add(jlHostname);
        mainPanel.add(jtfHostname);
        mainPanel.add(jlPassword);
        mainPanel.add(jtfPassword);
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
            Screen.Server1.setVisible(true);
            Screen.Servernaam1.setText(jtfName.getText() + "  -  " + jtfIP.getText());
        } else if (serverCount == 2) {
            Screen.Server2.setVisible(true);
            Screen.Servernaam2.setText(jtfName.getText() + "  -  " + jtfIP.getText());
        } else if (serverCount == 3) {
            Screen.Server3.setVisible(true);
            Screen.Servernaam3.setText(jtfName.getText() + "  -  " + jtfIP.getText());
        } else if (serverCount == 4) {
            Screen.Server4.setVisible(true);
            Screen.Servernaam4.setText(jtfName.getText() + "  -  " + jtfIP.getText());
        }
        Screen.monitorPanel.revalidate();
        jtfName.setText(getServerName());
        jtfIP.setText(getServerIP());
        jtfHostname.setText(getServerHostname());
        jtfPassword.setText(getServerPassword());
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbCancel) {
            dispose();
            jtfName.setText("");
            jtfIP.setText("");
            jtfHostname.setText("");
            jtfPassword.setText("");
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
        return jtfPassword.getText();
    }
}
