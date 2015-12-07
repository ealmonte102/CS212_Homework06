/**
 * Homework #6 Created by Evan Almonte on 12/7/2015.
 */
public class SudokuSquareLinkedList {
    private SudokuSquareNode head;
    SudokuSquareNode squareToReturn;

    public SudokuSquareLinkedList(SudokuSquareNode head) {
        this.head = head;
        squareToReturn = head;
    }

    public void append(SudokuSquareNode end) {
        SudokuSquareNode current = head;
        while(current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(end);
    }

    public SudokuSquareNode getNext() {
        SudokuSquareNode aSudokuNode = squareToReturn;
        if(squareToReturn != null) {
            squareToReturn = squareToReturn.getNext();
        }
        return aSudokuNode;
    }
}
