import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {

    JFrame jf;
    LedPanel lp;
    int width = 1000;
    int height = 800;
    //int width = 2000;
    //int height = 1300;
    int ledMax = 101;
    int pos = 0;

    public Gui(){

    }

    public void create(){
        jf = new JFrame("Controller");
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(width+20,height+60);
        jf.setLayout(null);

        JButton b = new JButton("next");
        b.setVisible(true);
        b.setBounds(width/2, height-height/4, 100,30);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientManager cm = new ClientManager();
                cm.setPixel(pos, 255,0,0);
                pos++;
            }
        });
        jf.add(b);

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
    int max = 101;
    int delay = 100;

    int r = 255;
    int g = 0;
    int b = 0;

    int hue = 0;

    int hueIndex = 1;

    ClientManager cm = new ClientManager();

    public LedSet(Led led, int max){
        this.led = led;
        this.max = max;
    }

    public void run(){
        while (true){
            int index = 5;
            /*for(int i = 0; i <= max; i++){
                led.setLed(1,i,delay,r,g,b);
                //increaseColor();
                rlColor();
                //rgbColor();
            }*/
            rlColor();
            cm.setAllLed(r,g,b);
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

    public void rlColor(){


        try {
            Robot robot = new Robot();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) screenSize.getWidth();
            int height = (int) screenSize.getHeight();

            Color col = robot.getPixelColor(1103,86);

            int red = col.getRed();
            int green = col.getGreen();
            int blue = col.getBlue();
            if(blue > 150){
                if(red > 120){
                    r = 0;
                    g = 255;
                    b = 255;
                }else{
                    r = 0;
                    g = 0;
                    b = 255;
                }
            }else if(red > 150){
                if(blue > 100){
                    r = 255;
                    g = 255;
                    b = 0;
                }else{
                    r = 255;
                    g = 100;
                    b = 0;
                }
            }else{
                r = 0;
                g = 255;
                b = 0;
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
