package at.eliastrummer.euler.Nr39.IntegerRightTriangles;

import java.util.Set;
import java.util.concurrent.*;

public class TriangleLauncher {
    
    public static void main(String[] args) {
        System.out.println("============== STARTING ==============");
        new TriangleLauncher().findSolution();
    }
    
    public void findSolution(){
        long start = System.currentTimeMillis();
        
        ExecutorService pool = Executors.newFixedThreadPool(6);
        CompletionService<Set<Triple>> service = new ExecutorCompletionService<>(pool);
        
        for(int p = 10; p <= 1000; p++){
            service.submit(new TriangleWorker(p));
        }
        
        pool.shutdown();
        
        Set<Triple> maxTriple = null;
        
        while(!pool.isTerminated()){
            try {
                Future<Set<Triple>> future = service.take();
                Set<Triple> result = future.get();
                
                if(maxTriple == null || result.size() > maxTriple.size()){
                    maxTriple = result;
                }                
                System.out.println("--> " + result.size());
            } catch (InterruptedException | ExecutionException ex) {
                System.out.println(ex.toString());
            }
        }
     
        System.out.println("\nRESULT: " + maxTriple.size());
        
        maxTriple.forEach(System.out::println);
        
        System.out.println((System.currentTimeMillis() - start) / 1000.0);
    }
}
