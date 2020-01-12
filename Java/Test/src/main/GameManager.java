package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GameManager {
    public static void main(String[] args) {
        GameManager manager = new GameManager();
        System.out.println("=============================================");
        boolean result = manager.makeMove(Direction.TOP);
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
                moveToDirection(direction);
                for(int x = values[y].length - 1; x > 0;){
                    if(values[y][x] == values[y][x - 1]){
                        values[y][x - 1] = 0;
                        values[y][x] = values[y][x] * 2;
                        points += values[y][x];
                        x -= 2;
                        hasMergedOccured = true;
                        continue;
                    }
                    x--;
                }
                moveToDirection(direction);
            }
        }else if(direction == Direction.LEFT){
            for(int y = 0; y < values.length; y++){
                moveToDirection(direction);
                for(int x = 0; x < values[y].length - 1;){
                    if(values[y][x] == values[y][x + 1]){
                        values[y][x] = values[y][x] * 2;
                        values[y][x + 1] = 0;
                        points += values[y][x];
                        x += 2;
                        hasMergedOccured = true;
                        continue;
                    }
                    x++;
                }
            }
        }else if(direction == Direction.BOTTOM){
            for(int x = 0; x < values[0].length; x++){
                moveToDirection(direction);
                for(int y = values.length - 1; y > 0;){
                    if(values[y][x] == values[y - 1][x]){
                        values[y - 1][x] = 0;
                        values[y][x] = values[y][x] * 2;
                        points += values[y][x];
                        y -= 2;
                        hasMergedOccured = true;
                        continue;
                    }
                    y--;
                }
                moveToDirection(direction);
            }
        }else if(direction == Direction.TOP){
            for(int x = 0; x < values[0].length; x++){
                moveToDirection(direction);
                for(int y = 0; y < values.length - 1;){
                    if(values[y][x] == values[y + 1][x]){
                        values[y + 1][x] = 0;
                        values[y][x] = values[y][x] * 2;
                        points += values[y][x];
                        y += 2;
                        hasMergedOccured = true;
                        continue;
                    }
                    y++;
                }
                moveToDirection(direction);
            }
        }

        if(!hasMergedOccured && freePoints.size() == 0){
            return false;
        }

        generateValue();

        return true;
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

    private void moveToDirection(Direction direction){
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

            case LEFT:
                for(int y = 0; y < values.length; y++){
                    List<Integer> valuesForStart = new ArrayList<>();
                    for(int x = 0; x < values[y].length; x++){
                        if(values[y][x] != 0){
                            valuesForStart.add(values[y][x]);
                        }

                        values[y][x] = 0;
                        if(!freePoints.contains(new Point(x, y))){
                            freePoints.add(new Point(x, y));
                        }
                    }

                    for(int i = 0; i < valuesForStart.size(); i++){
                        values[y][i] = valuesForStart.get(i);
                        freePoints.remove(new Point(i, y));
                    }
                }
                break;

            case BOTTOM:
                for(int x = 0; x < values[0].length; x++){
                    List<Integer> valuesForEnd = new ArrayList<>();
                    for(int y = 0; y < values.length; y++){
                        if(values[y][x] != 0){
                            valuesForEnd.add(values[y][x]);
                        }

                        values[y][x] = 0;
                        if(!freePoints.contains(new Point(x, y))){
                            freePoints.add(new Point(x, y));
                        }
                    }

                    for(int i = 0; i < valuesForEnd.size(); i++){
                        values[values.length - 1 - i][x] = valuesForEnd.get(valuesForEnd.size() - 1 - i);
                        freePoints.remove(new Point(x, values.length - 1 - i));
                    }
                }
                break;

            case TOP:
                for(int x = 0; x < values[0].length; x++){
                    List<Integer> valuesForEnd = new ArrayList<>();
                    for(int y = 0; y < values.length; y++){
                        if(values[y][x] != 0){
                            valuesForEnd.add(values[y][x]);
                        }

                        values[y][x] = 0;
                        if(!freePoints.contains(new Point(x, y))){
                            freePoints.add(new Point(x, y));
                        }
                    }

                    for(int i = 0; i < valuesForEnd.size(); i++){
                        values[i][x] = valuesForEnd.get(valuesForEnd.size() - 1 - i);
                        freePoints.remove(new Point(x, i));
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

enum Direction {
    TOP,
    RIGHT,
    BOTTOM,
    LEFT
}

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return getX() == point.getX() &&
                getY() == point.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}