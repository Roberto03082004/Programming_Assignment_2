package util;

import game.Game;
import game.Move;

public abstract class Piece {
    protected boolean white;

    public boolean isLegal(Move move, Game game) {
        if (white != game.isWhiteTurn())
            return false;

        Piece captured = game.getPiece(move.getRow1(), move.getCol1());
        if (captured != null && captured.white == this.white)
            return false;

        return true;
    }

    public Piece(boolean white) {
        this.white = white;
    }
}
