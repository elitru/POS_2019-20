package at.eliastrummer.producer_consumer;


public class Stack {
    private int[] values;
    private int tos = 0;
    
    public Stack(int size){
        values = new int[size];
    }
    
    public void push(int value){
        if(isFull()){
            throw new RuntimeException("Stack is full");
        }
        
        values[tos++] = value;
    }
    
    public int pop(){
        return values[--tos];
    }
    
    public boolean isFull(){
        return tos == this.values.length;
    }
    
    public boolean isEmpty(){
        return tos == 0;
    }

    @Override
    public String toString() {
        if(isEmpty()){
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < tos; i++){
            sb.append(values[i] + ",");
        }
        
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
    
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        System.out.println(stack);
        
        stack.push(1);
        stack.push(2);
        stack.push(3);
        
        System.out.println(stack);
    }
}
