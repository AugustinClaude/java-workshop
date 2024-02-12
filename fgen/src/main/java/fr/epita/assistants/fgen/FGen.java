package fr.epita.assistants.fgen;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Arrays;

import static java.nio.file.Paths.get;

public class FGen {
    String curPath;

    public FGen(final String inputPath) {
        curPath = new File("").getAbsolutePath();

        try {
            InputStream inputStream = FGen.class.getClassLoader().getResourceAsStream(inputPath);
            if (inputStream == null)
                return;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.charAt(0) == '+') {
                    String path = line.substring(2);
                    File file = new File(curPath + File.separator + path);
                    if (path.lastIndexOf(File.separator) != -1) {
                        String subFoldersPath = path.substring(0, path.lastIndexOf(File.separator) + 1);
                        if (path.charAt(path.length() - 1) == '/')
                            subFoldersPath = path.substring(0, subFoldersPath.substring(0, subFoldersPath.length() - 1).lastIndexOf(File.separator) + 1);

                        if (subFoldersPath.length() != 0) {
                            File subFolders = new File(curPath + File.separator + subFoldersPath);
                            subFolders.mkdirs();
                        }
                    }

                    if (path.charAt(path.length() - 1) == '/')
                        file.mkdirs();
                    else
                        file.createNewFile();
                } else if (line.charAt(0) == '>') {
                    String newPath = curPath + File.separator + line.substring(2);
                    File file2 = new File(newPath);
                    if (!file2.exists())
                        throw new RuntimeException("FileDoesntExist: " + newPath);

                    try {
                        get(newPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("CannotCDHere: " + newPath);
                    }

                    curPath = newPath;
                } else if (line.charAt(0) == '-') {
                    String path = line.substring(2);
                    File file = new File(curPath + File.separator + path);
                    deleteDirectory(file);
                }
            }

            inputStream.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("FileNotFound: " + inputPath);
        }
    }

    private void deleteDirectory(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDirectory(f);
            }
        }
        file.delete();
    }
}
