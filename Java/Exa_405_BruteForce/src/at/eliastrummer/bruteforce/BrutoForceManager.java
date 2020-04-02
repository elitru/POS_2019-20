package at.eliastrummer.bruteforce;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JTextArea;

public class BrutoForceManager {
    private JTextArea logger;
    private int amountThreads;
    private int amountPasswordsToSolve;
    private ExecutorService tPool;
    private CompletionService<ResultHolder> tService;
    private boolean isCancelled = false;

    public BrutoForceManager(JTextArea logger, int amountThreads, int amountPasswordsToSolve) {
        this.logger = logger;
        this.amountThreads = amountThreads;
        this.amountPasswordsToSolve = amountPasswordsToSolve;
    }
    
    public void crackPasswords() throws Exception{
        logger.setText("Starting...\n");
        List<Person> data = BrutoForceIOHelper.load();
        List<BrutoForceWorker> threads = new ArrayList<>();
        isCancelled = false;
        CounterHolder.counter = 0;
        
        ExecutorService pool = Executors.newFixedThreadPool(6);
        CompletionService<ResultHolder> service = new ExecutorCompletionService<>(pool);
        
        this.tPool = pool;
        this.tService = service;
        
        for(Person person : data){
            BrutoForceWorker task = new BrutoForceWorker(person);
            threads.add(task);
            service.submit(task);
        }
        
        pool.shutdown();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!pool.isTerminated()){
                    if(isCancelled || CounterHolder.counter == amountPasswordsToSolve){
                        pool.shutdownNow();
                        for(int i = 0; i < threads.size(); i++){
                            try{
                                threads.get(i).cancel();
                            }catch(Exception e){
                                System.out.println(":(");
                            }
                        }
                        
                        if(isCancelled){
                            logger.append("Cancelled...");
                        }
                    }
                }
            }
        }).start();
        
        while(!pool.isTerminated() && CounterHolder.counter < amountPasswordsToSolve){
            Future<ResultHolder> result = service.take();
            ResultHolder rh = result.get();
            if(rh != null){
                synchronized(logger){
                    logger.append(rh.getPerson().getFirstname() + " " + rh.getPerson().getLastname() + " --> " + rh.getPassword() + " (" + rh.getUsedTime() + "ms)\n");
                    CounterHolder.counter++;
                }
            }            
        }
        
        if(isCancelled){
            return;
        }
        
        logger.append("Finished...");
    }
    
    public void stop(){
        isCancelled = true;
    }
}

class CounterHolder{
    public static int counter = 0;
}