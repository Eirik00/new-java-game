package entity.race;

import entity.Entity;

import java.time.LocalDate;

public class NPC extends Entity {
    public NPC(String name, Race race, LocalDate birth) {
        super(name, race, birth);
    }
}

