/**
 * Homework #6 Created by Evan Almonte on 12/7/2015.
 */
public class SudokuBoard {
    private SudokuSquare myBoard[][];
    final int sizeOfBoard;

    SudokuBoard(SudokuSquareLinkedList aList) {
        sizeOfBoard = 4;
        myBoard = new SudokuSquare[sizeOfBoard][sizeOfBoard];
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

    public void reset() {
        for(int i = 0; i < sizeOfBoard; ++i) {
            for(int j = 0; j < sizeOfBoard; ++j) {
                if(! myBoard[i][j].isLocked()) {
                    myBoard[i][j] = new SudokuSquare(i, j, 0, false);
                }
            }
        }
    }
}
