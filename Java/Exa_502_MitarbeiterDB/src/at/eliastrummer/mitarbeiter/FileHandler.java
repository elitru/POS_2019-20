package at.eliastrummer.mitarbeiter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler {
    private static final String PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator + "import_data.csv";
    
    public static List<Employee> loadTestData() throws FileNotFoundException{
        return new BufferedReader(new FileReader(PATH))
                .lines()
                .skip(1)
                .map(Employee::new)
                .collect(Collectors.toList());
    }
}