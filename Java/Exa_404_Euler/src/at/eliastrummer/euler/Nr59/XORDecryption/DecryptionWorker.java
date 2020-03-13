package at.eliastrummer.euler.Nr59.XORDecryption;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;


public class DecryptionWorker implements Callable<String>{

    private List<Integer> encrypted;
    private Set<String> commonWords;
    private char letter;

    public DecryptionWorker(char letter, List<Integer> encrypted, Set<String> commonWords) {
        this.letter = letter;
        this.encrypted = encrypted;
        this.commonWords = commonWords;
    }
    
    @Override
    public String call() throws Exception {
        for(int i = 97; i <= 154; i++){
            for(int i2 = 97; i2 <= 154; i2++){
                List<Integer> currentSolution = new ArrayList<>();
                int[] keys = new int[] { (int)this.letter, i, i2 };
                int keyCounter = 0;
                
                for(int entry : encrypted){
                    currentSolution.add(entry ^ keys[keyCounter]);
                    keyCounter++;
                    keyCounter =  keyCounter % 3;
                }
                
                StringBuilder decrypted = new StringBuilder("");
                
                for(int entry : currentSolution){
                    decrypted.append((char)entry);
                }
                
                int wordCount = 0;
                
                for(String word : decrypted.toString().split(" ")){
                    if(this.commonWords.contains(word)){
                        wordCount++;
                    }
                 
                    if(wordCount >= 4){
                        System.out.println("Key: " + new String(new char[]{(char)keys[0], (char)keys[1], (char)keys[2]}));
                        return decrypted.toString();
                    }
                }
            }
        }
        
        throw new Exception("Key not found");
    }
    
}
