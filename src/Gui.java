import javax.swing.*;

public class Gui {

    JFrame jf;
    LedPanel lp;
    int width = 1000;
    int height = 800;

    public Gui(){

    }

    public void create(){
        jf = new JFrame("Controller");
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(width+20,height+20);
        jf.setLayout(null);

        lp = new LedPanel();
        CoordsCreator cc = new CoordsCreator(width, height);
        jf.add(cc.getPanel());




        jf.repaint();
    }

}
