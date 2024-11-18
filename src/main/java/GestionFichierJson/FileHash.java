package GestionFichierJson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHash {

    public static String readFileToString(String filename) throws IOException {

        return Files.readString(Paths.get(filename));
    }

    public static void writeStringInFile(String filePath, String content) throws IOException {

        Files.writeString(Paths.get(filePath), content);
    }
}
