import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Tools {

    private String fileContent = "";

    /**
     * Read an input file
     *
     * @param file the file path
     * @return the file content
     */

    public String readHashValue(String file) {
        try {
            Files.lines(Paths.get(file), StandardCharsets.UTF_8).forEach(s -> fileContent += s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return fileContent;
        }
    }
}
