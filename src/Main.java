import init.LoadFont;
import init.Worldgen;
import menu.CreateCharacter;
import menu.Game;
import menu.MainMenu;
import object.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static int cordToNum(int x, int y){
        return (y*64)+x;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        JPanel background = new JPanel();
        background.setBackground(Color.black);
        background.setBounds(0,0, 960, 960);


        //Create main application
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setFont(new Font("Verdana", Font.PLAIN, 18));
        //frame.setUndecorated(true);
        frame.setSize(1280,1000);

        //CreateCharacter createCharacter = new CreateCharacter();
        //createCharacter.view(frame);
        //MainMenu mainMenu = new MainMenu();
        //mainMenu.mainMenuInit(frame);
        Game gameView = new Game();
        gameView.view(frame);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.repaint();
    }
}