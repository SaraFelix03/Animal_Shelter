package it.unimol.app.exceptions;

public class AdopterNotExists extends Exception {
    public AdopterNotExists() { }

    public AdopterNotExists(String message) {
        super(message);
    }
}
