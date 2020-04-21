package KBS_ICTM2n4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    static JTextField jtfavailability = new JTextField();
    static JTextField jtfOptimizeAnswer = new JTextField();
    static JLabel jlDesignName = new JLabel("");
    static JComboBox dropdowndesign;
    JButton jbCalculate = new JButton("Calculate");
    JButton jbOptimize = new JButton("Optimize");
    JButton jbDelete = new JButton("Delete");
    JButton jbSave = new JButton("Save");
    JButton jbSaveAs = new JButton("Save as new Design");

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
        JPanel monitorPanel = new JPanel();
        JPanel designPanel = new JPanel();
        JPanel editPanel = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
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
        jlDesnameEdit.setBounds(10,20,100,25);
        //labels
        JLabel jlDbs1 = new JLabel("Database server 1");
        JLabel jlDbs2 = new JLabel("Database server 2");
        JLabel jlDbs3 = new JLabel("Database server 3");
        JLabel jlWs1 = new JLabel("Webserver 1");
        JLabel jlWs2 = new JLabel("Webserver 2");
        JLabel jlWs3 = new JLabel("Webserver 3");
        JLabel jlFw = new JLabel("Pfsense");
        JLabel jlAvailability = new JLabel("Availibility");
        //label bounds
        jlDbs1.setBounds(10,60,120,25);
        jlDbs2.setBounds(10,90,120,25);
        jlDbs3.setBounds(10,120,120,25);
        jlWs1.setBounds(10,150,120,25);
        jlWs2.setBounds(10,180,120,25);
        jlWs3.setBounds(10,210,120,25);
        jlFw.setBounds(10,240,120,25);
        jlAvailability.setBounds(10,330,100,25);
        //button bounds
        jbCalculate.setBounds(10,280,100,25);
        jbOptimize.setBounds(140,330,100,25);
        jbDelete.setBounds(70,500,100,25);
        jbSave.setBounds(270,500,100,25);
        jbSaveAs.setBounds(470,500,150,25);
        //textfield bounds
        jtfDesnameEdit.setBounds(110,20,100,25);
        jtfDb1.setBounds(140,60,25,25);
        jtfDb2.setBounds(140,90,25,25);
        jtfDb3.setBounds(140,120,25,25);
        jtfWs1.setBounds(140,150,25,25);
        jtfWs2.setBounds(140,180,25,25);
        jtfWs3.setBounds(140,210,25,25);
        jtfCalculateAnswer.setBounds(120,280,300,25);
        jtfavailability.setBounds(80,330, 40,25);
        jtfOptimizeAnswer.setBounds(10,370,300,25);
        //actionlisteneners
        jbOptimize.addActionListener(this);
        //toevoegen aan panel
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
        editPanel.add(jtfavailability);
        editPanel.add(jtfOptimizeAnswer);

        //designpanel
        //dropdown
        dropdownitemsdesign = dropdownitemsedit;
        dropdownitemsdesign.add("Add new Design");
        dropdowndesign = new JComboBox(dropdownitemsdesign.toArray());
        dropdowndesign.setBounds(525, 0, 150, 25);
        dropdowndesign.addActionListener(this);
        //graphics
        DisplayGraphics graphicsPanel = new DisplayGraphics();
        graphicsPanel.setBounds(10,250,660,270);
        //variabele JLabels
        jlDesignName.setText("Design name: " + dropdowndesign.getSelectedItem());
        //statische JLabels
        JLabel jlConfiguration = new JLabel("Configuration:");
        JLabel jlFirewall = new JLabel("PFsense");
        jlDesignName.setBounds(10,20,250,25);
        jlConfiguration.setBounds(10,50,100,25);
        //for loop waarin door de lijst met opgeslagen servers wordt gegaan om deze onder elkaar te krijgen.





        designPanel.add(dropdowndesign);
        designPanel.add(jlDesignName);
        designPanel.add(jlConfiguration);
        designPanel.add(graphicsPanel);

        //voegt de panes toe aan tabbedpane met een name
        tabbedPane.addTab("Monitor",monitorPanel);
        tabbedPane.addTab("Edit",editPanel);
        tabbedPane.addTab("Design",designPanel);
        add(tabbedPane);
        //zichtbaarheid aanzetten
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dropdowndesign) {
            jlDesignName.setText("Design name: " + dropdowndesign.getSelectedItem());
        }
        if (e.getSource() == jbOptimize) {
        }
        if (e.getSource() == jbSaveAs) {
            ArrayList<Server> servers = Server.getServerList();
            String name = jtfDesnameEdit.getText();
            int db1 = Integer.parseInt(jtfDb1.getText());
            int db2 = Integer.parseInt(jtfDb2.getText());
            int db3 = Integer.parseInt(jtfDb3.getText());
            int wb1 = Integer.parseInt(jtfWs1.getText());
            int wb2 = Integer.parseInt(jtfWs2.getText());
            int wb3 = Integer.parseInt(jtfWs3.getText());
            Integer[] list = new Integer[] {db1, db2, db3, wb1, wb2, wb3};
            ArrayList<Integer> serverAmount = new ArrayList<>();
            serverAmount.addAll(Arrays.asList(list));
            WriteJson.saveDesign(servers, name, serverAmount);
            // hier moet nog een check om te kijken of geen van deze waardes null is, als dat wel is toon een dialoog met error message


        }
    }
}



