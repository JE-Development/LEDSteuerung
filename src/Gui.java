import javax.swing.*;
import java.awt.*;

public class Gui {

    JFrame jf;
    LedPanel lp;
    int width = 1000;
    int height = 800;
    //int width = 2000;
    //int height = 1300;
    int ledMax = 101;

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

        LedSet ls = new LedSet(led, ledMax);
        ls.start();





        jf.repaint();
    }

}

class LedSet extends Thread{
    Led led;
    int max = 0;
    int delay = 50;

    int r = 255;
    int g = 0;
    int b = 0;

    int hue = 0;

    int hueIndex = 1;

    public LedSet(Led led, int max){
        this.led = led;
        this.max = max;
    }

    public void run(){
        while (true){
            int index = 5;
            for(int i = 0; i <= max; i++){
                led.setLed(1,delay,r,g,b);
                if(i - index >= 0){
                    led.setLed(1,delay,0,0,0);
                }
                increaseColor();
                //rgbColor();
            }
            led.reset();
            /*for(int i = 0; i <= max; i++){
                led.setLed(1,delay,0,255,0);
            }
            led.reset();
            for(int i = 0; i <= max; i++){
                led.setLed(1,delay,0,0,255);
            }
            led.reset();*/
        }
    }

    public void increaseColor(){
        Color color = Color.getHSBColor((float)hue / 360f, 1f, 1f);
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
        hue += hueIndex;
    }

    public void rgbColor(){
        if(r == 255){
            g = 255;
            r = 0;
        }else if(g == 255){
            g = 0;
            b = 255;
        }else if(b == 255){
            b = 0;
            r = 255;
        }
    }

}
