package at.EliasTrummer.PocketCalculator.BL;

public class CrazyStack {
    private double[] values = new double[10];
    private int topOfStack = 0;

    public void push(double param){
        if(isFull()){
            throw new RuntimeException("CrazyStack overflow");
        }

        values[topOfStack] = param;
        topOfStack++;
    }

    public double pop(){
        if(isEmpty()){
            throw  new RuntimeException("CrazyStack is empty! POP not possible!");
        }

        return values[--topOfStack];
    }

    public boolean isEmpty(){
        return topOfStack == 0;
    }

    public boolean isFull(){
        return topOfStack == values.length;
    }

    public int length(){
        return topOfStack;
    }
}
