package object;

import java.awt.*;

public abstract class Tile {
    private String tileName;

    private Color tileColor;
    private String tileChar;

    public String getTileName() {
        return tileName;
    }

    public Color getTileColor() {
        return tileColor;
    }
    public String getTileChar() {
        return tileChar;
    }
}
