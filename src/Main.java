import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static int cordToNum(int x, int y){
        return (y*64)+x;
    }

    public static void main(String[] args) {
        Random rand = new Random();

        JPanel background = new JPanel();
        background.setBackground(Color.black);
        background.setBounds(0,0, 640, 640);

        ArrayList<JPanel> objects = new ArrayList<>();

        for(int y = 0;y<64;y++){
            for(int x = 0;x<64;x++){
                JPanel object = new JPanel();
                object.setBounds(10*x,10*y,10,10);

                float r=rand.nextFloat();
                float g=rand.nextFloat();
                float b=rand.nextFloat();
                object.setBackground(new Color(r,g,b));
                objects.add(object);
            }
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setSize(640,640);
        frame.setVisible(true);
        int x= 0;
        for(JPanel jp : objects){
            frame.add(jp);
        }
        objects.get(cordToNum(0,0)).setBackground(Color.BLACK);
        objects.get(cordToNum(1,1)).setBackground(Color.BLACK);
        objects.get(cordToNum(2,2)).setBackground(Color.BLACK);
        objects.get(cordToNum(3,3)).setBackground(Color.BLACK);
        objects.get(cordToNum(4,4)).setBackground(Color.BLACK);
        frame.repaint();



    }
}