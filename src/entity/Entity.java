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

    public Entity(String name, int baseHealth, int baseAttack, Race race) {
        this.name = name;
        this.curHealth = baseHealth;
        this.curAttack = baseAttack;
        this.race = race;
        this.experience = 0;
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

    public Race getRace() {
        return race;
    }

    public void damageEntity(int damage, Entity from){
        this.curHealth = (curHealth - damage);
    }

}
