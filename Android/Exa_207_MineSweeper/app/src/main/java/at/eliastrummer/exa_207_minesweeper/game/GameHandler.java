package at.eliastrummer.exa_207_minesweeper.game;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameHandler {
    private Node[][] field;
    private int amountBombs;

    private static final Random RANDOM = new Random();

    public GameHandler(int length, int height, int amountBombs){
        if(length <= 0 || height <= 0){
            throw new RuntimeException("Length & height must be > 1");
        }

        this.field = new Node[height][length];
        this.amountBombs = amountBombs;

        initializeField();
    }

    public Node[][] getField(){
        return this.field;
    }

    public Node getNode(int x, int y){
        return this.field[y][x];
    }

    public int getNeighbourCount(int x, int y){
        
        return -1;
    }

    public boolean isFinished(){
        return false;
    }

    private void initializeField(){
        Set<Point> bombs = new HashSet<>();

        while(bombs.size() < amountBombs){
            bombs.add(new Point(RANDOM.nextInt(field[0].length), RANDOM.nextInt(field.length)));
        }

        for(int y = 0; y < field.length; y++){
            for(int x = 0; x < field[y].length; x++){
                Node node = new Node(bombs.contains(new Point(x, y)));
                field[y][x] = node;
            }
        }
    }
}
