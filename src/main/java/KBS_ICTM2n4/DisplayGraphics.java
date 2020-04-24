package KBS_ICTM2n4;

import org.json.simple.JSONObject;

import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class DisplayGraphics extends Canvas {
    public static Image WServerImage;   //Image object voor de web-servers
    public static Image DServerImage;   //Image object voor de database-servers
    private int i;
    private int x;
    private int aDatabaseServers1;
    private int aDatabaseServers2;
    private int aDatabaseServers3;
    private int aWServers1;
    private int aWServers2;
    private int aWServers3;
    private int[] serverAmount;
    public void paint(Graphics g) {
        try {

           // image = ImageIO.read(new File("src/Images/"+Screen.dropdowndesign.getSelectedItem()+".jpg"));\
              WServerImage = ImageIO.read(new File("src/Images/WebServer1.png"));
              DServerImage = ImageIO.read(new File("src/Images/DatabaseServer1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //////
        serverAmount = new int[6];  //initialiseren van de serverAmount array
        serverAmount = ReadJson.readDesign(Screen.dropdowndesign.getSelectedItem().toString());//Array van json krijgen
        aDatabaseServers1 = serverAmount[0];    //0tm2 zijn de database-servers  /   3tm5 zijn de web-servers
        aDatabaseServers2 = serverAmount[1];
        aDatabaseServers3 = serverAmount[2];
        aWServers1 = serverAmount[3];
        aWServers2 = serverAmount[4];
        aWServers3 = serverAmount[5];

        //While loops die de Database- en WebServers op het scherm tekent
        i=0;     //initializer voor While-loop
        x = 0;
        while(i < aDatabaseServers1){
            g.drawImage(DServerImage,x,140,66,120,this);//tekent plaatje van de webservers
            g.drawString("DServer 1",x,240);
            x+=80;
            i++;
        }
        i=0;    //initializer voor While-loop
        while(i < aDatabaseServers2){
            g.drawImage(DServerImage,x,140,66,120,this);//tekent plaatje van de webservers
            g.drawString("DServer 2",x,240);
            x+=80;
            i++;
        }
        i=0;    //initializer voor While-loop
        while(i < aDatabaseServers3){
            g.drawImage(DServerImage,x,140,66,120,this);//tekent plaatje van de webservers
            g.drawString("DServer 3",x,240);
            x+=80;
            i++;
        }
        i=0;    //initializer voor While-loop
        x=0;    //x op nul zetten voor een nieuwe rij afbeeldingen (web-servers)
        while(i < aWServers1){

            g.drawImage(WServerImage,x,0,66,120,this);//tekent plaatje van de webservers
            g.drawString("WServer 1",x,100);
            x+=80;
            i++;
        }
        i=0;    //initializer voor While-loop
        while(i < aWServers2){

            g.drawImage(WServerImage,x,0,66,120,this);//tekent plaatje van de webservers
            g.drawString("WServer 2",x,100);
            x+=80;
            i++;
        }
        i=0;    //initializer voor While-loop
        while(i < aWServers3){

            g.drawImage(WServerImage,x,0,66,120,this);//tekent plaatje van de webservers
            g.drawString("WServer 3",x,100);
            x+=80;
            i++;
        }
        i=0;     //initializer voor While-loop
        x = 0;

        //setBackground(Color.blue);


    }

    public void drawWs1(int y, int x) {

    }

}
