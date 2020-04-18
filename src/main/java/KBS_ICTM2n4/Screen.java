package KBS_ICTM2n4;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//gui, hier komen alle knoppen en weergaves van het progamma, het liefst geen functies
public class Screen extends JFrame implements ActionListener {

//    private JButton jbMonitor,jbEdit,jbDesign;
//    private JPanel mainPanel;
    private JPanel monitorPanel,editPanel,designPanel;
    private JTabbedPane tabbedPane;

    public Screen(){
        //titel van de window
        setTitle("Facility Monitoring Application");
        //grootte van de window
        setSize(700,600);
        //bij klikken op kruisje sluit het proces af
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //berekent het center van het scherm en plaatst de window daar
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        //componenten initialiseren
        monitorPanel = new JPanel();
        designPanel = new JPanel();
        editPanel = new JPanel();
        tabbedPane = new JTabbedPane();
        monitorPanel.setLayout(null);
        editPanel.setLayout(null);
        designPanel.setLayout(null);

        //hieronder kunnen onderdelen per panel met behulp van coordinaten worden geplaatst

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
