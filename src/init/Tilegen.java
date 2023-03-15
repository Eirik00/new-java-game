package init;

import object.worldTile;

import java.awt.*;

public class Tilegen {
    private final  worldTile mountain;
    private final worldTile forrest;
    private final  worldTile grass;

    public Tilegen(){
        this.mountain = new worldTile("Mountain", new Color(97, 97, 97), "#");
        this.forrest = new worldTile("Forrest", new Color(15, 74, 0), "A");
        this.grass = new worldTile("Grass", new Color(85, 194, 89), "-");
    }

    public worldTile getMountain() {
        return mountain;
    }

    public worldTile getForrest() {
        return forrest;
    }

    public worldTile getGrass() {
        return grass;
    }

}
