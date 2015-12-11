/**
 * Homework #6 Created by Evan Almonte on 12/7/2015.
 */
import java.io.*;
import javax.swing.JOptionPane;
import java.util.StringTokenizer;

public class Homework06 {
    public static void main(String[] args) {
        SudokuBoard startingBoard;
        try {
            startingBoard = new SudokuBoard(getBoardFromFile());
        } catch(IOException ioe) {
            System.out.println("ERROR, file not found.");
            return;
        }
        SudokuBoardView myView = new SudokuBoardView(startingBoard, 500, 500);
        SudokuBoardController sudokuController = new SudokuBoardController(startingBoard, myView);

    }

    public static void test(SudokuBoard gameBoard) {
        printBoard(gameBoard);
        while(!gameBoard.isFull()) {
            try {
                String [] response = JOptionPane.showInputDialog(null, "Please enter row, column, and value").split(",");
                int r = Integer.parseInt(response[0]);
                int c = Integer.parseInt(response[1]);
                int value = Integer.parseInt(response[2]);
                gameBoard.enterMove(r, c, value);
            } catch (NullPointerException npe) {
                int response = JOptionPane.showConfirmDialog(null, "Would you like to quit?", "Choose an Option",
                        JOptionPane.YES_NO_OPTION);
                if(response == JOptionPane.YES_OPTION) { return; }
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

    public static SudokuSquareLinkedList getBoardFromFile() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("board.txt")));
        String line = br.readLine();
        SudokuSquareLinkedList boardFromFile = new SudokuSquareLinkedList();
        while(line != null) {
            StringTokenizer tokenLine = new StringTokenizer(line, ",");
            try {
                int row = Integer.parseInt(tokenLine.nextToken());
                int column = Integer.parseInt(tokenLine.nextToken());
                int value = Integer.parseInt(tokenLine.nextToken());
                boardFromFile.append(new SudokuSquareNode(new SudokuSquare(row,column,value,true)));
            } catch(NumberFormatException nfe) {
                System.out.println("File has invalid input.");
                return null;
            }
            line = br.readLine();
        }


        return boardFromFile;
    }
}
