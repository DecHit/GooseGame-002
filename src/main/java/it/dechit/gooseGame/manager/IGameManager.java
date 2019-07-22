package it.dechit.gooseGame.manager;


import it.dechit.gooseGame.box.Space;

public interface IGameManager {

    void onPlayerMoved(String playerName, Space from, Space to);

    void onPlayerBounced(String playerName, Space to);

    void onPlayerWin(String playerName);

    void onPlayerJump(String playerName, Space to);

    void onPlayerPrank(String playerJokedName, Space from, Space to);
}
