package object.tile;

import object.tile.Tile;

import java.awt.*;

public class worldTile extends Tile {
    public worldTile(String tileName, Color tileColor, String tileChar){
        super(tileName, tileColor, tileChar);
    }

    @Override
    public String toString(){
        return super.getTileName();
    }
}
