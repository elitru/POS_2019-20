package at.eliastrummer.exa_205_game2048.game;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {
    private static final Random RANDOM = new Random();
    private static final int[] NUMBERS = {
            2, 2, 4
    };

    private int[][] values = new int[4][4];
    private List<Point> usedPoints = new ArrayList<>();
    private int points;

    public GameManager(){
        generateValue();
        generateValue();
    }

    public void makeMove(){

    }

    public void reset(){

    }

    public boolean generateValue(){
        //check if game is finished
        if(values.length * values[0].length == usedPoints.size()){
            return false;
        }

        return true;
    }

    private int getRandomNumber(){
        return NUMBERS[RANDOM.nextInt(NUMBERS.length)];
    }
}
