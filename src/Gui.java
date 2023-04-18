import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Gui {

    /*
    screen frame

    1 = 0-16 | 2559,1399 - 2131,1080
    2 = 17-33 | 2128,1393 - 1707,721 | 2129,721 - 2559,1081
    3 = 34-50 | 1278,1080 - 1707,0 | 1708,0 - 2131,722 | 2132,351 - 2559,720
    4 = 51-67 | 2132,351 - 2559,0 | 857,717 - 1277,1399 | 1278,1399 - 1707,1080
    5 = 68-84 | 427,0 - 855,1399 | 855,0 - 1277,716
    6 = 85-101 | 0,0 - 427,1399

     */

    JFrame jf;
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
        jf.setVisible(false);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(width+20,height+60);
        jf.setLayout(null);

        PixelCreator cc = new PixelCreator(width, height);
        jf.add(cc.getPanel());

        Led led = new Led(cc.getButtons());

        JButton b = new JButton("next");
        b.setVisible(true);
        b.setBounds(width/2, height-height/4, 100,30);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientManager cm = new ClientManager();
                cm.setPixel(pos, 255,0,0);
                //led.setLed(1,pos,0,255,0,0);
                System.out.println(pos);
                pos++;
            }
        });
        jf.add(b);

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
            getScreen();
            /*rlColor();
            cm.setAllLed(r,g,b);
            led.reset();*/
            /*for(int i = 0; i <= max; i++){
                led.setLed(1,i,delay,r,g,b);
                increaseColor();
                //rgbColor();
            }*/

            /*for(int i = 0; i <= max; i++){
                led.setLed(1,delay,0,255,0);
            }
            led.reset();
            for(int i = 0; i <= max; i++){
                led.setLed(1,delay,0,0,255);
            }*/
            led.reset();
        }
    }

    public void getScreen(){
        int[] zc = {0,0,0,0,0,0};
        int[] zar = {0,0,0,0,0,0};
        int[] zag = {0,0,0,0,0,0};
        int[] zab = {0,0,0,0,0,0};
        int[] zdr = {0,0,0,0,0,0};
        int[] zdg = {0,0,0,0,0,0};
        int[] zdb = {0,0,0,0,0,0};


        try {
            Robot r = new Robot();
            Rectangle screenRect = new Rectangle(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                    (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());

            BufferedImage capture = r.createScreenCapture(screenRect);
            for(int y = 0; y < capture.getHeight(); y++){
                for(int x = 0; x < capture.getWidth(); x++){
                    int zone = led.getZoneByPos(x,y);
                    if(zone != -1){
                        zc[zone]++;
                        Color col = new Color(capture.getRGB(x, y));
                        zar[zone] += col.getRed();
                        zag[zone] += col.getGreen();
                        zab[zone] += col.getBlue();
                    }
                }
            }
            for(int i = 0; i < zdr.length; i++){
                zdr[i] = zar[i]/zc[i];
                zdg[i] = zag[i]/zc[i];
                zdb[i] = zab[i]/zc[i];
            }

            for(int i = 0; i < zdr.length; i++){
                led.fillZone(i,zdr[i], zdg[i], zdb[i]);
            }



        } catch (AWTException e) {
            throw new RuntimeException(e);
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
