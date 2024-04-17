package org.example.turnedbasegameaiengine.game;

public class GameInfo {

    private String winner;
    private Player player;
    private boolean hasFork;
    private boolean isOver;
    private int numberOfMoves;

    public GameInfo(boolean isOver, String winner, boolean hasFork, Player player, int numberOfMoves) {
        this.winner = winner;
        this.isOver = isOver;
        this.player = player;
        this.hasFork = hasFork;
        this.numberOfMoves = numberOfMoves;
    }
}

