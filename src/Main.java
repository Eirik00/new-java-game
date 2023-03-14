import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static int cordToNum(int x, int y){
        return (y*64)+x;
    }

    public static void main(String[] args) throws IOException {
        Random rand = new Random();

        JPanel background = new JPanel();
        background.setBackground(Color.black);
        background.setBounds(0,0, 640, 640);

        ArrayList<JPanel> objects = new ArrayList<>();

        for(int y = 0;y<64;y++){
            for(int x = 0;x<64;x++){
                JPanel object = new JPanel(new BorderLayout());
                object.setBounds(10*x,10*y,10,10);

                float r=rand.nextFloat();
                float g=rand.nextFloat();
                float b=rand.nextFloat();
                object.setBackground(new Color(r,g,b));
                JLabel label = new JLabel("=");
                label.setBounds(10*x, 10*y, 10, 10);
                object.add(label);
                objects.add(object);
            }
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setSize(640,640);
        int x= 0;
        for(JPanel jp : objects){
            frame.add(jp);
        }
        frame.setVisible(true);
        frame.repaint();


        Runtime.getRuntime().exec(new String[]{"python python/worldgen.py", "world00001.txt"});

    }
}