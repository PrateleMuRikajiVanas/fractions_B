package pro1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class CsvWriter {
    private CsvReader reader = new CsvReader();

    public CsvWriter(){

    }

    public void processData(){
        Path Directory = Paths.get("input");
        try(Stream<Path> pathStream = Files.walk(Directory))
        {
            pathStream.filter(Files::isRegularFile).forEach(file->{
                IO.println("otevřel jsem soubor " + file.getFileName());
                try {
                    List<String> processedLines = reader.readData(file);
                    createOutput(String.valueOf(file.getFileName()), processedLines);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createOutput(String fileName, List<String> outputData){
        Path path = Paths.get("output/" + fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)){
            for(String line: outputData){
                writer.write(line);
                writer.newLine();
            }
                IO.println("Zapsal jsem soubor "+ fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
