package pro1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CsvReader {


    public CsvReader(){

    }

    public List<String> readData(Path path) throws IOException {
        // 1. Místo ArrayListu pro části řádku vytvoříme seznam pro výsledné záznamy
        List<String> processedLines = new ArrayList<>();

        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                if(line.contains(":")){
                line = line.replace(':',';');
                }
                else if(line.contains("=")){
                    line = line.replace('=',';');
                }
                String[] parts = line.split(";");

                if (parts.length >= 2) {
                    String studentName = parts[0];

                    Fraction score = Fraction.parse(parts[1]);

                    processedLines.add(studentName + ";" + score.toString());
                }
            });

        } catch (IOException e) {
            throw new IOException("Chyba při čtení souboru: " + path, e);
        }
        return processedLines;
    }
}
