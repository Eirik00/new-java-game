package items;

public abstract class Item {
    private int value;

    public abstract String getDiscription();
    @Override
    public String toString() {
        return getDiscription();
    }

    public Item(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
