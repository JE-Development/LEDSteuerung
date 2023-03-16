public class Knoten {

    int knoten1 = -1;
    int knoten2 = -1;
    int knoten3 = -1;

    public Knoten(int line1, int line2, int line3){
        knoten1 = line1;
        knoten2 = line2;
        knoten3 = line3;
    }

    public int getKnoten1(){
        return knoten1;
    }

    public int getKnoten2(){
        return knoten2;
    }

    public int getKnoten3(){
        return knoten3;
    }
}
