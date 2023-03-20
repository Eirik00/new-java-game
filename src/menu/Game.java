package menu;

import entity.Player;
import init.Worldgen;
import object.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Game {

    public int loadingProgress = 0;

    private Player mainPlayer;

    public Game(Player mainPlayer){
        this.mainPlayer = mainPlayer;
    }
    public void createAndShowGUI(Consumer<Integer> progressUpdate) throws IOException, InterruptedException {
        //Create main application
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setFont(new Font("Verdana", Font.PLAIN, 18));
        loadingProgress = 10;
        progressUpdate.accept(loadingProgress);
        //frame.setUndecorated(true);
        frame.setSize(1280,1000);
        Worldgen worldgen = new Worldgen();
        worldgen.genWorld("tempworld");
        loadingProgress = 35;
        progressUpdate.accept(loadingProgress);

        ArrayList<JPanel> objects = new ArrayList<>();

        //Create info sideBarTileInfo
        JPanel sideBarTileInfo = new JPanel(new GridLayout(3, 3));
        sideBarTileInfo.setBounds(980,20, 260, 240);
        sideBarTileInfo.setBackground(Color.black);
        frame.add(sideBarTileInfo);

        //Create labels
        JLabel tileType = new JLabel("Type: ");
        tileType.setForeground(Color.white);
        sideBarTileInfo.add(tileType);

        //Create info sideBarPlayerInfo
        JPanel sideBarPlayerInfo = new JPanel(new GridLayout(6,1));
        sideBarPlayerInfo.setBounds(980,280, 260, 240);
        sideBarPlayerInfo.setBackground(Color.black);
        frame.add(sideBarPlayerInfo);

        //Create labels
        JLabel playerName = new JLabel("Name: ");
        playerName.setForeground(Color.white);
        sideBarPlayerInfo.add(playerName);

        JLabel playerXp = new JLabel("LvL: ");
        playerXp.setForeground(Color.white);
        sideBarPlayerInfo.add(playerXp);



        //Generate world map tiles
        int tileY = 0;
        for(ArrayList<Tile> lineTiles : worldgen.getWorldLayout("tempworld")){
            int tileX = 0;
            for(Tile tile : lineTiles){
                //Create tile
                JPanel object = new JPanel(new BorderLayout());
                object.setBounds(15*tileX,15*tileY,15,15);
                object.setBackground(tile.getTileColor());

                object.addMouseListener(new MouseAdapter() {
                    private Color background;


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
            loadingProgress++;
            progressUpdate.accept(loadingProgress);
            tileY++;
        }
        //Draw map
        for(JPanel jp : objects){
            frame.add(jp);
        }


        loadingProgress = 99;
        progressUpdate.accept(loadingProgress);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.repaint();
        loadingProgress = 100;
        progressUpdate.accept(loadingProgress);
    }
}
