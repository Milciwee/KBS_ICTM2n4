package KBS_ICTM2n4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//gui, hier komen alle knoppen en weergaves van het progamma, het liefst geen functies
public class Screen extends JFrame implements ActionListener {

    private JButton test,test2,test3;
    private JPanel panel1;

    public Screen(){
        //titel van de window
        setTitle("Server Monitoring Application");
        //grootte van de window
        setSize(700,600);
        //bij klikken op kruisje sluit het proces af
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //berekent het center van het scherm en plaatst de window daar
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //componenten initialiseren
        panel1 = new JPanel();
        panel1.setLayout(null);

        //geeft de buttons naam en locaties op het jpanel
        test = new JButton("area 1");
        test.setBounds(0,0,125,40);
        test2 = new JButton("area 2");
        test2.setBounds(125,0,125,40);
        test3 = new JButton("area 3");
        test3.setBounds(250,0,125,40);

        //componenten toevoegen + eventlistener
        panel1.add(test);
        panel1.add(test2);
        panel1.add(test3);
        add(panel1);



        //zichtbaarheid aanzetten
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
