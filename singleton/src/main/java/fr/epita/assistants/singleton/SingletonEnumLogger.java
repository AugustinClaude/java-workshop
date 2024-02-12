package fr.epita.assistants.singleton;

import fr.epita.assistants.logger.Logger;

public enum SingletonEnumLogger implements Logger {
    INSTANCE;

    private static int infoCounter = 0;
    private static int warnCounter = 0;
    private static int errorCounter = 0;

    @Override
    public void log(Level level, String message) {
        switch (level) {
            case INFO -> infoCounter++;
            case WARN -> warnCounter++;
            default -> errorCounter++;
        }

        System.err.println("[" + level + "] " + message);
    }

    @Override
    public int getInfoCounter() {
        return infoCounter;
    }

    @Override
    public int getWarnCounter() {
        return warnCounter;
    }

    @Override
    public int getErrorCounter() {
        return errorCounter;
    }

    @Override
    public void reset() {
        infoCounter = 0;
        warnCounter = 0;
        errorCounter = 0;
    }
}
