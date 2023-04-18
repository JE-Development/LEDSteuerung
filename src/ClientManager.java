import client.OpenRGBClient;
import entity.OpenRGBColor;
import entity.OpenRGBDevice;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class ClientManager {

    int size = 102;
    OpenRGBColor[] openRGBColors;
    OpenRGBClient client;

    public ClientManager(){
        openRGBColors = new OpenRGBColor[size];
        Arrays.fill(openRGBColors, OpenRGBColor.fromHexaString("#000000"));
        client = new OpenRGBClient("localhost", 6742, "Some client name");
        try {
            client.connect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPixel(int pixel, int r, int g, int b){

        Color your_color = new Color(r,g,b);

        String hex = "#"+Integer.toHexString(your_color.getRGB()).substring(2);



        try {

            int deviceIndex = 0;

            OpenRGBDevice controller = client.getDeviceController(deviceIndex);
            //System.out.println(controller);

            openRGBColors[pixel] = OpenRGBColor.fromHexaString(hex);

            client.updateLeds(deviceIndex, openRGBColors);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAllLed(int r, int g, int b){

        Color your_color = new Color(r,g,b);

        String hex = "#"+Integer.toHexString(your_color.getRGB()).substring(2);

        OpenRGBClient client = new OpenRGBClient("localhost", 6742, "Some client name");

        try {
            client.connect();

            int deviceIndex = 0;

            OpenRGBDevice controller = client.getDeviceController(deviceIndex);
            //System.out.println(controller);

            for(int i = 0; i < size; i++){
                openRGBColors[i] = OpenRGBColor.fromHexaString(hex);
            }

            client.updateLeds(deviceIndex, openRGBColors);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
