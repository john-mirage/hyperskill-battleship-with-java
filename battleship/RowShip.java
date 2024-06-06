package battleship;

public class RowShip extends Ship {
    public RowShip(Coordinate a, Coordinate b, ShipState shipState, Battlefield battlefield) throws Exception {
        super(a, b, shipState, battlefield);
    }

    @Override
    protected int getMainAxis() throws Exception {
        if (this.a.getX() != this.b.getX() || this.a.getY() == this.b.getY()) {
            throw new Exception("Error!");
        }
        return this.a.getX();
    }

    @Override
    protected int getSecondaryAxisMin() {
        return Math.min(this.a.getY(), this.b.getY());
    }

    @Override
    protected int getSecondaryAxisMax() {
        return Math.max(this.a.getY(), this.b.getY());
    }

    @Override
    protected void checkShipArea() throws Exception {
        for (int i = this.from; i <= this.to; i++) {
            boolean leftIsTaken = i == this.from && i > 0 && this.battlefield.getBattlefield()[this.mainAxis][i - 1].equals("O");
            boolean rightIsTaken = i == this.to && i < 9 && this.battlefield.getBattlefield()[this.mainAxis][i + 1].equals("O");
            boolean topIsTaken = this.mainAxis > 0 && this.battlefield.getBattlefield()[this.mainAxis - 1][i].equals("O");
            boolean bottomIsTaken = this.mainAxis < 9 && this.battlefield.getBattlefield()[this.mainAxis + 1][i].equals("O");
            if (this.battlefield.getBattlefield()[this.mainAxis][i].equals("O")) {
                throw new Exception("Error! Wrong ship location! Try again:");
            } else if (leftIsTaken || rightIsTaken || topIsTaken || bottomIsTaken) {
                throw new Exception("Error! You placed it too close to another one. Try again:");
            }
        }
    }

    @Override
    protected void addShipToBattlefield() {
        for (int i = this.from; i <= this.to; i++) {
            this.battlefield.getBattlefield()[this.mainAxis][i] = "O";
        }
    }

    @Override
    public boolean isSunk() {
        for (int i = this.from; i <= this.to; i++) {
            if (!this.battlefield.getBattlefield()[this.mainAxis][i].equals("X")) {
                return false;
            }
        }
        return true;
    }
}
