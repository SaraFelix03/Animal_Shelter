package it.unimol.app.exceptions;

public class AdopterAlreadyRegistered extends Exception{
    public AdopterAlreadyRegistered(){}

    public AdopterAlreadyRegistered(String message){
        super(message);
    }
}
