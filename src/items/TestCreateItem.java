package items;

import items.weapons.Sword;

public class TestCreateItem {
    public static void main(String[] args) {
        Sword saber = new Sword("A bladed curved weapon", 200, 20, 40,
                100, "Saber", 1, 60, false);

        System.out.println(saber);
    }
}
