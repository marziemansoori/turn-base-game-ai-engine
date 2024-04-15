package org.example.turnedbasegameaiengine.boards;


import org.example.turnedbasegameaiengine.game.Board;
import org.example.turnedbasegameaiengine.game.Cell;
import org.example.turnedbasegameaiengine.game.Move;

public class TicTacToeBoard implements Board {

    public String[][] cells = new String[3][3];

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
        setCell(move.getCell(), move.getPlayer().getSymbol());
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
