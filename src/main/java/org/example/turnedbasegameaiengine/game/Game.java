package org.example.turnedbasegameaiengine.game;

public class Game {
    private GameConfig gameConfig;

    private int timeUsedInMillis;
    private int lastMoveTimeInMillis;
    private int maxTimePerPlayer;
    private int maxTimePerMove;

    private Board board;

    Player winner;

    public void move(Move move, int timestampInMillis) {
        if(!gameConfig.timed) {
            board.move(move);
        }

        int timeTakenSinceLastMove = timestampInMillis - lastMoveTimeInMillis;
        move.player().setTimeTaken(timeTakenSinceLastMove);

        if(gameConfig.timePerMove != null) {
            if(moveMadeInTime(timeTakenSinceLastMove)) {
                board.move(move);
            } else {
                winner = move.player().flip();
            }
        } else  {
            if(moveMadeInTime(move.player())) {
                board.move(move);
            } else {
                winner = move.player().flip();
            }
        }

    }

    private boolean moveMadeInTime(int timeTakenSinceLastMove) {
        return timeTakenSinceLastMove < maxTimePerMove;
    }

    private boolean moveMadeInTime(Player player) {
        return player.getTimeUsedInMillis() < maxTimePerPlayer;
    }
}
