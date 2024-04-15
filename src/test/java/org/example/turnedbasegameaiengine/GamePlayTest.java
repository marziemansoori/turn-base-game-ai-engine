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

        int row, col;
        int next = 0;
        int[][] moves = new int[][]{{1, 0}, {1, 1}, {1, 2}};
        while (!ruleEngine.getState(board).isOver()) {

            Player human = new Player("X");
            Player computer = new Player("O");

            // User Move
            row = moves[next][0];
            col = moves[next][1];
            next++;
            Move opponentMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, opponentMove);

            if (!ruleEngine.getState(board).isOver()) {
                // Computer Move
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
        }

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }



    @Test
    public void checkForColWin() {

        Board board = gameEngine.start("TicTacToe");

        int row, col;
        int next = 0;
        int[][] moves = new int[][]{{0, 0}, {1, 0}, {2,0}};
        while (!ruleEngine.getState(board).isOver()) {

            Player human = new Player("X");
            Player computer = new Player("O");

            // User Move
            row = moves[next][0];
            col = moves[next][1];
            next++;
            Cell cell = new Cell(row, col);
            Move opponentMove = new Move(cell, human);
            gameEngine.move(board, opponentMove);

            if (!ruleEngine.getState(board).isOver()) {
                // Computer Move
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
        }

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }
}
