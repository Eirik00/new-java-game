package entity;

import entity.race.Race;
import items.Item;

import java.util.ArrayList;

import java.time.LocalDate;

public class Player extends Entity {
    private ArrayList<Item> inventory;
    public Player(String name, Race playerRace){
        super(name, playerRace);
    }
    public Player(String name, int startHealth, int startAttack, Race playerRace, int playerStrenght, int playerAgility, LocalDate playerBirth) {
        super(name, startHealth, startAttack, playerRace, playerStrenght, playerAgility, playerBirth);
    }
}
