package battleship;

public class ColumnShip extends Ship {
    public ColumnShip(Coordinate a, Coordinate b, ShipState shipState, Battlefield battlefield) throws Exception {
        super(a, b, shipState, battlefield);
    }

    @Override
    protected int getMainAxis() throws Exception {
        if (this.a.getY() != this.b.getY() || this.a.getX() == this.b.getX()) {
            throw new Exception("Error!");
        }
        return this.a.getY();
    }

    @Override
    protected int getSecondaryAxisMin() {
        return Math.min(this.a.getX(), this.b.getX());
    }

    @Override
    protected int getSecondaryAxisMax() {
        return Math.max(this.a.getX(), this.b.getX());
    }

    @Override
    public void checkShipArea() throws Exception {
        for (int i = this.from; i <= this.to; i++) {
            boolean leftIsTaken = this.mainAxis > 0 && this.battlefield.getBattlefield()[i][this.mainAxis - 1].equals("O");
            boolean rightIsTaken = this.mainAxis < 9 && this.battlefield.getBattlefield()[i][this.mainAxis + 1].equals("O");
            boolean topIsTaken = i == this.from && i > 0 && this.battlefield.getBattlefield()[i - 1][this.mainAxis].equals("O");
            boolean bottomIsTaken = i == this.to && i < 9 && this.battlefield.getBattlefield()[i + 1][this.mainAxis].equals("O");
            if (this.battlefield.getBattlefield()[i][this.mainAxis].equals("O")) {
                throw new Exception("Error! Wrong ship location! Try again:");
            } else if (leftIsTaken || rightIsTaken || topIsTaken || bottomIsTaken) {
                throw new Exception("Error! You placed it too close to another one. Try again:");
            }
        }
    }

    @Override
    protected void addShipToBattlefield() {
        for (int i = this.from; i <= this.to; i++) {
            this.battlefield.getBattlefield()[i][this.mainAxis] = "O";
        }
    }

    @Override
    public boolean isSunk() {
        for (int i = this.from; i <= this.to; i++) {
            if (!this.battlefield.getBattlefield()[i][this.mainAxis].equals("X")) {
                return false;
            }
        }
        return true;
    }
}
