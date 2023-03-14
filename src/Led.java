import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Led {

    Strang[] lines = new Strang[17];
    Knoten[] knoten = new Knoten[8];
    ArrayList<JButton> buttons = new ArrayList<>();

    public Led(ArrayList<JButton> buttons){
        this.buttons = buttons;

        lines[0] = new Strang(0,16, true);
        lines[1] = new Strang(17,33, true);
        lines[2] = new Strang(34,50, true);
        lines[3] = new Strang(51,66, true);
        lines[4] = new Strang(67,83, true);
        lines[5] = new Strang(84,100, true);
        lines[6] = new Strang(101,117, true);
        lines[7] = new Strang(118,134, true);
        lines[8] = new Strang(135,151, true);
        lines[9] = new Strang(152,168, true);
        lines[10] = new Strang(169,185, true);
        lines[11] = new Strang(186,202, true);
        lines[12] = new Strang(203,219, false);
        lines[13] = new Strang(220,236, false);
        lines[14] = new Strang(237,252, true);
        lines[15] = new Strang(253,268, true);
        lines[16] = new Strang(269,285, true);

        knoten[0] = new Knoten(0,-1,1,5);
        knoten[1] = new Knoten(2,4,3,-1);
        knoten[2] = new Knoten(5,6,4,7);
        knoten[3] = new Knoten(7,9,8,-1);
        knoten[4] = new Knoten(10,11,9,12);
        knoten[5] = new Knoten(12,14,-1,13);
        knoten[6] = new Knoten(-1,16,11,-1);
        knoten[7] = new Knoten(14,-1,15,-1);
    }

    public void setLed(int step){
        for(int i = 0; i < step; i++){
            int pixel = lines[13].getNext();
            checkKnoten(pixel);
        }
    }

    public void checkKnoten(int pixel){
        if(getKnotenIndex(pixel) == -1) {
            getButtonById(pixel).setBackground(new Color(255, 0, 0));
        }else{
            if(knoten[getKnotenIndex(pixel)].knoten1 > -1){
                int index = lines[knoten[getKnotenIndex(pixel)].knoten1].getNext()+1;
                getButtonById(index).setBackground(new Color(255,0,0));
                checkKnoten(index);
            }
            if(knoten[getKnotenIndex(pixel)].knoten2 > -1){
                int index = lines[knoten[getKnotenIndex(pixel)].knoten2].getNext()+1;
                getButtonById(index).setBackground(new Color(255,0,0));
                checkKnoten(index);
            }
            if(knoten[getKnotenIndex(pixel)].knoten3 > -1){
                int index = lines[knoten[getKnotenIndex(pixel)].knoten3].getNext()+1;
                getButtonById(index).setBackground(new Color(255,0,0));
                checkKnoten(index);
            }
            if(knoten[getKnotenIndex(pixel)].knoten4 > -1){
                int index = lines[knoten[getKnotenIndex(pixel)].knoten4].getNext()+1;
                getButtonById(index).setBackground(new Color(255,0,0));
                checkKnoten(index);
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

    public int getKnotenIndex(int id){
        for(int i = 0; i < knoten.length; i++){
            if(knoten[i].knoten1 != -1 && lines[knoten[i].knoten1].getFirst() == id){
                return i;
            }else if(knoten[i].knoten2 != -1 && lines[knoten[i].knoten2].getFirst() == id){
                return i;
            }else if(knoten[i].knoten3 != -1 && lines[knoten[i].knoten3].getFirst() == id){
                return i;
            }else if(knoten[i].knoten4 != -1 && lines[knoten[i].knoten4].getFirst() == id){
                return i;
            }
        }
        return -1;
    }


}
