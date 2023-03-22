package entity;

import entity.race.Race;
import object.tile.Tile;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Entity {
    private String name;
    private int experience;
    private Tile position;

    private int curHealth;
    private int curAttack;
    private Race race;
    private int strength;
    private int agility;
    private LocalDate birth;
    private ArrayList adjectives;

    public Entity(String name, int baseHealth, int baseAttack, Race race, int strength, int agility, LocalDate birth,
                  ArrayList adjectives) {
        this.name = name;
        this.curHealth = baseHealth + agility;
        this.curAttack = baseAttack + strength;
        this.race = race;
        this.experience = 0;
        this.strength = getRace().getBaseStrength();
        this.agility = getRace().getBaseAgility();
        this.birth = birth;
        this.adjectives = adjectives;
    }

    public Entity(String name,  Race race) {
        this.name = name;
        this.curHealth = 100;
        this.curAttack = 10;
        this.race = race;
        this.experience = 0;
        this.strength = getRace().getBaseStrength();
        this.agility = getRace().getBaseAgility();
        this.birth = birth;
        this.adjectives = adjectives;

    }

    public Entity(String name, Race race, LocalDate birth, ArrayList adjectives) {
        this.name = name;
        this.race = race;
        this.birth = birth;
        this.adjectives = adjectives;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String generateEntityDescription() {
        return this.getName() + " is " + this.getAdjectives().get(getRandomNumber(0,26)) + " in appearance, with a " +
                this.getAdjectives().get(getRandomNumber(27, 48)) + " personality";
    }

    public void updateAttack(){
        this.curAttack = curAttack+race.getAttackBuff();
    }

    public int getEntityAge(){
        return LocalDate.now().getYear() - this.getBirth().getYear();
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

    public LocalDate getBirth() {
        return birth;
    }

    public ArrayList getAdjectives() {
        return adjectives;
    }

    public void damageEntity(int damage, Entity from){
        this.curHealth = (curHealth - damage);
    }

}
