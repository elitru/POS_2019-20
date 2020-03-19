package at.eliastrummer.bruteforce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class BrutoForceIOHelper {
    private static final String PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "src" + File.separator + "passwd_file.csv";
    
    public static List<Person> load() {
        try {
            return new BufferedReader(new FileReader(PATH))
                    .lines()
                    .skip(1)
                    .map(Person::new)        
                    .collect(Collectors.toList());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
}
