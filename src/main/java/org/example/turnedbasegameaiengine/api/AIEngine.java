package org.example.turnedbasegameaiengine.api;


import org.example.turnedbasegameaiengine.boards.TicTacToeBoard;
import org.example.turnedbasegameaiengine.game.Board;
import org.example.turnedbasegameaiengine.game.Cell;
import org.example.turnedbasegameaiengine.game.Move;
import org.example.turnedbasegameaiengine.game.Player;

public class AIEngine {

    public Move suggestMove(Player computer, Board board) {
        if (board instanceof TicTacToeBoard board1) {
            Move suggestion;
            if (countMoves(board1) < 3) {
                suggestion = getBasicMove(computer, board1);
            } else {
                suggestion = getSmartMove(computer, board1);
            }

            if (suggestion != null) return suggestion;
            throw new IllegalStateException();

        }
        throw new IllegalArgumentException();
    }

    private int countMoves(TicTacToeBoard board) {
        int count = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) != null) {
                    count++;
                }
            }
        }

        return count;
    }

    private Move getSmartMove(Player player, TicTacToeBoard board) {
        // 1. can I win with this move? is game going to finish by this move?
        // 2. is my opponent going to win and I need to block it?

        RuleEngine ruleEngine = new RuleEngine();
        // Victorious moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return move;
                    }
                }
            }
        }


        // Defensive Moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
        }

        return null;
    }

    private static Move getBasicMove(Player computer, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    return new Move(new Cell(i, j), computer);
                }
            }
        }
        return null;
    }


}
