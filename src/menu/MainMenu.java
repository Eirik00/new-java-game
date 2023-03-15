package menu;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    public void view(JFrame frame){
        JPanel mainMenuPanel = new JPanel(new GridLayout(3,1));
        mainMenuPanel.setBounds(frame.getBounds());
        mainMenuPanel.setBackground(Color.blue);
        frame.add(mainMenuPanel);

        JButton startGame = new JButton("Start Game");
        mainMenuPanel.add(startGame);
    }
}
