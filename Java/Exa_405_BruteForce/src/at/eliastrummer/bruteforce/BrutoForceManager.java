package at.eliastrummer.bruteforce;

import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BrutoForceManager {
    public static void main(String[] args) throws Exception {
        new BrutoForceManager().crackPasswords();
    }
    
    public void crackPasswords() throws Exception{
        List<Person> data = BrutoForceIOHelper.load();
        
        ExecutorService pool = Executors.newFixedThreadPool(6);
        CompletionService<ResultHolder> service = new ExecutorCompletionService<>(pool);
        
        for(Person person : data){
            service.submit(new BrutoForceWorker(person));
        }
        
        pool.shutdown();
        
        while(!pool.isTerminated()){
            Future<ResultHolder> result = service.take();
            ResultHolder rh = result.get();
            if(rh != null){
                System.out.println(rh.getPerson().getFirstname() + " " + rh.getPerson().getLastname() + " --> " + rh.getPassword());
            }
        }
    }
}