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

        //CreateCharacter createCharacter = new CreateCharacter();
        //createCharacter.view(frame);
        MainMenu mainMenu = new MainMenu();
        mainMenu.createAndShowGUI();
    }
}