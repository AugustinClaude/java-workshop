package fr.epita.assistants.throwback;

public class Pitcher {
    public static void throwException(String message) throws LongStringException, ShortStringException, PositiveIntegerException, NegativeIntegerException, UnknownException {
        if (message.matches("[0-9]+"))
            throw new PositiveIntegerException(message);
        else if (message.matches("-[0-9]+"))
            throw new NegativeIntegerException(message);
        else {
            for (int i = 0; i < message.length(); i++) {
                char c = message.toLowerCase().charAt(i);
                if ((c < 'a' || c > 'z') && c != ' ' && c != ',' && c != '.' && c != '\'')
                    throw new UnknownException(message);
            }

            if (message.length() >= 100)
                throw new LongStringException(message);
            throw new ShortStringException(message);
        }
    }
}
