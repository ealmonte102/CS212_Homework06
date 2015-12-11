/**
 * Created by Evan Almonte on 12/11/2015.
 */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class SudokuBoardController {
    private SudokuBoard boardModel;
    private SudokuBoardView boardView;
    public SudokuBoardController(SudokuBoard model, SudokuBoardView view) {
        boardModel = model;
        boardView = view;
        view.addClearRadioListener(new ClearRadioListener());
        addButtonListeners();
    }

    private void addButtonListeners() {
        //Intenionally left empty.
    }

    private class ClearRadioListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int userChoice = JOptionPane.showConfirmDialog(null, "Would you like to reset the board?",
                    "Please Choose An Option", JOptionPane.YES_NO_OPTION);
            if(userChoice == JOptionPane.YES_OPTION) {
                boardModel.reset();
                for(int i = 0; i < boardModel.boardSize; ++i) {
                    for(int j = 0; j < boardModel.boardSize; ++j) {
                        String boardValue = Integer.toString(boardModel.getSquare(i,j).getValue());
                        if(boardValue == "0") { boardValue = ""; }
                        boardView.setButtonText(boardValue, i, j);
                    }
                }
            }
            boardView.resetRadioSelection();
        }
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int valueFromInput;
            try {
                valueFromInput = Integer.parseInt(boardView.getInputText());
            } catch(NumberFormatException nfe) {
                boardView.setOutputText("Invalid input entered.");
                return;
            }
            String [] buttonLocation = e.getActionCommand().split(",");
            int row = Integer.parseInt(buttonLocation[0]);
            int column = Integer.parseInt(buttonLocation[1]);
            if(boardView.isCheckIfValidSelected()) {
                if(boardModel.isValid(row, column, valueFromInput)) {
                    boardView.setOutputText("The move is valid.");
                } else {
                    boardView.setOutputText("The move entered is not a valid move.");
                }
                return;
            }
            try {
                boardModel.enterMove(row, column, valueFromInput);
                boardView.setButtonText(Integer.toString(valueFromInput), row, column);
            } catch(SudokuException se) {
                boardView.setOutputText("Cannot place a " + valueFromInput + " in that location.");
            }
        }
    }
}
