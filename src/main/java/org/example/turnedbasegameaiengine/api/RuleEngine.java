package org.example.turnedbasegameaiengine.api;


import org.example.turnedbasegameaiengine.boards.TicTacToeBoard;
import org.example.turnedbasegameaiengine.game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    Map<String, List<Rule<TicTacToeBoard>>> rulesMap = new HashMap<>();

    public RuleEngine() {
        rulesMap.put(TicTacToeBoard.class.getName(), new ArrayList<>());
        rulesMap.get(TicTacToeBoard.class.getName()).add(new Rule<>(board -> outerTraversal(board::getSymbol))); // Row Win
        rulesMap.get(TicTacToeBoard.class.getName()).add(new Rule<>(board -> outerTraversal((i, j) -> board.getSymbol(j, i)))); // Col Win
        rulesMap.get(TicTacToeBoard.class.getName()).add(new Rule<>(board -> traverse(i -> board.getSymbol(i, i)))); // Diag Win
        rulesMap.get(TicTacToeBoard.class.getName()).add(new Rule<>(board -> traverse(i -> board.getSymbol(i, 2 - i)))); // Reverse Diag Win
        rulesMap.get(TicTacToeBoard.class.getName()).add(new Rule<>(this::countMoves));
    }

    public GameInfo gameInfo(Board board) {
        if (!(board instanceof TicTacToeBoard board1)) {
            throw new IllegalArgumentException();
        }

        GameState gameState = getState(board1);

        String[] players = new String[]{"X", "O"};
        for (int playerSymbol = 0; playerSymbol < 2; playerSymbol++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Board b = board1.copy();
                    Player player = new Player(players[playerSymbol]);
                    b.move(new Move(new Cell(i, j), player));
                    boolean canStillWin = false;
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            Board b1 = b.copy();
                            b1.move(new Move(new Cell(k, l), player.flip()));
                            if (getState(b1).getWinner().equals(player.flip().getSymbol())) {
                                canStillWin = true;
                                break;
                            }
                        }
                        if (canStillWin) {
                            break;
                        }
                    }
                    if (canStillWin) {
                        return new GameInfo(gameState, player.flip(), true);
                    }
                }
            }
        }

        return new GameInfo(gameState, null, false);
    }

    public GameState getState(Board board) {
        if (!(board instanceof TicTacToeBoard board1)) {
            throw new IllegalArgumentException();
        }

        List<Rule<TicTacToeBoard>> rules = rulesMap.get(TicTacToeBoard.class.getName());
        for (Rule<TicTacToeBoard> rule : rules) {
            GameState gameState = rule.condition.apply(board1);
            if (gameState.isOver()) {
                return gameState;
            }
        }

        return new GameState(false, "-");
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
}
