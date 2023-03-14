import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class PixelCreator {

    JPanel jp;
    int rc = 110;
    int cc = 100;
    int id = 0;
    int count = rc*cc;

    ArrayList<JButton> buttons = new ArrayList<>();
    int[][] coords = Var.coords;

    public PixelCreator(int  width, int height){
        jp = new JPanel();
        jp.setVisible(true);
        jp.setSize(width, height);

        /*System.out.println(getPixelByImage());
        String[] data = getPixelByImage().split(",");
        rc = getImageWidth();
        cc = getImageHeight();

        System.out.println(rc*cc);

        Led led = new Led();


        int index = 0;

        for(int y = 0; y < cc; y++){
            for(int x = 0; x < rc; x++){
                if(data[index].equals("0")) {

                    JButton b = new JButton();
                    b.setBackground(new Color(0, 0, 0));
                    b.setVisible(true);
                    b.setBounds(x * (width / rc), y * (width / rc), width / rc, width / rc);
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            b.setBackground(new Color(255, 0, 0));

                            try {
                                File f = new File("C:\\Users\\Jason Enns\\Desktop\\coords.txt");
                                FileWriter fw = new FileWriter(f, true);
                                fw.append("{" + b.getX() + "," + b.getY() + "," + id + "},");
                                id++;
                                fw.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            //System.out.println(id);

                        }
                    });
                    jp.add(b);
                }
                index++;
            }
        }*/
        for(int i = 0; i < coords.length; i++){
            JButton b = new JButton();
            b.setBackground(new Color(0, 0, 0));
            b.setVisible(true);
            b.setText(coords[i][2] + "");
            b.setBounds(coords[i][0]/2, coords[i][1]/2, width / rc, width / rc);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(b.getText());
                }
            });
            jp.add(b);
            buttons.add(b);
        }
    }

    public JPanel getPanel(){
        return jp;
    }

    public String getPixelByImage(){
        String colors = "";
        File f = new File("C:\\Users\\Jason Enns\\Desktop\\coordsImage.png");
        try{
            BufferedImage image = ImageIO.read(f);
            for(int y = 0; y < image.getHeight(); y++){
                for(int x = 0; x < image.getWidth(); x++){
                    if(colors.equals("")){
                        Color c = new Color(image.getRGB(x,y));
                        if(c.getRed() == 0){
                            colors = "0";
                        }else{
                            colors = "1";
                        }
                    }else{
                        Color c = new Color(image.getRGB(x,y));
                        if(c.getRed() == 0){
                            colors = colors + ",0";
                        }else{
                            colors = colors + ",1";
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return colors;

    }

    public int getImageWidth(){
        File f = new File("C:\\Users\\Jason Enns\\Desktop\\coordsImage.png");
        try{
            BufferedImage image = ImageIO.read(f);
            return image.getWidth();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;

    }

    public int getImageHeight(){
        File f = new File("C:\\Users\\Jason Enns\\Desktop\\coordsImage.png");
        try{
            BufferedImage image = ImageIO.read(f);
            return image.getHeight();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;

    }

    public ArrayList<JButton> getButtons(){
        return buttons;
    }

}
