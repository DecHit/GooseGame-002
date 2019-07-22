package it.dechit.gooseGame;

import it.dechit.gooseGame.box.Board;
import it.dechit.gooseGame.command.CommandExecutor;
import it.dechit.gooseGame.exception.CommandNotFoundException;
import it.dechit.gooseGame.exception.GameStoppedException;
import it.dechit.gooseGame.manager.GameManager;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello! This is The Goose Game!");

        GooseGame gooseGame = new GooseGame(new Board());
        gooseGame.addGameListener(new GameManager(System.out));

        Dice dice = new Dice(6);

        CommandExecutor commandExecutor = new CommandExecutor(gooseGame, dice, System.out);

        try (Scanner scanner = new Scanner(System.in)) {
            do {
                System.out.print("Type command: ");
                String command = scanner.nextLine();
                try {
                    commandExecutor.executeGameCommand(command);
                } catch (CommandNotFoundException cnfe) {
                    System.out.println(cnfe.getMessage());
                } catch (GameStoppedException gse) {
                    gooseGame.setGameOver(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                System.out.println();

            } while (!gooseGame.isGameOver());
        }

        System.out.println("The Goose Game terminated, Bye!");
    }
}
