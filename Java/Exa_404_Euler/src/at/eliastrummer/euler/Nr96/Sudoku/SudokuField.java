package at.eliastrummer.euler.Nr96.Sudoku;

public class SudokuField {
    private int[][] field = new int[9][9];
    private final String name;

    public SudokuField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int[][] getField() {
        return field;
    }
}
