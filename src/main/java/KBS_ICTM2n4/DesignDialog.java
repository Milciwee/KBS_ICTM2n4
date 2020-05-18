package KBS_ICTM2n4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//deze klasse opent een jdialog waarin DesignDialog een infrastructuurdesign tekent
public class DesignDialog extends JDialog implements ActionListener {

    private JButton jbReturn = new JButton("Return");

    public DesignDialog(JFrame frame){
        //modal instellen
        super(frame,true);
        //pakt de naam van het geselecteerde design en stelt deze in als titel
        setTitle(String.valueOf(Screen.dropdowndesign.getSelectedItem()));
        setSize(1000,800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        //zorgt ervoor dat de Jdialog in het midden van het scherm geplaats wordt
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        //een nieuw displaygraphics panel aanmaken
        DisplayGraphics graphicspanel = new DisplayGraphics();
        //coordinaten van buttons instellen
        jbReturn.setBounds(420,720,100,25);
        graphicspanel.setBounds(10,10,960,700);
        //actionlistener toevoegen
        jbReturn.addActionListener(this);
        //achtergrondkleur instellen
        Color backgroundColor = new Color(199, 234, 249);
        mainPanel.setBackground(backgroundColor);
        //toevoegen van aan de panel
        add(mainPanel);
        mainPanel.add(graphicspanel);
        mainPanel.add(jbReturn);
        //zichtbaar maken
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //als op return wordt gedrukt sluit de JDialog af
        if (e.getSource() == jbReturn){
            dispose();
        }
    }
}
