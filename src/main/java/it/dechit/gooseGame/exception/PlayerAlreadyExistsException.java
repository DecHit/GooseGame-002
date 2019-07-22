package it.dechit.gooseGame.exception;

public class PlayerAlreadyExistsException extends Exception {
    public PlayerAlreadyExistsException(String name) {
        super(name +": already existing player");
    }
}
