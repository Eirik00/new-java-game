package object;

import java.awt.*;

public class worldTile extends Tile{
    private final String  tileName;
    private final Color  tileColor;
    private final String tileChar;
    public worldTile(String tileName, Color tileColor, String tileChar){
        this.tileName = tileName;
        this.tileColor = tileColor;
        this.tileChar = tileChar;
    }

    public String getTileName() {
        return tileName;
    }

    public Color getTileColor() {
        return tileColor;
    }

    public String getTileChar() {
        return tileChar;
    }

    @Override
    public String toString(){
        return tileName;
    }
}
