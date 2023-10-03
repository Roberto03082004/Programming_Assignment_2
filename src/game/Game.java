package game;

import util.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Stack;

public class Game {
    private String player1, player2;
    private ArrayList<Move> moves;
    private Square[][] board;
    private Stack<Square[][]> boardStack;

    public Piece getPiece(int row, int col) {
        return board[row][col].getOccupant();
    }

    public boolean isWhiteTurn() {
        return moves.size() % 2 == 0;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public Game(String player1, String player2) {
        moves = new ArrayList<Move>();
        board = new Square[8][8];
        boardStack = new Stack<Square[][]>();

        this.player1 = player1;
        this.player2 = player2;

        // Initialize the chessboard with black pieces at the top and white pieces at the bottom
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boolean white = (row + col) % 2 == 0;

                if (row == 0)
                    board[row][col] = new Square(white, getInitialPiece(col, false)); // Black back row
                else if (row == 1)
                    board[row][col] = new Square(white, new Pawn(false)); // Black pawns
                else if (row == 6)
                    board[row][col] = new Square(white, new Pawn(true)); // White pawns
                else if (row == 7)
                    board[row][col] = new Square(white, getInitialPiece(col, true)); // White back row
                else
                    board[row][col] = new Square(white, null); // Empty squares
            }
        }
    }

    private Piece getInitialPiece(int col, boolean isWhite) {
        switch (col) {
            case 0:
            case 7:
                return new Rook(isWhite);
            case 1:
            case 6:
                return new Knight(isWhite);
            case 2:
            case 5:
                return new Bishop(isWhite);
            case 3:
                return new Queen(isWhite);
            case 4:
                return new King(isWhite);
            default:
                return null;
        }
    }

    public boolean move(Move move) {
        Square src = board[move.getRow0()][move.getCol0()];
        Square dst = board[move.getRow1()][move.getCol1()];

        if (src.getOccupant() == null || !src.getOccupant().isLegal(move, this))
            return false;

        boardStack.push(copyBoard(board));
        dst.setOccupant(src.getOccupant());
        src.setOccupant(null);
        moves.add(move);

        return true;
    }

    public boolean capture(Move move) {
        Square src = board[move.getRow0()][move.getCol0()];
        Square dst = board[move.getRow1()][move.getCol1()];

        if (src.getOccupant() == null || !src.getOccupant().isLegal(move, this))
            return false;

        boardStack.push(copyBoard(board));
        dst.setOccupant(src.getOccupant());
        src.setOccupant(null);
        moves.add(move);

        return true;
    }

    public void undo() {
        if (!boardStack.isEmpty()) {
            board = boardStack.pop();
            // Remove the last move from the moves list.
            if (!moves.isEmpty()) {
                moves.remove(moves.size() - 1);
            }
        }
    }

    public void printStatus(PrintStream stream) {
        stream.println("Player 1: " + player1);
        stream.println("Player 2: " + player2);
        stream.println("Moves:");
        for (int i = 0; i < moves.size(); i++) {
            String playerName = (i % 2 == 0) ? player1 : player2;
            stream.println(playerName + " moves: " + moves.get(i));
        }
    }

    public void showBoard(PrintStream stream) {
        // Clear the console
        stream.print("\033[H\033[2J");

        // Inside the showBoard method
        stream.println("--------------------------------");

        // Print the top row with player names and colors
        String player1Info = getPlayer1() + " (White)";
        String player2Info = getPlayer2() + " (Black)";
        String currentPlayerInfo = isWhiteTurn() ? player1Info : player2Info;
        stream.println("       " + player1Info + "          " + player2Info);
        stream.println("--------------------------------");
        stream.println("       " + player2Info);

        for (int row = 0; row < 8; row++) {
            stream.print(8 - row + " ");
            for (int col = 0; col < 8; col++) {
                Piece piece = getPiece(row, col);
                String cellContent = piece != null ? piece.toString() : " ";
                stream.print(" " + cellContent + " ");
            }
            stream.println();
        }

        // Print the column labels
        stream.println("   a  b  c  d  e  f  g  h");

        // Print the player's information on the bottom
        stream.println("       " + player1Info);
    }

    private Square[][] copyBoard(Square[][] board) {
        Square[][] copy = new Square[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                copy[row][col] = new Square(board[row][col].isLightColor(), board[row][col].getOccupant());
            }
        }
        return copy;
    }
}
