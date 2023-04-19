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
    int ledMaxWall = 101;
    int ledMaxCase = 15;
    int pos = 0;
    LedSet ls;
    boolean isCaseModus = false;

    public Gui(){

    }

    public void create(){


        PixelCreator cc = new PixelCreator(width, height);

        Led led = new Led(cc.getButtons());

        ls = new LedSet(led, ledMax);
        ls.start();



        //createPreviewGui(led, cc);
        createSelectionGui(led);
    }

    public void createPreviewGui(Led led, PixelCreator cc){
        jf = new JFrame("Controller");
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(width+20,height+60);
        jf.setLayout(null);

        jf.add(cc.getPanel());

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

        JButton pc = new JButton("pc led modus");
        pc.setVisible(true);
        pc.setBounds(width/2, height-height/4+40, 130,30);
        pc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isCaseModus){
                    isCaseModus = false;
                    pc.setText("pc led modus");
                    ls.stop();
                    ledMax = ledMaxWall;
                    ls = new LedSet(led, ledMax);
                    ls.start();
                }else{
                    isCaseModus = true;
                    pc.setText("wall led modus");
                    ls.stop();
                    ledMax = ledMaxCase;
                    ls = new LedSet(led, ledMax);
                    ls.start();
                }
            }
        });
        jf.add(pc);


        jf.repaint();
    }

    public void createSelectionGui(Led led){
        jf = new JFrame("Selection");
        jf.setSize(500,300);
        jf.setLayout(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);

        JButton next = new JButton("next");
        next.setVisible(true);
        next.setBounds(10, 10, 100,30);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientManager cm = new ClientManager();
                cm.setPixel(pos, 255,0,0);
                //led.setLed(1,pos,0,255,0,0);
                System.out.println(pos);
                pos++;
            }
        });
        jf.add(next);

        JButton pc = new JButton("pc led modus");
        pc.setVisible(true);
        pc.setBounds(10, 50, 130,30);
        pc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isCaseModus){
                    isCaseModus = false;
                    pc.setText("pc led modus");
                    ls.stop();
                    ledMax = ledMaxWall;
                    ls = new LedSet(led, ledMax);
                    ls.start();
                }else{
                    isCaseModus = true;
                    pc.setText("wall led modus");
                    ls.stop();
                    ledMax = ledMaxCase;
                    ls = new LedSet(led, ledMax);
                    ls.start();
                }
            }
        });
        jf.add(pc);

        JButton step = new JButton("step");
        step.setVisible(true);
        step.setBackground(new Color(255,0,0));
        step.setBounds(140, 10, 130,30);
        step.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ls.setModus("step");
                if(ls.step){
                    step.setBackground(new Color(0,255,0));
                }else{
                    step.setBackground(new Color(255,0,0));
                }
            }
        });
        jf.add(step);

        JButton clear = new JButton("clear");
        clear.setVisible(true);
        clear.setBackground(new Color(255,255,0));
        clear.setBounds(10, 90, 100,30);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                led.allBlack();
                ls.clear();
            }
        });
        jf.add(clear);

        JButton types = new JButton("Animation Types");
        types.setVisible(true);
        types.setBackground(new Color(255,255,255));
        types.setBounds(10, 130, 140,30);
        types.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame j = new JFrame("Animations");
                j.setSize(500,200);
                j.setLayout(null);
                j.setVisible(true);
                j.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

                JLabel green = new JLabel("Wall Animation");
                green.setForeground(new Color(0,255,0));
                green.setVisible(true);
                green.setBounds(5,5,100,15);
                j.add(green);

                JLabel blue = new JLabel("PC Animation");
                blue.setForeground(new Color(0,0,255));
                blue.setVisible(true);
                blue.setBounds(105,5,100,15);
                j.add(blue);

                JButton gb = new JButton("Green Blue");
                gb.setVisible(true);
                gb.setBackground(new Color(0,255,0));
                gb.setBounds(10, 20, 100,30);
                gb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ledMax = ledMaxWall;
                        ls.setMax(ledMax);
                        ls.setModus("gb");
                    }
                });
                j.add(gb);

                JButton gbpc = new JButton("Green Blue");
                gbpc.setVisible(true);
                gbpc.setBackground(new Color(0,0,255));
                gbpc.setForeground(new Color(255,255,255));
                gbpc.setBounds(120, 20, 100,30);
                gbpc.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ledMax = ledMaxCase;
                        ls.setMax(ledMax);
                        ls.setModus("gb");
                    }
                });
                j.add(gbpc);

                JButton ic = new JButton("Increase Color");
                ic.setVisible(true);
                ic.setBackground(new Color(0,255,0));
                ic.setBounds(10, 60, 120,30);
                ic.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ledMax = ledMaxWall;
                        ls.setMax(ledMax);
                        ls.setModus("increaseColor");
                    }
                });
                j.add(ic);

                JButton icpc = new JButton("Increase Color");
                icpc.setVisible(true);
                icpc.setBackground(new Color(0,0,255));
                icpc.setForeground(new Color(255,255,255));
                icpc.setBounds(140, 60, 120,30);
                icpc.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ledMax = ledMaxCase;
                        ls.setMax(ledMax);
                        ls.setModus("increaseColorPc");
                    }
                });
                j.add(icpc);

                JButton screen = new JButton("Screen");
                screen.setVisible(true);
                screen.setBackground(new Color(255,255,255));
                screen.setBounds(10, 100, 120,30);
                screen.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ledMax = ledMaxWall;
                        ls.setMax(ledMax);
                        ls.setModus("screen");
                    }
                });
                j.add(screen);

                JButton rl = new JButton("Rocket League");
                rl.setVisible(true);
                rl.setBackground(new Color(255,255,255));
                rl.setBounds(140, 100, 130,30);
                rl.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ledMax = ledMaxWall;
                        ls.setMax(ledMax);
                        ls.setModus("rl");
                    }
                });
                j.add(rl);



                j.repaint();
            }
        });
        jf.add(types);



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
    int huePc = 0;

    int hueIndex = 1;
    int hueIndexPc = 4;

    boolean clear = false;

    boolean step = false;
    boolean gb = false;
    boolean ic = true;
    boolean icpc = false;
    boolean screen = false;
    boolean rl = false;

    boolean rlGoalBlue = false;
    boolean rlGoalOrange = false;

    ClientManager cm = new ClientManager();

    public LedSet(Led led, int max){
        this.led = led;
        this.max = max;
    }

    public void run(){
        while (true){
            if(!step) {
                if(screen) {
                    getScreen();
                }
                if(rl){
                    if(rlGoalOrange){
                        rlColor();
                        cm.setAllLed(r,g,b);
                        rlGoalOrange = false;
                        for (int i = 0; i <= max; i++) {
                            led.setLed(1, i, 10, 255, 255, 0);
                        }
                        led.reset();
                        for (int i = 0; i <= max; i++) {
                            led.setLed(1, i, 10, 255, 100, 0);
                        }
                    }
                    if(rlGoalBlue){
                        rlColor();
                        cm.setAllLed(r,g,b);
                        rlGoalBlue = false;
                        for (int i = 0; i <= max; i++) {
                            led.setLed(1, i, 10, 0, 255, 255);
                        }
                        led.reset();
                        for (int i = 0; i <= max; i++) {
                            led.setLed(1, i, 10, 0, 0, 255);
                        }
                    }

                    rlColor();
                    cm.setAllLed(r,g,b);
                    led.reset();
                }
                if(ic) {
                    for (int i = 0; i <= max; i++) {
                        led.setLed(1, i, delay, r, g, b);
                        increaseColor();
                        if(clear){
                            break;
                        }
                    }
                }

                if(icpc) {
                    for (int i = 0; i <= max; i++) {
                        led.setLed(1, i, delay, r, g, b);
                        increaseColorPc();
                        if(clear){
                            break;
                        }
                    }
                }

                if(gb){
                    for (int i = 0; i <= max; i++) {
                        if(!step) {
                            led.setLed(1, i, delay, 0, 255, 0);
                        }
                        if(clear){
                            break;
                        }
                    }
                    led.reset();
                    for (int i = 0; i <= max; i++) {
                        if(!step) {
                            led.setLed(1, i, delay, 0, 0, 255);
                        }
                        if(clear){
                            break;
                        }
                    }
                }
            }
            clear = false;
            led.reset();
        }
    }

    public void setModus(String modus){
        switch (modus){
            case "step":
                setAllFalse();
                if(step){
                    step = false;
                }else {
                    step = true;
                }
                clear();
                break;
            case "gb":
                setAllFalse();
                gb = true;
                clear();
                break;
            case "increaseColor":
                setAllFalse();
                ic = true;
                clear();
                break;
            case "increaseColorPc":
                setAllFalse();
                icpc = true;
                clear();
                break;
            case "screen":
                setAllFalse();
                screen = true;
                clear();
                break;
            case "rl":
                setAllFalse();
                rl = true;
                clear();
                break;

        }
    }

    public void setAllFalse(){
        step = false;
        gb = false;
        ic = false;
        icpc = false;
        screen = false;
        rl = false;
    }

    public void clear(){
        clear = true;
    }

    public void setMax(int max){
        this.max = max;
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

    public void increaseColorPc(){
        Color color = Color.getHSBColor((float)huePc / 360f, 1f, 1f);
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
        huePc += hueIndexPc;
    }

    public void rlColor(){


        try {
            Robot robot = new Robot();

            Color col = robot.getPixelColor(1058,90);

            Color verify[] = new Color[6];

            verify[0] = robot.getPixelColor(1270,73);
            verify[1] = robot.getPixelColor(1270,89);

            verify[2] = robot.getPixelColor(1256,73);
            verify[3] = robot.getPixelColor(1256,89);

            verify[4] = robot.getPixelColor(1241,73);
            verify[5] = robot.getPixelColor(1241,89);



            if(checkVerify(verify)){
                int red = col.getRed();
                int green = col.getGreen();
                int blue = col.getBlue();
                if(blue > 150){
                    if(red > 120){
                        rlGoalBlue = true;
                    }else{
                        r = 0;
                        g = 0;
                        b = 255;
                    }
                }else if(red > 150){
                    if(blue > 100){
                        rlGoalOrange = true;
                    }else{
                        r = 255;
                        g = 100;
                        b = 0;
                    }
                }else{
                    r = red;
                    g = green;
                    b = blue;
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

    public boolean checkVerify(Color[] verify){
        for(int i = 0; i < verify.length; i++){
            if(verify[i].getRed() >= 230 && verify[i].getGreen() >= 230 && verify[i].getBlue() >= 230){
                return true;
            }
        }
        return false;
    }

}
