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
                  LocalDate playerBirth, ArrayList playerAdjectives) {
        super(name, startHealth, startAttack, playerRace, playerStrenght, playerAgility, playerBirth, playerAdjectives);
        this.playerColor = new Color(250, 255, 0);
    }

    public void spawnPlayer(ArrayList<ArrayList<Tile>> worldMap, Tile position, JPanel sideBarPlayerPanel){
        Random rand = new Random();
        if(position != null) {
            this.worldPosition = position;
        }
        else{
            this.worldPosition = worldMap.get(rand.nextInt(0,64)).get(rand.nextInt(0,64));
        }


        for(Component jComponent : sideBarPlayerPanel.getComponents()){
            if(jComponent instanceof JLabel) {
                JLabel label = (JLabel) jComponent;
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

}
