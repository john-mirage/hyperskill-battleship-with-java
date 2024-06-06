package battleship;

import java.util.Arrays;

public class Battlefield {
    private final String[][] battlefield;

    public Battlefield() {
        String[][] battlefield = new String[10][10];
        for (String[] row : battlefield) {
            Arrays.fill(row, "~");
        }
        this.battlefield = battlefield;
    }

    public String[][] getBattlefield() {
        return this.battlefield;
    }

    public void print(boolean fogOfWar) {
        StringBuilder matrix = new StringBuilder();
        int rows = this.battlefield.length;
        int cols = this.battlefield[0].length;
        matrix.append("\n");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= cols; j++) {
                if (i == 0 && j == 0) {
                    matrix.append("\s\s");
                } else if (i == 0) {
                    matrix.append(j).append(" ");
                } else if (j == 0) {
                    matrix.append((char) (64 + i)).append(" ");
                } else if (fogOfWar && this.battlefield[i - 1][j - 1].equals("O")) {
                    matrix.append("~ ");
                } else {
                    matrix.append(this.battlefield[i - 1][j - 1]).append(" ");
                }
            }
            matrix.append("\n");
        }
        System.out.println(matrix);
    }
}
