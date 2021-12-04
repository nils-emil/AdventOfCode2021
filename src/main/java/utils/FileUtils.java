package utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {

    private FileUtils() {
    }

    public static List<String> getCsvLines(String filename) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = classloader.getResourceAsStream(filename);
        List<String> names = new ArrayList<>();
        try (Scanner scanner = new Scanner(resourceAsStream)) {
            while (scanner.hasNextLine()) {
                names.add(scanner.nextLine());
            }
        }
        return names;
    }

}
