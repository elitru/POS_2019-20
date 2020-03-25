package at.eliastrummer.euler.Nr96.Sudoku;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class SudokuLauncher {
    public static int count = 0;
    public static void main(String[] args) throws FileNotFoundException {
        new SudokuLauncher().launch();
    }
    
    public void launch() throws FileNotFoundException{
        long resultSum = 0;
        List<SudokuField> data = SudokuIOHelper.load();
        ExecutorService pool = Executors.newFixedThreadPool(6);
        CompletionService<Integer> service = new ExecutorCompletionService<>(pool);
        
        for(SudokuField field : data){
            service.submit(new SudokuWorker(field));
        }
        
        pool.shutdown();
        
        while(!pool.isTerminated()){
            try{
                Future<Integer> result = service.take();
                resultSum += result.get();
            }catch (InterruptedException | ExecutionException ex) {
                System.out.println(ex.toString());
            }
        }
        
        System.out.println("Solution --> " + resultSum);
    }
}
