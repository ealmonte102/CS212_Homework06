/**
 * Homework #6 Created by Evan Almonte on 12/6/2015.
 */
public class SudokuSquare {
    private int row;
    private int column;
    private int value;
    private boolean locked;

    public SudokuSquare() {}

    public SudokuSquare(int row, int column, int value, boolean locked) {
        if(row < 0 || column < 0) { throw new IllegalArgumentException("Row/column value cannot be negative"); }
        this.row = row;
        this.column = column;
        this.value = value;
        this.locked = locked;
    }

    public int getRow() { return row; }

    public int getColumn() { return column; }

    public int getValue() { return value; }

    public boolean isLocked() { return locked; }
}
