package at.eliastrummer.producer_consumer;

import java.util.Random;

public class Producer implements Runnable{
    private Stack stack;
    private int counter;
    
    private static final Random RND = new Random();

    public Producer(Stack stack, int counter) {
        this.stack = stack;
        this.counter = counter;
    }

    @Override
    public void run() {
        System.out.println("Producer started...");
        
        for(int i = 0; i < counter; i++){
            synchronized(stack){
                while(stack.isFull()){
                    System.out.println("Stack is full");
                    try {
                        System.out.println("Producer has to wait");
                        stack.wait();
                        System.out.println("Producer finished waiting");
                    } catch (InterruptedException ex) {
                        
                    }
                }
                
                System.out.println("Producer pushes to stack");
                stack.push(RND.nextInt(100));
                stack.notify();
                System.out.println(stack);
            }
            
            try {
                Thread.sleep(RND.nextInt(1000));
            } catch (InterruptedException ex) {

            }
        }
    }
}
