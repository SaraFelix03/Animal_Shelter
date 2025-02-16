package it.unimol.app.exceptions;

public class AnimalNotAdopted extends Exception {
    public AnimalNotAdopted() { }

    public AnimalNotAdopted(String message) {
        super(message);
    }
}
