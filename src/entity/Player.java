package entity;

import entity.race.Race;

public class Player extends Entity {

    public Player(String name, int startHealth, int startAttack, Race playerRace) {
        super(name, startHealth, startAttack, playerRace);
    }
}
