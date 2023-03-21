package entity;

import entity.race.Race;
import object.Tile;

public abstract class Entity {
    private String name;
    private int experience;
    private Tile position;

    private int curHealth;
    private int curAttack;
    private Race race;
    private int strength;
    private int agility;

    public Entity(String name, int baseHealth, int baseAttack, Race race, int strength, int agility) {
        this.name = name;
        this.curHealth = baseHealth + agility;
        this.curAttack = baseAttack + strength;
        this.race = race;
        this.experience = 0;
        this.strength = getRace().getBaseStrength();
        this.agility = getRace().getBaseAgility();
    }
    public Entity(String name,  Race race) {
        this.name = name;
        this.curHealth = 100;
        this.curAttack = 10;
        this.race = race;
        this.experience = 0;
        this.strength = getRace().getBaseStrength();
        this.agility = getRace().getBaseAgility();
    }
    public void updateAttack(){
        this.curAttack = curAttack+race.getAttackBuff();
    }


    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public Tile getPosition() {
        return position;
    }

    public int getCurHealth() {
        return curHealth;
    }

    public int getCurAttack() {
        return curAttack;
    }

    public int getStrength() { return strength; }

    public int getAgility() { return agility; }

    public Race getRace() {
        return race;
    }

    public void damageEntity(int damage, Entity from){
        this.curHealth = (curHealth - damage);
    }

}
