package at.eliastrummer.euler.Nr59.XORDecryption;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class DecryptionWorker implements Callable<Long>{

    private List<Integer> encrypted = new ArrayList<>();
    private int[] key;

    public DecryptionWorker(int[] key, List<Integer> encryptedData) {
        this.key = key;
        this.encrypted.addAll(encrypted);
    }
    
    @Override
    public Long call() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
