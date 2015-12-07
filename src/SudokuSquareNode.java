/**
 * Homework #6 Created by Evan Almonte on 12/7/2015.
 */
public class SudokuSquareNode {
    private SudokuSquare data;
    private SudokuSquareNode next;

    public SudokuSquareNode(SudokuSquare data) {
        this.data = data;
    }

    public SudokuSquareNode(SudokuSquare data, SudokuSquareNode next) {
        this.data = data;
        this.next = next;
    }

    public SudokuSquare getData() { return data; }

    public SudokuSquareNode getNext() { return next; }

    public void setNext(SudokuSquareNode next) { this.next = next; }
}
