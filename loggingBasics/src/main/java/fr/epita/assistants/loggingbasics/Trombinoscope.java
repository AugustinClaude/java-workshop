package fr.epita.assistants.loggingbasics;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Trombinoscope {
    private final Logger LOGGER;
    private final HashMap<String, Long> map;

    public Trombinoscope() {
        LOGGER = (Logger) LoggerFactory.getLogger(Trombinoscope.class);
        LOGGER.setLevel(Level.TRACE);
        LOGGER.trace("Instantiating new Trombinoscope");

        map = new HashMap<>();
    }

    public Long putPerson(String name, long photoId) {
        LOGGER.setLevel(Level.DEBUG);
        LOGGER.debug("Putting person (\"" + name + "\", " + photoId + ")");

        LOGGER.setLevel(Level.TRACE);
        if (!map.containsKey(name))
            LOGGER.trace("Added entry for person \"" + name + "\"");
        else
            LOGGER.trace("Updated entry for person \"" + name + "\"");

        Long oldPhotoId = map.put(name,
                                  photoId);

        return oldPhotoId;
    }
}
