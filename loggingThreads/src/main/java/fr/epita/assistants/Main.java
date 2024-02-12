package fr.epita.assistants;

import fr.epita.assistants.loggingThreads.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("Entering application");

        TimeWaster waster = new TimeWaster();

        Thread sum = new Thread(() -> waster.sumUpTo(123456789));
        Thread importantThing = new Thread(waster::doImportantThing);

        sum.start();
        importantThing.start();

        // importantThing.interrupt();

        sum.join();
        importantThing.join();

        logger.info("Clean application exit");
    }
}