package entity.race;

public class Race {
    //race buffs
    private String raceName;
    private int healthBuff;
    private int attackBuff;
    private int baseStrength;
    private int baseAgility;
    private Religion religion;

    public Race(String raceName, int healthBuff, int attackBuff, int baseStrength, int baseAgility, Religion religion) {
        this.raceName = raceName;
        this.healthBuff = healthBuff;
        this.attackBuff = attackBuff;
        this.baseStrength = baseStrength;
        this.baseAgility = baseAgility;
        this.religion = religion;
    }

    public int getHealthBuff() {
        return healthBuff;
    }

    public int getAttackBuff() {
        return attackBuff;
    }

    public int getBaseStrength() {
        return baseStrength;
    }

    public int getBaseAgility() {
        return baseAgility;
    }

    public Religion getReligion() {
        return religion;
    }

    @Override
    public String toString(){
        return raceName;
    }
}
