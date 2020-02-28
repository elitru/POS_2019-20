package at.eliastrummer.name.threads;

public class NameThread implements Runnable {

    private StringBuilder sb = new StringBuilder(10_000_000);
    
    @Override
    public void run() {
        for(int i = 0; i < 100_000; i++){
            /*synchronized(sb){
                sb.append(Thread.currentThread().getName());
            }*/
            
            sb.append(Thread.currentThread().getName());
        }
    }

    public StringBuilder getSb() {
        return sb;
    }

    public static void main(String[] args) {
        NameThread nt = new NameThread();
        Thread t1 = new Thread(nt, "Thread 1");
        Thread t2 = new Thread(nt, "Thread 2");
        Thread t3 = new Thread(nt, "Thread 3");

        t1.start();
        t2.start();
        t3.start();
        
        long start = System.currentTimeMillis();
        
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException ex) {
            
        }
        
        long dur = System.currentTimeMillis() - start;
        System.out.format("%d ms\n", dur);
        
        System.out.println(nt.getSb().length());
        System.out.println(nt.getSb().substring(0, 1000));
    }
}
