package object.path;

class GridPoint {
    int x;
    int y;
    GridPoint parent;

    public GridPoint(int x, int y, GridPoint parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }
}