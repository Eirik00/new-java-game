import init.Worldgen;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Main {

    public static int cordToNum(int x, int y){
        return (y*64)+x;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Random rand = new Random();

        JPanel background = new JPanel();
        background.setBackground(Color.black);
        background.setBounds(0,0, 960, 960);

        ArrayList<JPanel> objects = new ArrayList<>();

        for(int y = 0;y<64;y++){
            for(int x = 0;x<64;x++){
                JPanel object = new JPanel(new BorderLayout());
                object.setBounds(15*x,15*y,15,15);

                float r=rand.nextFloat();
                float g=rand.nextFloat();
                float b=rand.nextFloat();
                object.setBackground(new Color(r,g,b));
                JLabel label = new JLabel("=");
                label.setBounds(15*x, 15*y, 15, 15);
                object.add(label);
                objects.add(object);
            }
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setSize(960,960);
        int x= 0;
        for(JPanel jp : objects){
            frame.add(jp);
        }
        frame.setVisible(true);
        frame.repaint();

        Worldgen worldgen = new Worldgen();
        worldgen.genWorld();
    }
}