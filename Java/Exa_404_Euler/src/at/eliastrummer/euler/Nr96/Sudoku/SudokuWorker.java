package at.eliastrummer.euler.Nr96.Sudoku;

import java.util.concurrent.Callable;


public class SudokuWorker implements Callable<Integer>{

    private int[][] field;
    private String name;

    public SudokuWorker(SudokuField field) {
        this.field = field.getField();
        this.name = field.getName();
    }
    
    @Override
    public Integer call() throws Exception {
        if(solve()){
            int digit_1 = field[0][0];
            int digit_2 = field[0][1];
            int digit_3 = field[0][2];
            int result = Integer.parseInt("" + digit_1 + digit_2 + digit_3);
            return result;
        }
        return -1;
    }

    private boolean isValid(int num, int row, int column){
        
        //check row & column
        for(int i = 0; i < field.length; i++){
            if(field[row][i] == num){
                return false;
            }
            
            if(field[i][column] == num){
                return false;
            }
        }
        
        //check square
        int sqrt = (int) Math.sqrt(field.length);
        int rowStart = row - row % sqrt;
        int columnStart = column - column % sqrt;
        
        for(int i =  rowStart; i < rowStart + sqrt; i++){
            for(int i2 = columnStart; i2 < columnStart + sqrt; i2++){
                if(field[i][i2] == num){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public boolean solve(){
        int row = -1;
        int column = -1;
        boolean isEmpty = true;
        
        for(int i = 0; i < field.length; i++){
            for(int i2 = 0; i2 < field.length; i2++){
                if(field[i][i2] == 0){
                    row = i;
                    column = i2;
                    isEmpty = false;
                    break;
                }
            }
        }
        
        if(isEmpty){
            return true;
        }
        
        for(int num = 1; num <= field.length; num++){
            if(isValid(num, row, column)){
                field[row][column] = num;
                if(solve()){
                    return true;
                }else{
                    field[row][column] = 0;
                }
            }
        }
        
        return false;
    }
}
