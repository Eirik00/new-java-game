package entity.race;

import java.nio.file.Files;
import java.nio.file.Path;
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


        ArrayList broAdjectives = new ArrayList<>();

        broAdjectives.add("blue");
        broAdjectives.add("red");
        broAdjectives.add("green");

        ArrayList<String> broAdjectives2 = new ArrayList<String>();



      String contentAppearance = "attractive bald beautiful chubby clean dazzling drab elegant fancy fit flabby glamorous gorgeous handsome long magnificent muscular plain plump quaint scruffy shapely short skinny stocky ugly unkempt unsightly";
      String contentPersonality = "aggressive agreeable ambitious brave calm delightful eager faithful gentle happy jolly kind lively nice obedient polite proud silly thankful victorious witty wonderful zealous";

        for(String word : contentAppearance.split(" ")) {
            broAdjectives2.add(word);
        }

        for(String word : contentPersonality.split(" ")) {
            broAdjectives2.add(word);
        }

        NPC bro = new NPC("Bro", dwarf, LocalDate.of(1907, 2, 2), broAdjectives2);

        System.out.println(bro.getName() + " is a " + bro.getRace() + ", he is " + bro.getEntityAge() + " years old");

        System.out.println(bro.generateEntityDescription());
    }
}
