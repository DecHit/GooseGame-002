package it.dechit.gooseGame.command;

import it.dechit.gooseGame.exception.CommandNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public enum Command {

    ADD_PLAYER("add player", true, Collections::singletonList),
    MOVE_PLAYER("move", true, Collections::singletonList),
    EXIT("exit", false, null);

    private static final String COMMAND_ARGUMENTS_SEPARATOR = " ";

    private final String command;
    private final boolean haveArguments;
    private final Function<String, List<String>> argumentParser;

    Command(String command, boolean haveArguments, Function<String, List<String>> argumentParser) {
        this.command = command;
        this.haveArguments = haveArguments;
        this.argumentParser = argumentParser;
    }

    public static Command getCommandFromString(String userString) throws CommandNotFoundException {
        return Arrays.stream(Command.values())
                .filter(c -> userString.startsWith(c.command + (c.haveArguments ? COMMAND_ARGUMENTS_SEPARATOR : "")))
                .findFirst()
                .orElseThrow(() -> new CommandNotFoundException(userString));
    }

    public List<String> getCommandArguments(String userCommand) {
        if (!haveArguments) {
            return Collections.emptyList();
        }
        String arguments = userCommand.replace(command + COMMAND_ARGUMENTS_SEPARATOR, "");
        return argumentParser.apply(arguments);
    }
}
