package object.tile;

import entity.Player;

import java.awt.*;

public abstract class Tile {
    private String tileName;
    private Color tileRootColor;
    private Color tileColor;
    private String tileRootChar;
    private String tileChar;
    private Boolean selected;
    private Boolean hasPlayer;
    private int tilePosX;
    private int tilePosY;

    public Tile(String tileName, Color tileColor, String tileChar){
        this.tileName = tileName;
        this.tileColor = tileColor;
        this.tileChar = tileChar;
        this.selected = false;
        this.hasPlayer = false;
        this.tileRootColor = tileColor;
        this.tileRootChar = tileChar;
    }


    public void setPosition(int x, int y){
        tilePosX = x;
        tilePosY = y;
    }
    public int[] getPos(){
        int[] position = new int[2];
        position[0] = tilePosX;
        position[1] = tilePosY;
        return position;
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
    public Boolean getSelected(){
        return selected;
    }
    public void toggleSelected(){
        this.selected = !selected;
    }
    public Boolean getHasPlayer() {
        return hasPlayer;
    }
    public void toggleHasPlayer(Color playerColor) {
        this.hasPlayer = !hasPlayer;
        if(hasPlayer){
            this.tileColor = playerColor;
            this.tileChar = "P";
        } else {
            this.tileColor = tileRootColor;
            this.tileChar = tileRootChar;
        }
    }
}
