package menu;

import entity.Player;
import init.Worldgen;
import object.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Game {

    public int loadingProgress = 0;

    private Player mainPlayer;

    private boolean updateMap = true;

    public Game(Player mainPlayer){
        this.mainPlayer = mainPlayer;
    }
    public void createAndShowGUI(Worldgen worldgen, Consumer<Integer> progressUpdate) throws IOException, InterruptedException {
        ArrayList<ArrayList<Tile>> worldMapTiles = worldgen.getWorldLayout("tempworld");
        final Tile[] activeTile = {null};

        //Create main application
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setFont(new Font("Verdana", Font.PLAIN, 18));
        //frame.setUndecorated(true);
        frame.setSize(1280,1000);
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

        JLabel tileSelected = new JLabel("Selected Tile: ");
        tileSelected.setForeground(Color.white);
        sideBarTileInfo.add(tileSelected);

        //Create info sideBarPlayerInfo
        JPanel sideBarPlayerInfo = new JPanel(new GridLayout(6,1));
        sideBarPlayerInfo.setBounds(980,280, 260, 240);
        sideBarPlayerInfo.setBackground(Color.black);
        frame.add(sideBarPlayerInfo);

        //Create labels
        JLabel playerName = new JLabel("Name: ");
        playerName.setName("nameLabel");
        playerName.setForeground(Color.white);
        sideBarPlayerInfo.add(playerName);

        JLabel playerXp = new JLabel("LvL: ");
        playerXp.setName("xpLabel");
        playerXp.setForeground(Color.white);
        sideBarPlayerInfo.add(playerXp);

        //Create actionbuttons panel
        JPanel actionButtons = new JPanel(new GridLayout(3,3));
        actionButtons.setBounds(980,540,260,240);
        actionButtons.setBackground(Color.black);
        frame.add(actionButtons);

        //Create actionbuttons
        JButton travelButton = new JButton("Travel");
        travelButton.setEnabled(false);
        travelButton.addActionListener(e -> {
            if(activeTile[0] != null) {
                mainPlayer.travelPlayer(worldMapTiles, activeTile[0]);
                System.out.println("Travel Button Clicked");
            }
        });
        actionButtons.add(travelButton);

        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(e ->{

        });
        actionButtons.add(inventoryButton);

        // Spawn Player
        mainPlayer.spawnPlayer(worldMapTiles, null, sideBarPlayerInfo);
        System.out.println(mainPlayer.getWorldPosition());

        //Draw world map tiles
        int tileY = 0;
        for(ArrayList<Tile> lineTiles : worldMapTiles){
            int tileX = 0;
            for(Tile tile : lineTiles){
                tile.setTileX(tileX);
                tile.setTileY(tileY);

                //Create tile
                JPanel object = new JPanel(new BorderLayout());
                object.setBounds(15*tileX,15*tileY,15,15);
                object.setBackground(tile.getTileColor());

                Color background = tile.getTileColor();
                object.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if(!tile.getSelected()){
                            object.setBackground(new Color(255,255,0));
                        }
                        tileType.setText("Type: "+tile.getTileName());
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        if(tile.getSelected()) return;
                        object.setBackground(background);
                        tileType.setText("Type: ");
                    }
                    @Override
                    public void mouseReleased(MouseEvent e){
                        System.out.println(tile.getTileX() + " : " + tile.getTileY());
                        int objectIndex = 0;
                        for(ArrayList<Tile> chcklineTiles : worldMapTiles){
                            for(Tile chckTile : chcklineTiles){
                                if(chckTile.getSelected()) {
                                    chckTile.toggleSelected();
                                    objects.get(objectIndex).setBackground(chckTile.getTileColor());
                                }
                                objectIndex++;
                            }
                        }
                        tile.toggleSelected();
                        activeTile[0] = tile;
                        object.setBackground(new Color(255, 0, 0));
                        tileSelected.setText("Selected Type: "+tile.getTileName());
                        travelButton.setEnabled(mainPlayer.canPlayerTravel(tile));
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
        loadingProgress = 99;
        progressUpdate.accept(loadingProgress);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.repaint();
        loadingProgress = 100;
        progressUpdate.accept(loadingProgress);

        //map updater
        new Thread(() -> {
            while(updateMap){
                frame.revalidate();
                frame.repaint();
                for(ArrayList<Tile> tilelist : worldMapTiles){
                    for(Tile tile : tilelist){
                        if(tile.getHasPlayer()){
                            System.out.println("player pos: " + tile.getTileX() + " : " + tile.getTileY());
                        }
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void updatePlayerPos(){

    }
}
