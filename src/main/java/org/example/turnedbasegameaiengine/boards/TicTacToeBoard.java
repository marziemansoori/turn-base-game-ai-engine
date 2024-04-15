package org.example.turnedbasegameaiengine.boards;


import org.example.turnedbasegameaiengine.game.Board;
import org.example.turnedbasegameaiengine.game.Cell;
import org.example.turnedbasegameaiengine.game.Move;

public class TicTacToeBoard extends Board {

    public String[][] cells = new String[3][3];

    public String getCell(int row, int column) {
        return cells[row][column];
    }

    public void setCell(Cell cell, String symbol) {
        cells[cell.getRow()][cell.getCol()] = symbol;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result += cells[i][j] == null ? "-" : cells[i][j];
            }
            result+= "\n";
        }

        return result;
    }

    @Override
    public void move(Move move) {
        setCell(move.getCell(), move.getPlayer().getSymbol());
    }
}
