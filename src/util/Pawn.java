package util;

import game.Game;
import game.Move;

public class Pawn extends Piece {
    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean isLegal(Move move, Game game) {
        if (!super.isLegal(move, game))
            return false;

        int rowDiff = move.getRow1() - move.getRow0();
        int colDiff = move.getCol1() - move.getCol0();

        if (rowDiff > 0 && white || rowDiff < 0 && !white)
            return false;

        if (colDiff == 0) {
            if (move.getRow0() == 6 && white && rowDiff == -2 && game.getPiece(move.getRow0() - 1, move.getCol0()) != null)
                return false;
            if (move.getRow0() == 1 && !white && rowDiff == 2 && game.getPiece(move.getRow0() + 1, move.getCol0()) != null)
                return false;
            return game.getPiece(move.getRow1(), move.getCol1()) == null;
        } else if (Math.abs(colDiff) == 1 && Math.abs(rowDiff) == 1) {
            return game.getPiece(move.getRow1(), move.getCol1()) != null;
        }

        return false;
    }

    @Override
    public String toString() {
        return white ? "\u2659" : "\u265F";
    }
}
