package at.eliastrummer.exa_207_minesweeper.game;

public class Node {
    private boolean isBomb;
    private boolean isHit;
    private boolean isMarked;

    public Node(boolean isBomb, boolean isHit, boolean isMarked) {
        this.isBomb = isBomb;
        this.isHit = isHit;
        this.isMarked = isMarked;
    }

    public Node(boolean isBomb) {
        this.isBomb = isBomb;
        this.isHit = false;
        this.isMarked = false;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }
}
