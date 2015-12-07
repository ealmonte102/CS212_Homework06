/**
 * Homework #6 Created by Evan Almonte on 12/7/2015.
 */
import javax.swing.JOptionPane;
import java.io.IOException;

public class Homework06 {
    public static void main(String[] args) {
        SudokuSquareLinkedList startingBoard =
                new SudokuSquareLinkedList(new SudokuSquareNode(new SudokuSquare(0,1,1,true)));
        startingBoard.append(new SudokuSquareNode(new SudokuSquare(0,2,3,true)));
        startingBoard.append(new SudokuSquareNode(new SudokuSquare(1,0,2,true)));
        startingBoard.append(new SudokuSquareNode(new SudokuSquare(2,3,3,true)));
        startingBoard.append(new SudokuSquareNode(new SudokuSquare(3,1,2,true)));
        startingBoard.append(new SudokuSquareNode(new SudokuSquare(3,2,1,true)));
        test(new SudokuBoard(startingBoard));
    }

    public static void test(SudokuBoard gameBoard) {
        printBoard(gameBoard);
        while(!gameBoard.isFull()) {
            String [] response = JOptionPane.showInputDialog(null, "Please enter row, column, and value").split(",");
            int r = Integer.parseInt(response[0]);
            int c = Integer.parseInt(response[1]);
            int value = Integer.parseInt(response[2]);
            try {
                gameBoard.enterMove(r, c, value);
            } catch(SudokuException se) {
                System.out.println(se.getMessage());
            }
            printBoard(gameBoard);
        }
    }

    public static void printBoard(SudokuBoard gameBoard) {
        System.out.println();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print(gameBoard.getSquare(i,j).getValue() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
