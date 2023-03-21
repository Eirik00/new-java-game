package items;

import items.weapons.Sword;
import items.weapons.Weapon;

public class Main {
    public static void main(String[] args) {
        Sword saber = new Sword("A bladed curved weapon", 200, 20, 40,
                100, "Saber", 1, 60, true);

        System.out.println(saber.getSwordDescription());
    }
}
