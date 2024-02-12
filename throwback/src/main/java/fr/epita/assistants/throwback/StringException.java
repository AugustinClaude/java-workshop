package fr.epita.assistants.throwback;

abstract class StringException extends Exception {
    public StringException(String message) {
        super("StringException: " + message);
    }
}
