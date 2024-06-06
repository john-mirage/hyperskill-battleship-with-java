package battleship;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(String xAsLetter, String yAsInteger) throws Exception {
        try {
            this.x = xAsLetter.charAt(0) - 65;
            this.y = Integer.parseInt(yAsInteger) - 1;
        } catch (NumberFormatException exception) {
            throw new Exception(exception.getMessage());
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
