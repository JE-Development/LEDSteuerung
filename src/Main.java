public class Main {
    public static void main(String[] args) {
        Gui g = new Gui();
        g.create();

        ClientManager cm = new ClientManager();
        cm.setPixel(101,0,0,0);
    }
}