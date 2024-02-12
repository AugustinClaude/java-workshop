package fr.epita.assistants.mykitten;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class MyKitten {
    /**
     * Initializer.
     *
     * @param srcPath Source file path.
     */
    public MyKitten(String srcPath) {
        try {
            Path file = Paths.get(srcPath);
            streamContent = Files.lines(file);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("FileNotFound: " + srcPath);
        }
    }

    /**
     * Use the streamContent to replace `wordToReplace` with "miaou". Don't forget
     * to add the line number beforehand for each line. Store the new
     * result directly in the streamContent field.
     *
     * @param wordToReplace The word to replace
     */
    public void replaceByMiaou(String wordToReplace) {
        AtomicInteger nbLine = new AtomicInteger(1);
        streamContent = streamContent.map(w -> nbLine.getAndIncrement() + " " + w);
        streamContent = streamContent.map(w -> w.replace(wordToReplace, "miaou"));
    }

    /**
     * Use the streamContent to write the content into the destination file.
     *
     * @param destPath Destination file path.
     */
    public void toFile(String destPath) {
        try {
            Path file = Paths.get(destPath);
            Files.write(file, streamContent.toList(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("DestinationNotFound: " + destPath);
        }
    }

    /**
     * Creates an instance of MyKitten and calls the above methods to do it
     * straightforwardly.
     *
     * @param srcPath       Source file path
     * @param destPath      Destination file path
     * @param wordToReplace Word to replace
     */
    public static void miaou(String srcPath, String destPath,
                             String wordToReplace) {
        MyKitten myKitten = new MyKitten(srcPath);
        myKitten.replaceByMiaou(wordToReplace);
        myKitten.toFile(destPath);
    }

    public Stream<String> streamContent;
}
