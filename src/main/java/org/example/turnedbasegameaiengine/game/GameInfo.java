package org.example.turnedbasegameaiengine.game;

public class GameInfo {

    private String winner;
    private Player player;
    private boolean hasFork;
    private boolean isOver;

    public GameInfo(GameState gameState, Player player, boolean hasFork) {
        this.winner = gameState.getWinner();
        this.isOver = gameState.isOver();
        this.player = player;
        this.hasFork = hasFork;
    }
}
