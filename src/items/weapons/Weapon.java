package items.weapons;

import items.Item;

public abstract class Weapon extends Item {

    private int weaponDamage;
    private int weaponSpeed;
    private int weaponDurability;


    public Weapon(String description, int value, int weaponDamage, int weaponSpeed, int weaponDurability) {
        super(description, value);
        this.weaponDamage = weaponDamage;
        this.weaponSpeed = weaponSpeed;
        this.weaponDurability = weaponDurability;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public int getWeaponSpeed() {
        return weaponSpeed;
    }

    public int getWeaponDurability() {
        return weaponDurability;
    }
}
