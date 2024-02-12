package fr.epita.assistants.exceptions;

public class InvalidAgeException extends Exception {
    public InvalidAgeException(int number) {
        super("InvalidAgeException: " + number);
    }
}
