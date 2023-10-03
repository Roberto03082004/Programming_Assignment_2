package util;

import game.Game;
import game.Move;

public class Bishop extends Piece {
    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public boolean isLegal(Move move, Game game) {
        if (!super.isLegal(move, game))
            return false;

        int rowDiff = move.getRow1() - move.getRow0();
        int colDiff = move.getCol1() - move.getCol0();

        if (Math.abs(rowDiff) != Math.abs(colDiff))
            return false;

        int rowDir = rowDiff < 0 ? -1 : 1;
        int colDir = colDiff < 0 ? -1 : 1;

        int row = move.getRow0() + rowDir;
        int col = move.getCol0() + colDir;

        while (row != move.getRow1() && col != move.getCol1()) {
            if (game.getPiece(row, col) != null)
                return false;
            row += rowDir;
            col += colDir;
        }

        return true;
    }

    @Override
    public String toString() {
        return white ? "\u2657" : "\u265D";
    }
}
