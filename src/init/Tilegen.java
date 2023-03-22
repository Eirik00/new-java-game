package init;

import object.tile.worldTile;

import java.awt.*;

public class Tilegen {

    public worldTile genMountain() {
        return new worldTile("Mountain", new Color(97, 97, 97), "#");
    }

    public worldTile genForrest() {
        return new worldTile("Forrest", new Color(15, 74, 0), "A");
    }

    public worldTile genGrass() {
        return new worldTile("Grass", new Color(85, 194, 89), "-");
    }

    public worldTile genRiver(){
        return new worldTile("River", new Color(0, 0, 255), "r");
    }

}
