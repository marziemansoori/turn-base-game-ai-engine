package org.example.turnedbasegameaiengine.api;


import org.example.turnedbasegameaiengine.boards.TicTacToeBoard;
import org.example.turnedbasegameaiengine.game.Board;
import org.example.turnedbasegameaiengine.game.Cell;
import org.example.turnedbasegameaiengine.game.Move;
import org.example.turnedbasegameaiengine.game.Player;

public class AIEngine {

    public Move suggestMove(Player computer, Board board) {
        if (board instanceof TicTacToeBoard board1) {
            for (int i = 0; i < 3; i++) {

                for (int j = 0; j < 3; j++) {
                    if (board1.getCell(i, j) == null) {
                        return new Move(new Cell(i, j), computer);
                    }
                }

            }

        }
        throw new IllegalArgumentException();
    }
}
