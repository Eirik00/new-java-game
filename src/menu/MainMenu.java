package menu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainMenu {
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Game Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> {
            LoadingScreen loadingScreen = new LoadingScreen();
            loadingScreen.createAndShowGUI();
            frame.dispose();
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

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(newGameButton, gbc);

        gbc.gridy = 1;
        panel.add(loadGameButton, gbc);

        gbc.gridy = 2;
        panel.add(exitButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }
}