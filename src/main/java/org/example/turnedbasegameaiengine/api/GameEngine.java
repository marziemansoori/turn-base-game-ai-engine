package org.example.turnedbasegameaiengine.api;

import org.example.turnedbasegameaiengine.boards.TicTacToeBoard;
import org.example.turnedbasegameaiengine.game.Board;
import org.example.turnedbasegameaiengine.game.Move;

public class GameEngine {

    public Board start(String type) {
        if (type.equals("TicTacToe")) {
            return new TicTacToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }


    public void move(Board board, Move move) {
        if (board instanceof TicTacToeBoard) {
            board.move(move);
        } else {
            throw new IllegalArgumentException();
        }
    }


}
