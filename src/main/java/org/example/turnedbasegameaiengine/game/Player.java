package org.example.turnedbasegameaiengine.game;

public class Player {

    User id;

    private int timeUsedInMillis;

    private final String symbol;

    public Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public Player flip() {
        return new Player(symbol.equals("X") ? "O" : "X");
    }

    public void setTimeTaken(int timestampInMillis) {
        timeUsedInMillis += timestampInMillis;
    }

    public int getTimeUsedInMillis() {
        return timeUsedInMillis;
    }
}
