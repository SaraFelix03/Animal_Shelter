package it.unimol.app.exceptions;

public class NoRegistredVisitsException extends Exception{
    public NoRegistredVisitsException(){}

    public NoRegistredVisitsException(String message){
        super(message);
    }
}
