package at.eliastrummer.euler.Nr96.Sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SudokuIOHelper {
    private static final String PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "at" + File.separator + "eliastrummer" + 
            File.separator + "euler" + File.separator + "Nr96" + File.separator + "Sudoku" + File.separator + "sudoku.txt";
    
    public static List<SudokuField> load() throws FileNotFoundException{
        List<SudokuField> result = new ArrayList<>();
        List<String> lines = new BufferedReader(new FileReader(PATH)).lines().collect(Collectors.toList());
        
        for(int i = 0; i <= lines.size() - 10; i += 10){
            SudokuField field = new SudokuField(lines.get(i));
            int row = 0;
            
            for(int i2 = i + 1; i2 < (i + 9); i2++){
                char[] line = lines.get(i2).toCharArray();
                
                for(int i3 = 0; i3 < line.length; i3++){
                    field.getField()[row][i3] = Integer.parseInt(Character.toString(line[i3]));
                }
                row++;
            }
            
            result.add(field);
        }
        
        return result;
    }
}
