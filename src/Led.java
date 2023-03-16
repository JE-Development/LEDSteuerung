import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Led {
    ClientManager cm = new ClientManager();

    Strang[] lines = new Strang[17];
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

    public void setPixel(int pixel,int frame, int r, int g, int b){
        getButtonById(pixel).setBackground(new Color(r, g, b));
        cm.setPixel(frame,r,g,b);
    }


}
