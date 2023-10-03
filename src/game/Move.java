package game;

public class Move {
    private int row0, col0, row1, col1;

    public Move(int row0, int col0, int row1, int col1) {
        this.row0 = row0;
        this.col0 = col0;
        this.row1 = row1;
        this.col1 = col1;
    }

    public int getRow0() {
        return row0;
    }

    public int getCol0() {
        return col0;
    }

    public int getRow1() {
        return row1;
    }

    public int getCol1() {
        return col1;
    }

    @Override
    public String toString() {
        char col0Char = (char) ('a' + col0);
        char col1Char = (char) ('a' + col1);
        return "" + col0Char + (8 - row0) + col1Char + (8 - row1);
    }
}
