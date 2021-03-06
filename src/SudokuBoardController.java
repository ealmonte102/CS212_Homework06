/**
 * Created by Evan Almonte on 12/11/2015.
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class SudokuBoardController {
    private SudokuBoard boardModel;
    private SudokuBoardView boardView;

    public SudokuBoardController(SudokuBoard model, SudokuBoardView view) {
        boardModel = model;
        boardView = view;
        addButtonListeners();
        boardView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        view.setVisible(true);
    }

    private void addButtonListeners() {
        for (int i = 0; i < boardModel.boardSize; ++i) {
            for (int j = 0; j < boardModel.boardSize; ++j) {
                boardView.addButtonListener(new ButtonListener(), i, j);
            }
        }
        boardView.addResetBtnListener(new resetButtonListener());
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] buttonLocation = e.getActionCommand().split(",");
            int row = Integer.parseInt(buttonLocation[0]);
            int column = Integer.parseInt(buttonLocation[1]);
            if (boardView.isClearSelected()) {
                boardModel.clearSquare(row, column);
                String updatedText = Integer.toString(boardModel.getSquare(row, column).getValue());
                boardView.setButtonText(updatedText,row, column);
                return;
            }
            int inputValue;
            String inputAsString = boardView.getInputText();
            //Checks if the input entered into the text field is valid
            if (!isValidInt(inputAsString)) {
                return;
            }
            inputValue = Integer.parseInt(inputAsString);
            if (inputValue > boardModel.boardSize || inputValue < 1) {
                boardView.setOutputText("The value entered must be within the range of 0 - " + boardModel.boardSize);
                return;
            }
            if (boardView.isCheckIfValidSelected()) {
                if (boardModel.isValid(row, column, inputValue)) {
                    boardView.setOutputText("The move entered is VALID!");
                } else {
                    boardView.setOutputText("The move entered is NOT valid.");
                }
                return;
            }
            enterMove(row, column, inputValue);
        }

        private boolean isValidInt(String checkInput) {
            try {
                Integer.parseInt(checkInput);
            } catch (NumberFormatException nfe) {
                if(checkInput.isEmpty()) { boardView.setOutputText("No input entered."); }
                else { boardView.setOutputText("Invalid input entered."); }
                return false;
            }
            return true;
        }

        private void enterMove(int row, int column, int value) {
            try {
                boardModel.enterMove(row, column, value);
                boardView.setButtonText(Integer.toString(value), row, column);
                if (boardModel.isFull()) {
                    int userChoice = JOptionPane.showConfirmDialog(null, "Would you like to start over?",
                            "Congratulation, You Won!", JOptionPane.YES_NO_OPTION);
                    switch (userChoice) {
                        case JOptionPane.YES_OPTION:
                            boardModel.reset();
                            boardView.setModel(boardModel);
                            boardView.resetButtons();
                            boardView.resetRadioSelection();
                            break;
                        default:
                    }
                }
            } catch (SudokuException se) {
                boardView.setOutputText("Cannot place a " + value + " in that location.");
            }
        }
    }

    private class resetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showConfirmDialog(null, "Would you like to reset the board?", "Please choose an option",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(option == JOptionPane.YES_OPTION) {
                boardModel.reset();
                boardView.resetButtons();
                boardView.setOutputText("");
            }
        }
    }
}
