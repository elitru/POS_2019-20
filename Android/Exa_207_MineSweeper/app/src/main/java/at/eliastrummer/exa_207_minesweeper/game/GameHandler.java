package at.eliastrummer.exa_207_minesweeper.game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameHandler {

    public static void main(String[] args) {
        GameHandler gh = new GameHandler(9, 9, 40);
        gh.print();
        System.out.println(gh.getNeighbourCount(3, 3));
        gh.makeHit(2,2);
    }

    private Node[][] field;
    private int amountBombs;
    private Point[] noBombPoints;

    private static final Random RANDOM = new Random();

    public GameHandler(int length, int height, int amountBombs, Point... noBomb) {
        if (length <= 0 || height <= 0) {
            throw new RuntimeException("Length & height must be > 1");
        }

        this.field = new Node[height][length];
        this.amountBombs = amountBombs;
        this.noBombPoints = noBomb;

        initializeField();

        if(noBomb != null){
            for (Point point : noBomb) {
                makeHit(point.getX(), point.getY());
            }
        }
    }

    public void print() {
        for (Node[] nodes : field) {
            for (Node node : nodes) {
                System.out.print(node.isHit() ? "H " : "N ");
            }

            System.out.println();
        }
    }

    public Node[][] getField() {
        return this.field;
    }

    public Node getNode(int x, int y) {
        return this.field[y][x];
    }

    public int getNeighbourCount(int x, int y) {
        int count = 0;

        //top row
        for (int i = x - 1; i <= x + 1; i++) {
            if (y - 1 >= 0 && i >= 0 && i < field[y - 1].length) {
                if (field[y - 1][i].isBomb()) {
                    count++;
                }
            }
        }

        //left
        if (x - 1 >= 0 && field[y][x - 1].isBomb()) {
            count++;
        }

        //right
        if (x + 1 < field[0].length && field[y][x + 1].isBomb()) {
            count++;
        }

        //bottom row
        for (int i = x - 1; i <= x + 1; i++) {
            if (y + 1 < field.length && i >= 0 && i < field[y + 1].length) {
                if (field[y + 1][i].isBomb()) {
                    count++;
                }
            }
        }

        return count;
    }

    public boolean isFinished() {
        int rightBombMarkedCount = 0;

        for (Node[] lines : field) {
            for (Node node : lines) {
                if(node.isBomb() && node.isMarked()){
                    rightBombMarkedCount++;
                }
            }
        }

        return rightBombMarkedCount == amountBombs;
    }

    public boolean changeNodeMarkedState(int x, int y){
        field[y][x].setMarked(!field[y][x].isMarked());
        return isFinished();
    }

    public boolean makeHit(int xCoordinate, int yCoordinate){
        field[yCoordinate][xCoordinate].setHit(true);

        if(field[yCoordinate][xCoordinate].isBomb()) {
            return true;
        }

        for(int y = yCoordinate - 1; y <= yCoordinate + 1; y++){
            for(int x = xCoordinate - 1; x <= xCoordinate + 1; x++){
                try{
                    if(!field[y][x].isBomb() && !field[y][x].isHit() && getNeighbourCount(x, y) == 0){
                        makeHit(x, y);
                    }else{
                        if(!field[y][x].isBomb()){
                            field[y][x].setHit(true);
                        }
                    }
                }catch (IndexOutOfBoundsException e){ }
            }
        }

        return false;
    }

    private void initializeField() {
        Set<Point> bombs = new HashSet<>();
        List<Point> noBombs = null;

        if(noBombPoints != null){
            noBombs = Arrays.asList(noBombPoints);
        }

        while (bombs.size() < amountBombs) {
            Point point = new Point(RANDOM.nextInt(field[0].length), RANDOM.nextInt(field.length));
            if(noBombs != null && noBombs.contains(point)){
                continue;
            }

            bombs.add(point);
        }

        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                Node node = new Node(bombs.contains(new Point(x, y)));
                field[y][x] = node;
            }
        }
    }
}
