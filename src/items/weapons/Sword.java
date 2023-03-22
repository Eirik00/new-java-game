package items.weapons;

public class Sword extends Weapon {
    private String swordType;
    private int bladedSides;
    private int length;
    private boolean isCurved;



    public Sword(String description, int value, int weaponDamage, int weaponSpeed, int weaponDurability,
                 String swordType, int bladedSides, int length, boolean isCurved) {
        super(description, value, weaponDamage, weaponSpeed, weaponDurability);
        this.swordType = swordType;
        this.bladedSides = bladedSides;
        this.length = length;
        this.isCurved = isCurved;
    }

    public String getDiscription(){
        if (isCurved() == true) { return "This is a " + swordType + ", it is a " + bladedSides + "-sided blade,"
                + " it is a " + length + " cm long blade, the blade is curved.";}
        else return "This is a " + swordType + ", it is a " + bladedSides + "-sided blade,"
                + " it is a " + length + " cm long blade, the blade is straight.";
    }

    public String getSwordType() {
        return swordType;
    }

    public int getBladedSides() {
        return bladedSides;
    }

    public int getLength() {
        return length;
    }

    public boolean isCurved() {
        return isCurved;
    }
}
