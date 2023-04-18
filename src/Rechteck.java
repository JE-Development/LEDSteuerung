public class Rechteck {

    int x1;
    int y1;

    int x2;
    int y2;

    public Rechteck(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.x2 = x2;

        this.y1 = y1;
        this.y2 = y2;
    }

    public boolean isInRect(int x, int y){
        int xBig = 0;
        int xSmall = 0;
        if(x1 < x2){
            xSmall = x1;
            xBig = x2;
        }else{
            xSmall = x2;
            xBig = x1;
        }

        if(x >= xSmall && x <= xBig){
            int yBig = 0;
            int ySmall = 0;
            if(y1 < y2){
                ySmall = y1;
                yBig = y2;
            }else{
                ySmall = y2;
                yBig = y1;
            }
            if(y >= ySmall && y <= yBig){
                return true;
            }
        }
        return false;

    }

}
