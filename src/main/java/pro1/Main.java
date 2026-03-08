package pro1;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args){
        CsvWriter writer = new CsvWriter();

        writer.processData();
    }
}