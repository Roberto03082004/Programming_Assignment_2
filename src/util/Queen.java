package util;

import game.Game;
import game.Move;

public class Queen extends Piece {
    public Queen(boolean white) {
        super(white);
    }

    @Override
    public boolean isLegal(Move move, Game game) {
        if (!super.isLegal(move, game))
            return false;

        int rowDiff = move.getRow1() - move.getRow0();
        int colDiff = move.getCol1() - move.getCol0();

        if (rowDiff == 0) {
            for (int i = 1; i < Math.abs(colDiff); i++) {
                if (colDiff > 0 && game.getPiece(move.getRow0(), move.getCol0() + i) != null ||
                        colDiff < 0 && game.getPiece(move.getRow0(), move.getCol1() + i) != null)
                    return false;
            }
            return true;
        } else if (colDiff == 0) {
            for (int i = 1; i < Math.abs(rowDiff); i++) {
                if (rowDiff > 0 && game.getPiece(move.getRow0() + i, move.getCol0()) != null ||
                        rowDiff < 0 && game.getPiece(move.getRow1() + i, move.getCol0()) != null)
                    return false;
            }
            return true;
        } else if (Math.abs(rowDiff) == Math.abs(colDiff)) {
            for (int i = 1; i < Math.abs(rowDiff); i++) {
                if (rowDiff > 0 && colDiff > 0 && game.getPiece(move.getRow0() + i, move.getCol0() + i) != null ||
                        rowDiff > 0 && colDiff < 0 && game.getPiece(move.getRow0() + i, move.getCol1() + i) != null ||
                        rowDiff < 0 && colDiff > 0 && game.getPiece(move.getRow1() + i, move.getCol0() + i) != null ||
                        rowDiff < 0 && colDiff < 0 && game.getPiece(move.getRow1() + i, move.getCol1() + i) != null)
                    return false;
            }
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return white ? "\u2655" : "\u265B";
    }
}
