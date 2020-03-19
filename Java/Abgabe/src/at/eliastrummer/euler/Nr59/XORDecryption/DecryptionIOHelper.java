package at.eliastrummer.euler.Nr59.XORDecryption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DecryptionIOHelper {

    private static final String PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "at" + File.separator + "eliastrummer" + 
            File.separator + "euler" + File.separator + "Nr59" + File.separator + "XORDecryption" + File.separator;
    
    public static List<Integer> load(){
        try {
            List<Integer> result = new ArrayList<>();
            new BufferedReader(new FileReader(PATH + "input.txt"))
                    .lines()
                    .forEach(l -> Arrays.stream(l.split(","))
                            .map(Integer::parseInt)
                            .forEach(result::add));
            
            return result;
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
        
        return null;
    }
    
    public static Set<String> commonWords(){
        try {
            return new BufferedReader(new FileReader(PATH + "commonwords.txt"))
                    .lines()
                    .collect(Collectors.toSet());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
        
        return null;
    }
}
