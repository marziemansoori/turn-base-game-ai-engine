package org.example.turnedbasegameaiengine.api;


import org.example.turnedbasegameaiengine.boards.TicTacToeBoard;
import org.example.turnedbasegameaiengine.game.Board;
import org.example.turnedbasegameaiengine.game.GameState;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard board1) {

            GameState rowWin = outerTraversal((i, j) -> board1.getSymbol(i, j));
            if (rowWin.isOver()) return rowWin;

            GameState colWin = outerTraversal((i, j) -> board1.getSymbol(j, i));
            if (colWin.isOver()) return colWin;


            GameState diagWin = traverse(i -> board1.getSymbol(i, i));
            if (diagWin.isOver()) return diagWin;

            GameState revDiagWin = traverse(i -> board1.getSymbol(i, 2 - i));
            if (revDiagWin.isOver()) return revDiagWin;


            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getSymbol(i, j) != null) {
                        countOfFilledCells++;
                    }
                }
            }

            if (countOfFilledCells == 9) {
                return new GameState(true, "-");
            } else {
                return new GameState(false, "-");
            }

        } else {
            return new GameState(false, "-");
        }
    }

    private GameState traverse(Function<Integer, String> traversal) {
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int j = 0; j < 3; j++) {
            if (traversal.apply(j) == null || !traversal.apply(0).equals(traversal.apply(j))) {
                possibleStreak = false;
                break;
            }
        }
        if (possibleStreak) {
            result = new GameState(true, traversal.apply(0));
        }
        return result;
    }

    private GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");

        for (int i = 0; i < 3; i++) {
            final int ii = i;
            GameState traverse = traverse(j -> next.apply(ii, j));
            if (traverse.isOver()) {
                return traverse;
            }
        }
        return result;
    }
}
