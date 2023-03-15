import init.LoadFont;
import init.Worldgen;
import object.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static int cordToNum(int x, int y){
        return (y*64)+x;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Random rand = new Random();

        Worldgen worldgen = new Worldgen();
        worldgen.genWorld("tempworld01");

        JPanel background = new JPanel();
        background.setBackground(Color.black);
        background.setBounds(0,0, 960, 960);

        ArrayList<JPanel> objects = new ArrayList<>();


        //Create main application
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setFont(new Font("Verdana", Font.PLAIN, 18));
        //frame.setUndecorated(true);
        frame.setSize(1280,1000);


        //Create info sidebar
        JPanel sideBar = new JPanel(new GridLayout(3, 3));
        sideBar.setBounds(980,20, 260, 240);
        sideBar.setBackground(Color.black);
        frame.add(sideBar);

        Rectangle sideBarBounds = sideBar.getBounds();

        //Create labels
        JLabel tileType = new JLabel("Type: ");
        tileType.setForeground(Color.white);
        //tileType.setBounds(sideBarBounds.x+5, sideBar.getBounds().y+5, sideBar.getBounds().width-5,20);
        sideBar.add(tileType, BorderLayout.WEST);

        //Generate world map tiles
        int tileY = 0;
        for(ArrayList<Tile> lineTiles : worldgen.getWorldLayout("tempworld01")){
            int tileX = 0;
            for(Tile tile : lineTiles){
                //Create tile
                JPanel object = new JPanel(new BorderLayout());
                object.setBounds(15*tileX,15*tileY,15,15);
                object.setBackground(tile.getTileColor());

                object.addMouseListener(new MouseAdapter() {
                    private Color background;
                    private Boolean selected = false;


                    @Override
                    public void mouseEntered(MouseEvent e) {
                        background = object.getBackground();
                        object.setBackground(new Color(255,255,0));
                        tileType.setText("Type: "+tile.getTileName());
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        object.setBackground(background);
                        tileType.setText("Type: ");
                    }
                });
                //tile char
                JLabel label = new JLabel(tile.getTileChar());
                label.setBounds(15*tileX, 15*tileY, 15, 15);
                object.add(label);

                objects.add(object);
                tileX++;
            }
            tileY++;
        }
        //Draw map
        for(JPanel jp : objects){
            frame.add(jp);
        }
        frame.setVisible(true);
        frame.repaint();
    }
}