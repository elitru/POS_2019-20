package at.eliastrummer.producer_consumer;

import java.util.Random;


public class Consumer implements Runnable{
    private Stack stack;
    private int counter;
    
    private static final Random RND = new Random();

    public Consumer(Stack stack, int counter) {
        this.stack = stack;
        this.counter = counter;
    }

    @Override
    public void run() {
        System.out.println("Consumer started...");
        
        for(int i = 0; i < counter; i++){
            synchronized(stack){
                while(stack.isEmpty()){
                    System.out.println("Stack is empty");
                    try {
                        System.out.println("Stack is empty");
                        stack.wait();
                        System.out.println("Consumer finished waiting");
                    } catch (InterruptedException ex) {
                    
                    }
                }
                
                int value = stack.pop();
                System.out.println("Consumer reads value --> " + value);
                System.out.println(stack);
                stack.notify();
            }
            
            try {
                Thread.sleep(RND.nextInt(1000));
            } catch (InterruptedException ex) {

            }
        }
    }
}
