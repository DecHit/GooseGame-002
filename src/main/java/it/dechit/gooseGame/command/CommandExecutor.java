package it.dechit.gooseGame.command;

import it.dechit.gooseGame.Dice;
import it.dechit.gooseGame.GooseGame;
import it.dechit.gooseGame.exception.PlayerAlreadyExistsException;
import it.dechit.gooseGame.exception.PlayerNotFoundException;
import it.dechit.gooseGame.exception.CommandNotFoundException;
import it.dechit.gooseGame.exception.GameStoppedException;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

public class CommandExecutor {

    private final GooseGame gooseGame;
    private final Dice dice;

    private final PrintStream printStream;

    public CommandExecutor(GooseGame gooseGame, Dice dice, PrintStream printStream) {
        this.gooseGame = gooseGame;
        this.dice = dice;
        this.printStream = printStream;
    }

    public void executeGameCommand(String userString) throws Exception {
        Command command = Command.getCommandFromString(userString);
        List<String> userArguments = command.getCommandArguments(userString);
        switch (command) {
            case ADD_PLAYER:
                handleAddPlayer(userArguments);
                break;
            case MOVE_PLAYER:
                handleMovePlayer(userArguments);
                break;
            case EXIT:
                throw new GameStoppedException();
            default:
                throw new CommandNotFoundException(userString);
        }
    }

    private void handleMovePlayer(List<String> userArguments) throws PlayerNotFoundException {

        String playerName = userArguments.get(0);
        List<Integer> rolls;
        if (!userArguments.get(0).contains(",")) {
            rolls = Arrays.asList(dice.roll(), dice.roll());
        } else {
            int index=userArguments.get(0).indexOf(",");
             rolls = Arrays.asList(Integer.parseInt(userArguments.get(0).substring(index-1,index)),
                     Integer.parseInt(userArguments.get(0).substring(index+1,index+2)));
             playerName=playerName.substring(0,index-2);
        }

            String rollsString = rolls.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            printStream.print(playerName + " rolls " + rollsString + ". ");

            gooseGame.movePlayer(playerName, rolls);

    }

    private void handleAddPlayer(List<String> userArguments) throws PlayerAlreadyExistsException {
        String playerName = userArguments.get(0);

        gooseGame.addPlayer(playerName);

        printStream.print(gooseGame.getPlayers().keySet()
                .stream()
                .collect(Collectors.joining(", ", "players: ", "")));
    }
}
