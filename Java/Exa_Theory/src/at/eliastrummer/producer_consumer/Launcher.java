package at.eliastrummer.producer_consumer;


public class Launcher {
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        int counter = 100;
        Producer producer = new Producer(stack, counter);
        Consumer consumer = new Consumer(stack, counter);
        
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        
        producerThread.start();
        consumerThread.start();
    }
}