package pl.great.waw.company.exceptions;



public class PeselAlreadyExistException extends Exception{
    public PeselAlreadyExistException(String message) {
        super(message);
    }
}
