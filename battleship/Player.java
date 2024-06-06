package battleship;

public class Player {
    private final int id;
    private final Battlefield battlefield;
    private final Ship[] ships;
    private int sunkShips;

    public Player(int id) {
        this.id = id;
        this.battlefield = new Battlefield();
        this.ships = new Ship[5];
        this.sunkShips = 0;
    }

    public int getId() {
        return this.id;
    }

    public Battlefield getBattlefield() {
        return this.battlefield;
    }

    public Ship[] getShips() {
        return this.ships;
    }

    public int getSunkShips() {
        return this.sunkShips;
    }
}
