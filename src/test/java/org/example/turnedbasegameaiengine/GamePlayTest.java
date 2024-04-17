package org.example.turnedbasegameaiengine;


import org.example.turnedbasegameaiengine.api.AIEngine;
import org.example.turnedbasegameaiengine.api.GameEngine;
import org.example.turnedbasegameaiengine.api.RuleEngine;
import org.example.turnedbasegameaiengine.game.Board;
import org.example.turnedbasegameaiengine.game.Cell;
import org.example.turnedbasegameaiengine.game.Move;
import org.example.turnedbasegameaiengine.game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GamePlayTest {

    GameEngine gameEngine;
    AIEngine aiEngine;
    RuleEngine ruleEngine;

    @BeforeEach
    public void setUp() {
        gameEngine = new GameEngine();
        aiEngine = new AIEngine();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin() {

        Board board = gameEngine.start("TicTacToe");

        int[][] firstPlayerMoves = new int[][]{{1, 0}, {1, 1}, {1, 2}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {0, 1}, {0, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }


    @Test
    public void checkForColWin() {

        Board board = gameEngine.start("TicTacToe");

        int[][] firstPlayerMoves = new int[][]{{0, 0}, {1, 0}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{0, 1}, {1, 1}, {0, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }


    @Test
    public void checkForDiagWin() {
        Board board = gameEngine.start("TicTacToe");

        int next = 0;
        int[][] firstPlayerMove = new int[][]{{0, 0}, {1, 1}, {2, 2}};
        int[][] secondPlayerMove = new int[][]{{0, 1}, {1, 0}, {2, 0}};
        playGame(board, firstPlayerMove, secondPlayerMove);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForRevDiagWin() {
        Board board = gameEngine.start("TicTacToe");

        int[][] firstPlayerMoves = new int[][]{{0, 2}, {1, 1}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {0, 1}, {2, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForSecondPlayerWin() {
        Board board = gameEngine.start("TicTacToe");

        int[][] firstPlayerMoves = new int[][]{{1, 0}, {1, 1}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {0, 1}, {0, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("O", ruleEngine.getState(board).getWinner());
    }


    private void playGame(Board board, int[][] firstPlayerMoves, int[][] secondPlayerMoves) {
        int next = 0;
        while (!ruleEngine.getState(board).isOver()) {

            Player firstPlayer = new Player("X");
            Player secondPlayer = new Player("O");

            // User Move
            int row = firstPlayerMoves[next][0];
            int col = firstPlayerMoves[next][1];
            Move opponentMove = new Move(new Cell(row, col), firstPlayer);
            gameEngine.move(board, opponentMove);

            if (!ruleEngine.getState(board).isOver()) {
                // Computer Move
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];
                Move computerMove = new Move(new Cell(sRow, sCol), secondPlayer);
                gameEngine.move(board, computerMove);
            }
            next++;
        }
    }
}
