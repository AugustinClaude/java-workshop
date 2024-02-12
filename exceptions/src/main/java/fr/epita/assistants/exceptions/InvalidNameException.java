package fr.epita.assistants.exceptions;

public class InvalidNameException extends Exception {
    public InvalidNameException(String message) {
        super("InvalidNameException: " + message);
    }
}
