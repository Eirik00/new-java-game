import menu.MainMenu;
import java.io.IOException;

public class Main {

    public static int cordToNum(int x, int y){
        return (y*64)+x;
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.createAndShowGUI();
    }
}