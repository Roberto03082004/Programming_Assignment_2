package test;

import game.*;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Chess Game!");
        System.out.println("To start a new game, use the following command:");
        System.out.println("new game PLAYER1 PLAYER2");
        System.out.println("PLAYER1 and PLAYER2 should be the names of the two players.");
        Scanner scanner = new Scanner(System.in);
        PrintStream out = System.out;
        Game game = null;
        Stack<Move> moveHistory = new Stack<>();

        while (true) {
            String command = scanner.nextLine();
            String[] parts = command.split(" ");

            if (parts.length == 4 && parts[0].equals("new") && parts[1].equals("game")) {
                String player1 = parts[2];
                String player2 = parts[3];
                game = new Game(player1, player2);
                moveHistory.clear(); // Clear move history for the new game
                System.out.println("New game created.");
                System.out.println("--------------------------------");
                showAvailableCommands();
                game.showBoard(out);
            } else if (parts[0].equals("mv")) {
                if (game == null) {
                    out.println("No game created yet.");
                } else {
                    int row0 = 8 - (parts[1].charAt(1) - '0');
                    int col0 = parts[1].charAt(0) - 'a';
                    int row1 = 8 - (parts[2].charAt(1) - '0');
                    int col1 = parts[2].charAt(0) - 'a';
                    Move move = new Move(row0, col0, row1, col1);

                    if (game.move(move)) {
                        out.println(game.isWhiteTurn() ? game.getPlayer1() : game.getPlayer2() +
                                " moves: " + parts[1] + parts[2]);
                        game.showBoard(out);
                        moveHistory.push(move); // Add the move to move history
                    } else {
                        out.println("Illegal move.");
                    }
                }
            } else if (parts[0].equals("cp")) {
                if (game == null) {
                    out.println("No game created yet.");
                } else {
                    int row0 = 8 - (parts[1].charAt(1) - '0');
                    int col0 = parts[1].charAt(0) - 'a';
                    int row1 = 8 - (parts[2].charAt(1) - '0');
                    int col1 = parts[2].charAt(0) - 'a';
                    Move move = new Move(row0, col0, row1, col1);

                    if (game.capture(move)) {
                        out.println(game.isWhiteTurn() ? game.getPlayer1() : game.getPlayer2() +
                                " captures: " + parts[1] + parts[2]);
                        game.showBoard(out);
                        moveHistory.push(move); // Add the move to move history
                    } else {
                        out.println("Illegal capture.");
                    }
                }
            } else if (parts[0].equals("undo")) {
                if (game == null) {
                    out.println("No game created yet.");
                } else if (moveHistory.isEmpty()) {
                    out.println("No moves to undo.");
                } else {
                    game.undo(); // Just call undo without passing a move
                    out.println("Undoing the last move.");
                    game.showBoard(out);
                }
            } else if (parts[0].equals("print") && parts[1].equals("status")) {
                if (game == null) {
                    out.println("No game created yet.");
                } else {
                    out.println("Current status of the board:");
                    game.showBoard(out);
                    out.println("Moves played in the game:");
                    for (int i = 0; i < moveHistory.size(); i++) {
                        out.println(i + 1 + ". " + moveHistory.get(i));
                    }
                }
            } else if (parts[0].equals("exit")) {
                break;
            } else {
                out.println("Invalid command");
            }
        }
    }

    private static void showAvailableCommands() {
        System.out.println("Available commands:");
        System.out.println("new game PLAYER1 PLAYER2 - Start a new game with two players.");
        System.out.println("mv <from> <to> - Move a piece from one square to another (e.g., mv a2 a4).");
        System.out.println("cp <from> <to> - Capture an opponent's piece (e.g., cp b5 c6).");
        System.out.println("undo - Undo the last move.");
        System.out.println("print status - Print the current status of the board and moves played.");
        System.out.println("exit - Exit the game.");
    }
}
