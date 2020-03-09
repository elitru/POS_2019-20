package at.eliastrummer.euler.Nr59.XORDecryption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DecryptionIOHelper {

    private static final String PATH = System.getProperty("user.dir") + File.separator + "at" + File.separator + "eliastrummer" + 
            File.separator + "euler" + File.separator + "Nr59" + File.separator + "XORDecryption" + File.separator + "input.txt";
    
    public static List<Integer> load(){
        try {
            List<Integer> result = new ArrayList<>();
            new BufferedReader(new FileReader(PATH))
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
    
}
