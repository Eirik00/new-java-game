package menu;

import entity.Player;
import entity.race.Race;
import entity.race.Religion;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CreateCharacter {
    public Player mainPlayer;
    private static final Font goodFont = new Font("Verdana", Font.PLAIN, 24);

    public interface CharacterCreatedCallback {
        void onCharacterCreated(String characterName, Race characterRace);
    }



    private Race[] createRaces(){


        //Lager religioner
        ArrayList dwarf_gods = new ArrayList<>();
        ArrayList human_gods = new ArrayList<>();
        ArrayList elven_gods = new ArrayList<>();

        dwarf_gods.add("God of Ruby");
        dwarf_gods.add("God of Emerald");
        dwarf_gods.add("God of Sapphire");
        Religion dwarven_religion = new Religion(dwarf_gods);

        human_gods.add("God of Life");
        human_gods.add("God of Death");
        Religion human_religion = new Religion(human_gods);

        elven_gods.add("God of Growth");
        elven_gods.add("God of Harvest");
        Religion elven_religion = new Religion(elven_gods);


        ArrayList<Race> outputList = new ArrayList<>();
        Race human = new Race("Human", 0, 10, 20, 20, human_religion);
        outputList.add(human);
        Race dwarf = new Race("Dwarf", -10, 20, 30, 10, dwarven_religion);
        outputList.add(dwarf);
        Race elf = new Race("Elf", 10, -10, 10, 30, elven_religion);
        outputList.add(elf);
        Race[] output = new Race[outputList.size()];
        output = outputList.toArray(output);

        return output;
    }
    public void createAndShowGUI(CharacterCreatedCallback callback) {
        // Create the main JFrame
        JFrame frame = new JFrame("Create Character");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a JPanel with GridBagLayout for flexible layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Name label and input field
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Name:");
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JTextField nameField = new JTextField(15);
        panel.add(nameField, gbc);

        // Race label and selector
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel raceLabel = new JLabel("Race:");
        panel.add(raceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        Race[] races = createRaces();
        JComboBox<Race> raceSelector = new JComboBox<>(races);
        panel.add(raceSelector, gbc);

        // Create character button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton createButton = new JButton("Create Character");
        panel.add(createButton, gbc);


        // Return Player Func
        // Add ActionListener to the button
        // Add ActionListener to the button
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String characterName = nameField.getText();
                Race characterRace = (Race) raceSelector.getSelectedItem();

                // Call the provided callback
                callback.onCharacterCreated(characterName, characterRace);

                frame.dispose();
            }
        });

        // Add the panel to the main JFrame
        frame.add(panel);

        // Position the frame in the center of the screen and display it
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
