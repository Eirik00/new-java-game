package entity.race;

public class Race {
    //race buffs
    private String raceName;
    private int healthBuff;
    private int attackBuff;

    public Race(String raceName, int healthBuff, int attackBuff) {
        this.raceName = raceName;
        this.healthBuff = healthBuff;
        this.attackBuff = attackBuff;
    }

    public int getHealthBuff() {
        return healthBuff;
    }

    public int getAttackBuff() {
        return attackBuff;
    }

    @Override
    public String toString(){
        return raceName;
    }
}
