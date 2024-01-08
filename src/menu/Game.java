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

    private Tile selectedTile;

    private Player mainPlayer;

    public Game(Player mainPlayer){
        this.mainPlayer = mainPlayer;
    }
    public void createAndShowGUI(Worldgen worldgen, Consumer<Integer> progressUpdate) throws IOException, InterruptedException {
        ArrayList<ArrayList<Tile>> worldMapTiles = worldgen.getWorldLayout("tempworld");

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

        //Controll menu
        JPanel CMpanel = new JPanel(new GridBagLayout());
        CMpanel.setBounds(980, 540, 260, 160);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        /// Controll Menu Buttons
        JButton movePlayerButton = new JButton("Travel to Marker");
        movePlayerButton.addActionListener(e -> {
            return;
        });

        gbc.gridx = gbc.gridy = 0;
        CMpanel.add(movePlayerButton, gbc);

        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(e ->{
            return;
        });

        gbc.gridy = 1;
        CMpanel.add(inventoryButton, gbc);

        frame.add(CMpanel);

        //Movement Menu
        JPanel movePanel = new JPanel(new GridBagLayout());
        movePanel.setBounds(980, 700, 260, 160);
        GridBagConstraints movePanelConstraints = new GridBagConstraints();
        movePanelConstraints.gridx = 1;
        movePanelConstraints.gridy = 0;

        JLabel controllLabel = new JLabel("Move");
        movePanel.add(controllLabel, movePanelConstraints);

        JButton movUp = new JButton("↑");
        movUp.addActionListener(e ->{
            updatePlayerPos(0, worldMapTiles, mainPlayer.getWorldPosition().getPos());
        });
        JButton movDown = new JButton("↓");
        movDown.addActionListener(e ->{
            updatePlayerPos(1, worldMapTiles, mainPlayer.getWorldPosition().getPos());
        });
        JButton movLeft = new JButton("←");
        movLeft.addActionListener(e ->{
            updatePlayerPos(2, worldMapTiles, mainPlayer.getWorldPosition().getPos());
        });
        JButton movRight = new JButton("→");
        movRight.addActionListener(e ->{
            updatePlayerPos(3, worldMapTiles, mainPlayer.getWorldPosition().getPos());
        });
        movePanelConstraints.gridx = movePanelConstraints.gridy = 1;
        movePanel.add(movUp, movePanelConstraints);
        movePanelConstraints.gridx = 0;
        movePanelConstraints.gridy = 2;
        movePanel.add(movLeft, movePanelConstraints);
        movePanelConstraints.gridx = 2;
        movePanel.add(movRight, movePanelConstraints);
        movePanelConstraints.gridx = 1;
        movePanelConstraints.gridy = 3;
        movePanel.add(movDown, movePanelConstraints);

        frame.add(movePanel);

        // Spawn Player
        mainPlayer.spawnPlayer(worldMapTiles, null, sideBarPlayerInfo);
        System.out.println(mainPlayer.getWorldPosition());

        //Draw world map tiles
        int tileY = 0;
        for(ArrayList<Tile> lineTiles : worldMapTiles){
            int tileX = 0;
            for(Tile tile : lineTiles){
                //Create tile
                JPanel object = new JPanel(new BorderLayout());
                object.setBounds(15*tileX,15*tileY,15,15);
                object.setBackground(tile.getTileColor());

                Color background = tile.getTileColor();
                //Set Tile Position
                tile.setPosition(tileX, tileY);
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
                        selectedTile = tile;
                        System.out.println(selectedTile.getTileName());
                        object.setBackground(new Color(255, 0, 0));
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
    }

    private void updatePlayerPos(int dir, ArrayList<ArrayList<Tile>> worldMapTiles, int[] pos){
        // 0 = up | 1 = down | 2 = left | 3 = right
        int x, y;
        x = pos[0];
        y = pos[1];
        switch(dir){
            case 0:
                System.out.println(worldMapTiles.get(y-1).get(x).getTileName());
                break;
            case 1:
                System.out.println(worldMapTiles.get(y+1).get(x).getTileName());
                break;
            case 2:
                System.out.println(worldMapTiles.get(y).get(x-1).getTileName());
                break;
            case 3:
                System.out.println(worldMapTiles.get(y).get(x+1).getTileName());
                break;
            default:
                return;
        }
    }
}
