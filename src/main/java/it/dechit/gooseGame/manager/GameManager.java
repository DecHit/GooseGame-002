package it.dechit.gooseGame.manager;


import it.dechit.gooseGame.box.Space;

import java.io.PrintStream;

public class GameManager implements IGameManager {

    private final PrintStream printStream;

    public GameManager(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void onPlayerMoved(String playerName, Space from, Space to) {
        printStream.print(playerName
                + " moves from "
                + from.getName()
                + " to " + to.getName() + ". ");
    }

    @Override
    public void onPlayerBounced(String playerName, Space to) {
        printStream.print(playerName + " bounces! " + playerName + " returns to " + to.getName() + ". ");
    }

    @Override
    public void onPlayerWin(String playerName) {
        printStream.print(playerName + " Wins!!");
    }

    @Override
    public void onPlayerJump(String playerName, Space to) {
        printStream.print(playerName + " jumps to " + to.getName() + ". ");
    }

    @Override
    public void onPlayerPrank(String playerJokedName, Space from, Space to) {
        printStream.print("On " + from.getName() + " there is " + playerJokedName + ", who returns to " + to.getName() + ". ");
    }
}
