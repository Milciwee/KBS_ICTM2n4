package KBS_ICTM2n4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

//gui, hier komen alle knoppen en weergaves van het progamma, het liefst geen functies
public class Screen extends JFrame implements ActionListener {

    // static String[] dropdownitemsdesign = new String[50];
    // //beschikbare ontwerpen
    // static String[] dropdownitemsedit = new String[50];
    static ArrayList<String> dropdownitemsedit = new ArrayList<>();
    static ArrayList<String> dropdownitemsdesign = new ArrayList<>();
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
    static JComboBox dropdowndesign;
    JButton jbCalculate = new JButton("Calculate");
    JButton jbOptimize = new JButton("Optimize");
    JButton jbDelete = new JButton("Delete");
    JButton jbSave = new JButton("Save");
    JButton jbSaveAs = new JButton("Save as new Design");
    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel monitorPanel = new JPanel();
    static JPanel designPanel = new JPanel();
    JPanel editPanel = new JPanel();
    // Labels voor de configuration op de designtab
    static JLabel jlWb1 = new JLabel();
    static JLabel jlWb2 = new JLabel();
    static JLabel jlWb3 = new JLabel();
    static JLabel jlDb1 = new JLabel();
    static JLabel jlDb2 = new JLabel();
    static JLabel jlDb3 = new JLabel();

    public Screen() {
        // titel van de window

        setTitle("Facility Monitoring Application");
        // grootte van de window
        setSize(700, 600);
        // gebruiker kan groote van window niet aanpassen
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

        // hieronder kunnen onderdelen per panel met behulp van coordinaten wordn
        // geplaatste
        // Monitorpanel

        // editpanel
        JComboBox dropdownedit = new JComboBox(dropdownitemsedit.toArray());
        dropdownedit.setBounds(525, 0, 150, 25);
        JLabel jlDesnameEdit = new JLabel("Design name:");
        jlDesnameEdit.setBounds(10, 20, 100, 25);
        // labels
        JLabel jlDbs1 = new JLabel("Database server 1");
        JLabel jlDbs2 = new JLabel("Database server 2");
        JLabel jlDbs3 = new JLabel("Database server 3");
        JLabel jlWs1 = new JLabel("Webserver 1");
        JLabel jlWs2 = new JLabel("Webserver 2");
        JLabel jlWs3 = new JLabel("Webserver 3");
        JLabel jlFw = new JLabel("Pfsense");
        JLabel jlFwAmount = new JLabel("1");
        JLabel jlAvailability = new JLabel("Availability");
        JLabel jlAvailabilityDot = new JLabel(",");
        JLabel jlAvailabilityPercent = new JLabel("%");
        // label bounds
        jlDbs1.setBounds(10, 60, 120, 25);
        jlDbs2.setBounds(10, 90, 120, 25);
        jlDbs3.setBounds(10, 120, 120, 25);
        jlWs1.setBounds(10, 150, 120, 25);
        jlWs2.setBounds(10, 180, 120, 25);
        jlWs3.setBounds(10, 210, 120, 25);
        jlFw.setBounds(10, 240, 120, 25);
        jlFwAmount.setBounds(140, 240, 25, 25);
        jlAvailability.setBounds(10, 330, 100, 25);
        jlAvailabilityDot.setBounds(113, 330, 10, 25);
        jlAvailabilityPercent.setBounds(155, 330, 10, 25);
        // button bounds
        jbCalculate.setBounds(10, 280, 100, 25);
        jbOptimize.setBounds(190, 330, 100, 25);
        jbDelete.setBounds(70, 500, 100, 25);
        jbSave.setBounds(270, 500, 100, 25);
        jbSaveAs.setBounds(470, 500, 150, 25);
        // textfield bounds
        jtfDesnameEdit.setBounds(110, 20, 100, 25);
        jtfDb1.setBounds(140, 60, 25, 25);
        jtfDb2.setBounds(140, 90, 25, 25);
        jtfDb3.setBounds(140, 120, 25, 25);
        jtfWs1.setBounds(140, 150, 25, 25);
        jtfWs2.setBounds(140, 180, 25, 25);
        jtfWs3.setBounds(140, 210, 25, 25);
        jtfCalculateAnswer.setBounds(120, 280, 300, 25);
        jtfAvailabilitypart1.setBounds(80, 330, 30, 25);
        jtfAvailabilitypart2.setBounds(120, 330, 30, 25);
        jtfOptimizeAnswer.setBounds(10, 370, 300, 25);
        // niet editable
        jtfCalculateAnswer.setEditable(false);
        jtfOptimizeAnswer.setEditable(false);
        // actionlisteneners
        jbOptimize.addActionListener(this);
        jbCalculate.addActionListener(this);
        jbSaveAs.addActionListener(this);
        // toevoegen aan panel
        editPanel.add(dropdownedit);
        editPanel.add(jlDesnameEdit);
        editPanel.add(jtfDesnameEdit);
        editPanel.add(jlWs1);
        editPanel.add(jlWs2);
        editPanel.add(jlWs3);
        editPanel.add(jlDbs1);
        editPanel.add(jlDbs2);
        editPanel.add(jlDbs3);
        editPanel.add(jlFw);
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
        dropdownitemsdesign = dropdownitemsedit;
        dropdownitemsdesign.add("Add new Design");
        dropdowndesign = new JComboBox(dropdownitemsdesign.toArray());
        dropdowndesign.setBounds(525, 0, 150, 25);
        dropdowndesign.addActionListener(this);
        // graphics
        DisplayGraphics graphicsPanel = new DisplayGraphics();
        graphicsPanel.setBounds(10, 260, 660, 265);
        // variabele JLabels
        jlDesignName.setText("Design name: " + dropdowndesign.getSelectedItem());
        // statische JLabels
        JLabel jlConfiguration = new JLabel("Configuration:");
        JLabel jlFirewall = new JLabel("PFsense");
        jlDesignName.setBounds(10, 20, 250, 25);
        jlConfiguration.setBounds(10, 50, 100, 25);
        // for loop waarin door de lijst met opgeslagen servers wordt gegaan om deze
        // onder elkaar te krijgen.
        showConfig();

        designPanel.add(graphicsPanel);
        designPanel.add(jlDesignName);
        designPanel.add(jlConfiguration);
        designPanel.add(dropdowndesign);

        // voegt de panes toe aan tabbedpane met een name
        tabbedPane.addTab("Monitor", monitorPanel);
        tabbedPane.addTab("Edit", editPanel);
        tabbedPane.addTab("Design", designPanel);
        add(tabbedPane);
        // zichtbaarheid aanzetten
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dropdowndesign) {
            jlDesignName.setText("Design name: " + dropdowndesign.getSelectedItem());
            if (dropdowndesign.getSelectedItem().equals("Add new Design")) {
                tabbedPane.setSelectedComponent(editPanel);
            }
            showConfig();
            revalidate();
            repaint();

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
                if (jtfAvailabilitypart1.getText().equals("")) {
                    jtfAvailabilitypart1.setText("00");
                }
                if (jtfAvailabilitypart2.getText().equals("")) {
                    jtfAvailabilitypart2.setText("00");
                }
                String availabilityTotal = (jtfAvailabilitypart1.getText() + jtfAvailabilitypart2.getText());
                double availabilityDouble = Double.parseDouble(availabilityTotal);
                if (availabilityDouble >= 10000 || availabilityDouble < 0.00) {
                    int throwsError = 0 / 0;
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
        if (e.getSource() == jbSaveAs) {
            ArrayList<Server> servers = Server.getServerList();
            // haal ingevulde naam op voor design
            String name = jtfDesnameEdit.getText();
            // haal alle aantalen op van ingevulde waardes
            String db1 = jtfDb1.getText();
            String db2 = jtfDb2.getText();
            String db3 = jtfDb3.getText();
            String wb1 = jtfWs1.getText();
            String wb2 = jtfWs2.getText();
            String wb3 = jtfWs3.getText();
            // doe deze waardes in een array en daarna is een arraylist
            ArrayList<Integer> serverAmount = new ArrayList<>();
            String[] listString = new String[] { db1, db2, db3, wb1, wb2, wb3 };
            // Checken of er niks is ingevuld, als dit is verander dan het naar 0
            for (String string : listString) {
                if (string.equals("")) {
                    string = "0";
                }
                serverAmount.add(Integer.parseInt(string));

            }
            // roep de write functie aan
            WriteJson.saveDesign(servers, name, serverAmount);

        }
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

    public String prijsbeschikbaarheidberekenen(JTextField Db1, JTextField Db2, JTextField Db3, JTextField Ws1,
            JTextField Ws2, JTextField Ws3) {
        ArrayList<Server> serverList = new ArrayList<>();
        if (isNumeric(Db1.getText()) && Integer.parseInt(Db1.getText()) >= 0) {
            int count = Integer.parseInt(Db1.getText());
            for (int i = 0; i < count; i++) {
                Server db1 = new Server(0, "database", "Database server 1", 0.90, 5100);
                serverList.add(db1);
            }
        }
        if (isNumeric(Db2.getText()) && Integer.parseInt(Db2.getText()) >= 0) {
            int count2 = Integer.parseInt(Db2.getText());
            for (int i = 0; i < count2; i++) {
                Server db2 = new Server(1, "database", "Database server 2", 0.95, 7700);
                serverList.add(db2);
            }
        }
        if (isNumeric(Db3.getText()) && Integer.parseInt(Db3.getText()) >= 0) {
            int count3 = Integer.parseInt(Db3.getText());
            for (int i = 0; i < count3; i++) {
                Server db3 = new Server(2, "database", "Database server 3", 0.98, 12200);
                serverList.add(db3);
            }
        }
        if (isNumeric(Ws1.getText()) && Integer.parseInt(Ws1.getText()) >= 0) {
            int count4 = Integer.parseInt(Ws1.getText());
            for (int i = 0; i < count4; i++) {
                Server ws1 = new Server(3, "webserver", "Webserver 1", 0.80, 2200);
                serverList.add(ws1);
            }
        }
        if (isNumeric(Ws2.getText()) && Integer.parseInt(Ws2.getText()) >= 0) {
            int count5 = Integer.parseInt(Ws2.getText());
            for (int i = 0; i < count5; i++) {
                Server ws2 = new Server(4, "webserver", "Webserver 2", 0.90, 3200);
                serverList.add(ws2);
            }
        }
        if (isNumeric(Ws3.getText()) && Integer.parseInt(Ws3.getText()) >= 0) {
            int count6 = Integer.parseInt(Ws3.getText());
            for (int i = 0; i < count6; i++) {
                Server ws3 = new Server(5, "webserver", "Webserver 3", 0.95, 5100);
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
            return "Serverlist is empty";
        } catch (Exception ex3) {
            double b = Calculatepriceavailability.calculateTotalPrice(serverList);
            return "Price : €" + b;
        }
    }

    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static void showConfig() {

        JLabel[] labels = new JLabel[] { jlDb1, jlDb2, jlDb3, jlWb1, jlWb2, jlWb3 };
        int[] serverAmount = ReadJson.readDesign((String) dropdowndesign.getSelectedItem());
        String[] serverAmount2 = ReadJson.readDesign2((String) dropdowndesign.getSelectedItem());
        int counter = 0;
        int y = 30;
        for (int i : serverAmount) {
            if (i != 0) {
                String temp = String.valueOf(i);
                System.out.println(temp);
                System.out.println(serverAmount2[counter]);
                JLabel jlTemp = labels[counter];
                jlTemp.setText(serverAmount2[counter] + ":     " + temp);
                jlTemp.setBounds(10, 50 + y, 200, 25);
                designPanel.add(jlTemp);
                y += 30;

            } else {
                JLabel jlTemp = labels[counter];
                jlTemp.setText("");
            }
            counter++;
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
            if (server.getName().equals("db1")) {
                amountDb1++;
            }
            if (server.getName().equals("db2")) {
                amountDb2++;
            }
            if (server.getName().equals("db3")) {
                amountDb3++;
            }
            if (server.getName().equals("w1")) {
                amountWs1++;
            }
            if (server.getName().equals("w2")) {
                amountWs2++;
            }
            if (server.getName().equals("w3")) {
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
}
