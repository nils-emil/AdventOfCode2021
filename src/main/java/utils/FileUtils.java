package utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class FileUtils {

    private FileUtils() {
    }

    public static List<String> getCsvLines(String filename) throws FileNotFoundException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = classloader.getResourceAsStream(filename);
        if (resourceAsStream == null) {
            throw new FileNotFoundException("File not found");
        }
        List<String> names = new ArrayList<>();
        try (Scanner scanner = new Scanner(resourceAsStream)) {
            while (scanner.hasNextLine()) {
                names.add(scanner.nextLine());
            }
        }
        return names;
    }

}
