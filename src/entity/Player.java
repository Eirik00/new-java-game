package entity;

import entity.race.Race;
import items.Item;
import object.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import java.time.LocalDate;
import java.util.Random;

public class Player extends Entity {
    private ArrayList<Item> inventory;

    // Player Pos
    private Tile worldPosition;
    private Tile worldNextPos;
    private Color playerColor;

    // Init Player
    public Player(String name, Race playerRace){  // current
        super(name, playerRace);
        this.playerColor = new Color(250, 255, 0);
    }
    public Player(String name, int startHealth, int startAttack,
                  Race playerRace, int playerStrenght, int playerAgility,
                  LocalDate playerBirth, ArrayList playerAdjectives) { // ?ArrayList?
        super(name, startHealth, startAttack, playerRace, playerStrenght, playerAgility, playerBirth, playerAdjectives);
        this.playerColor = new Color(250, 255, 0);
    }

    public void spawnPlayer(ArrayList<ArrayList<Tile>> worldMap, Tile position, JPanel sideBarPlayerPanel){
        Random rand = new Random();
        if(position != null) {
            this.worldPosition = position;
        }
        else{
            do {
                this.worldPosition = worldMap.get(rand.nextInt(0, 64)).get(rand.nextInt(0, 64));
            } while (this.worldPosition.getTileChar().equals("r"));
        }


        for(Component jComponent : sideBarPlayerPanel.getComponents()){
            if(jComponent instanceof JLabel label) {
                switch (label.getName()) {
                    case "nameLabel":
                        label.setText(label.getText() + super.getName());
                    case "xpLabel":
                        label.setText(label.getText() + super.getExperience());
                }
            }
        }
        worldPosition.toggleHasPlayer(playerColor);
    }

    public Tile getWorldPosition() {
        return worldPosition;
    }

    private void setWorldPosition(Tile posTile){
        Tile prevTile = getWorldPosition();
        prevTile.toggleHasPlayer(playerColor);
        posTile.toggleHasPlayer(playerColor);
    }

    public boolean canPlayerTravel( Tile destTile) {
        if (destTile.getTileChar().equals("r")){
            return false;
        }
        return !destTile.getHasPlayer();
    }

    public void travelPlayer(ArrayList<ArrayList<Tile>> worldMap, Tile destTile) {
        new Thread(() -> {
            System.out.println("Thread Started");
            int cX = getWorldPosition().getTileX();
            int cY = getWorldPosition().getTileY();
            int dX = destTile.getTileX();
            int dY = destTile.getTileY();
            System.out.println("Cur: " + cX + " : " + cY + " | des: " + dX + " : " + dY);

            while(cX != dX && cY != dY){
                if(cX > dX){
                    cX--;
                }else if(cX < dX){
                    cX++;
                }
                if(cY > dY){
                    cY--;
                }else if(cY < dY){
                    cY++;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(cX + " : " + cY);
                setWorldPosition(worldMap.get(cX).get(cY));
            }
        }).start();
    }
}
