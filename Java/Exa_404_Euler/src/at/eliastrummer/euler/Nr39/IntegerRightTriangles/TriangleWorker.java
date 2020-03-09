package at.eliastrummer.euler.Nr39.IntegerRightTriangles;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;


public class TriangleWorker implements Callable<Set<Triple>>{
    private int p;

    public TriangleWorker(int p) {
        this.p = p;
    }

    @Override
    public Set<Triple> call() throws Exception {
        Set<Triple> result = new HashSet<>();
        
        for(int i = 1; i < p / 2; i++){
            for(int i2 = 1; i2 < p / 2; i2++){
                for(int i3 = 1; i3 < p / 2; i3++){
                    if(((i * i) + (i2 * i2) == (i3 * i3)) && ((i + i2 + i3) == p)){
                        result.add(new Triple(p, i, i2, i3));
                    }
                    
                }
            }
        }
        
        return result;
    }
}
