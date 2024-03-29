import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Led {
    ClientManager cm = new ClientManager();

    Strang[] lines = new Strang[17];
    Zone[] zonen = new Zone[6];
    ArrayList<JButton> buttons = new ArrayList<>();


    public Led(ArrayList<JButton> buttons){
        this.buttons = buttons;

        lines[0] = new Strang(0,16, false);
        lines[1] = new Strang(17,33, false);
        lines[2] = new Strang(34,50, false);
        lines[3] = new Strang(51,66, false);
        lines[4] = new Strang(67,83, false);
        lines[5] = new Strang(84,100, false);
        lines[6] = new Strang(101,117, true);
        lines[7] = new Strang(118,134, false);
        lines[8] = new Strang(135,151, true);
        lines[9] = new Strang(152,168, false);
        lines[10] = new Strang(169,185, false);
        lines[11] = new Strang(186,202, true);
        lines[12] = new Strang(203,219, false);
        lines[13] = new Strang(220,236, false);
        lines[14] = new Strang(237,252, true);
        lines[15] = new Strang(253,268, false);
        lines[16] = new Strang(269,285, true);

        lines[13].setKnoten(new Knoten(12,14,-1));
        lines[14].setKnoten(new Knoten(15,-1,-1));
        lines[12].setKnoten(new Knoten(10,11,9));
        lines[11].setKnoten(new Knoten(16,-1,-1));
        lines[9].setKnoten(new Knoten(7,8,-1));
        lines[7].setKnoten(new Knoten(5,6,4));
        lines[4].setKnoten(new Knoten(3,2,-1));
        lines[5].setKnoten(new Knoten(1,0,-1));


        zonen[0] = new Zone(0,16);
        zonen[0].addRect(2559,1399,2131,1080);

        zonen[1] = new Zone(17,33);
        zonen[1].addRect(2128,1393,1707,721);
        zonen[1].addRect(2129,721,2559,1081);

        zonen[2] = new Zone(34,50);
        zonen[2].addRect(1278,1080,1707,0);
        zonen[2].addRect(1708,0,2131,722);
        zonen[2].addRect(2132,351,2559,720);

        zonen[3] = new Zone(51,67);
        zonen[3].addRect(2132,351,2559,0);
        zonen[3].addRect(857,717,1277,1399);
        zonen[3].addRect(1278,1399,1707,1080);

        zonen[4] = new Zone(68,84);
        zonen[4].addRect(427,0,855,1399);
        zonen[4].addRect(855,0,1277,716);

        zonen[5] = new Zone(85,101);
        zonen[5].addRect(0,0,427,1399);

    }

    public int getZoneByPos(int x, int y){
        for(int i = 0; i < zonen.length; i++){
            if(zonen[i].isInRect(x,y)){
                return i;
            }
        }
        return -1;
    }

    public void fillZone(int zoneId, int r, int g, int b){
        int from = zonen[zoneId].fromFrame;
        int to = zonen[zoneId].toFrame;
        for(int i = from; i <= to; i++){
            setLed(1,i,0,r,g,b);
        }
    }
    public void allBlack(){
        for(int i = 0; i < zonen.length; i++){
            fillZone(i,0,0,0);
        }
    }

    public void setLed(int step, int frame, int delay, int r, int g, int b){
        for(int i = 0; i < step; i++){
            checkKnoten(lines[13], frame,r,g,b);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void checkKnoten(Strang strang,int frame, int r, int g, int b){
        int next = strang.getNext();
        if(next != -1){
            setPixel(next,frame,r,g,b);
        }
        if(strang.isEnd()){
            if(strang.knoten != null) {
                if (strang.knoten.knoten1 != -1) {
                    checkKnoten(lines[strang.knoten.knoten1],frame, r,g,b);
                }
                if (strang.knoten.knoten2 != -1) {
                    checkKnoten(lines[strang.knoten.knoten2],frame,r,g,b);
                }
                if (strang.knoten.knoten3 != -1) {
                    checkKnoten(lines[strang.knoten.knoten3],frame,r,g,b);
                }
            }
        }

    }

    public JButton getButtonById(int id){
        for(int i = 0; i < buttons.size(); i++){
            if(buttons.get(i).getText().equals(id + "")){
                return buttons.get(i);
            }
        }
        return null;
    }

    public void reset(){
        for(int i = 0; i < lines.length; i++){
            lines[i].reset();
        }
    }

    public void setButton(int pixel, int r, int g, int b){
        getButtonById(pixel).setBackground(new Color(r, g, b));
    }

    public void setPixel(int pixel,int frame, int r, int g, int b){
        setButton(pixel, r, g, b);
        cm.setPixel(frame,r,g,b);
    }


}
