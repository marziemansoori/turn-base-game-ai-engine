package org.example.turnedbasegameaiengine.boards;


import org.example.turnedbasegameaiengine.api.RuleSet;
import org.example.turnedbasegameaiengine.game.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacToeBoard implements Board {

    public String[][] cells = new String[3][3];

    public RuleSet<TicTacToeBoard> getRules() {
        RuleSet<TicTacToeBoard> rules = new RuleSet<>();
        rules.add(new Rule<>(board -> outerTraversal(board::getSymbol))); // Row Win
        rules.add(new Rule<>(board -> outerTraversal((i, j) -> board.getSymbol(j, i)))); // Col Win
        rules.add(new Rule<>(board -> traverse(i -> board.getSymbol(i, i)))); // Diag Win
        rules.add(new Rule<>(board -> traverse(i -> board.getSymbol(i, 2 - i)))); // Reverse Diag Win
        rules.add(new Rule<>(this::countMoves));
        return rules;
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

    private GameState countMoves(TicTacToeBoard board) {
        int countOfFilledCells = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) != null) {
                    countOfFilledCells++;
                }
            }
        }

        if (countOfFilledCells == 9) {
            return new GameState(true, "-");
        }

        return new GameState(false, "-");
    }

    public String getSymbol(int row, int column) {
        return cells[row][column];
    }

    public void setCell(Cell cell, String symbol) {
        if (cells[cell.getRow()][cell.getCol()] == null) {
            cells[cell.getRow()][cell.getCol()] = symbol;
        } else {
            throw new IllegalArgumentException(cell.getRow() + " " + cell.getCol());
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result += cells[i][j] == null ? "-" : cells[i][j];
            }
            result += "\n";
        }

        return result;
    }

    @Override
    public void move(Move move) {
        setCell(move.cell(), move.player().getSymbol());
    }

    @Override
    public TicTacToeBoard copy() {
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();
        for (int i = 0; i < 3; i++) {
            System.arraycopy(cells[i], 0, ticTacToeBoard.cells[i], 0, 3);
        }

        return ticTacToeBoard;
    }
}
