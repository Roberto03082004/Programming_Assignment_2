package util;

import game.Game;
import game.Move;

public class Rook extends Piece {
    public Rook(boolean white) {
        super(white);
    }

    @Override
    public boolean isLegal(Move move, Game game) {
        if (!super.isLegal(move, game))
            return false;

        int rowDiff = move.getRow1() - move.getRow0();
        int colDiff = move.getCol1() - move.getCol0();

        if (rowDiff != 0 && colDiff != 0)
            return false;

        int dir = (rowDiff == 0) ? (colDiff > 0 ? 1 : -1) : (rowDiff > 0 ? 1 : -1);

        int row = move.getRow0();
        int col = move.getCol0();

        while (row != move.getRow1() || col != move.getCol1()) {
            row += (rowDiff == 0) ? 0 : dir;
            col += (colDiff == 0) ? 0 : dir;

            if (game.getPiece(row, col) != null && (row != move.getRow1() || col != move.getCol1()))
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return white ? "\u2656" : "\u265C";
    }
}
