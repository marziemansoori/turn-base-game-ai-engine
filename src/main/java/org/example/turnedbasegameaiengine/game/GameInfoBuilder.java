package org.example.turnedbasegameaiengine.game;

public class GameInfoBuilder {

    private String winner;
    private Player player;
    private boolean hasFork;
    private boolean isOver;
    private int numberOfMoves;

    public GameInfoBuilder winner(String winner) {
        this.winner = winner;
        return this;
    }

    public GameInfoBuilder player(Player player) {
        this.player = player;
        return this;
    }

    public GameInfoBuilder hasFork(boolean hasFork) {
        this.hasFork = hasFork;
        return this;
    }

    public GameInfoBuilder isOver(boolean over) {
        isOver = over;
        return this;
    }

    public GameInfoBuilder numberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
        return this;
    }

    public GameInfo build() {
        return new GameInfo(isOver, winner, hasFork, player, numberOfMoves);
    }
}
