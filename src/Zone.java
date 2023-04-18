import java.util.ArrayList;

public class Zone {

    ArrayList<Rechteck> rechteck = new ArrayList<>();
    int fromFrame = 0;
    int toFrame = 0;

    public Zone(int from, int to){
        fromFrame = from;
        toFrame = to;
    }

    public void addRect(int x1, int y1, int x2, int y2){
        Rechteck r = new Rechteck(x1,y1,x2,y2);
        rechteck.add(r);
    }

    public boolean isInRect(int x, int y){
        for(int i = 0; i < rechteck.size(); i++){
            if(rechteck.get(i).isInRect(x, y)){
                return true;
            }
        }
        return false;
    }

}
