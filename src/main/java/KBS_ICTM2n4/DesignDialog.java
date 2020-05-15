package KBS_ICTM2n4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DesignDialog extends JDialog implements ActionListener {

    JButton jbReturn = new JButton("Return");
    DisplayGraphics graphicspanel = new DisplayGraphics();

    public DesignDialog(JFrame frame){
        super(frame,true);
        setTitle("");
        setSize(1000,800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        jbReturn.setBounds(420,720,100,25);
        graphicspanel.setBounds(10,10,960,700);
        jbReturn.addActionListener(this);
        Color backgroundColor = new Color(199, 234, 249);
        mainPanel.setBackground(backgroundColor);

        add(mainPanel);
        mainPanel.add(graphicspanel);
        mainPanel.add(jbReturn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbReturn){
            dispose();
        }
    }
}
