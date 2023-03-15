package menu;

import entity.race.Race;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CreateCharacter {
    private static final Font goodFont = new Font("Verdana", Font.PLAIN, 24);
    private ArrayList<Race> createRaces(){
        ArrayList<Race> outputList = new ArrayList<>();
        Race human = new Race("Human", 0, 10);
        outputList.add(human);
        Race dwarf = new Race("Dwarf", -10, 20);
        outputList.add(dwarf);
        Race elf = new Race("Elf", 10, -10);
        outputList.add(elf);

        return outputList;
    }
    public void view(JFrame frame){
        JPanel createCharPanel = new JPanel(new BorderLayout());
        createCharPanel.setBounds(frame.getBounds());

        JList raceSelectList = new JList(createRaces().toArray());
        raceSelectList.setSelectedIndex(0);
        raceSelectList.setFont(goodFont);


        JTextField nameInput = new JTextField();
        nameInput.setFont(goodFont);

        createCharPanel.add(nameInput, BorderLayout.CENTER);
        createCharPanel.add(raceSelectList, BorderLayout.WEST);
        frame.add(createCharPanel);
        frame.pack();
    }
}
