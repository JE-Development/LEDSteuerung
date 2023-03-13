import java.util.Arrays;

public class Strang {

    int[] line;



    public Strang(int fromId, int toId){
        createStrang(fromId, toId);
    }

    public void createStrang(int fromId, int toId){
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
}
