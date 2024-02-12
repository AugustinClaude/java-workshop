package fr.epita.assistants.throwback;

abstract class IntegerException extends Exception {
    public IntegerException(String message) {
        super("IntegerException: " + message);
    }
}
