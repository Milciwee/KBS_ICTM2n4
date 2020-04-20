package KBS_ICTM2n4;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//gui, hier komen alle knoppen en weergaves van het progamma, het liefst geen functies
public class Screen extends JFrame implements ActionListener {

//    static String[] dropdownitemsdesign = new String[50];
//    //beschikbare ontwerpen
//    static String[] dropdownitemsedit = new String[50];
    static ArrayList<String> dropdownitemsedit = new ArrayList<>();
    static ArrayList<String> dropdownitemsdesign = new ArrayList<>();

    public Screen(){
        //titel van de window
        setTitle("Facility Monitoring Application");
        //grootte van de window
        setSize(700,600);
        //gebruiker kan groote van window niet aanpassen
        setResizable(false);
        //bij klikken op kruisje sluit het proces af
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //berekent het center van het scherm en plaatst de window daar
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        //componenten initialiseren
        JPanel monitorPanel = new JPanel();
        JPanel designPanel = new JPanel();
        JPanel editPanel = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        monitorPanel.setLayout(null);
        editPanel.setLayout(null);
        designPanel.setLayout(null);


        //hieronder kunnen onderdelen per panel met behulp van coordinaten wordn geplaatste
        //Monitorpanel

        //editpanel
        JComboBox dropdownedit = new JComboBox(dropdownitemsedit.toArray());
        dropdownedit.setBounds(525,0,150,25);
        JLabel jlDesnameEdit = new JLabel("Design name:");
        jlDesnameEdit.setBounds(10,20,100,25);
        //labels
        JLabel jlWs1 = new JLabel("Webserver 1");
        JLabel jlWs2 = new JLabel("Webserver 2");
        JLabel jlWs3 = new JLabel("Webserver 3");
        JLabel jlDbs1 = new JLabel("Database server 1");
        JLabel jlDbs2 = new JLabel("Database server 2");
        JLabel jlDbs3 = new JLabel("Database server 3");
        JLabel jlFw = new JLabel("Pfsense");
        JLabel jlAvailability = new JLabel("Availibility");
        //label bounds
        jlWs1.setBounds(10,60,120,25);
        jlWs2.setBounds(10,90,120,25);
        jlWs3.setBounds(10,120,120,25);
        jlDbs1.setBounds(10,150,120,25);
        jlDbs2.setBounds(10,180,120,25);
        jlDbs3.setBounds(10,210,120,25);
        jlFw.setBounds(10,240,120,25);
        jlAvailability.setBounds(10,330,100,25);

        //buttons
        JButton jbCalculate = new JButton("Calculate");
        JButton jbOptimize = new JButton("Optimize");
        JButton jbDelete = new JButton("Delete");
        JButton jbSave = new JButton("Save");
        JButton jbSaveAs = new JButton("Save as new Design");

        //button bounds
        jbCalculate.setBounds(10,280,100,25);
        jbOptimize.setBounds(140,330,100,25);
        jbDelete.setBounds(70,500,100,25);
        jbSave.setBounds(270,500,100,25);
        jbSaveAs.setBounds(470,500,150,25);

        //textfields
        JTextField jtfDesnameEdit= new JTextField(); //designnaam
        JTextField jtfWs1 = new JTextField();
        JTextField jtfWs2 = new JTextField();
        JTextField jtfWs3 = new JTextField();
        JTextField jtfDb1 = new JTextField();
        JTextField jtfDb2 = new JTextField();
        JTextField jtfDb3 = new JTextField();
        JTextField jtfCalculateAnswer = new JTextField();
        JTextField jtfavailability = new JTextField();
        JTextField jtfOptimizeAnswer = new JTextField();
        //textfield bounds
        jtfDesnameEdit.setBounds(110,20,100,25);
        jtfWs1.setBounds(140,60,25,25);
        jtfWs2.setBounds(140,90,25,25);
        jtfWs3.setBounds(140,120,25,25);
        jtfDb1.setBounds(140,150,25,25);
        jtfDb2.setBounds(140,180,25,25);
        jtfDb3.setBounds(140,210,25,25);
        jtfCalculateAnswer.setBounds(120,280,300,25);
        jtfavailability.setBounds(80,330, 40,25);
        jtfOptimizeAnswer.setBounds(10,370,300,25);
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
        dropdownitemsdesign = dropdownitemsedit;
        dropdownitemsdesign.add("Add new Design");
        JComboBox dropdowndesign = new JComboBox(dropdownitemsdesign.toArray());
        dropdowndesign.setBounds(525,0,150,25);

        designPanel.add(dropdowndesign);



        //voegt de panes toe aan tabbedpane met een name
        tabbedPane.addTab("Monitor",monitorPanel);
        tabbedPane.addTab("Edit",editPanel);
        tabbedPane.addTab("Design",designPanel);
        add(tabbedPane);
        //oude code
//        mainPanel = new JPanel();
//        mainPanel.setLayout(null);
//        JButton jbDesign = new JButton("Design");
//        jbDesign.setBounds(100,0,100,25);
//        designPanel.add(jbDesign);
//        jbMonitor = new JButton("Monitoring");
//        jbMonitor.setBounds(0,0,100,25);
//        jbEdit = new JButton("Edit");
//        jbEdit.setBounds(100,0,100,25);
//        jbDesign = new JButton("Design");
//        jbDesign.setBounds(200,0,100,25);
//
//        //componenten toevoegen + eventlistener
//        mainPanel.add(jbMonitor);
//        mainPanel.add(jbEdit);
//        mainPanel.add(jbDesign);
//        add(mainPanel);

        //zichtbaarheid aanzetten
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
