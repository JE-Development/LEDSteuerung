import javax.swing.*;

public class Gui {

    JFrame jf;
    LedPanel lp;
    int width = 1000;
    int height = 800;
    //int width = 2000;
    //int height = 1300;

    public Gui(){

    }

    public void create(){
        jf = new JFrame("Controller");
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(width+20,height+60);
        jf.setLayout(null);

        lp = new LedPanel();
        PixelCreator cc = new PixelCreator(width, height);
        jf.add(cc.getPanel());

        Led led = new Led(cc.getButtons());
        led.setLed(40);





        jf.repaint();
    }

}
