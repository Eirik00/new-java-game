package entity.race;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestEntityMaker {
    public static void main(String[] args) {

        ArrayList dwarf_gods = new ArrayList<>();

        dwarf_gods.add("God of Ruby");
        dwarf_gods.add("God of Emerald");
        dwarf_gods.add("God of Sapphire");
        Religion dwarven_religion = new Religion(dwarf_gods);
        Race dwarf = new Race("Dwarf", -10, 20, 30, 10, dwarven_religion);

        NPC bro = new NPC("Bro", dwarf, LocalDate.of(1920, 2, 2));

        System.out.println(bro.getName() + " is a " + bro.getRace() + ", he is " + bro.getEntityAge() + " years old");
    }
}
