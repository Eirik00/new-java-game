package entity;

import entity.race.Race;

import java.time.LocalDate;

public class Player extends Entity {
    public Player(String name, Race playerRace){
        super(name, playerRace);
    }
    public Player(String name, int startHealth, int startAttack, Race playerRace, int playerStrenght, int playerAgility, LocalDate playerBirth) {
        super(name, startHealth, startAttack, playerRace, playerStrenght, playerAgility, playerBirth);
    }
}
