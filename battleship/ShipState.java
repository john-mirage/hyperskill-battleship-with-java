package battleship;

public enum ShipState {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5, 0),
    BATTLESHIP("Battleship", 4, 1),
    SUBMARINE("Submarine", 3, 2),
    CRUISER("Cruiser", 3, 3),
    DESTROYER("Destroyer", 2, 4);

    final private String name;
    final private int length;
    final private int index;

    ShipState(String name, int length, int index) {
        this.name = name;
        this.length = length;
        this.index = index;
    }

    public String getName() {
        return this.name;
    }

    public int getLength() {
        return this.length;
    }

    public int getIndex() {
        return this.index;
    }
}
