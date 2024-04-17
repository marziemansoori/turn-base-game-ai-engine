package org.example.turnedbasegameaiengine;


import org.example.turnedbasegameaiengine.api.AIEngine;
import org.example.turnedbasegameaiengine.api.GameEngine;
import org.example.turnedbasegameaiengine.api.RuleEngine;
import org.example.turnedbasegameaiengine.game.Board;
import org.example.turnedbasegameaiengine.game.Cell;
import org.example.turnedbasegameaiengine.game.Move;
import org.example.turnedbasegameaiengine.game.Player;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        Board board = gameEngine.start("TicTacToe");

        while (!ruleEngine.getState(board).isOver()) {
            System.out.println("Make your move");


            Player human = new Player("X");
            Player computer = new Player("O");

            // User Move
            Scanner scanner = new Scanner(System.in);
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Move opponentMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, opponentMove);
            System.out.println(board);

            if (!ruleEngine.getState(board).isOver()) {
                // Computer Move
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
                System.out.println(board);
            }
        }

        System.out.println("GameResult: " + ruleEngine.getState(board));
        System.out.println(board);
    }
}
