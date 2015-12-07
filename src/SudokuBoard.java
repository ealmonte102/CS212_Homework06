/**
 * Homework #6 Created by Evan Almonte on 12/7/2015.
 */
public class SudokuBoard {
    private SudokuSquare myBoard[][];

    SudokuBoard(SudokuSquareLinkedList aList) {
        myBoard = new SudokuSquare[4][4];
        SudokuSquareNode aNode = aList.getNext();
        while(aNode != null) {
            SudokuSquare aSquare = aNode.getData();
            int r, c;
            r = aSquare.getRow();
            c = aSquare.getColumn();
            int value = aSquare.getValue();
            myBoard[r][c] = new SudokuSquare(r, c, value, true);
            aNode = aList.getNext();
        }
    }
}
