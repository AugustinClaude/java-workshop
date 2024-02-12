package fr.epita.assistants.cinematheque;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Logger implements PropertyChangeListener {
    private String logger;
    PrintStream output;

    public Logger(PrintStream output) {
        logger = "";
        this.output = output;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object obj = evt.getNewValue();
        if (!(obj instanceof Stock.Operation operation))
            throw new IllegalArgumentException();
        if (!Arrays.stream(Stock.Operation.values()).toList().contains(operation))
            throw new IllegalArgumentException();

        String curDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
        String curTime = DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
        logger = "[" + curDate + "|" + curTime + "] ";
        if (operation == Stock.Operation.Add)
            output.println(logger + "An element has been added.");
        else if (operation == Stock.Operation.Delete)
            output.println(logger + "An element has been removed.");
        else if (operation == Stock.Operation.Sort)
            output.println(logger + "A sorting has occurred.");
    }
}
