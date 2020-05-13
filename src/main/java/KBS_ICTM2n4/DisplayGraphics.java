package KBS_ICTM2n4;

import org.json.simple.JSONObject;

import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class DisplayGraphics extends Canvas {
    public static Image FirewallImage; //Image object voor firewall
    public static Image WServerImage;   //Image object voor de web-servers
    public static Image DServerImage;   //Image object voor de database-servers
    public static Image RouterImage;  //Image object voor de routers
    private int i;
    private int x;
    private int aDatabaseServers1;
    private int aDatabaseServers2;
    private int aDatabaseServers3;
    private int aWServers1;
    private int aWServers2;
    private int aWServers3;
    private int[] serverAmount;
    private int DServerY;
    private int WServerY;
    private int databaseGroupBegin;
    private int databaseGroupEnd;
    private int webserverGroupBegin;
    private int webserverGroupEnd;
    private int wserverIp;
    private int dserverIp;

    public void paint(Graphics g) {
        try {

           // image = ImageIO.read(new File("src/Images/"+Screen.dropdowndesign.getSelectedItem()+".jpg"));\
              WServerImage = ImageIO.read(new File("src/Images/WebServer1.png"));
              DServerImage = ImageIO.read(new File("src/Images/DatabaseServer1.png"));
              FirewallImage = ImageIO.read(new File("src/Images/Firewall.png"));
              RouterImage = ImageIO.read(new File("src/Images/Router.png"));

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
        //Hierondder initaliseer ik een paar variabelen die gebruikt worden om de rectangle etc te tekenen
        DServerY = 360;
        WServerY = 0;
        wserverIp = 30;
        dserverIp = 20;
        //
        x=0;
        g.drawImage(FirewallImage,x,0,66,120,this);

        g.drawString("Firewall",x,110);
        g.drawString("192.168.1.1",x,20);
        g.drawRect(x,0,66,120+1);


        //g.drawLine(66,60,180,60);//lijn van firewall naar groep webservers
        //g.drawLine(66 /2,120,66/2,DServerY);//lijn van firewall naar groep databaseservers

        x+=0;
        //While loops die de Database- en WebServers op het scherm tekent
        i=0;     //initializer voor While-loop
        databaseGroupBegin = x;
        //g.drawRect(x-10,100,100,100);
        while(i < aDatabaseServers1){
            g.drawImage(DServerImage,x,DServerY,66,120,this);//tekent plaatje van de webservers
            g.drawString("DServer 1",x,DServerY+100);
            g.drawString("." + dserverIp++,x,DServerY+100+20);
            x+=80;
            i++;
        }
        i=0;    //initializer voor While-loop
        while(i < aDatabaseServers2){
            g.drawImage(DServerImage,x,DServerY,66,120,this);//tekent plaatje van de webservers
            g.drawString("DServer 2",x,DServerY+100);
            g.drawString("." + dserverIp++,x,DServerY+100+20);
            x+=80;
            i++;
        }
        i=0;    //initializer voor While-loop
        while(i < aDatabaseServers3){
            g.drawImage(DServerImage,x,DServerY,66,120,this);//tekent plaatje van de webservers
            g.drawString("DServer 3",x,DServerY+100);
            g.drawString("." + dserverIp++,x,DServerY+100+20);
            x+=80;
            i++;
        }
        databaseGroupEnd=x-14;
        //Hieronder begint het tekenen van een ruimte om de servers heen als er tenminste 1 server is.
        if(aDatabaseServers1 > 0 ||aDatabaseServers2 > 0 ||aDatabaseServers3 > 0){
           // g.drawLine(66 /2,120+1,66/2,DServerY);//lijn van firewall naar groep databaseservers


            //g.drawLine(66 /2,(240-((120/2)/2)) + (120/2+1)/2,170,(240-((120/2)/2)) + (120/2+1)/2);//lijn naar de routerbox vanaf vorige lijn
            g.drawLine(66/2,(240-((120/2)/2)+(120/2+1)),66/2,DServerY);//lijn van midden routerbox naar de database servers

            g.drawRect(databaseGroupBegin,DServerY,databaseGroupEnd-databaseGroupBegin,120+1);
            g.drawString("192.168.1.-",databaseGroupBegin+((databaseGroupEnd-databaseGroupBegin)/2)-30,DServerY+20);
        }
        i=0;    //initializer voor While-loop
        x=0 + 180;    //x op nul(+180) zetten voor een nieuwe rij afbeeldingen (web-servers)
        //int xWebserverInit= x;
        webserverGroupBegin = x-10;
        g.drawImage(RouterImage,0,240-((120/2)/2),66,120/2,this);
        g.drawImage(RouterImage,0+80,240-((120/2)/2),66,120/2,this);
        g.drawLine(66 /2,120+1,66/2,(240-((120/2)/2)));//lijn van firewall naar midden van de router box
        g.drawString("192.168.1.-",(66+14+66)/2-30,240-((120/2)/2)+20);
        g.drawString(".2",0,240-((120/2)/2)+60);
        g.drawString(".3",0+80,240-((120/2)/2)+60);


        //g.drawString("Router 1",x,(240-((120/2)/2))+50);
        //g.drawRect(webserverGroupBegin,240-((120/2)/2),(x+80+66)-webserverGroupBegin +10,120/2+1);
        g.drawRect(0,240-((120/2)/2),66+14+66,120/2+1);
        //g.drawRect(webserverGroupBegin,240-((120/2)/2),(10+66+14+66+10),120/2+1);

        while(i < aWServers1){

            g.drawImage(WServerImage,x,WServerY+1,66,120,this);//tekent plaatje van de webservers
            g.drawString("WServer 1",x,WServerY+100);
            g.drawString("." + wserverIp++,x,WServerY+100+20);
            x+=80;
            i++;
        }
        i=0;    //initializer voor While-loop
        while(i < aWServers2){

            g.drawImage(WServerImage,x,WServerY+1,66,120,this);//tekent plaatje van de webservers
            g.drawString("WServer 2",x,WServerY+100);
            g.drawString("." + wserverIp++,x,WServerY+100+20);

            x+=80;
            i++;
        }
        i=0;    //initializer voor While-loop
        while(i < aWServers3){

            g.drawImage(WServerImage,x,WServerY+1,66,120,this);//tekent plaatje van de webservers
            g.drawString("WServer 3",x,WServerY+100);
            g.drawString("." + wserverIp++,x,WServerY+100+20);

            x+=80;
            i++;

        }
        webserverGroupEnd = x-4;    //de -4 is er zodat er evenveel ruimte tussen het laatste plaatje en de "eindlijn" is als in het begin,eerste plaatje en beginlijn van de rectangle,(x-10)
        //Hieronder begint het tekenen van een ruimte om de servers heen als er tenminste 1 server is.
        if(aWServers1 > 0 ||aWServers2 > 0 ||aWServers3 > 0){
            g.drawLine(66,60,webserverGroupBegin,60);//lijn van firewall naar groep webservers
            g.drawRect(webserverGroupBegin,WServerY,webserverGroupEnd-webserverGroupBegin,120+1);
           // g.drawString("192.168.1.-",webserverGroupBegin-webserverGroupEnd+webserverGroupEnd + webserverGroupBegin,WServerY+20);
            g.drawString("192.168.1.-",webserverGroupBegin+((webserverGroupEnd-webserverGroupBegin)/2)-30,WServerY+20);
        }

        i=0;     //initializer voor While-loop
        x = 0;

        //setBackground(Color.blue);


    }

    public void drawWs1(int y, int x) {

    }

}
