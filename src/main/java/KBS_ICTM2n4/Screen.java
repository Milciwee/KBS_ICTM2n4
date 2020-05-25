package KBS_ICTM2n4;

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

//gui, hier komen alle knoppen en weergaves van het progamma
public class Screen extends JFrame implements ActionListener {

    //maximaal aantal infrastructuur componenten op monitoring
    static int maxServersMonitoring = 4;
    //inputs van gebruiker op edit tab
    static JTextField jtfDesnameEdit = new JTextField();
    static JTextField jtfWs1 = new JTextField();
    static JTextField jtfWs2 = new JTextField();
    static JTextField jtfWs3 = new JTextField();
    static JTextField jtfDb1 = new JTextField();
    static JTextField jtfDb2 = new JTextField();
    static JTextField jtfDb3 = new JTextField();
    //antwoord van berekende prijsbeschikbaarheid
    JTextField jtfCalculateAnswer = new JTextField();
    //invoer van beschikbaarheidspercentage
    JTextField jtfAvailabilitypart1 = new JTextField();
    JTextField jtfAvailabilitypart2 = new JTextField();
    //prijsbeschikbaarheid antwoord uit backtracking
    JTextField jtfOptimizeAnswer = new JTextField();
    //labels om een ontwerp te beschrijven op de design tab
    JLabel jlDesignName = new JLabel();
    JLabel jlTotalAvailability = new JLabel();
    JLabel jlTotalCosts = new JLabel();
    //de dropdowns op de design en edit tab
    static JComboBox dropdowndesign;
    static JComboBox dropdownedit;
    //button voor toevoegen nieuw infrastructuur component op monitor panel
    JButton jbNewServer = new JButton("Add New Server");
    //de 3 main panels van de applicatie
    JPanel monitorPanel = new JPanel();
    JPanel editPanel = new JPanel();
    static JPanel designPanel = new JPanel();
    //jlabels om de inhoud van een ontwerp te laten zien op design
    static JLabel jlWb1 = new JLabel();
    static JLabel jlWb2 = new JLabel();
    static JLabel jlWb3 = new JLabel();
    static JLabel jlDb1 = new JLabel();
    static JLabel jlDb2 = new JLabel();
    static JLabel jlDb3 = new JLabel();
    static JLabel jlIsCorrupt = new JLabel();
    //2 main panels op de monitor tab
    JPanel jpPanel1 = new JPanel();
    JPanel jpPanel2 = new JPanel();
    //onderdelen van de infrastructuur componenten
    static JPanel[] jpServers = new JPanel[maxServersMonitoring];
    static JLabel[] jlServernamen = new JLabel[maxServersMonitoring];
    static JPanel[] jpKoppen = new JPanel[maxServersMonitoring];
    static JPanel[] jpStatuspanel = new JPanel[maxServersMonitoring];
    static JLabel[] jlStatussen = new JLabel[maxServersMonitoring];
    static JTextArea[] jtaInfos = new JTextArea[maxServersMonitoring];
    static JButton[] jbKruisen = new JButton[maxServersMonitoring];
    // Buttons op edit panel
    JButton jbCalculate = new JButton("Calculate");
    JButton jbOptimize = new JButton("Optimize");
    JButton jbDelete = new JButton("Delete");
    JButton jbSave = new JButton("Save");
    JButton jbSaveAs = new JButton("Save as new Design");
    //buttons op design panel
    JButton jbOpenDesign = new JButton("Show visual design");
    //buttons op monitor panel
    JButton jbRefresh = new JButton("Refresh");
    JButton jbStartStop = new JButton("Start monitoring");
    //zorgt ervoor dat er 3 tabs kunnen zijn
    JTabbedPane tabbedPane = new JTabbedPane();
    //arraylist met alle typen servers
    ArrayList<Server> totalServers = Server.getServerList();
    //boolean voor de start/stop monitoring functie
    static boolean isClickedStartStop = false;

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
        //layout van panels op null zetten zodat coordinaten gebruikt kunnen worden voor de plaatsing van de knoppen
        monitorPanel.setLayout(null);
        editPanel.setLayout(null);
        designPanel.setLayout(null);
        // Monitorpanel instellen
        monitorPanel.setLayout(new BorderLayout());
        jpPanel1.setLayout(new FlowLayout());
        jpPanel2.setLayout(null);
        monitorPanel.setPreferredSize(new Dimension(100, 50));
        jpPanel2.setPreferredSize(new Dimension(100, 500));
        monitorPanel.add(jpPanel1, BorderLayout.LINE_END);
        monitorPanel.add(jpPanel2, BorderLayout.PAGE_END);
        jpPanel1.add(jbRefresh);
        jpPanel1.add(jbNewServer);
        jpPanel1.add(jbStartStop);
        this.add(monitorPanel);
        // ActionListener
        jbRefresh.addActionListener(this);
        jbStartStop.addActionListener(this);
        jbNewServer.addActionListener(this);
        //loop voor het aanmaken van een nieuwe server box
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
            Screen.jpStatuspanel[i] = jpStatuspanel;
            JLabel jlStatust = new JLabel();
            jlStatussen[i] = jlStatust;
            JTextArea jtaInfot = new JTextArea();
            jtaInfos[i] = jtaInfot;
            JButton jbKruisje = new JButton("X");
            jbKruisen[i] = jbKruisje;
            //gegevens van servers ophalen uit de arrays
            JPanel jpServer = jpServers[i];
            JLabel jlNaam = jlServernamen[i];
            JPanel jpKop = jpKoppen[i];
            JPanel jpStatusPanel = Screen.jpStatuspanel[i];
            JLabel jlStatus = jlStatussen[i];
            JTextArea jtaInfo = jtaInfos[i];
            JButton jbKruis = jbKruisen[i];
            String label = String.valueOf(i);
            jpServer.setName(label);
            jbKruis.setName(label);
            //jpanel server instellen
            jpServer.setLayout(null);
            jpServer.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));
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
        jlIsCorrupt.setForeground(Color.red);
        //initialiseren van dropdown op edit tab
        dropdownedit = new JComboBox();
        dropdownedit.setBounds(525, 0, 150, 25);
        // labels
        JLabel jlDesnameEdit = new JLabel("Design name:");
        jlDesnameEdit.setBounds(10, 10, 100, 25);
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
        jlIsCorrupt.setBounds(230,10,200,25);
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
        // textfields als niet editable instellen
        jtfCalculateAnswer.setEditable(false);
        jtfOptimizeAnswer.setEditable(false);
        // actionlisteneners toevoegen
        jbOptimize.addActionListener(this);
        jbCalculate.addActionListener(this);
        jbDelete.addActionListener(this);
        jbOpenDesign.addActionListener(this);
        jbSave.addActionListener(this);
        jbSaveAs.addActionListener(this);
        dropdownedit.addActionListener(this);
        // toevoegen aan editpanel
        editPanel.add(jlIsCorrupt);
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
        // dropdown van design initialiseren
        dropdowndesign = new JComboBox();
        dropdowndesign.setBounds(525, 0, 150, 25);
        dropdowndesign.addActionListener(this);
        // variabele JLabels
        jlDesignName.setText("Design name: " + dropdowndesign.getSelectedItem());
        // statische JLabels
        JLabel jlConfiguration = new JLabel("Configuration:");
        JLabel jlFirewall = new JLabel("PFsense:   1");
        //jlabel bounds
        jlDesignName.setBounds(10, 10, 250, 25);
        jlTotalAvailability.setBounds(10, 35, 250, 25);
        jlTotalCosts.setBounds(320, 10, 250, 25);
        jlConfiguration.setBounds(10, 60, 100, 25);
        jlFirewall.setBounds(10, 85, 100, 25);
        // toevoegen aan designpanel
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
        //functie om de opgeslagen infrastructuur componenten op te halen uit de json bestanden en te plaatsen in de monitor panel
        MonitoringDialog.addServerFromJson();
        //functie om de designs uit json bestanden te halen
        readDesignsList(this);
        //functie om de gegevens uit het json bestand in design te zetten
        showConfigDesign();
        //functie om de gegevens uit het json bestand in edit te zetten
        showConfigEdit();
        //als het json bestand corrupt is, wordt de jlabel rood
        if (totalAvailability().equals("Design is corrupt")){
            jlTotalAvailability.setForeground(Color.red);
        }else{
            jlTotalAvailability.setForeground(Color.black);
        }
        //invullen van availability en cost op designtab
        jlTotalAvailability.setText(totalAvailability());
        //algemene styling
        Color backgroundColor = new Color(199, 234, 249);
        designPanel.setBackground(backgroundColor);
        editPanel.setBackground(backgroundColor);
        monitorPanel.setBackground(backgroundColor);
        jpPanel1.setBackground(backgroundColor);
        jpPanel2.setBackground(backgroundColor);
        tabbedPane.setBackground(new Color(100, 190, 223));
        // zichtbaarheid aanzetten
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbOpenDesign) {
            //opent een frame voor de paintfuctie
            JDialog designDialog = new DesignDialog(this);
        }
        if (e.getSource() == jbStartStop){
            //start en stopknop voor het continu monitoren van de infrastructuur componenten
            if(isClickedStartStop){
                Main.stopTimer();
                jbStartStop.setText("Start monitoring");//knop weergeeft nu "Start"
                isClickedStartStop = false;
            }else{
                Main.startTimer();
                jbStartStop.setText("Stop monitoring");//knop weergeeft nu "Stop"
                isClickedStartStop = true;
            }
        }
        if (e.getSource() == jbNewServer) {
            //opent een dialog zodat de gebruiker gegevens van de server kan invullen
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
        //functie om het infrastructuur component te verwijderen
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
            //roept functie aan die een nieuwe gegevensrequest stuurt naar alle infrastructuur componenten
            MonitoringDialog.refreshServers();
        }
        if (e.getSource() == dropdowndesign) {
            try {
                jlTotalAvailability.setText(totalAvailability());
                if (dropdowndesign.getSelectedItem().equals("Add new Design")) {
                    // clear de textfields in edit tab
                    JTextField[] labelsEdit = new JTextField[]{jtfDb1, jtfDb2, jtfDb3, jtfWs1, jtfWs2, jtfWs3,};
                    jtfDesnameEdit.setText("");
                    for (JTextField jTextField : labelsEdit) {
                        jTextField.setText("");
                    }
                    dropdowndesign.setSelectedIndex(0);
                    //verwijst de gebruiker door naar de editpanel
                    tabbedPane.setSelectedComponent(editPanel);
                }
                if (!dropdowndesign.getSelectedItem().equals("Add new Design")) {
                    if (totalAvailability().equals("Design is corrupt")){
                        jlTotalAvailability.setForeground(Color.red);
                        jbOpenDesign.setVisible(false);
                    }else{
                        jlTotalAvailability.setForeground(Color.black);
                        jbOpenDesign.setVisible(true);
                    }
                    //haalt de design naam uit de dropdown
                    jlDesignName.setText("Design name: " + dropdowndesign.getSelectedItem());
                    //functie om de configuratie van het design te laten zien
                    showConfigDesign();
                    //functie om de totale beschikbaarheid en kosten te laten zien
                    jlTotalAvailability.setText(totalAvailability());
                }
            } catch (NullPointerException ex4) {
                //zo blijft het progamma runnen als iets fout gaat
            }
        }
        if (e.getSource() == dropdownedit) {
            try {
                //functie om het ontwerp uit json in de edit tab te verwerken
                showConfigEdit();
                //update de berekende prijsbeschikbaarheid
                jtfCalculateAnswer.setText(prijsbeschikbaarheidberekenen(jtfDb1, jtfDb2, jtfDb3, jtfWs1, jtfWs2, jtfWs3));
            } catch (NullPointerException ex) {
                //zo blijft het progamma runnen als iets fout gaat
            }
        }
        if (e.getSource() == jbOptimize) {
            //maakt een array aan waarin het aantal servers staan
            int[] arrayServers = new int[6];
            //checkt of de gebruiker goede input geeft, anders wordt het aantal servers 0
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
            //haalt de door de gebruiker ingegeven beschikbaarheidspercentage op
            try {
                //dit wordt in 2 ddelen gedaan, zodat de gebruiker niet hoeft na te denken over het gebruiken van een comma of punt
                if (!jtfAvailabilitypart2.getText().equals("") && jtfAvailabilitypart1.getText().equals("")) {
                    jtfAvailabilitypart1.setText("00");
                }
                if (jtfAvailabilitypart2.getText().equals("") && !jtfAvailabilitypart1.getText().equals("")) {
                    jtfAvailabilitypart2.setText("00");
                }
                //de twee delen worden in een string geplaatst
                String availabilityTotal = (jtfAvailabilitypart1.getText() + jtfAvailabilitypart2.getText());
                //vervolgens maakt het progamma van de string een double
                double availabilityDouble = Double.parseDouble(availabilityTotal);
                //als de ingegeven waarde ongeldig is, zorgt het progamma ervoor dat het naar het catch blok gaat
                if (availabilityDouble >= 10000 || availabilityDouble < 0.00) {
                    int throwsError = 0 / 0; // geeft een error zodat de code verdergaat in de catch
                }
                //verandert de gegevens zodat de klasse Bac
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
            //berekent de prijsbeschikbaarheid van een ontwerp als de gebruiker op de calculate knop drukt
            jtfCalculateAnswer.setText(prijsbeschikbaarheidberekenen(jtfDb1, jtfDb2, jtfDb3, jtfWs1, jtfWs2, jtfWs3));
        }
        if (e.getSource() == jbSave) {// hierin worden bestaande designs ge edit
            try {
                ArrayList<Server> servers = Server.getServerList();
                // haal ingevulde naam op voor design
                if (jtfDesnameEdit.getText().equals("")) {
                    //checkt of de gebruiker een naam heeft ingevuld voor het design
                    JOptionPane.showMessageDialog(this, "Please enter a valid design name");
                    //geeft een error, waardoor de code in de catch beland
                    int x = 0 / 0;
                }
                String name = jtfDesnameEdit.getText();
                // haal alle aantalen op van ingevulde waardes
                //als het vakje leeg is of een ongeldige waarde bevat, dan wordt de eerst ingestelde 0 niet aangepast
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
                    //als gebruiker moet je minimaal 1 webserver en 1 databaseserver in een ontwerp hebben, anders krijg je een foutmelding
                    JOptionPane.showMessageDialog(this, "Please choose at least 1 webserver and 1 databaseserver");
                    //door de exeption belandt de code in dde catch
                    int x = 0 / 0;
                }
                // doet deze waardes in een array en daarna is een arraylist
                ArrayList<Integer> serverAmount = new ArrayList<>();
                String[] listString = new String[]{db1, db2, db3, wb1, wb2, wb3};
                for (String string : listString) {
                    serverAmount.add(Integer.parseInt(string));
                }
                if (!dropdownedit.getSelectedItem().equals(name)) {
                    //als de gebruiker de naam van het design heeft gewijzigd, dan checkt het progamma of deze naam al eerder is gebruikt
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
                        //past de positie van de dropdown aan zodat ie klopt
                        dropdownedit.setSelectedIndex(locatie);
                        //geeft aan dat deze naam al bestaat en dus niet gebruikt mag worden
                        JOptionPane.showMessageDialog(this, "This design name already exists");
                        //code gaat verder in de catch
                        int x = 0 / 0;
                    }
                    //past de positie van de dropdown aan zodat ie klopt
                    dropdownedit.setSelectedIndex(locatie);
                }
                //pakt de locatie van het ontwerp
                int locatie1 = dropdownedit.getSelectedIndex();
                //verwijdert het oude ontwerp
                File temp = new File("src/savedDesigns/" + dropdownedit.getSelectedItem() + ".json");
                if (temp.delete()) {
                    System.out.println(dropdownedit.getSelectedItem() + " deleted");
                }
                //slaat een nieuwe versie van dit ontwerp op
                WriteJson.saveDesign(servers, name, serverAmount);
                //ververst de dropdown
                readDesignsList(this);
                //zet de originele locatie terug
                dropdownedit.setSelectedIndex(locatie1);
                System.out.println("design saved");
            } catch (Exception eSave) {
                //de gebruiker heeft iets fout ingegeven, dus het bestand slaat niet op
                System.out.println("invalid user input, design was not saved");
            }
        }
        if (e.getSource() == jbSaveAs) {
            //functie om het ontwerp op te slaan als nieuw ontwerp
            try {
                ArrayList<Server> servers = Server.getServerList();
                // haalt ingevulde naam op voor design
                if (jtfDesnameEdit.getText().equals("")) {
                    //als de naam nit ingevuld is, geeft hij een foutmelding en slaat hij het bestand niet op
                    JOptionPane.showMessageDialog(this, "Please enter a valid design name");
                    int x = 0 / 0;
                }
                String name = jtfDesnameEdit.getText();
                // haal alle aantalen op van ingevulde waardes, checkt op gebruikersinput
                //als de gebruiker een ongeldige waarde heeft ingevuld, dan blijft het aantal 0
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
                    //checkt of de gebruiker tenminste 1 webserver en 1 databaseserver heeft ingevuld
                    JOptionPane.showMessageDialog(this, "Please choose at least 1 webserver and 1 databaseserver");
                    int x = 0 / 0;
                }
                //loop om te checken of de naam die is ingevuld al bestaat
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
                    //als de naam bestaat, dan geeft de applicatie een foutmelding
                    JOptionPane.showMessageDialog(this, "This design name already exists");
                    int x = 0 / 0;
                }
                // doet deze waardes in een array en daarna is een arraylist
                ArrayList<Integer> serverAmount = new ArrayList<>();
                String[] listString = new String[]{db1, db2, db3, wb1, wb2, wb3};
                // Checken of er niks is ingevuld, als dit wel het geval is verandert het dan naar 0
                for (String string : listString) {
                    if (string.equals("")) {
                        string = "0";
                    }
                    serverAmount.add(Integer.parseInt(string));
                }
                // roep de write functie aan
                WriteJson.saveDesign(servers, name, serverAmount);
                //ververst de dropdown
                readDesignsList(this);
                //geeft het nieuwe design weer in de dropdown
                dropdownedit.setSelectedItem(name);
                System.out.println("design saved");
            } catch (Exception eSave) {
                //de gebruiker heeft iets ongeldigs opgegeven, en het bestand wordt niet opgeslagen
                System.out.println("invalid user input, design was not saved");
            }
        }
        if (e.getSource() == jbDelete) {
            //functie om ontwerpen te verwijderen
            if (dropdownedit.getItemCount() > 1) {
                //checkt of er meer dan 1 designs zijn, zodat niet alle designs verwijdert kunnen worden
                //vraagt aan de gebruiker of die het zeker weet
                int dialogButton = 0;
                int dialogResult = JOptionPane.showConfirmDialog(this,
                    "are you sure you want to delete design " + dropdownedit.getSelectedItem() + "?",
                    "Delete design", dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    File temp = new File("src/savedDesigns/" + dropdownedit.getSelectedItem() + ".json");
                    //verwijdert het ontwerp
                    if (temp.delete()) {
                        System.out.println(dropdownedit.getSelectedItem() + " deleted");
                    }
                }
                //ververst de dropdown
                readDesignsList(this);
            } else {
                //geeft de melding dat er ten minste 1 design aanwezig moet zijn
                JOptionPane.showMessageDialog(this, "this application requires at least 1 design");
            }
        }
        //dit zorgt ervoor dat elke keer als er op een knop wordt gedrukt, de panels gerepaint worden
        repaint();
    }

    public static boolean isNumeric(String strNum) {
        //functie om te checken of een String een volledig numeric waarde bevat
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
        //functie om getallen af te ronden
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static void showConfigDesign() {
        //laat de inhoud van een ontwerp zien op de designpagina
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
        //laat de configuratie van het ontwerp dat geselecteerd is in de dropdown zien op de edit tab
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
            jlIsCorrupt.setText("");
        } catch (Exception exRead2) {
            //als er iets foutgaat bij het lezen, dan neemt de applicatie aan dat het bestand corrupt is
            System.out.println("error whilst reading Designs");
            jlIsCorrupt.setText("Design is corrupt");
        }
    }

    private static void readDesignsList(Screen screen) {
        try {
            //vult de dropdowns met de beschikbare ontwerpen
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
    public String prijsbeschikbaarheidberekenen(JTextField Db1, JTextField Db2, JTextField Db3, JTextField Ws1,
                                                JTextField Ws2, JTextField Ws3) {
        //berekent de prijs en beschikbaarheid van de componenten die worden meegegeven in de jTaxtfields
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
        //vult de waarden van edit met het geselecteerde design
        int amountDb1 = 0;
        int amountDb2 = 0;
        int amountDb3 = 0;
        int amountWs1 = 0;
        int amountWs2 = 0;
        int amountWs3 = 0;
        for (Server server : servers) {
            //System.out.println(server.getName());
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
        //geeft de availibility van het geselecteerde ontwerp aan op de design tab
        try {
            ArrayList<Integer> availability = new ArrayList<Integer>();
            String a = String.valueOf(dropdowndesign.getSelectedItem());
            //System.out.println(a);
            ReadJson.readDesign(a);
            if (dropdowndesign.getSelectedItem() == null) {
                dropdowndesign.setSelectedIndex(0);
            }
            for (int i = 0; i < ReadJson.readDesign(a).length; i++) {
                availability.add(ReadJson.readDesign(a)[i]);
            }
            //maakt nieuwe textfields aan
            JTextField _db1 = new JTextField();
            JTextField _db2 = new JTextField();
            JTextField _db3 = new JTextField();
            JTextField _ws1 = new JTextField();
            JTextField _ws2 = new JTextField();
            JTextField _ws3 = new JTextField();
            //haalt de waarden uit het ontwerp en zet de gevonden waardes in de textfields
            _db1.setText(String.valueOf(availability.get(0)));
            _db2.setText(String.valueOf(availability.get(1)));
            _db3.setText(String.valueOf(availability.get(2)));
            _ws1.setText(String.valueOf(availability.get(3)));
            _ws2.setText(String.valueOf(availability.get(4)));
            _ws3.setText(String.valueOf(availability.get(5)));
            //roept de prijsbeschikbaarheidsberekenen funcite aan met de aangemaakte textfields
            return prijsbeschikbaarheidberekenen(_db1, _db2, _db3, _ws1, _ws2, _ws3);
        }catch (Exception ex5){
            //als er iets fout gaat, gaat de applicatie ervan uit dat het bestand corrupt is
            return "Design is corrupt";
        }
    }
}


