package fr.epita.assistants.exceptions;

public class InvalidMajorException extends Exception {
    public InvalidMajorException(String message) {
        super("InvalidMajorException: " + message);
    }
}
