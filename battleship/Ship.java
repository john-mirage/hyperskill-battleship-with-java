package battleship;

public abstract class Ship {
    protected Battlefield battlefield;
    protected Coordinate a;
    protected Coordinate b;
    protected int mainAxis;
    protected int from;
    protected int to;
    protected ShipState shipState;

    public Ship(Coordinate a, Coordinate b, ShipState shipState, Battlefield battlefield) throws Exception {
        try {
            this.battlefield = battlefield;
            this.a = a;
            this.b = b;
            this.mainAxis = this.getMainAxis();
            this.from = this.getSecondaryAxisMin();
            this.to = this.getSecondaryAxisMax();
            this.shipState = shipState;
            this.checkShipLength(this.shipState.getLength());
            this.checkShipArea();
            this.addShipToBattlefield();
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    private void checkShipLength(int checkedLength) throws Exception {
        if (checkedLength != (this.to - this.from + 1)) {
            throw new Exception("Error! Wrong length of the %s! Try again:".formatted(this.shipState.getName()));
        }
    }

    protected abstract void checkShipArea() throws Exception;
    protected abstract int getMainAxis() throws Exception;
    protected abstract int getSecondaryAxisMin();
    protected abstract int getSecondaryAxisMax();
    protected abstract void addShipToBattlefield();
    public abstract boolean isSunk();
}
