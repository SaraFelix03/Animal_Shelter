package it.unimol.app.exceptions;

public class AnimalAlreadyAdoptedException extends Exception {
    public AnimalAlreadyAdoptedException() { }

    public AnimalAlreadyAdoptedException(String message) {
        super(message);
    }
}
