package entity;

import entity.race.Race;
import items.Item;

import java.util.ArrayList;

public class Player extends Entity {
    private ArrayList<Item> inventory;
    public Player(String name, Race playerRace){
        super(name, playerRace);
    }
    public Player(String name, int startHealth, int startAttack, Race playerRace) {
        super(name, startHealth, startAttack, playerRace);
    }
}
