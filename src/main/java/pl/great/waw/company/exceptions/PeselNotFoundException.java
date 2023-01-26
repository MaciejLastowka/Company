package pl.great.waw.company.exceptions;

public class PeselNotFoundException extends Exception{
    public PeselNotFoundException(String message) {
        super(message);
    }
}
