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
        JPanel createCharPanel = new JPanel(new GridLayout(6,1));
        createCharPanel.setBounds(64,64,1152, 882);
        createCharPanel.setBackground(Color.black);

        JPanel name = new JPanel(new GridLayout(2,1));

        JLabel nameTag = new JLabel("Your Name:");
        nameTag.setForeground(Color.black);
        name.add(nameTag);
        JTextField nameInput = new JTextField();
        nameInput.setFont(goodFont);
        nameInput.setSize(600,40);
        name.add(nameInput);

        JPanel race = new JPanel();
        JLabel raceTag = new JLabel("Your Race:");

        JList raceSelectList = new JList(createRaces().toArray());
        raceSelectList.setSelectedIndex(0);
        raceSelectList.setFont(goodFont);

        JScrollPane raceScroll = new JScrollPane(raceSelectList);
        createCharPanel.add(name);
        createCharPanel.add(raceTag);
        createCharPanel.add(raceScroll);
        frame.add(createCharPanel);
    }
}
