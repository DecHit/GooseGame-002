package it.dechit.gooseGame;

import it.dechit.gooseGame.box.Board;
import it.dechit.gooseGame.exception.PlayerAlreadyExistsException;
import it.dechit.gooseGame.manager.GameManager;
import it.dechit.gooseGame.manager.IGameManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PlayerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private IGameManager gameManager;
    private GooseGame gooseGame;
    private Board board;
    private PrintStream printStream;

    @Before
    public void setUp() {
        board = new Board();
        printStream = new PrintStream(System.out);
        gameManager = new GameManager(printStream);
        gooseGame = new GooseGame(board);
        gooseGame.addGameListener(gameManager);
    }

    @Test
    public void addPlayerPippo() throws Exception {

        gooseGame.addPlayer("Pippo");
        assertEquals("players: Pippo", gooseGame.getPlayers().keySet().stream()
                .collect(Collectors.joining(", ", "players: ", "")));
    }


    @Test
    public void addPlayers() throws Exception {

        gooseGame.addPlayer("Pippo");
        gooseGame.addPlayer("Pluto");
        assertEquals("players: Pippo, Pluto",
                gooseGame.getPlayers().keySet().stream()
                        .collect(Collectors.joining(", ", "players: ", "")));
    }

    @Test
    public void duplicatePlayer() throws Exception {
        gooseGame.addPlayer("Pippo");
        expectedException.expect(PlayerAlreadyExistsException.class);
        gooseGame.addPlayer("Pippo");
    }

    @Test
    public void testMovePlayer() throws Exception {
        String playerName = "Pippo";
        gooseGame.addPlayer(playerName);
        gooseGame.movePlayer(playerName, Arrays.asList(2, 2));
        assertThat(gooseGame.getPlayers().get(playerName), equalTo(4));
    }

    @Test
    public void testMovePlayerAndWin() throws Exception {
        String playerName = "Pippo";
        gooseGame.addPlayer(playerName);
        gooseGame.movePlayer(playerName, Arrays.asList(60, 3));
        assertThat(gooseGame.getPlayers().get(playerName), equalTo(63));
    }

    @Test
    public void testMovePlayerOverMaxSpaceAndBounce() throws Exception {
        String playerName = "Pippo";
        gooseGame.addPlayer(playerName);
        gooseGame.movePlayer(playerName, Arrays.asList(60, 5));
        assertThat(gooseGame.getPlayers().get(playerName), equalTo(61));
    }

    @Test
    public void testMovePlayerOnGooseSpace() throws Exception {
        String playerName = "Pippo";
        gooseGame.addPlayer(playerName);
        gooseGame.movePlayer(playerName, Arrays.asList(4, 1));
        assertThat(gooseGame.getPlayers().get(playerName), equalTo(10));
    }

    @Test
    public void testMovePlayerOnGooseSpaceAndMoveTwice() throws Exception {
        String playerName = "Pippo";
        gooseGame.addPlayer(playerName);

        gooseGame.movePlayer(playerName, Arrays.asList(4, 6));
        gooseGame.movePlayer(playerName, Arrays.asList(2, 2));

        assertThat(gooseGame.getPlayers().get(playerName), equalTo(22));
    }

    @Test
    public void testMovePlayerOnAlreadyOccupiedSpace() throws Exception {
        String playerName1 = "Pippo";
        gooseGame.addPlayer(playerName1);
        gooseGame.movePlayer(playerName1, Arrays.asList(4, 6));

        String playerName2 = "Pluto";
        gooseGame.addPlayer(playerName2);
        gooseGame.movePlayer(playerName2, Arrays.asList(4, 4));
        gooseGame.movePlayer(playerName2, Arrays.asList(1, 1));

        assertThat(gooseGame.getPlayers().get(playerName1), equalTo(8));
        assertThat(gooseGame.getPlayers().get(playerName2), equalTo(10));
    }
}