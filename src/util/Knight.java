package util;

import game.*;

public class Knight extends Piece{
    public Knight(boolean white) {
	super(white);
    }
    @Override
    public boolean isLegal(Move move, Game game) {
	if(!super.isLegal(move, game))
	    return false;
	//rules for knight only!
	return true;
    }
    @Override
    public String toString() {
	return white?"\u2658":"\u265E";
    }
}
