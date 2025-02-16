package it.unimol.app.exceptions;

public class AnimalAlreadyRegistered extends Exception {
    public AnimalAlreadyRegistered() { }

    public AnimalAlreadyRegistered(String message) {
        super(message);
    }
}
