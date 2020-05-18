package KBS_ICTM2n4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//deze klasse sluit de verbinding van een infrastructuurcomponent en verwijdert de gegevens uit de json bestanden
public class DeleteServerDialog extends JDialog implements ActionListener {

    private JButton jbCancel = new JButton("Cancel");
    private JButton jbConfirm = new JButton("Confirm");
    private static boolean ok = false;
    public static int welkeServer = 0;

    public DeleteServerDialog(JFrame frame){
        super(frame, true);
        setTitle("Delete Server");
        setSize(245, 105);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        //zet de locatie van Dialog in het midden van het scherm
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        add(mainPanel);
        //Jlabel initialiseren
        JLabel jllabel = new JLabel("Do you want to delete this server?");
        //Componenten toevoegen
        mainPanel.add(jllabel);
        mainPanel.add(jbCancel);
        mainPanel.add(jbConfirm);
        //Componenten bounds
        jllabel.setBounds(10,0, 250, 30);
        jbCancel.setBounds(10, 30, 100, 30);
        jbConfirm.setBounds(120, 30, 100, 30);
        //Actionlistener
        jbCancel.addActionListener(this);
        jbConfirm.addActionListener(this);
        setVisible(true);

    }

    public static void setWelkeServer(int welkeServer) {
        DeleteServerDialog.welkeServer = welkeServer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbCancel){
            ok = false;
            dispose();
        }
        if (e.getSource() == jbConfirm){
            ok = true;
            DeleteServer();
            dispose();
        }
    }

    public static boolean getOk(){
        return ok;
    }


    public void DeleteServer(){
        //functie die de server verwijderd waarvan het kruisje is geklikt, de connectie met de server wordt gesloten, en het bijbehorende  json bestand wordt verwijderd.
        JPanel[] jpServer = Screen.jpServers;
        Serverconnection[] serverConnections = MonitoringDialog.getServerConnections();
        serverConnections[welkeServer].closeConnectionWithServer();
        serverConnections[welkeServer] = null;
        String name = jpServer[welkeServer].getName();
        //zoekt het json bestand in de folder en verwijdert hem
        File[] files = new File("src/savedServers").listFiles();
            for (File file : files) {
                String nameFile = file.getName();
                nameFile = nameFile.replace(".json", "");
                if(nameFile.indexOf(name) != -1){
                    if(file.delete()){
                        System.out.println("Server " + nameFile + " deleted");
                    } else {
                        System.out.println("Something went wrong");
                    }
                }

            }
            //verplaatst de positie van de servers binnen de applicatie zodat alles doorschuift.
        if (MonitoringDialog.serverCount == 1){
            jpServer[0].setVisible(false);
        }else if (MonitoringDialog.serverCount == 2){
            jpServer[1].setVisible(false);
        } else if (MonitoringDialog.serverCount == 3){
            jpServer[2].setVisible(false);
        } else if (MonitoringDialog.serverCount == 4){
            jpServer[3].setVisible(false);
        }
    }
}
