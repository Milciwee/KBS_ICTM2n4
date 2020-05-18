package KBS_ICTM2n4;

import org.json.simple.JSONArray;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Arrays;


//gui, hier komen alle knoppen en weergaves van het progamma, het liefst geen functies
public class Screen extends JFrame implements ActionListener {

    // static String[] dropdownitemsdesign = new String[50];
    // //beschikbare ontwerpen
    // static String[] dropdownitemsedit = new String[50];
    // static ArrayList<String> dropdownitemsedit = new ArrayList<>();
    // static ArrayList<String> dropdownitemsdesign = new ArrayList<>();
    private static int maxServersMonitoring = 4;
    static JTextField jtfDesnameEdit = new JTextField(); // designnaam
    static JTextField jtfWs1 = new JTextField();
    static JTextField jtfWs2 = new JTextField();
    static JTextField jtfWs3 = new JTextField();
    static JTextField jtfDb1 = new JTextField();
    static JTextField jtfDb2 = new JTextField();
    static JTextField jtfDb3 = new JTextField();
    static JTextField jtfCalculateAnswer = new JTextField();
    static JTextField jtfAvailabilitypart1 = new JTextField();
    static JTextField jtfAvailabilitypart2 = new JTextField();
    static JTextField jtfOptimizeAnswer = new JTextField();
    static JLabel jlDesignName = new JLabel("");
    static JLabel jlTotalAvailability = new JLabel("");
    static JLabel jlTotalCosts = new JLabel("");
    static JComboBox dropdowndesign;
    // DisplayGraphics graphicsPanel;
    static JComboBox dropdownedit;
    static JButton jbNewServer = new JButton("Add New Server");
    static JPanel monitorPanel = new JPanel();
    static JPanel designPanel = new JPanel();
    static JLabel jlWb1 = new JLabel();
    static JLabel jlWb2 = new JLabel();
    static JLabel jlWb3 = new JLabel();
    static JLabel jlDb1 = new JLabel();
    static JLabel jlDb2 = new JLabel();
    static JLabel jlDb3 = new JLabel();
    // Dingen voor Monitoring
    static JPanel jpPanel1 = new JPanel();
    static JPanel jpPanel2 = new JPanel();
    static JPanel[] jpServers = new JPanel[maxServersMonitoring];
    static JLabel[] jlServernamen = new JLabel[maxServersMonitoring];
    static JPanel[] jpKoppen = new JPanel[maxServersMonitoring];
    static JPanel[] jpSatuspanen = new JPanel[maxServersMonitoring];
    static JLabel[] jlSatussen = new JLabel[maxServersMonitoring];
    static JTextArea[] jtaInfos = new JTextArea[maxServersMonitoring];
    static JButton[] jbKruisen = new JButton[maxServersMonitoring];
    // Buttons
    JButton jbCalculate = new JButton("Calculate");
    JButton jbOptimize = new JButton("Optimize");
    JButton jbDelete = new JButton("Delete");
    JButton jbSave = new JButton("Save");
    JButton jbSaveAs = new JButton("Save as new Design");
    JButton jbOpenDesign = new JButton("Show visual design");
    JButton jbRefresh = new JButton("Refresh");
    JButton jbStartStop = new JButton("Start");
    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel editPanel = new JPanel();
    // Labels voor de configuration op de designtab
    JLabel jlCostAvailability;
    ArrayList<Server> totalServers = Server.getServerList();
    static boolean isClickedStartStop = false;
    //Timer StartStop = new Timer("");

    public Screen() {

        // titel van de window
        setTitle("Facility Monitoring Application");
        // logo van de window
        ImageIcon img = new ImageIcon("src/Images/NerdyGadgets.png");
        setIconImage(img.getImage());
        // grootte van de window
        setSize(700, 600);
        // gebruiker kan grootte van window niet aanpassen
        setResizable(false);
        // bij klikken op kruisje sluit het proces af
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // berekent het center van het scherm en plaatst de window daar
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        // componenten initialiseren
        monitorPanel.setLayout(null);
        editPanel.setLayout(null);
        designPanel.setLayout(null);

        // hieronder kunnen onderdelen per panel met behulp van coordinaten worden
        // geplaatst
        // Monitorpanel
        // button bounds
        jbRefresh.setBounds(330, 5, 80, 25);
        jbRefresh.addActionListener(this);
        //kevin
        jbStartStop.addActionListener(this);
        jbNewServer.setBounds(545, 5, 129, 25);

        // ActionListener
        jbNewServer.addActionListener(this);

        // Monitorpanel instellen
        monitorPanel.setLayout(new BorderLayout());
        monitorPanel.setPreferredSize(new Dimension(100, 50));
        jpPanel2.setLayout(null);
        jpPanel2.setPreferredSize(new Dimension(100, 500));
        monitorPanel.add(jpPanel1, BorderLayout.LINE_END);
        monitorPanel.add(jpPanel2, BorderLayout.PAGE_END);
        jpPanel1.add(jbRefresh);
        jpPanel1.add(jbNewServer);
        jpPanel1.add(jbStartStop);
        this.add(monitorPanel);

        int x = 20;
        int y = 20;
        int counter = 0;
        for (int i = 0; i < maxServersMonitoring; i++) {
            if (counter == 2) {
                x = 20;
                y += 220;
                counter = 0;
            }

            // alle components aanmaken en in arrays doen
            JPanel jpServert = new JPanel();
            jpServers[i] = jpServert;
            JLabel jlServernaam = new JLabel("Server 4 - Ip address");
            jlServernamen[i] = jlServernaam;
            JPanel jpKopje = new JPanel();
            jpKoppen[i] = jpKopje;
            JPanel jpStatuspanel = new JPanel();
            jpSatuspanen[i] = jpStatuspanel;
            JLabel jlStatust = new JLabel();
            jlSatussen[i] = jlStatust;
            JTextArea jtaInfot = new JTextArea();
            jtaInfos[i] = jtaInfot;
            JButton jbKruisje = new JButton("X");
            jbKruisen[i] = jbKruisje;

            JPanel jpServer = jpServers[i];
            JLabel jlNaam = jlServernamen[i];
            JPanel jpKop = jpKoppen[i];
            JPanel jpStatusPanel = jpSatuspanen[i];
            JLabel jlStatus = jlSatussen[i];
            JTextArea jtaInfo = jtaInfos[i];
            JButton jbKruis = jbKruisen[i];

            String label = String.valueOf(i);
            jpServer.setName(label);
            jbKruis.setName(label);
            jpServer.setLayout(null);
            jpServer.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));
            // panel
            jpPanel2.add(jpServer);
            jpServer.setBounds(x, y, 300, 200);

            // Kopje met naam
            jpServer.add(jpKop);
            jpKop.setLayout(null);
            jpKop.setBounds(0, 0, 300, 30);
            jpKop.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));
            jpKop.add(jlNaam);
            jlNaam.setBounds(7, -3, 300, 30);
            jlNaam.setFont(jlServernaam.getFont().deriveFont(15.0f));
            // kruis
            jpKop.add(jbKruis);
            jbKruis.setBounds(270, 0, 30, 30);
            jbKruis.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));
            jbKruis.addActionListener(this);
            // StatusPanel
            jpServer.add(jpStatusPanel);
            jpStatuspanel.setBackground(Color.red);
            jpStatuspanel.setBounds(50, 40, 200, 30);
            jpStatuspanel.add(jlStatus);
            jlStatus.setText("Offline");
            jlStatus.setFont(jlStatus.getFont().deriveFont(16.0f));
            jlStatus.setForeground(Color.white);
            // TextArea
            jpServer.add(jtaInfo);
            jtaInfo.setBounds(30, 80, 240, 110);
            jtaInfo.setEditable(false);
            jtaInfo.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));
            jtaInfo.setFont(new Font("Consolas", Font.ITALIC, 13));
            jtaInfo.setText("Uptime          - unavailable\n" + "CPU usage       - unavailable\n"
                + "Free Disk space - unavailable");
            counter++;
            x += 320;
            jpServer.setVisible(false);

        }


        // editpanel
        dropdownedit = new JComboBox();
        dropdownedit.setBounds(525, 0, 150, 25);
        JLabel jlDesnameEdit = new JLabel("Design name:");
        jlDesnameEdit.setBounds(10, 10, 100, 25);
        // labels
        JLabel jlSpecs = new JLabel("Name:               Availability:       Price per server:");
        JLabel jlDbs1 = new JLabel("Database server 1");
        JLabel jlDbs1i = new JLabel(totalServers.get(0).getName() + "          "
            + (totalServers.get(0).getavailability() * 100) + "%             €" + totalServers.get(0).getPrice());
        JLabel jlDbs2 = new JLabel("Database server 2");
        JLabel jlDbs2i = new JLabel(totalServers.get(1).getName() + "          "
            + (totalServers.get(1).getavailability() * 100) + "%             €" + totalServers.get(1).getPrice());
        JLabel jlDbs3 = new JLabel("Database server 3");
        JLabel jlDbs3i = new JLabel(totalServers.get(2).getName() + "          "
            + (totalServers.get(2).getavailability() * 100) + "%             €" + totalServers.get(2).getPrice());
        JLabel jlWs1 = new JLabel("Webserver 1");
        JLabel jlWs1i = new JLabel(totalServers.get(3).getName() + "           "
            + (totalServers.get(3).getavailability() * 100) + "%             €" + totalServers.get(3).getPrice());
        JLabel jlWs2 = new JLabel("Webserver 2");
        JLabel jlWs2i = new JLabel(totalServers.get(4).getName() + "           "
            + (totalServers.get(4).getavailability() * 100) + "%             €" + totalServers.get(4).getPrice());
        JLabel jlWs3 = new JLabel("Webserver 3");
        JLabel jlWs3i = new JLabel(totalServers.get(5).getName() + "           "
            + (totalServers.get(5).getavailability() * 100) + "%             €" + totalServers.get(5).getPrice());
        JLabel jlFw = new JLabel("Pfsense");
        JLabel jlFwi = new JLabel("pfSense              99.998%          €4000");
        JLabel jlFwAmount = new JLabel("1");
        JLabel jlAvailability = new JLabel("Minimum Availability:");
        JLabel jlAvailabilityDot = new JLabel(",");
        JLabel jlAvailabilityPercent = new JLabel("%");
        // label bounds
        jlSpecs.setBounds(220, 35, 350, 25);
        jlDbs1.setBounds(10, 60, 120, 25);
        jlDbs1i.setBounds(220, 60, 350, 25);
        jlDbs2.setBounds(10, 90, 120, 25);
        jlDbs2i.setBounds(220, 90, 350, 25);
        jlDbs3.setBounds(10, 120, 120, 25);
        jlDbs3i.setBounds(220, 120, 350, 25);
        jlWs1.setBounds(10, 150, 120, 25);
        jlWs1i.setBounds(220, 150, 350, 25);
        jlWs2.setBounds(10, 180, 120, 25);
        jlWs2i.setBounds(220, 180, 350, 25);
        jlWs3.setBounds(10, 210, 120, 25);
        jlWs3i.setBounds(220, 210, 350, 25);
        jlFw.setBounds(10, 240, 120, 25);
        jlFwi.setBounds(220, 240, 350, 25);
        jlFwAmount.setBounds(140, 240, 25, 25);
        jlAvailability.setBounds(10, 360, 150, 25);
        jlAvailabilityDot.setBounds(173, 360, 10, 25);
        jlAvailabilityPercent.setBounds(215, 360, 10, 25);
        // button bounds
        jbCalculate.setBounds(10, 300, 100, 25);
        jbOptimize.setBounds(240, 360, 100, 25);
        jbDelete.setBounds(70, 500, 100, 25);
        jbSave.setBounds(270, 500, 100, 25);
        jbSaveAs.setBounds(470, 500, 150, 25);
        jbOpenDesign.setBounds(255, 500, 150, 25);
        // textfield bounds
        jtfDesnameEdit.setBounds(110, 10, 100, 25);
        jtfDb1.setBounds(140, 60, 25, 25);
        jtfDb2.setBounds(140, 90, 25, 25);
        jtfDb3.setBounds(140, 120, 25, 25);
        jtfWs1.setBounds(140, 150, 25, 25);
        jtfWs2.setBounds(140, 180, 25, 25);
        jtfWs3.setBounds(140, 210, 25, 25);
        jtfCalculateAnswer.setBounds(120, 300, 300, 25);
        jtfAvailabilitypart1.setBounds(140, 360, 30, 25);
        jtfAvailabilitypart2.setBounds(180, 360, 30, 25);
        jtfOptimizeAnswer.setBounds(10, 400, 300, 25);
        // niet editable
        jtfCalculateAnswer.setEditable(false);
        jtfOptimizeAnswer.setEditable(false);
        // actionlisteneners
        jbOptimize.addActionListener(this);
        jbCalculate.addActionListener(this);
        jbDelete.addActionListener(this);
        jbOpenDesign.addActionListener(this);
        jbSave.addActionListener(this);
        // toevoegen aan panel
        jbSaveAs.addActionListener(this);
        dropdownedit.addActionListener(this);
        // toevoegen aan panel
        editPanel.add(dropdownedit);
        editPanel.add(jlDesnameEdit);
        editPanel.add(jtfDesnameEdit);
        editPanel.add(jlSpecs);
        editPanel.add(jlWs1);
        editPanel.add(jlWs1i);
        editPanel.add(jlWs2);
        editPanel.add(jlWs2i);
        editPanel.add(jlWs3);
        editPanel.add(jlWs3i);
        editPanel.add(jlDbs1);
        editPanel.add(jlDbs1i);
        editPanel.add(jlDbs2);
        editPanel.add(jlDbs2i);
        editPanel.add(jlDbs3);
        editPanel.add(jlDbs3i);
        editPanel.add(jlFw);
        editPanel.add(jlFwi);
        editPanel.add(jlFwAmount);
        editPanel.add(jlAvailability);
        editPanel.add(jbCalculate);
        editPanel.add(jbOptimize);
        editPanel.add(jbDelete);
        editPanel.add(jbSave);
        editPanel.add(jbSaveAs);
        editPanel.add(jtfWs1);
        editPanel.add(jtfWs2);
        editPanel.add(jtfWs3);
        editPanel.add(jtfDb1);
        editPanel.add(jtfDb2);
        editPanel.add(jtfDb3);
        editPanel.add(jtfCalculateAnswer);
        editPanel.add(jtfAvailabilitypart1);
        editPanel.add(jtfAvailabilitypart2);
        editPanel.add(jlAvailabilityDot);
        editPanel.add(jlAvailabilityPercent);
        editPanel.add(jtfOptimizeAnswer);

        // designpanel
        // dropdown
        dropdowndesign = new JComboBox();
        dropdowndesign.setBounds(525, 0, 150, 25);
        dropdowndesign.addActionListener(this);
        // graphics
        // graphicsPanel = new DisplayGraphics();
        // graphicsPanel.setBounds(10, 250, 660, 270);

        // variabele JLabels
        jlDesignName.setText("Design name: " + dropdowndesign.getSelectedItem());
        jlCostAvailability = new JLabel();

        // statische JLabels
        JLabel jlConfiguration = new JLabel("Configuration:");
        JLabel jlFirewall = new JLabel("PFsense:   1");

        jlDesignName.setBounds(10, 10, 250, 25);

        jlTotalAvailability.setBounds(10, 35, 250, 25);
        jlTotalCosts.setBounds(320, 10, 250, 25);

        jlConfiguration.setBounds(10, 60, 100, 25);
        jlFirewall.setBounds(10, 85, 100, 25);

        // designPanel.add(graphicsPanel);
        designPanel.add(jbOpenDesign);
        designPanel.add(jlDesignName);
        designPanel.add(jlTotalAvailability);
        designPanel.add(jlTotalCosts);
        designPanel.add(jlConfiguration);
        designPanel.add(jlFirewall);
        designPanel.add(dropdowndesign);


        // voegt de panes toe aan tabbedpane met een name
        tabbedPane.addTab("Monitor", monitorPanel);
        tabbedPane.addTab("Edit", editPanel);
        tabbedPane.addTab("Design", designPanel);
        add(tabbedPane);
        readDesignsList(this);
        showConfigDesign();
        showConfigEdit();
        MonitoringDialog.addServerFromJson();
        //styling
        Color backgroundColor = new Color(199, 234, 249);
        designPanel.setBackground(backgroundColor);
        editPanel.setBackground(backgroundColor);
        monitorPanel.setBackground(backgroundColor);
        jpPanel1.setBackground(backgroundColor);
        jpPanel2.setBackground(backgroundColor);
        tabbedPane.setBackground(new Color(100, 190, 223));
        jlTotalAvailability.setText(totalAvailability());


        // zichtbaarheid aanzetten
        setVisible(true);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static void showConfigDesign() {
        try {
            JLabel[] labels = new JLabel[]{jlDb1, jlDb2, jlDb3, jlWb1, jlWb2, jlWb3};
            int[] serverAmount = ReadJson.readDesign((String) dropdowndesign.getSelectedItem());
            String[] serverNames = ReadJson.readDesignNames((String) dropdowndesign.getSelectedItem());
            int counter = 0;
            int y = 60;
            for (int i : serverAmount) {
                if (i != 0) {
                    String temp = String.valueOf(i);
                    JLabel jlTemp = labels[counter];
                    jlTemp.setText(serverNames[counter] + ":     " + temp);
                    jlTemp.setBounds(10, 50 + y, 200, 25);
                    designPanel.add(jlTemp);
                    y += 30;

                } else {
                    JLabel jlTemp = labels[counter];
                    jlTemp.setText("");
                }
                counter++;
            }
        } catch (Exception exRead) {
            System.out.println("error whilst reading Designs");
        }
    }

    private static void showConfigEdit() {
        try {
            String DesignName = (String) dropdownedit.getSelectedItem();
            int[] serverAmount = ReadJson.readDesign((String) dropdownedit.getSelectedItem());
            String[] serverNames = ReadJson.readDesignNames((String) dropdownedit.getSelectedItem());
            JTextField[] labelsEdit = new JTextField[]{jtfDb1, jtfDb2, jtfDb3, jtfWs1, jtfWs2, jtfWs3,};
            int counter = 0;
            jtfDesnameEdit.setText(DesignName);
            for (int i : serverAmount) {
                JTextField jtfTemp = labelsEdit[counter];
                jtfTemp.setText("");
                if (i != 0) {
                    jtfTemp.setText(String.valueOf(serverAmount[counter]));
                }
                counter++;

            }
        } catch (Exception exRead2) {
            System.out.println("error whilst reading Designs");
        }

    }

    private static void readDesignsList(Screen screen) {
        try {
            dropdownedit.removeAllItems();
            dropdowndesign.removeAllItems();
            File[] files = new File("src/savedDesigns").listFiles();
            for (File file : files) {
                String name = file.getName();
                /* dropdownitemsedit.add(name.replace(".json", "")); */
                dropdownedit.addItem(name.replace(".json", ""));
                dropdowndesign.addItem(name.replace(".json", ""));
            }
            dropdowndesign.addItem("Add new Design");
        } catch (Exception ex2) {
            System.out.println("error whilst reading Designs");
            dropdowndesign.addItem("Add new Design");
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbOpenDesign) {
            JDialog designDialog = new DesignDialog(this);
        }
        if (e.getSource() == jbStartStop){
            if(isClickedStartStop == true){
                Main.stopTimer();
                jbStartStop.setText("Start");//knop weergeeft nu "Start"
                isClickedStartStop = false;
            }else if(isClickedStartStop == false){
                Main.startTimer();
                jbStartStop.setText("Stop");//knop weergeeft nu "Stop"
                isClickedStartStop = true;
            }

        }

        if (e.getSource() == jbNewServer) {
            JDialog monitoringDialog = new MonitoringDialog(this);
            monitoringDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    MonitoringDialog.setJtfName("");
                    MonitoringDialog.setJtfHostname("");
                    MonitoringDialog.setJtfIP("");
                    MonitoringDialog.setJpfPassword("");
                }
            });
        }
        for (int i = 0; i < jbKruisen.length; i++) {
            JButton jbTemp = jbKruisen[i];
            if (e.getSource() == jbTemp) {
                DeleteServerDialog.setWelkeServer(Integer.valueOf(jbTemp.getName()));
                JDialog deleteDialog = new DeleteServerDialog(this);
                if (DeleteServerDialog.getOk()) {
                    MonitoringDialog.addServerFromJson();
                }


            }
        }
        if (e.getSource() == jbRefresh) {
            MonitoringDialog.refreshServers();
        }

        if (e.getSource() == dropdowndesign) {
            try {
                if (dropdowndesign.getSelectedItem().equals("Add new Design")) {
                    // clear de textfields in edit tab
                    JTextField[] labelsEdit = new JTextField[]{jtfDb1, jtfDb2, jtfDb3, jtfWs1, jtfWs2, jtfWs3,};
                    jtfDesnameEdit.setText("");
                    for (JTextField jTextField : labelsEdit) {
                        jTextField.setText("");
                    }
                    dropdowndesign.setSelectedIndex(0);
                    tabbedPane.setSelectedComponent(editPanel);
                }

                if (!dropdowndesign.getSelectedItem().equals("Add new Design")) {
                    jlDesignName.setText("Design name: " + dropdowndesign.getSelectedItem());
                    showConfigDesign();
                    jlTotalAvailability.setText(totalAvailability());
                    repaint();
                }
                // graphicsPanel.repaint();
            } catch (NullPointerException ex) {
                // TODO

            }

        }
        if (e.getSource() == dropdownedit) {
            try {
                showConfigEdit();

                // graphicsPanel.repaint();
            } catch (NullPointerException ex) {
                // TODO

            }

        }
        if (e.getSource() == jbOptimize) {
            int[] arrayServers = new int[6];
            if (isNumeric(jtfDb1.getText()) && Integer.parseInt(jtfDb1.getText()) >= 0) {
                arrayServers[0] = Integer.parseInt(jtfDb1.getText());
            } else {
                arrayServers[0] = 0;
            }
            if (isNumeric(jtfDb2.getText()) && Integer.parseInt(jtfDb2.getText()) >= 0) {
                arrayServers[1] = Integer.parseInt(jtfDb2.getText());
            } else {
                arrayServers[1] = 0;
            }
            if (isNumeric(jtfDb3.getText()) && Integer.parseInt(jtfDb3.getText()) >= 0) {
                arrayServers[2] = Integer.parseInt(jtfDb3.getText());
            } else {
                arrayServers[2] = 0;
            }
            if (isNumeric(jtfWs1.getText()) && Integer.parseInt(jtfWs1.getText()) >= 0) {
                arrayServers[3] = Integer.parseInt(jtfWs1.getText());
            } else {
                arrayServers[3] = 0;
            }
            if (isNumeric(jtfWs2.getText()) && Integer.parseInt(jtfWs2.getText()) >= 0) {
                arrayServers[4] = Integer.parseInt(jtfWs2.getText());
            } else {
                arrayServers[4] = 0;
            }
            if (isNumeric(jtfWs3.getText()) && Integer.parseInt(jtfWs3.getText()) >= 0) {
                arrayServers[5] = Integer.parseInt(jtfWs3.getText());
            } else {
                arrayServers[5] = 0;
            }
            try {
                if (!jtfAvailabilitypart2.getText().equals("") && jtfAvailabilitypart1.getText().equals("")) {
                    jtfAvailabilitypart1.setText("00");
                }
                if (jtfAvailabilitypart2.getText().equals("") && !jtfAvailabilitypart1.getText().equals("")) {
                    jtfAvailabilitypart2.setText("00");
                }
                String availabilityTotal = (jtfAvailabilitypart1.getText() + jtfAvailabilitypart2.getText());
                double availabilityDouble = Double.parseDouble(availabilityTotal);
                if (availabilityDouble >= 10000 || availabilityDouble < 0.00) {
                    int throwsError = 0 / 0; // geeft een error zodat de code verdergaat in de catch
                }
                availabilityDouble = availabilityDouble / 100;
                Backtracking backtracking = new Backtracking();
                ArrayList<Server> calcServers = backtracking.optimisation(arrayServers, availabilityDouble);
                double available = Calculatepriceavailability.calculateavailability(calcServers);
                double price = Calculatepriceavailability.calculateTotalPrice(calcServers);
                available = available * 100;
                available = round(available, 2);
                jtfOptimizeAnswer.setText("Availability: " + available + "%, Price: €" + price);
                inputServersInEdit(calcServers);
            } catch (Exception ex2) {
                jtfOptimizeAnswer.setText("Please choose a value between 0 - 99.99%");
            }
        }
        if (e.getSource() == jbCalculate) {
            jtfCalculateAnswer.setText(prijsbeschikbaarheidberekenen(jtfDb1, jtfDb2, jtfDb3, jtfWs1, jtfWs2, jtfWs3));
        }
        if (e.getSource() == jbSave) {// hierin worden bestaande designs ge edit
            try {
                ArrayList<Server> servers = Server.getServerList();
                // haal ingevulde naam op voor design
                if (jtfDesnameEdit.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid design name");
                    int x = 0 / 0;
                }
                String name = jtfDesnameEdit.getText();
                // haal alle aantalen op van ingevulde waardes
                String db1 = "0";
                String db2 = "0";
                String db3 = "0";
                String wb1 = "0";
                String wb2 = "0";
                String wb3 = "0";
                if (isNumeric(jtfDb1.getText()) && Integer.parseInt(jtfDb1.getText()) >= 0) {
                    db1 = jtfDb1.getText();
                }
                if (isNumeric(jtfDb2.getText()) && Integer.parseInt(jtfDb2.getText()) >= 0) {
                    db2 = jtfDb2.getText();
                }
                if (isNumeric(jtfDb3.getText()) && Integer.parseInt(jtfDb3.getText()) >= 0) {
                    db3 = jtfDb3.getText();
                }
                if (isNumeric(jtfWs1.getText()) && Integer.parseInt(jtfWs1.getText()) >= 0) {
                    wb1 = jtfWs1.getText();
                }
                if (isNumeric(jtfWs2.getText()) && Integer.parseInt(jtfWs2.getText()) >= 0) {
                    wb2 = jtfWs2.getText();
                }
                if (isNumeric(jtfWs3.getText()) && Integer.parseInt(jtfWs3.getText()) >= 0) {
                    wb3 = jtfWs3.getText();
                }
                if (db1.equals("0") && db2.equals("0") && db3.equals("0")
                    || wb1.equals("0") && wb2.equals("0") && wb3.equals("0")) {
                    JOptionPane.showMessageDialog(this, "Please choose at least 1 webserver and 1 databaseserver");
                    int x = 0 / 0;
                }
                // doe deze waardes in een array en daarna is een arraylist
                ArrayList<Integer> serverAmount = new ArrayList<>();
                String[] listString = new String[]{db1, db2, db3, wb1, wb2, wb3};
                for (String string : listString) {
                    serverAmount.add(Integer.parseInt(string));
                }
                if (!dropdownedit.getSelectedItem().equals(name)) {
                    int locatie = dropdownedit.getSelectedIndex();
                    boolean found = false;
                    int length = dropdownedit.getItemCount() - 1;
                    while (length >= 0) {
                        dropdownedit.setSelectedIndex(length);
                        if (dropdownedit.getSelectedItem().equals(name)) {
                            found = true;
                        }
                        length--;
                    }
                    if (found) {
                        dropdownedit.setSelectedIndex(locatie);
                        JOptionPane.showMessageDialog(this, "This design name already exists");
                        int x = 0 / 0;
                    }
                    dropdownedit.setSelectedIndex(locatie);
                }
                // roep de write functie aan
                int locatie1 = dropdownedit.getSelectedIndex();
                File temp = new File("src/savedDesigns/" + dropdownedit.getSelectedItem() + ".json");
                if (temp.delete()) {
                    System.out.println(dropdownedit.getSelectedItem() + " deleted");
                }
                WriteJson.saveDesign(servers, name, serverAmount);
                readDesignsList(this);
                dropdownedit.setSelectedIndex(locatie1);
                System.out.println("design saved");
            } catch (Exception eSave) {
                System.out.println("invalid user input, design was not saved");
            }
        }
        if (e.getSource() == jbSaveAs) {// hierbij mag de naam van het design niet bestaan in de lijst
            try {
                ArrayList<Server> servers = Server.getServerList();
                // haal ingevulde naam op voor design
                if (jtfDesnameEdit.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid design name");
                    int x = 0 / 0;
                }
                String name = jtfDesnameEdit.getText();
                // haal alle aantalen op van ingevulde waardes
                String db1 = "0";
                String db2 = "0";
                String db3 = "0";
                String wb1 = "0";
                String wb2 = "0";
                String wb3 = "0";
                if (isNumeric(jtfDb1.getText()) && Integer.parseInt(jtfDb1.getText()) >= 0) {
                    db1 = jtfDb1.getText();
                }
                if (isNumeric(jtfDb2.getText()) && Integer.parseInt(jtfDb2.getText()) >= 0) {
                    db2 = jtfDb2.getText();
                }
                if (isNumeric(jtfDb3.getText()) && Integer.parseInt(jtfDb3.getText()) >= 0) {
                    db3 = jtfDb3.getText();
                }
                if (isNumeric(jtfWs1.getText()) && Integer.parseInt(jtfWs1.getText()) >= 0) {
                    wb1 = jtfWs1.getText();
                }
                if (isNumeric(jtfWs2.getText()) && Integer.parseInt(jtfWs2.getText()) >= 0) {
                    wb2 = jtfWs2.getText();
                }
                if (isNumeric(jtfWs3.getText()) && Integer.parseInt(jtfWs3.getText()) >= 0) {
                    wb3 = jtfWs3.getText();
                }
                if (db1.equals("0") && db2.equals("0") && db3.equals("0")
                    || wb1.equals("0") && wb2.equals("0") && wb3.equals("0")) {
                    JOptionPane.showMessageDialog(this, "Please choose at least 1 webserver and 1 databaseserver");
                    int x = 0 / 0;
                }
                boolean found = false;
                int length = dropdownedit.getItemCount() - 1;
                while (length >= 0) {
                    dropdownedit.setSelectedIndex(length);
                    if (dropdownedit.getSelectedItem().equals(name)) {
                        found = true;
                    }
                    length--;
                }
                if (found) {
                    JOptionPane.showMessageDialog(this, "This design name already exists");
                    int x = 0 / 0;
                }
                // doe deze waardes in een array en daarna is een arraylist
                ArrayList<Integer> serverAmount = new ArrayList<>();
                String[] listString = new String[]{db1, db2, db3, wb1, wb2, wb3};
                // Checken of er niks is ingevuld, als dit is verander dan het naar 0
                for (String string : listString) {
                    if (string.equals("")) {
                        string = "0";
                    }
                    serverAmount.add(Integer.parseInt(string));

                }
                // roep de write functie aan
                WriteJson.saveDesign(servers, name, serverAmount);
                readDesignsList(this);
                dropdownedit.setSelectedItem(name);
                System.out.println("design saved");
            } catch (Exception eSave) {
                System.out.println("invalid user input, design was not saved");
            }
        }
        if (e.getSource() == jbDelete) {
            if (dropdownedit.getItemCount() > 1) {
                int dialogButton = 0;
                int dialogResult = JOptionPane.showConfirmDialog(this,
                    "are you sure you want to delete design " + dropdownedit.getSelectedItem() + "?",
                    "Delete design", dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    File temp = new File("src/savedDesigns/" + dropdownedit.getSelectedItem() + ".json");
                    if (temp.delete()) {
                        System.out.println(dropdownedit.getSelectedItem() + " deleted");
                    }
                }
                readDesignsList(this);
            } else {
                JOptionPane.showMessageDialog(this, "this application requires at least 1 design");
            }
        }
    }

    public String prijsbeschikbaarheidberekenen(JTextField Db1, JTextField Db2, JTextField Db3, JTextField Ws1,
                                                JTextField Ws2, JTextField Ws3) {
        ArrayList<Server> serverList = new ArrayList<>();
        ArrayList<Server> totalServers = Server.getServerList();
        if (isNumeric(Db1.getText()) && Integer.parseInt(Db1.getText()) >= 0) {
            int count = Integer.parseInt(Db1.getText());
            for (int i = 0; i < count; i++) {
                Server db1 = totalServers.get(0);
                serverList.add(db1);
            }
        }
        if (isNumeric(Db2.getText()) && Integer.parseInt(Db2.getText()) >= 0) {
            int count2 = Integer.parseInt(Db2.getText());
            for (int i = 0; i < count2; i++) {
                Server db2 = totalServers.get(1);
                serverList.add(db2);
            }
        }
        if (isNumeric(Db3.getText()) && Integer.parseInt(Db3.getText()) >= 0) {
            int count3 = Integer.parseInt(Db3.getText());
            for (int i = 0; i < count3; i++) {
                Server db3 = totalServers.get(2);
                serverList.add(db3);
            }
        }
        if (isNumeric(Ws1.getText()) && Integer.parseInt(Ws1.getText()) >= 0) {
            int count4 = Integer.parseInt(Ws1.getText());
            for (int i = 0; i < count4; i++) {
                Server ws1 = totalServers.get(3);
                serverList.add(ws1);
            }
        }
        if (isNumeric(Ws2.getText()) && Integer.parseInt(Ws2.getText()) >= 0) {
            int count5 = Integer.parseInt(Ws2.getText());
            for (int i = 0; i < count5; i++) {
                Server ws2 = totalServers.get(4);
                serverList.add(ws2);
            }
        }
        if (isNumeric(Ws3.getText()) && Integer.parseInt(Ws3.getText()) >= 0) {
            int count6 = Integer.parseInt(Ws3.getText());
            for (int i = 0; i < count6; i++) {
                Server ws3 = totalServers.get(5);
                serverList.add(ws3);
            }
        }
        try {
            if (!serverList.isEmpty()) {
                double a = Calculatepriceavailability.calculateavailability(serverList);
                double b = Calculatepriceavailability.calculateTotalPrice(serverList);
                a = a * 100;
                a = round(a, 2);
                return "Availability: " + a + "%, Price: €" + b;
            }
            return "Design is empty";
        } catch (Exception ex3) {
            double b = Calculatepriceavailability.calculateTotalPrice(serverList);
            return "Price : €" + b;
        }
    }

    public void inputServersInEdit(ArrayList<Server> servers) {
        int amountDb1 = 0;
        int amountDb2 = 0;
        int amountDb3 = 0;
        int amountWs1 = 0;
        int amountWs2 = 0;
        int amountWs3 = 0;
        for (Server server : servers) {
            System.out.println(server.getName());
            if (server.getId() == 0) {
                amountDb1++;
            }
            if (server.getId() == 1) {
                amountDb2++;
            }
            if (server.getId() == 2) {
                amountDb3++;
            }
            if (server.getId() == 3) {
                amountWs1++;
            }
            if (server.getId() == 4) {
                amountWs2++;
            }
            if (server.getId() == 5) {
                amountWs3++;
            }
        }
        jtfDb1.setText(String.valueOf(amountDb1));
        jtfDb2.setText(String.valueOf(amountDb2));
        jtfDb3.setText(String.valueOf(amountDb3));
        jtfWs1.setText(String.valueOf(amountWs1));
        jtfWs2.setText(String.valueOf(amountWs2));
        jtfWs3.setText(String.valueOf(amountWs3));

    }

    public String totalAvailability() {

        ArrayList<Integer> availability = new ArrayList<Integer>();
        String a = String.valueOf(dropdowndesign.getSelectedItem());
        System.out.println(a);
        ReadJson.readDesign(a);
        if (dropdowndesign.getSelectedItem() == null) {
            dropdowndesign.setSelectedIndex(0);
        }
        for (int i = 0; i < ReadJson.readDesign(a).length; i++) {
            availability.add(ReadJson.readDesign(a)[i]);
        }

        JTextField _db1 = new JTextField();
        JTextField _db2 = new JTextField();
        JTextField _db3 = new JTextField();
        JTextField _ws1 = new JTextField();
        JTextField _ws2 = new JTextField();
        JTextField _ws3 = new JTextField();

        int db1 = availability.get(0);
        int db2 = availability.get(1);
        int db3 = availability.get(2);
        int ws1 = availability.get(3);
        int ws2 = availability.get(4);
        int ws3 = availability.get(5);

        _db1.setText(String.valueOf(db1));
        _db2.setText(String.valueOf(db2));
        _db3.setText(String.valueOf(db3));
        _ws1.setText(String.valueOf(ws1));
        _ws2.setText(String.valueOf(ws2));
        _ws3.setText(String.valueOf(ws3));

        return prijsbeschikbaarheidberekenen(_db1,_db2,_db3,_ws1,_ws2,_ws3);
    }
}

