package at.eliastrummer.exa_205_game2048.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import at.eliastrummer.exa_205_game2048.utils.Direction;

public class GameManager {

    public static void main(String[] args) {
        GameManager manager = new GameManager();
        System.out.println("=============================================");
        manager.makeMove(Direction.RIGHT);
        manager.print();
    }

    private static final Random RANDOM = new Random();
    private static final int[] NUMBERS = {
            2, 2, 4
    };

    private int[][] values = new int[4][4];
    private List<Point> freePoints = new ArrayList<>();
    private int points;

    public GameManager(){
        reset();
        print();
    }

    public void print(){
        for(int i = 0; i < values.length; i++){
            for(int i2 = 0; i2 < values[i].length; i2++){
                System.out.printf(values[i][i2] + " ");
            }
            System.out.printf("\n");
        }
    }

    public boolean makeMove(Direction direction){
        boolean hasMergedOccured = false;

        if(direction == Direction.RIGHT){
            for(int y = 0; y < values.length; y++){
                for(int x = 0; x < values[y].length - 1; x++){
                    //TODO: search for same number not just compare next one
                    if(values[y][x] == values[y][x + 1]){
                        values[y][x + 1] = values[y][x + 1] * 2;
                        values[y][x] = 0;
                        hasMergedOccured = true;
                        moveRowToDirection(direction);
                    }
                }
            }
        }

        if(hasMergedOccured){
            generateValue();
        }

        return false;
    }

    public void reset(){
        for(int i = 0; i < values.length; i++){
            for(int i2 = 0; i2 < values[i].length; i2++){
                freePoints.add(new Point(i2, i));
            }
        }

        values = new int[4][4];
        generateValue();
        generateValue();
    }

    public boolean generateValue(){
        //check if game is finished
        if(freePoints.size() == 0){
            return false;
        }

        Point point = freePoints.get(RANDOM.nextInt(freePoints.size()));
        values[point.getY()][point.getX()] = getRandomNumber();
        freePoints.remove(point);

        return true;
    }

    private void moveRowToDirection(Direction direction){
        switch (direction){
            case RIGHT:
                for(int y = 0; y < values.length; y++){
                    List<Integer> valuesForEnd = new ArrayList<>();
                    for(int x = 0; x < values[y].length; x++){
                        if(values[y][x] != 0){
                            valuesForEnd.add(values[y][x]);
                        }

                        values[y][x] = 0;
                        if(!freePoints.contains(new Point(x, y))){
                            freePoints.add(new Point(x, y));
                        }
                    }

                    for(int i = 0; i < valuesForEnd.size(); i++){
                        values[y][values.length - 1 - i] = valuesForEnd.get(valuesForEnd.size() - 1 - i);
                        freePoints.remove(new Point(values.length - 1 - i, y));
                    }
                }
                break;
        }
        /*switch (direction) {
            case RIGHT:
                for(int i = values[y].length - 1; i >= x && i >= 1; i--){
                    values[y][i] = values[y][i - 1];
                }
                break;

            case LEFT:
                for(int i = x; i < values[y].length - 1; i++){
                    values[y][i] = values[y][i + 1];
                }
                break;

            case BOTTOM:
                for(int i = values.length - 1; i >= y && i >= 1; i--){
                    values[i][x] = values[i - 1][x];
                }
                break;

            case TOP:
                for(int i = y; i < values.length - 1; i++){
                    values[i][x] = values[i + 1][x];
                }
                break;
        }*/
    }

    private int getRandomNumber(){
        return NUMBERS[RANDOM.nextInt(NUMBERS.length)];
    }
}
