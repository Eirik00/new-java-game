package entity.race;

import entity.Entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class NPC extends Entity {
    public NPC(String name, Race race, LocalDate birth, ArrayList adjectives) {
        super(name, race, birth, adjectives);
    }
}

