package fr.epita.assistants.embedfiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

public class DisplayEmbedFile {
    private final String filename;

    public DisplayEmbedFile(String filename) {
        this.filename = filename;
    }

    public Optional<String> display() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
        if (inputStream == null)
            return Optional.empty();

        try {
            return Optional.of(new String(inputStream.readAllBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
