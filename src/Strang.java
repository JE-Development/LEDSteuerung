import java.util.Arrays;

public class Strang {

    int[] line;
    int pos = 0;
    boolean dir = true;



    public Strang(int fromId, int toId, boolean dir){
        createStrang(fromId, toId, dir);
        if(!dir){
            pos = line.length-1;
        }
    }

    public void createStrang(int fromId, int toId, boolean dir){
        this.dir = dir;

        int smallId = 0;
        int bigId = 0;
        int anzahl = 0;
        if(fromId >= toId){
            bigId = fromId;
            smallId = toId;
            anzahl = fromId - toId+1;
        }else{
            bigId = toId;
            smallId = fromId;
            anzahl = toId - fromId+1;
        }
        line = new int[anzahl];
        int index = 0;
        for(int i = smallId; i <= bigId; i++){
            line[index] = i;


            index++;
        }
    }

    public int[] getStrang(){
        return line;
    }

    public int getNext(){
        if(dir){
            return getForwardNext();
        }else{
            return getBackwardNext();
        }
    }

    public int getForwardNext(){
        if(pos == line.length-1){
            return line[line.length-1];
        }else {
            int num = line[pos];
            pos++;
            return num;
        }
    }

    public int getBackwardNext(){
        if(pos == 0){
            return line[0];
        }else {
            int num = line[pos];
            pos--;
            return num;
        }
    }

    public int getCurrent(){
        return line[pos];
    }

    public int getFirst(){
        return line[0];
    }

    public int getLast(){
        return line[line.length-1];
    }
}
