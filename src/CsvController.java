import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CsvController {
    CSVWriter writer;

    public CsvController() {
    }

    public void write(List<String[]> allLines, String filename) {
        try {
            writer = new CSVWriter(new FileWriter(filename));
            writer.writeAll(allLines);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
