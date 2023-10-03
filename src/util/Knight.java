package util;

import game.Game;
import game.Move;

public class Knight extends Piece {
    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean isLegal(Move move, Game game) {
        if (!super.isLegal(move, game))
            return false;

        int rowDiff = Math.abs(move.getRow1() - move.getRow0());
        int colDiff = Math.abs(move.getCol1() - move.getCol0());

        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    @Override
    public String toString() {
        return white ? "\u2658" : "\u265E";
    }
}
