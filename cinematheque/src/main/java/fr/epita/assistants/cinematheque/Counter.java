package fr.epita.assistants.cinematheque;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public class Counter implements PropertyChangeListener {
    private int count = 0;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object obj = evt.getNewValue();
        if (!(obj instanceof Stock.Operation operation))
            throw new IllegalArgumentException();
        if (!Arrays.stream(Stock.Operation.values()).toList().contains(operation))
            throw new IllegalArgumentException();

        if (operation == Stock.Operation.Add)
            count++;
        else if (operation == Stock.Operation.Delete)
            count--;
    }

    public int getCount() {
        return count;
    }
}
