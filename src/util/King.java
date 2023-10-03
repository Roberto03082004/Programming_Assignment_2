package util;

import game.Game;
import game.Move;

public class King extends Piece {
    public King(boolean white) {
        super(white);
    }

    @Override
    public boolean isLegal(Move move, Game game) {
        if (!super.isLegal(move, game))
            return false;

        int rowDiff = Math.abs(move.getRow1() - move.getRow0());
        int colDiff = Math.abs(move.getCol1() - move.getCol0());

        return rowDiff <= 1 && colDiff <= 1;
    }

    @Override
    public String toString() {
        return white ? "\u2654" : "\u265A";
    }
}
