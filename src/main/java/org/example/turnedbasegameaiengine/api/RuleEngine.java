package org.example.turnedbasegameaiengine.api;

import org.example.turnedbasegameaiengine.boards.TicTacToeBoard;
import org.example.turnedbasegameaiengine.game.*;
import java.util.HashMap;
import java.util.Map;

public class RuleEngine {
    Map<String, RuleSet> rulesMap = new HashMap<>();

    public RuleEngine() {
        rulesMap.put(TicTacToeBoard.class.getName(), (new TicTacToeBoard()).getRules());
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
                        return new GameInfoBuilder()
                                .isOver(gameState.isOver())
                                .winner(gameState.getWinner())
                                .player(player.flip())
                                .hasFork(true)
                                .build();
                    }
                }
            }
        }

        return new GameInfoBuilder()
                .isOver(gameState.isOver())
                .winner(gameState.getWinner())
                .build();
    }

    public GameState getState(Board board) {
        if (!(board instanceof TicTacToeBoard board1)) {
            throw new IllegalArgumentException();
        }

        RuleSet<TicTacToeBoard> rules = rulesMap.get(TicTacToeBoard.class.getName());
        for (Rule<TicTacToeBoard> rule : rules) {
            GameState gameState = rule.condition.apply(board1);
            if (gameState.isOver()) {
                return gameState;
            }
        }

        return new GameState(false, "-");
    }
}
