package at.eliastrummer.euler.Nr59.XORDecryption;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DecryptionLauncher {
    public static void main(String[] args) throws ExecutionException, InterruptedException{
        new DecryptionLauncher().launch();
    }
    
    public void launch() throws ExecutionException, InterruptedException{
        ExecutorService pool = Executors.newFixedThreadPool(6);
        List<Callable<String>> worker = new ArrayList<>();

        List<Integer> encrypted = DecryptionIOHelper.load();
        Set<String> commonWords = DecryptionIOHelper.commonWords();

        for(int c = (int)'a'; c <= (int)'z'; c++){
            worker.add(new DecryptionWorker((char)c, encrypted, commonWords));
        }

        String result = pool.invokeAny(worker);
        pool.shutdown();

        System.out.println(result);
        System.out.println("==> " + CharBuffer.wrap(result.toCharArray()).chars().sum());
    }
}
