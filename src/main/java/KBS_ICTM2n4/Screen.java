package KBS_ICTM2n4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//gui, hier komen alle knoppen en weergaves van het progamma, het liefst geen functies
public class Screen extends JFrame implements ActionListener {

    private JButton test,test2,test3;

    public Screen(){
        //titel van de window
        setTitle("Server Monitoring Application");
        //grootte van de window
        setSize(700,600);
        //indeling van de window
        setLayout(new GridLayout(4,3));
        //bij klikken op kruisje sluit het proces af
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //berekent het center van het scherm en plaatst de window daar
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //componenten initialiseren
        test = new JButton("area 1");
        test2 = new JButton("area 2");
        test3 = new JButton("area 3");

        //componenten toevoegen + eventlistener
        add(test);
        add(test2);
        add(test3);


        //zichtbaarheid aanzetten
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
