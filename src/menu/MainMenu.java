package menu;

import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainMenu {
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Game TestCreateItem Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> {

            frame.dispose();
            SwingUtilities.invokeLater(() -> {
                CreateCharacter createCharacter = new CreateCharacter();
                createCharacter.createAndShowGUI((characterName, characterRace) -> {
                    LoadingScreen loadingScreen = new LoadingScreen();
                    loadingScreen.createAndShowGUI(new Player(characterName, characterRace));

                });
            });
        });

        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(e -> {
            // TODO: Implement load game functionality
            System.out.println("Load Game clicked");
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            System.out.println("Exit clicked");
            System.exit(0);
        });

        gbc.gridx = gbc.gridy = 0;
        panel.add(newGameButton, gbc);

        gbc.gridy = 1;
        panel.add(loadGameButton, gbc);

        gbc.gridy = 2;
        panel.add(exitButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }
}