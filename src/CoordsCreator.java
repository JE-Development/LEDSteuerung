import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

public class CoordsCreator {

    JPanel jp;
    int rc = 110;
    int cc = 100;
    int id = 0;
    int count = rc*cc;
    /*int[][] coords = {{14,14},{28,28},{42,42},{56,56},{70,70},{84,84},{98,98},{112,112},{126,126},{140,140},{126,154},
            {112,168},{98,182},{84,196},{70,210},{56,224},{42,238},{28,252},{14,266},{28,280},{42,294},{56,308},{70,322},
            {84,336},{98,350},{112,364},{126,378},{140,392},{126,406},{112,420},{98,434},{84,448},{70,462},{56,476},
            {42,490},{28,504},{14,518},{154,154},{168,168},{182,182},{196,196},{210,210},{224,224},{238,238},{252,252},
            {266,266},{154,378},{168,364},{182,350},{196,336},{210,322},{224,308},{252,280},{238,294},{280,280},{294,294},
            {308,308},{322,322},{336,336},{350,350},{364,364},{378,378},{392,392},{406,378},{420,364},{434,350},{448,336},
            {462,322},{476,308},{490,294},{504,280},{518,266},{280,252},{294,238},{308,224},{322,210},{336,196},{350,182},
            {364,168},{378,154},{392,140},{406,154},{420,168},{434,182},{448,196},{462,210},{476,224},{504,252},{490,238},
            {532,280},{546,294},{560,308},{574,322},{588,336},{602,350},{616,364},{630,378},{644,392},{532,252},{546,238},
            {560,224},{574,210},{588,196},{602,182},{616,168},{630,154},{644,140},{658,154},{672,168},{686,182},{700,196},
            {714,210},{728,224},{742,238},{756,252},{770,266},{756,280},{742,294},{728,308},{714,322},{700,336},{686,350},
            {672,364},{658,378},{658,126},{672,112},{686,98},{700,84},{714,70},{728,56},{742,42},{756,28},{770,14},{658,406},
            {672,420},{686,434},{700,448},{714,462},{728,476},{742,490},{756,504},{770,518},{392,406},{392,420},{392,434},
            {392,448},{392,462},{392,476},{392,490},{392,504},{392,518}};
*/
    int[][] coords = Var.coords;

    public CoordsCreator(int  width, int height){
        Led led = new Led();

        jp = new JPanel();
        jp.setVisible(true);
        jp.setSize(width, height);

        for(int y = 0; y < cc; y++){
            for(int x = 0; x < rc; x++){

                //for(int i = 0; i < coords.length; i++){
                    //if(x*(width/rc) == coords[i][0] && y*(width/rc) == coords[i][1]){


                        JButton b = new JButton();
                        b.setBackground(new Color(0,0,0));
                        b.setVisible(true);
                        b.setBounds(x*(width/rc), y*(width/rc), width/rc, width/rc);
                        b.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                b.setBackground(new Color(255,0,0));

                                try {
                                    File f = new File("C:\\Users\\Jason\\Desktop\\coords.txt");
                                    FileWriter fw = new FileWriter(f, true);
                                    fw.append("{" + b.getX() + "," + b.getY() + "," + id + "},");
                                    id++;
                                    fw.close();
                                }catch (Exception ex){
                                    ex.printStackTrace();
                                }
                                //System.out.println(id);

                            }
                        });

                        /*try {
                            File f = new File("C:\\Users\\Jason\\Desktop\\coords.txt");
                            FileWriter fw = new FileWriter(f, true);
                            int pos  = ((x+5)*(width/rc));
                            fw.append("{" + pos + "," + b.getY() + "},");
                            fw.close();
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }*/
                        jp.add(b);
                        //id++;
                    //}
                //}
            }
        }
    }

    public JPanel getPanel(){
        return jp;
    }

}
